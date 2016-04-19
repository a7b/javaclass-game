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

	private static final double SPEED = 50;

	private Context ctx;

	private WorldObject cannon;

	private boolean isMovingLeft;
	private boolean isMovingRight;

	Game() {
		addKeyListener(this);
		isMovingLeft = false;
		isMovingRight = false;

		objects = new ArrayList<>();
		cannon = new WorldObject();

		objects.add(cannon);

		cannon.addShape(new Rectangle2D.Double(30, 40, 100, 20));
		cannon.setColor(cannon.lastShape(), new Color(0x60, 0x7D, 0x8B));

		cannon.addShape(new Ellipse2D.Double(0, 0, 100, 100));
		cannon.setColor(cannon.lastShape(), new Color(0x9E, 0x9E, 0x9E));

		cannon.getTransform().translate(100, 100);

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
		cannon.getTransform().translate(50.0 / Context.TICK, 0);

		if (isMovingLeft == true) {
			System.out.println("Left Triggered");
			// cannon.getTransform().rotate(Math.PI / 2 / Context.TICK);
		} else if (isMovingRight == true) {
			System.out.println("Right Triggered");
			// cannon.getTransform().rotate(Math.PI / 2 / Context.TICK);
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
		System.out.println("hi");
	}

	@Override
	public void keyPressed(KeyEvent e) {
		System.out.println("keyPressed");
		switch (e.getKeyChar()) {
		case KeyEvent.VK_A:
			isMovingRight = false;
			isMovingLeft = true;
			break;
			
		case KeyEvent.VK_D:
			isMovingLeft = false;
			isMovingRight = true;
			break;
			
		case KeyEvent.VK_LEFT:
			isMovingRight = false;
			isMovingLeft = true;
			break;
			
		case KeyEvent.VK_RIGHT:
			isMovingLeft = false;
			isMovingRight = true;
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		System.out.println("keyReleased");
		switch (e.getKeyChar()){
		case KeyEvent.VK_A:
			isMovingLeft = false;
			break;
			
		case KeyEvent.VK_D:
			isMovingRight = false;
			break;
			
		case KeyEvent.VK_LEFT:
			isMovingLeft = false;
			break;
		
		case KeyEvent.VK_RIGHT:
			isMovingRight = false;
			break;
		}
		
	}
}