import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.Timer;


public class Window {

	private Context ctx;

	protected Timer timer;

	public static void main(String[] args) {
		new Window();
	}

	public Window() {
		JFrame f = new JFrame("Word game");
		f.setSize(1280, 720);
		f.setLocationRelativeTo(null);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		CardLayout l = new CardLayout();

		f.getContentPane().setLayout(l);

		this.ctx = new Context.Factory().setWindow(this).create();
		this.timer = new Timer(1000 / Context.TICK, ctx.getTicker());

		f.getContentPane().add(ctx.getGame());
		l.addLayoutComponent(ctx.getGame(), "game");
		l.show(f.getContentPane(), "game");

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				timer.start();
				f.setVisible(true);
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
