import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.Timer;

import javax.swing.JPanel;

public class Game extends JPanel implements KeyListener, ActionListener {

	private static final long serialVersionUID = 469989049178129651L;

	private Context ctx;

	private AffineTransform cannonTransform;
	private WorldObject cannonBody;
	private WorldObject cannonBarrel;
	private Timer tm;

	Game() {
		cannonTransform = new AffineTransform();
		cannonBody = new WorldObject(new Ellipse2D.Double(0, 0, 100, 100),
				cannonTransform);
		cannonBarrel = new WorldObject(new Rectangle2D.Double(30, 40, 100, 20),
				cannonTransform);

		cannonTransform.translate(100, 100);
	}

	@Override
	public void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);
		Graphics2D g = (Graphics2D) graphics;

		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		g.drawString("Time: " + System.currentTimeMillis(), 100, 100);

		g.setColor(new Color(0x9E, 0x9E, 0x9E));
		g.fill(cannonBarrel.render());
		g.setColor(new Color(0x60, 0x7D, 0x8B));
		g.fill(cannonBody.render());
	}

	public void tick() {
		// cannonTransform.translate(10.0 / Context.TICK, 0);
		// cannonTransform.rotate(Math.PI / Context.TICK, 50, 50);
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
	
	public void keyTyped(KeyEvent e) {
		
	}

	public void keyPressed(KeyEvent e) {
		if (e.getSource() == " "){
			tm = new Timer();
		}
	}

	public void keyReleased(KeyEvent e) {
		
	}
	
	public void actionPerformed(ActionEvent e) {
		
	}
}