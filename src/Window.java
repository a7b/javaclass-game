import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;


public class Window extends JPanel {

	private static final long serialVersionUID = -8078394741457691220L;

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
		f.add(new Window());
		f.setVisible(true);

		this.ctx = new Context.Factory().setWindow(this).create();
		this.timer = new Timer(1000 / Context.TICK, ctx.getTicker());

		this.timer.start();
	}

	public Context getContext() {
		return ctx;
	}

	public void setContext(Context ctx) {
		this.ctx = ctx;
	}

}
