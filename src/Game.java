import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Game extends JPanel {

	private static final long serialVersionUID = 469989049178129651L;

	private Context ctx;

	private WorldObject cannonBodyPath;
	private WorldObject cannonBarrelPath;

	Game() {
		cannonBodyPath = new WorldObject(new GeneralPath(new Ellipse2D.Double(
				0, 0, 100, 100)));
		cannonBarrelPath = new WorldObject(new GeneralPath(
				new Rectangle2D.Double(50, 40, 100, 20)));
	}

	@Override
	public void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);
		Graphics2D g = (Graphics2D) graphics;
		g.drawString("Time: " + System.currentTimeMillis(), 100, 100);

		// g.setColor(Color.BLACK);

		g.fill(cannonBodyPath.path());
		g.fill(cannonBarrelPath.path());
	}

	public void tick() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				repaint();
			}
		});
	}

	public Context getContext() {
		return ctx;
	}

	public void setContext(Context ctx) {
		this.ctx = ctx;
	}
}