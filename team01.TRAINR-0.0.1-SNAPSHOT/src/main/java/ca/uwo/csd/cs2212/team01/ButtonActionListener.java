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
			case 1: // Dashboard Screen
				label = this.mainWindow.getSplashScreen().getSetGoalPanel().getDesc();
				
				try {
					value = this.mainWindow.getSplashScreen().getSetGoalPanel().getCurrentWeight();
					currentWeight = Integer.parseInt(value);
					
					value = this.mainWindow.getSplashScreen().getSetGoalPanel().getTargetWeight();
					targetWeight = Integer.parseInt(value);
					
					if (currentWeight-targetWeight < 2) {
						label.setText("Try to lose at least 2 lbs!");
						size = label.getPreferredSize();
						label.setBounds((503-size.width)/2, label.getY(), size.width, size.height);
						break;
					}else if (currentWeight <= 0 || targetWeight <= 0) {
						label.setText("Invalid format! Enter a positive integer.");
						size = label.getPreferredSize();
						label.setBounds((503-size.width)/2, label.getY(), size.width, size.height);
						break;
					}

					this.mainWindow.setupVirtualTrainer(currentWeight, targetWeight);

					this.mainWindow.setVisible(false);
					this.mainWindow.getContentPane().removeAll();
					this.mainWindow.add(this.mainWindow.getDashboardScreen());
					this.mainWindow.setVisible(true);
				}catch (NumberFormatException ex) {
					System.out.println("Invalid format! Enter a number.");
					label.setText("Invalid format! Enter a number.");
					size = label.getPreferredSize();
					label.setBounds((503-size.width)/2, label.getY(), size.width, size.height);
				}finally {
					System.out.println("Button Clicked");
				}
				break;
				
			case 2: // Weigh Screen
				this.mainWindow.setVisible(false);
				this.mainWindow.getContentPane().removeAll();
				this.mainWindow.add(this.mainWindow.getWeighScreen());
				this.mainWindow.setVisible(true);
				break;
			}
			break;
			
		case 1: // Refresh Button
			label = mainWindow.getDashboardScreen().getRefreshDesc();
			mainWindow.updateLastRefreshed();
			mainWindow.setVisible(false);
			label.setText("<html>Last Refreshed:<br>" + mainWindow.lastRefreshed().getTXTone().get(0) + "</html>");
			mainWindow.setVisible(true);
			break;
		}
	}
}
