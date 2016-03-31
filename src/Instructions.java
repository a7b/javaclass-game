import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;


public class Instructions extends JPanel implements ActionListener{
	private JLabel instructionsL1;
	private JLabel instructionsL2;
	private JLabel instructionsL3;
	private JLabel instructionsL4;
	private JLabel instructionsL5;
	private JLabel instructionsL6;
	
	private JButton back;
	
	private static JPanel instructionsPanel;
	private SpringLayout layout;
	
	public Instructions(){
		instructionsPanel = new JPanel();
		layout = new SpringLayout();
		instructionsPanel.setLayout(layout);
		
		back = new JButton("Back");
		back.addActionListener(this);
		instructionsPanel.add(back);
		
		instructionsL1 = new JLabel("INSTRUCTIONS");
		
		instructionsL2 = new JLabel("- Type a word that you see floating down the screen into the text box to load the cannon");
		instructionsL3 = new JLabel("- Pressing \"Enter\" will shoot a laser beam");
		instructionsL4 = new JLabel("- Use the mouse to aim the cannon");
		instructionsL5 = new JLabel("- If the laser collides with the same word as the word that is typed in the box, the player gets a point, and the word disappears");
		instructionsL6 = new JLabel("- If a word reaches the bottom of the screen before it is destroyed, the player will lose a life");
		
		instructionsPanel.add(instructionsL1);
		instructionsPanel.add(instructionsL2);
		instructionsPanel.add(instructionsL3);
		instructionsPanel.add(instructionsL4);
		instructionsPanel.add(instructionsL5);
		instructionsPanel.add(instructionsL6);
		
		layout.putConstraint(SpringLayout.WEST, back, 20, SpringLayout.WEST, instructionsPanel);
		layout.putConstraint(SpringLayout.NORTH, back, 20, SpringLayout.NORTH, instructionsPanel);
		
		layout.putConstraint(SpringLayout.NORTH, instructionsL1, 40, SpringLayout.SOUTH, back);
		layout.putConstraint(SpringLayout.WEST, instructionsL1, 30, SpringLayout.WEST, instructionsPanel);
		
		layout.putConstraint(SpringLayout.NORTH, instructionsL2, 20, SpringLayout.SOUTH, instructionsL1);
		layout.putConstraint(SpringLayout.WEST, instructionsL2, 30, SpringLayout.WEST, instructionsPanel);
		
		layout.putConstraint(SpringLayout.NORTH, instructionsL3, 20, SpringLayout.SOUTH, instructionsL2);
		layout.putConstraint(SpringLayout.WEST, instructionsL3, 30, SpringLayout.WEST, instructionsPanel);
		
		layout.putConstraint(SpringLayout.NORTH, instructionsL4, 20, SpringLayout.SOUTH, instructionsL3);
		layout.putConstraint(SpringLayout.WEST, instructionsL4, 30, SpringLayout.WEST, instructionsPanel);
		
		layout.putConstraint(SpringLayout.NORTH, instructionsL5, 20, SpringLayout.SOUTH, instructionsL4);
		layout.putConstraint(SpringLayout.WEST, instructionsL5, 30, SpringLayout.WEST, instructionsPanel);
		
		layout.putConstraint(SpringLayout.NORTH, instructionsL6, 20, SpringLayout.SOUTH, instructionsL5);
		layout.putConstraint(SpringLayout.WEST, instructionsL6, 30, SpringLayout.WEST, instructionsPanel);
	}
	/*public static void main(String[] args){
		Instructions screen = new Instructions();
		JFrame test = new JFrame("Testing");
		test.setSize(1280, 720);
		test.setLocationRelativeTo(null);
		test.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		test.add(instructionsPanel);
		test.setVisible(true);
	}*/
	
	public void actionPerformed(ActionEvent e) {
	}
	
}
