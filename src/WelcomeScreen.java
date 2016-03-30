import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

public class WelcomeScreen extends JPanel implements ActionListener{
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
	
	public static void main(String[] args){
		WelcomeScreen screen = new WelcomeScreen();
		JFrame test = new JFrame("Testing");
		test.setSize(250, 100);
		test.setLocationRelativeTo(null);
		test.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		test.add(welcomePanel);
		test.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == play){
			
		}
	}
	
}
