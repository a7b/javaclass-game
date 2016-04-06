import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.Timer;


public class Window {

	private Context ctx;
	protected Timer timer;
	private JMenuBar menu;

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

		WelcomeScreen ws = new WelcomeScreen();

		f.getContentPane().add(ws);
		f.getContentPane().add(ctx.getGame());
		l.addLayoutComponent(ctx.getGame(), "game");
		l.addLayoutComponent(ws, "welcome-screen");
		l.show(f.getContentPane(), "welcome-screen");
		// l.show(f.getContentPane(), "game");

		menu = new JMenuBar();
		JMenu menuGame = new JMenu("Game");
		JMenuItem menuExit = new JMenuItem("Exit");
		menuExit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		menuGame.add(menuExit);
		menu.add(menuGame);

		timer.start();
		f.setVisible(true);
	}

	public Context getContext() {
		return ctx;
	}

	public void setContext(Context ctx) {
		this.ctx = ctx;
	}

}
