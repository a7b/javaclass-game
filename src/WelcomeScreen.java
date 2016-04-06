import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

public class WelcomeScreen extends JPanel implements ActionListener {

	private static final long serialVersionUID = -5432368345116470291L;

	private JButton play;
	private JButton howToPlay;
	private static JPanel welcomePanel;
	private SpringLayout layout;
	
	public WelcomeScreen(){
		welcomePanel = new JPanel();
		layout = new SpringLayout();
		welcomePanel.setLayout(layout);
		
		play = new JButton("Play");
		play.addActionListener(this);
		welcomePanel.add(play);
		
		howToPlay = new JButton("How to Play");
		howToPlay.addActionListener(this);
		welcomePanel.add(howToPlay);
		
		layout.putConstraint(SpringLayout.WEST, play, 20, SpringLayout.WEST, welcomePanel);
	    layout.putConstraint(SpringLayout.NORTH, play, 20, SpringLayout.NORTH, welcomePanel);
	    layout.putConstraint(SpringLayout.NORTH, howToPlay, 20, SpringLayout.NORTH, welcomePanel);
	    layout.putConstraint(SpringLayout.WEST, howToPlay, 20, SpringLayout.EAST, play);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == play){
			
		}
	}
	
}
