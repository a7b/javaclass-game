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

public class MainMenu extends JPanel implements ActionListener {

	private static final long serialVersionUID = -5432368345116470291L;

	private static final int ITEM_WIDTH = 300;
	private static final Font TITLE_FONT = new Font(Font.SERIF, Font.BOLD, 72);
	private static final Font BUTTON_FONT = new Font(Font.SANS_SERIF, Font.PLAIN, 24);

	private Context ctx;

	private JButton play;
	private JButton changeDiff;
	private JButton instructions;
	
	public MainMenu() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JLabel title = new JLabel("MRW dictionary invaders");
		this.play = new JButton("Play");
		this.changeDiff = new JButton("Change Difficulty");
		this.instructions = new JButton("How to Play");

		title.setFont(TITLE_FONT);
		title.setAlignmentX(Component.CENTER_ALIGNMENT);
		play.setFont(BUTTON_FONT);
		play.setAlignmentX(Component.CENTER_ALIGNMENT);
		play.setMaximumSize(new Dimension(ITEM_WIDTH, (int) play.getPreferredSize().getHeight()));
		play.addActionListener(this);
		changeDiff.setFont(BUTTON_FONT);
		changeDiff.setAlignmentX(Component.CENTER_ALIGNMENT);
		changeDiff.setMaximumSize(new Dimension(ITEM_WIDTH, (int) changeDiff.getPreferredSize().getHeight()));
		changeDiff.addActionListener(this);
		instructions.setFont(BUTTON_FONT);
		instructions.setAlignmentX(Component.CENTER_ALIGNMENT);
		instructions.setMaximumSize(new Dimension(ITEM_WIDTH, (int) instructions.getPreferredSize().getHeight()));
		instructions.addActionListener(this);

		add(Box.createVerticalStrut(100));
		add(title);
		add(Box.createVerticalStrut(20));
		add(play);
		add(Box.createVerticalStrut(20));
		add(changeDiff);
		add(Box.createVerticalStrut(20));
		add(instructions);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == play){
			ctx.getWindow().show("game");
		} else if (e.getSource() == changeDiff) {
			ctx.getWindow().show("change-difficulty");
		} else if (e.getSource() == instructions) {
			ctx.getWindow().show("instructions");
		}
	}

	public Context getContext() {
		return ctx;
	}

	public void setContext(Context ctx) {
		this.ctx = ctx;
	}
	
}
