/**
 * 
 */
package ca.uwo.csd.cs2212.team01;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

import javax.imageio.ImageIO;
import javax.swing.*;

/**
 * @author Kamal
 *
 */
public class SplashScreen extends JPanel {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Font FONT_HELVETICA_NEUE_THIN = null, FONT_HELVETICA_NEUE_ITALIC = null, FONT_HELVETICA_NEUE_BOLD = null;
	private MainWindow mainWindow;
	
	private JPanel loggingInPanel, titlePanel;
	private GoalPanel setGoalPanel;
	
	public SplashScreen(MainWindow mainWindow) {
		this.mainWindow = mainWindow;
		
    	//this.setLayout(null);
		this.setOpaque(false);
    	this.setLocation(0, 0);
    	this.setSize(1480, 800);
    	
		this.initUI();
	}
	
    private void initUI() {
    	this.titlePanel = new JPanel() {
    		@Override
    		protected void paintComponent(Graphics g) {
    			super.paintComponent(g);
    			
    			this.setLayout(null);
    			this.setOpaque(false);
    			
    			Graphics2D g2 = (Graphics2D) g;
    			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    			
    			JLabel title = new JLabel("Trainr", JLabel.LEFT);
    			title.setFont(FONT_HELVETICA_NEUE_THIN.deriveFont(120.0f));
    			Dimension sizeTitle = title.getPreferredSize();
    			title.setBounds(0, 0, sizeTitle.width, sizeTitle.height);
    			title.setForeground(new Color(255,255,255,150));
    			
    			JLabel version = new JLabel("Beta Version 1.0", JLabel.LEFT);
    			version.setFont(FONT_HELVETICA_NEUE_THIN.deriveFont(25.0f));
    			Dimension sizeVersion = version.getPreferredSize();
    			version.setBounds((sizeTitle.width-sizeVersion.width)/2, sizeTitle.height, sizeVersion.width, sizeVersion.height);
    			version.setForeground(new Color(255,255,255,128));

    			this.setSize(sizeTitle.width, sizeTitle.height+sizeVersion.height);
    			this.setLocation((1480/2-getWidth())/2, (800-getHeight())/2);
    			
    			this.add(title);
    			this.add(version);
    		}
    	};
    	this.add(titlePanel);
    	
    	this.add(mainWindow.getLoadingScreen().getDevicesPanel());

    	this.setGoalPanel = new GoalPanel(mainWindow, FONT_HELVETICA_NEUE_THIN);
	}
    
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
			image = ImageIO.read(new File("UI/fitbit-logo.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		g2.drawImage(image, 13, getHeight()-image.getHeight()-13, null);

		JLabel label = new JLabel("<html>Fitbit Privacy Statement<br>Copy Rights & Endorsements</html>", JLabel.LEFT);
		label.setFont(FONT_HELVETICA_NEUE_THIN.deriveFont(20.0f));
		Dimension size = label.getPreferredSize();
		label.setBounds(13+image.getWidth()+13, getHeight()-13-50, size.width, size.height);
		label.setForeground(Color.WHITE);
		this.add(label);
		
		image = null;
		try {
			image = ImageIO.read(new File("UI/logo-s.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		g2.drawImage(image, getWidth()-image.getWidth()-13, getHeight()-13-image.getHeight(), null);
	}
}