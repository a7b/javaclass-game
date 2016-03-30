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
		
		instructionsL1 = new JLabel("Testing 12");
		instructionsPanel.add(instructionsL1);
		
		layout.putConstraint(SpringLayout.WEST, back, 20, SpringLayout.WEST, instructionsPanel);
		layout.putConstraint(SpringLayout.NORTH, back, 20, SpringLayout.NORTH, instructionsPanel);
		layout.putConstraint(SpringLayout.NORTH, instructionsL1, 20, SpringLayout.SOUTH, back);
		layout.putConstraint(SpringLayout.WEST, instructionsL1, 30, SpringLayout.WEST, instructionsPanel);
	}
	public static void main(String[] args){
		Instructions screen = new Instructions();
		JFrame test = new JFrame("Testing");
		test.setSize(400, 400);
		test.setLocationRelativeTo(null);
		test.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		test.add(instructionsPanel);
		test.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {
	}
	
}
