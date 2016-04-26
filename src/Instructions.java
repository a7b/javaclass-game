import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;


public class Instructions extends JPanel implements ActionListener{
	private static final long serialVersionUID = 4717552635018877910L;
	
	private Context ctx;

	private JButton backButton;

	public Instructions() {
		super(new SpringLayout());
		SpringLayout layout = (SpringLayout) getLayout();
		
		backButton = new JButton("Back");
		backButton.addActionListener(this);
		add(backButton);
		
		JLabel instructionsLabel = new JLabel(
				"<html>"
						+ "INSTRUCTIONS<br>"
						+ "Type a word that you see floating down the screen into the text box to load the cannon<br>"
						+ "Pressing \"Enter\" will shoot a laser beam<br>"
						+ "Use the mouse to aim the cannon<br>"
						+ "If the laser collides with the same word as the word that is typed in the box, you get a point, and the word disappears<br>"
						+ "If a word reaches the bottom of the screen before it is destroyed, you will lose a life<br>"
						+ "Stay alive for as long as possible!<br>"
						+ "</html>");
		add(instructionsLabel);
		
		layout.putConstraint(SpringLayout.WEST, backButton, 20,
				SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, backButton, 20,
				SpringLayout.NORTH, this);
		
		layout.putConstraint(SpringLayout.NORTH, instructionsLabel, 40,
				SpringLayout.SOUTH, backButton);
		layout.putConstraint(SpringLayout.WEST, instructionsLabel, 30,
				SpringLayout.WEST, this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == backButton) {
			ctx.getWindow().show("main-menu");
		}
	}

	public Context getContext() {
		return ctx;
	}

	public void setContext(Context context) {
		this.ctx = context;
	}
	
}
