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
	private JButton howToPlay;
	
	public MainMenu() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JLabel title = new JLabel("MRW dictionary invaders");
		this.play = new JButton("Play");
		this.howToPlay = new JButton("How to Play");

		title.setFont(TITLE_FONT);
		title.setAlignmentX(Component.CENTER_ALIGNMENT);
		play.setFont(BUTTON_FONT);
		play.setAlignmentX(Component.CENTER_ALIGNMENT);
		play.setMaximumSize(new Dimension(ITEM_WIDTH, (int) play.getPreferredSize().getHeight()));
		play.addActionListener(this);
		howToPlay.setFont(BUTTON_FONT);
		howToPlay.setAlignmentX(Component.CENTER_ALIGNMENT);
		howToPlay.setMaximumSize(new Dimension(ITEM_WIDTH, (int) howToPlay.getPreferredSize().getHeight()));
		howToPlay.addActionListener(this);

		add(Box.createVerticalStrut(100));
		add(title);
		add(Box.createVerticalStrut(20));
		add(play);
		add(Box.createVerticalStrut(20));
		add(howToPlay);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == play){
			ctx.getWindow().show("game");
		} else if (e.getSource() == howToPlay) {
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
