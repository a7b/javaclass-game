import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;


public class Window extends JPanel {

	private static final long serialVersionUID = -8078394741457691220L;

	public static final int TICK = 250;

	public Context ctx;

	protected Timer timer;

	public static void main(String[] args) {
		Window w = new Window();
		JFrame f = new JFrame("Word game");
		f.setSize(1280, 720);
		f.setLocationRelativeTo(null);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.add(new Window());
		f.setVisible(true);
		w.start();
	}

	public Window() {
		this.ctx = new Context.Factory().create();
		timer = new Timer(1000 / TICK, ctx.getTicker());
	}

	public void start() {
		timer.start();
	}

}
