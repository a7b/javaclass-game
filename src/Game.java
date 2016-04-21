import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import javax.swing.JPanel;

public class Game extends JPanel implements KeyListener {

	private static final long serialVersionUID = 469989049178129651L;

	public ArrayList<WorldObject> objects;

	private static final double SPEED = 100; // radians per second

	private Context ctx;

	private WorldObject cannon;

	private boolean cmdLeft;
	private boolean cmdRight;

	private Direction direction;

	Game() {
		cmdLeft = false;
		cmdRight = false;
		direction = Direction.NEUTRAL;

		objects = new ArrayList<>();
		cannon = new WorldObject();

		objects.add(cannon);

		//cannon.addShape(new Rectangle2D.Double(30, 40, 100, 20));
		cannon.addShape(new Rectangle2D.Double(40, -30, 20, 100)).color(
				new Color(0x60, 0x7D, 0x8B));

		cannon.addShape(new Ellipse2D.Double(0, 0, 100, 100)).color(
				new Color(0x9E, 0x9E, 0x9E));

		cannon.getTransform().translate(640-50, 720-50-10);

	}

	@Override
	public void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);
		Graphics2D g = (Graphics2D) graphics;

		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		g.drawString("Time: " + System.currentTimeMillis(), 100, 100);

		objects.forEach((o) -> {
			o.render(g);
		});
	}

	public void tick() {
//		cannon.getTransform().translate(50.0 / Context.TICK, 0);

		Double[] center = cannon.getCenter(cannon.lastShape());
		
		if (direction == Direction.LEFT) {
			cannon.getTransform().rotate(-Math.PI/ Context.TICK, center[0], center[1]);
		} else if (direction == Direction.RIGHT) {
			cannon.getTransform().rotate(Math.PI/ Context.TICK, center[0], center[1]);
		}

		if (this.isVisible()) {
			repaint();
		}
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
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (!this.isVisible()) {
			return;
		}
		switch (e.getKeyCode()) {
		case KeyEvent.VK_A:
		case KeyEvent.VK_LEFT:
			cmdLeft = true;
			direction = Direction.LEFT;
			break;
		case KeyEvent.VK_D:
		case KeyEvent.VK_RIGHT:
			cmdRight = true;
			direction = Direction.RIGHT;
			break;
		case KeyEvent.VK_SPACE:
			new LaserBeam(5);
			repaint();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (!this.isVisible()) {
			return;
		}
		switch (e.getKeyCode()) {
		case KeyEvent.VK_A:
		case KeyEvent.VK_LEFT:
			cmdLeft = false;
			direction = cmdRight ? Direction.RIGHT : Direction.NEUTRAL;
			break;
		case KeyEvent.VK_D:
		case KeyEvent.VK_RIGHT:
			cmdRight = false;
			direction = cmdLeft ? Direction.LEFT : Direction.NEUTRAL;
			break;
		case KeyEvent.VK_SPACE:
			new LaserBeam(5);
		}
	}

	private enum Direction {
		RIGHT, NEUTRAL, LEFT
	}
}