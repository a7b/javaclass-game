import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Random;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Game extends JPanel implements KeyListener {

	private static final long serialVersionUID = 469989049178129651L;

	protected double wordSpeed;
	protected double cannonFireRate;
	protected double msToSpeedUp;

	public ArrayDeque<WorldObject> objects;

	private Context ctx;

	private Random rng;

	private JLabel wordDisplay;
	protected int wordDisplayHeight;

	private WorldObject cannon;

	private boolean cmdLeft;
	private boolean cmdRight;
	private boolean cmdShoot;

	private long timeLastShot;

	private Direction direction;
	
	private String word;
	private Rectangle2D wordBounds;
	private File dictionary;
	private Font wordFont;
	private FontMetrics wordMetrics;
	
	private String guess;
	private Font guessFont;

	private ImageIcon i = new ImageIcon("src/background.jpg");
	private Image background = i.getImage();
	private double[] wordLoc;
	
	private int points;
	private int livesLeft;

	Game() throws IOException {
		rng = new Random();
		wordFont = new Font("TimesRoman", Font.PLAIN, 69);
		guessFont = new Font(Font.MONOSPACED, Font.PLAIN, 48);
		// hack for getting FontMetrics
		wordMetrics = this.getFontMetrics(wordFont);

		FontMetrics guessMetrics = this.getFontMetrics(guessFont);

		// make the panel bigger than the word
		wordDisplayHeight = guessMetrics.getMaxAscent()
				- guessMetrics.getMaxDescent() + 20;

		guess = "";

		cmdLeft = false;
		cmdRight = false;
		timeLastShot = 0;
		direction = Direction.NEUTRAL;
		wordDisplay = new JLabel();

		objects = new ArrayDeque<>();
		cannon = new WorldObject();

		setLayout(new BorderLayout());

		setDifficulty(Difficulty.NORMAL);

		objects.add(cannon);

		//cannon.addShape(new Rectangle2D.Double(30, 40, 100, 20));
		cannon.addShape(new Rectangle2D.Double(30, 40, 100, 20)).color(
				new Color(0x60, 0x7D, 0x8B));

		Double[] center = cannon.addShape(new Ellipse2D.Double(0, 0, 100, 100)).color(new Color(0x9E, 0x9E, 0x9E))
				.center();

		cannon.setCenter(center);

		cannon.getTransform().translate(590, 660 - wordDisplayHeight);
		cannon.setRotation(-Math.PI / 2);

		wordLoc = new double[] { (int) (Math.random() * 1200) + 1, 0 };

		dictionary = new File("src/words");
		
		newWord();

		JPanel wordPanel = new JPanel();
		wordPanel.setLayout(new BoxLayout(wordPanel, BoxLayout.Y_AXIS));
		wordPanel.setPreferredSize(new Dimension(1280, wordDisplayHeight));
		wordPanel.add(wordDisplay);
		wordPanel.setBackground(Color.WHITE);

		wordDisplay.setAlignmentX(Component.CENTER_ALIGNMENT);
		wordDisplay.setFont(guessFont);

		add(wordPanel, BorderLayout.SOUTH);

		points = 0;
		livesLeft = 3;
	}

	@Override
	public void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);
		Graphics2D g = (Graphics2D) graphics;
		
		g.drawImage(background, 0, 0, null);

		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		objects.forEach((o) -> {
			o.render(g);
		});
		
		g.setColor(Color.BLACK);
		g.setFont(wordFont);
		// get the width
		wordBounds = new Rectangle2D.Double(wordLoc[0], wordLoc[1],
				wordMetrics.stringWidth(word), wordMetrics.getMaxAscent()
						- wordMetrics.getMaxDescent());
		g.drawString(word, (int) wordLoc[0], (int) wordLoc[1]);
		
		Font f = new Font(Font.MONOSPACED, Font.PLAIN, 30);
		
		g.setFont(f);
		
		g.drawString("Points: " + Integer.toString(points), 10, 45);
		g.drawString("Lives: " + Integer.toString(livesLeft), 1110, 45);
	}

	public void tick() throws IOException {
		if (!this.isVisible()) {
			return;
		}


		// decay laser beam
		objects.removeIf(o -> o instanceof LaserBeam && ((LaserBeam) o).dead());
		// travel laser beam
		objects.forEach(o -> {
			if (o instanceof LaserBeam) {
				LaserBeam laser = (LaserBeam) o;
				laser.update();
				if (guess.trim().equalsIgnoreCase(word.trim())
						&& laser.renderMesh(0).intersects(wordBounds)) {
					try {
						newWord();
					} catch (IOException e) {
						e.printStackTrace();
					}
					points++;
				}
				//wordBounds.intersects(laser.center[0], laser.center[1], 100, 10)
				
			}
		});

		// update cannon
		if (direction == Direction.LEFT && cannon.getRotation() > -Math.PI) {
			cannon.rotate(-Math.PI / Context.TICK);
		} else if (direction == Direction.RIGHT && cannon.getRotation() < 0) {
			cannon.rotate(Math.PI / Context.TICK);
		}

		// Create shot
		if (cmdShoot
 && System.currentTimeMillis() - timeLastShot > cannonFireRate) {
			Double[] center = cannon.getCenter();
			// Don't mutate center
			Double[] position = {
					center[0] + cannon.getTransform().getTranslateX(),
					center[1] + cannon.getTransform().getTranslateY() };

			objects.addFirst(new LaserBeam(cannon.getRotation(), position));
			timeLastShot = System.currentTimeMillis();
		}
		// move the word

		wordLoc[1] += wordSpeed / Context.TICK;
		if (wordLoc[1] > 720 + wordMetrics.getHeight()) {
			newWord();
			livesLeft--;
		}
		
		

		repaint();
	}

	public void newWord() throws IOException {
		setGuess("");
		// resevoir sampling algorithm, the nth line has 1/n chance to replace
		// the last line
		int lineno = 1; // offset by one for the rng
		for (BufferedReader reader = new BufferedReader(new FileReader(
				dictionary)); reader.ready(); lineno++) {
			// scroll a line
			String line = reader.readLine();
			// roll the dice
			if (rng.nextInt(lineno) == 0) {
				word = line;
			}
		}
		// clamp
		wordLoc[0] = (int) (Math.random() * (1280 - wordMetrics
				.stringWidth(word)));
		wordLoc[1] = 0;
	}

	public void setGuess(String guess) {
		this.guess = guess;
		wordDisplay.setText(guess);
	}

	public void setDifficulty(Difficulty diff) {
		this.wordSpeed = diff.getWordSpeed();
		this.cannonFireRate = diff.getCannonFireRate();
		this.msToSpeedUp = diff.getMsToSpeedUp();
	}

	public Context getContext() {
		return ctx;
	}

	public void setContext(Context ctx) {
		this.ctx = ctx;
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		if (!this.isVisible()) {
			return;
		}
		String letter = String.valueOf(e.getKeyChar());
		if (letter.matches("[A-Za-z0-9-]+")) {
			setGuess(guess + letter);
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (!this.isVisible()) {
			return;
		}
		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			cmdLeft = true;
			direction = Direction.LEFT;
			break;
		case KeyEvent.VK_RIGHT:
			cmdRight = true;
			direction = Direction.RIGHT;
			break;
		case KeyEvent.VK_ENTER:
			cmdShoot = true;
			break;
		// retry
		case KeyEvent.VK_BACK_SPACE:
			if (e.getModifiersEx() == KeyEvent.SHIFT_DOWN_MASK) {
				setGuess("");
			} else if (guess.length() > 0) {
				setGuess(guess.substring(0, guess.length() - 1));
			}
			break;
		// retry
		case KeyEvent.VK_TAB:
			setGuess("");
			break;
		case KeyEvent.VK_PERIOD:
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (!this.isVisible()) {
			return;
		}
		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			cmdLeft = false;
			direction = cmdRight ? Direction.RIGHT : Direction.NEUTRAL;
			break;
		case KeyEvent.VK_RIGHT:
			cmdRight = false;
			direction = cmdLeft ? Direction.LEFT : Direction.NEUTRAL;
			break;
		case KeyEvent.VK_ENTER:
			cmdShoot = false;
			break;
		}
	}

	protected static enum Direction {
		RIGHT, NEUTRAL, LEFT
	}
}