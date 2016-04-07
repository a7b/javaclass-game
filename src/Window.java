import java.awt.CardLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.Timer;


public class Window {

	protected Context ctx;
	protected Timer timer;
	protected JFrame frame;
	protected JMenuBar menu;
	protected Container contentPane;
	protected CardLayout layout;

	public static void main(String[] args) {
		new Window();
	}

	public Window() {
		frame = new JFrame("Word game");
		frame.setSize(1280, 720);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		layout = new CardLayout();

		contentPane = frame.getContentPane();
		contentPane.setLayout(layout);

		this.ctx = new Context.Factory().setWindow(this).create();

		this.timer = new Timer(1000 / Context.TICK, ctx.getTicker());

		contentPane.add(ctx.getMainMenu());
		layout.addLayoutComponent(ctx.getMainMenu(), "main-menu");

		contentPane.add(ctx.getGame());
		layout.addLayoutComponent(ctx.getGame(), "game");

		contentPane.add(ctx.getInstructions());
		layout.addLayoutComponent(ctx.getInstructions(), "instructions");

		layout.show(contentPane, "main-menu");
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
		frame.setVisible(true);
	}

	public void show(String name) {
		layout.show(contentPane, name);
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	public Context getContext() {
		return ctx;
	}

	public void setContext(Context ctx) {
		this.ctx = ctx;
	}

}
