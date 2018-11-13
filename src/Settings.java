import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class Settings extends JFrame {

	static JRadioButton mouse;
	JRadioButton keyboard;
	
	ButtonGroup controller;
	
	JPanel controlPanel;
	
	public Settings(){
		
		super("Settings");
		
		setLayout(new GridLayout(2, 1));
		
		mouse = new JRadioButton("Mouse");
		keyboard = new JRadioButton("Keyboard");
		
		controller = new ButtonGroup();
		
		controller.add(mouse);
		controller.add(keyboard);
		
		controlPanel = new JPanel();
		controlPanel.add(mouse);
		controlPanel.add(keyboard);
		
		add(controlPanel);
	}
}
