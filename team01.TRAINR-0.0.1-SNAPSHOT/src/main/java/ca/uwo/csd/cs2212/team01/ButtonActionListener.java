package ca.uwo.csd.cs2212.team01;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class ButtonActionListener implements ActionListener {
	private int btnMode, value;
	private MainWindow mainWindow;
	
	public ButtonActionListener(int btnMode, int value, MainWindow mainWindow) {
		this.mainWindow = mainWindow;
		this.btnMode = btnMode;
		this.value = value;
	}
	
	public void actionPerformed(ActionEvent e) {
		String value;
		JLabel label;
		Dimension size;
		float currentWeight, targetWeight;
		
		// TODO Auto-generated method stub
		switch(this.btnMode) {
		case 0: // Submit Current/Target Weights
			switch(this.value) {
			case 2: // Weigh Screen
				this.mainWindow.setVisible(false);
				this.mainWindow.getContentPane().removeAll();
				this.mainWindow.add(this.mainWindow.getWeighScreen());
				this.mainWindow.setVisible(true);
				break;
			}
			break;
		}
	}
}
