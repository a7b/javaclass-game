import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class GameOver extends JPanel implements ActionListener {

	private static final long serialVersionUID = -8443449217857603332L;

	private static final int ITEM_WIDTH = 300;
	private static final Font TITLE_FONT = new Font(Font.SERIF, Font.PLAIN, 72);
	private static final Font BUTTON_FONT = new Font(Font.SANS_SERIF, Font.PLAIN, 24);

	private Context ctx;

	private int score;
	private long time;

	private JLabel lMsg;
	private JButton bRestart;
	private JButton bMenu;
	private JButton bQuit;

	public GameOver() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		lMsg = new JLabel();
		bRestart = new JButton("Restart");
		bMenu = new JButton("Go to the Main Menu");
		bQuit = new JButton("Quit");
		
		// generate text for lMsg
		score = 0;
		time = 0;
		updateMsg();

		lMsg.setFont(TITLE_FONT);
		lMsg.setAlignmentX(Component.CENTER_ALIGNMENT);
		lMsg.setHorizontalAlignment(SwingConstants.CENTER);
		bRestart.setFont(BUTTON_FONT);
		bRestart.setAlignmentX(Component.CENTER_ALIGNMENT);
		bRestart.setMaximumSize(new Dimension(ITEM_WIDTH, (int) bRestart.getMaximumSize().getHeight()));
		bRestart.addActionListener(this);
		bMenu.setFont(BUTTON_FONT);
		bMenu.setAlignmentX(Component.CENTER_ALIGNMENT);
		bMenu.setMaximumSize(new Dimension(ITEM_WIDTH, (int) bMenu.getMaximumSize().getHeight()));
		bMenu.addActionListener(this);
		bQuit.setFont(BUTTON_FONT);
		bQuit.setAlignmentX(Component.CENTER_ALIGNMENT);
		bQuit.setMaximumSize(new Dimension(ITEM_WIDTH, (int) bQuit.getMaximumSize().getHeight()));
		bQuit.addActionListener(this);

		add(Box.createVerticalStrut(100));
		add(lMsg);
		add(Box.createVerticalStrut(20));
		add(bRestart);
		add(Box.createVerticalStrut(20));
		add(bMenu);
		add(Box.createVerticalStrut(20));
		add(bQuit);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == bRestart) {
			Game newGame = null;
			try {
				newGame = new Game();
			} catch (IOException ex) {
				ex.printStackTrace();
				System.exit(1);
			}
			newGame.setContext(ctx);
			ctx.setGame(newGame);
			ctx.getWindow().show("game");
		} else if (e.getSource() == bMenu) {
			ctx.getWindow().show("main-menu");
		} else if (e.getSource() == bQuit) {
			System.exit(0);
		}
	}

	public void updateMsg() {
		StringBuilder msg = new StringBuilder();
		msg.append("<html><div style=\"text-align: center;\"><strong>Game Over!</strong><br>Score: ");
		msg.append(score);
		msg.append("<br>Time: ");
		msg.append(time / 1000.0);
		msg.append("</p></html>");
		lMsg.setText(msg.toString());
	}

	public Context getContext() {
		return ctx;
	}

	public void setContext(Context ctx) {
		this.ctx = ctx;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
		updateMsg();
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
		updateMsg();
	}

}
