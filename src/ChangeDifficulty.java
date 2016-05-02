
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ChangeDifficulty extends JPanel implements ActionListener {

	private static final long serialVersionUID = -8443449217857603332L;

	private static final int ITEM_WIDTH = 300;
	private static final Font TITLE_FONT = new Font(Font.SERIF, Font.BOLD, 72);
	private static final Font BUTTON_FONT = new Font(Font.SANS_SERIF, Font.PLAIN, 24);

	private Context ctx;

	private JLabel lMsg;
	private JButton b1;
	private JButton b2;
	private JButton b3;
	private JButton bback;

	public ChangeDifficulty() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		lMsg = new JLabel("Change Difficulty");
		b1 = new JButton("Easy");
		b2 = new JButton("Normal");
		b3 = new JButton("Expert");
		bback = new JButton("Back");

		lMsg.setFont(TITLE_FONT);
		lMsg.setAlignmentX(Component.CENTER_ALIGNMENT);
		b1.setFont(BUTTON_FONT);
		b1.setAlignmentX(Component.CENTER_ALIGNMENT);
		b1.setMaximumSize(new Dimension(ITEM_WIDTH, (int) b1.getMaximumSize().getHeight()));
		b1.addActionListener(this);
		b2.setFont(BUTTON_FONT);
		b2.setAlignmentX(Component.CENTER_ALIGNMENT);
		b2.setMaximumSize(new Dimension(ITEM_WIDTH, (int) b2.getMaximumSize().getHeight()));
		b2.addActionListener(this);
		b3.setFont(BUTTON_FONT);
		b3.setAlignmentX(Component.CENTER_ALIGNMENT);
		b3.setMaximumSize(new Dimension(ITEM_WIDTH, (int) b3.getMaximumSize().getHeight()));
		b3.addActionListener(this);
		bback.setFont(BUTTON_FONT);
		bback.setAlignmentX(Component.CENTER_ALIGNMENT);
		bback.setMaximumSize(new Dimension(ITEM_WIDTH, (int) bback.getMaximumSize().getHeight()));
		bback.addActionListener(this);

		add(Box.createVerticalStrut(100));
		add(lMsg);
		add(Box.createVerticalStrut(20));
		add(b1);
		add(Box.createVerticalStrut(20));
		add(b2);
		add(Box.createVerticalStrut(20));
		add(b3);
		add(Box.createVerticalStrut(20));
		add(bback);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == b1) {
			ctx.getGame().setDifficulty(Difficulty.EASY);
		} else if (e.getSource() == b2) {
			ctx.getGame().setDifficulty(Difficulty.NORMAL);
		} else if (e.getSource() == b3) {
			ctx.getGame().setDifficulty(Difficulty.EXPERT);
		}
		// go back
		ctx.getWindow().show("main-menu");
	}

	public Context getContext() {
		return ctx;
	}

	public void setContext(Context ctx) {
		this.ctx = ctx;
	}

}
