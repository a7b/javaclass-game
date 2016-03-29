import java.awt.Graphics;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Game extends JPanel {

	private static final long serialVersionUID = 469989049178129651L;

	private Context ctx;

	Game() {

	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawString("Time: " + System.currentTimeMillis(), 100, 100);
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