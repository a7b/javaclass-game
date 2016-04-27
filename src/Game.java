import java.awt.BorderLayout;
import java.awt.Color;
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
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayDeque;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Game extends JPanel implements KeyListener {

	private static final long serialVersionUID = 469989049178129651L;

	protected static final int LASER_COOLDOWN = 200;
	protected static final int WORD_DISPLAY_HEIGHT = 40;

	public ArrayDeque<WorldObject> objects;

	private Context ctx;

	private JLabel wordDisplay;

	private WorldObject cannon;

	private boolean cmdLeft;
	private boolean cmdRight;
	private boolean cmdShoot;

	private long timeLastShot;

	private Direction direction;
	
	private String word;
	private Rectangle2D wordBounds;
	private BufferedReader reader;
	
	private ImageIcon i = new ImageIcon("src/background.jpg");
	private Image background = i.getImage();
	private double[] wordLoc;

	Game() throws IOException {
		cmdLeft = false;
		cmdRight = false;
		timeLastShot = 0;
		direction = Direction.NEUTRAL;
		wordDisplay = new JLabel();

		objects = new ArrayDeque<>();
		cannon = new WorldObject();

		setLayout(new BorderLayout());

		objects.add(cannon);

		//cannon.addShape(new Rectangle2D.Double(30, 40, 100, 20));
		cannon.addShape(new Rectangle2D.Double(30, 40, 100, 20)).color(
				new Color(0x60, 0x7D, 0x8B));

		Double[] center = cannon.addShape(new Ellipse2D.Double(0, 0, 100, 100)).color(new Color(0x9E, 0x9E, 0x9E))
				.center();

		cannon.setCenter(center);

		cannon.getTransform().translate(590, 660 - WORD_DISPLAY_HEIGHT);
		cannon.setRotation(-Math.PI / 2);

		wordLoc = new double[] { (int) (Math.random() * 1200) + 1, 0 };

		reader = new BufferedReader(new FileReader("/usr/share/dict/words"));
		
		newWord();

		JPanel wordPanel = new JPanel();
		wordPanel.setLayout(new BoxLayout(wordPanel, BoxLayout.X_AXIS));
		wordPanel.setPreferredSize(new Dimension(1280, WORD_DISPLAY_HEIGHT));
		add(wordPanel, BorderLayout.SOUTH);
		wordPanel.add(wordDisplay);
		wordPanel.setBackground(Color.WHITE);
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
		g.setFont(new Font("TimesRoman", Font.PLAIN, 69));
		// get the width
		FontMetrics metrics = g.getFontMetrics();
		wordBounds = new Rectangle2D.Double(wordLoc[0], wordLoc[1],
				metrics.stringWidth(word),
				metrics.getMaxAscent() - metrics.getMaxDescent());
		g.drawString(word, (int) wordLoc[0], (int) wordLoc[1]);
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
				((LaserBeam) o).update();
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
				&& System.currentTimeMillis() - timeLastShot > LASER_COOLDOWN) {
			Double[] center = cannon.getCenter();
			// Don't mutate center
			Double[] position = {
					center[0] + cannon.getTransform().getTranslateX(),
					center[1] + cannon.getTransform().getTranslateY() };

			objects.addFirst(new LaserBeam(cannon.getRotation(), position));
			timeLastShot = System.currentTimeMillis();
		}
		// move the word

		wordLoc[1] += 100 / Context.TICK;
		if (wordLoc[1] > 720 + 69) {
			newWord();
			wordLoc[0] = (int) (Math.random() * 1200) + 1;
			wordLoc[1] = 0;
		}

		repaint();
	}

	public void newWord() throws IOException {
		word = reader.readLine();
		int n = (int) (Math.random() * 100)+1;
		
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
		System.out.println(e.getKeyChar());
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
		case KeyEvent.VK_SPACE:
			cmdShoot = true;
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
		case KeyEvent.VK_SPACE:
			cmdShoot = false;
			break;
		}
	}

	private enum Direction {
		RIGHT, NEUTRAL, LEFT
	}
}