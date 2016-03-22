package ca.uwo.csd.cs2212.team01;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JButton;

public class ButtonMotionListener implements MouseMotionListener {
	private int btnMode, value;
	private MainWindow mainWindow;
	
	/**
	 * Class Constructor
	 * @param btnMode
	 * @param value
	 * @param mainWindow
	 */
	public ButtonMotionListener(int btnMode, int value, MainWindow mainWindow) {
		this.mainWindow = mainWindow;
		this.btnMode = btnMode;
		this.value = value;
	}
	
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		switch(btnMode) {
		case 4: // Setup Screen Exit Button
			JButton src = (JButton)arg0.getSource();
			src.setForeground(Color.WHITE);
			mainWindow.getSetupScreen().repaint();
			break;
		}
	}

}
