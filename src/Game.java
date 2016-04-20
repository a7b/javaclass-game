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
		
		isMovingLeft = false;
		isMovingRight = false;

		objects = new ArrayList<>();
		cannon = new WorldObject();

		objects.add(cannon);

		//cannon.addShape(new Rectangle2D.Double(30, 40, 100, 20));
		cannon.addShape(new Rectangle2D.Double(40, -30, 20, 100));
		cannon.setColor(cannon.lastShape(), new Color(0x60, 0x7D, 0x8B));

		cannon.addShape(new Ellipse2D.Double(0, 0, 100, 100));
		cannon.setColor(cannon.lastShape(), new Color(0x9E, 0x9E, 0x9E));

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
		
		if (isMovingLeft == true) {
			cannon.getTransform().rotate(-Math.PI / 2 / Context.TICK, center[0], center[1]);
		} else if (isMovingRight == true) {
			cannon.getTransform().rotate(Math.PI / 2 / Context.TICK, center[0], center[1]);
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
			isMovingRight = false;
			isMovingLeft = true;
			break;
			
		case KeyEvent.VK_D:
		case KeyEvent.VK_RIGHT:
			isMovingLeft = false;
			isMovingRight = true;
			break;
		case KeyEvent.VK_SPACE:
			new LaserBeam(5);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (!this.isVisible()) {
			return;
		}
		
		isMovingRight = false;
		isMovingLeft = false;
		/*
		System.out.println("keyReleased");
		switch (e.getKeyChar()){
		case KeyEvent.VK_A:
			isMovingRight = false;
			isMovingLeft = false;
			break;
			
		case KeyEvent.VK_D:
			isMovingLeft = false;
			isMovingRight = false;
			break;
			
		case KeyEvent.VK_LEFT:
			isMovingLeft = false;
			break;
		
		case KeyEvent.VK_RIGHT:
			isMovingRight = false;
			break;
		}*/
		
	}
}