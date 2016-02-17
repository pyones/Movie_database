import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.LayoutManager;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class Printout extends JFrame implements ActionListener{
	private JLabel info;
	JPanel panel = new JPanel(); 
	JFrame frame;
	JTextArea text;
	JButton ok=new JButton("Ok");

	public Printout(){
		JFrame frame = new JFrame("FrameDemo");	
	}
	
	public void setLabel(String input){
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		frame=new JFrame("table");
		
		//this.info=new JLabel("table");
		text=new JTextArea(input);
		text.setEditable(false);
		panel.add(text);
		panel.add(ok);
		ok.addActionListener((ActionListener) this);
		frame.add(panel);
		frame.pack();
		frame.setVisible(true);

	}
	public void actionPerformed(ActionEvent e) {
		frame.dispose();
	}

}
