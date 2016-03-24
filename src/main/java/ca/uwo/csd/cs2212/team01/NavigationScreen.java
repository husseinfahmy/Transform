package ca.uwo.csd.cs2212.team01;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;

public class NavigationScreen extends JPanel implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private MainWindow mainWindow;

	/**
	 * Class Constructor
	 * @param mainWindow
	 */
	public NavigationScreen(MainWindow mainWindow) {
		this.mainWindow = mainWindow;

		this.setLayout(null);
		this.setSize(1480,800);
    	this.setLocation(0,0);
    	
		this.initUI();
	}
	
    private void initUI() {
    	// Dashboard
		JButton button = new JButton();
		button.setBackground(null);
		button.setBorder(null);
		button.setFocusPainted(false);
		button.setMargin(new Insets(0, 0, 0, 0));
		button.setContentAreaFilled(false);
		button.setBorderPainted(false);
		button.setOpaque(false);
		button.setFocusable(false);
		button.setBounds(660,70, 150, 220);
		button.addActionListener(new ButtonActionListener(13, 0, mainWindow));
		this.add(button);
		
		// Profile
		button = new JButton();
		button.setBackground(null);
		button.setBorder(null);
		button.setFocusPainted(false);
		button.setMargin(new Insets(0, 0, 0, 0));
		button.setContentAreaFilled(false);
		button.setBorderPainted(false);
		button.setOpaque(false);
		button.setFocusable(false);
		button.setBounds(865, 185, 205, 210);
		button.addActionListener(new ButtonActionListener(13, 1, mainWindow));
		this.add(button);
		
		// Create Meals or Dishes
		button = new JButton();
		button.setBackground(null);
		button.setBorder(null);
		button.setFocusPainted(false);
		button.setMargin(new Insets(0, 0, 0, 0));
		button.setContentAreaFilled(false);
		button.setBorderPainted(false);
		button.setOpaque(false);
		button.setFocusable(false);
		button.setBounds(860, 400, 210, 210);
		button.addActionListener(new ButtonActionListener(13, 2, mainWindow));
		this.add(button);
		
		// Quit
		button = new JButton();
		button.setBackground(null);
		button.setBorder(null);
		button.setFocusPainted(false);
		button.setMargin(new Insets(0, 0, 0, 0));
		button.setContentAreaFilled(false);
		button.setBorderPainted(false);
		button.setOpaque(false);
		button.setFocusable(false);
		button.setBounds(680, 510, 125, 220);
		button.addActionListener(new ButtonActionListener(13, 3, mainWindow));
		this.add(button);
		
		// My Meals & Dishes
		button = new JButton();
		button.setBackground(null);
		button.setBorder(null);
		button.setFocusPainted(false);
		button.setMargin(new Insets(0, 0, 0, 0));
		button.setContentAreaFilled(false);
		button.setBorderPainted(false);
		button.setOpaque(false);
		button.setFocusable(false);
		button.setBounds(410, 400, 205, 210);
		button.addActionListener(new ButtonActionListener(13, 4, mainWindow));
		this.add(button);
		
		// Plans
		button = new JButton();
		button.setBackground(null);
		button.setBorder(null);
		button.setFocusPainted(false);
		button.setMargin(new Insets(0, 0, 0, 0));
		button.setContentAreaFilled(false);
		button.setBorderPainted(false);
		button.setOpaque(false);
		button.setFocusable(false);
		button.setBounds(410, 180, 205, 215);
		button.addActionListener(new ButtonActionListener(13, 5, mainWindow));
		this.add(button);
    }
    
    /* (non-Javadoc)
     * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
     */
    @Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		this.setLayout(null);
		this.setSize(1480,800);
    	this.setLocation(0,0);
		
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		BufferedImage image = null;
		try {
			image = ImageIO.read(new File("UI/bg.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		g2.drawImage(image, 0, 0, getWidth(), getHeight(), 0, 0, image.getWidth(), image.getHeight(), null);
		
		image = null;
		try {
			image = ImageIO.read(new File("UI/nav-bg.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		g2.drawImage(image, (getWidth()-image.getWidth())/2, (getHeight()-image.getHeight())/2, null);
	}
}
