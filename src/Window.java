import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;


public class Window extends JPanel {

	private static final long serialVersionUID = -8078394741457691220L;

	public static final int TICK = 250;

	Ticker ticker;
	Timer timer;

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
		ticker = new Ticker();
		timer = new Timer(1000 / TICK, ticker);
	}

	public void start() {
		timer.start();
	}

}
