/**
 * 
 */
package ca.uwo.csd.cs2212.team01;

import java.awt.*;
import java.awt.geom.Arc2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Date;
import java.util.LinkedList;

import javax.imageio.ImageIO;
import javax.swing.*;

/**
 * @author Kamal
 *
 */
public class DashboardScreen extends JPanel {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Font FONT_HELVETICA_NEUE_THIN = null, FONT_HELVETICA_NEUE_ITALIC = null, FONT_HELVETICA_NEUE_BOLD = null;
	private MainWindow mainWindow;
	
	private JPanel bannerPanel;
	
	public DashboardScreen(MainWindow mainWindow) {
		this.mainWindow = mainWindow;
		
		this.setOpaque(false);
    	this.setLocation(0, 0);
    	this.setSize(1480, 800);
    	
		this.initUI();
	}
	
    private void initUI() {
    	bannerPanel = new JPanel() {
    		@Override
    		protected void paintComponent(Graphics g) {
    			super.paintComponent(g);
    			
    			this.setLayout(null);
    			this.setOpaque(false);
    	    	this.setLocation(0,0);
    			
    			Graphics2D g2 = (Graphics2D) g;
    			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    			
    			BufferedImage image = null;
				try {
					image = ImageIO.read(new File("UI/banner-bg.png"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    			this.setSize(1480, image.getHeight());
    			g2.drawImage(image, 0, 0, getWidth(), image.getHeight(), 0, 0, image.getWidth(), image.getHeight(), null);
    			
    			image = null;
				try {
					image = ImageIO.read(new File("UI/logo-s.png"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    			g2.drawImage(image, 13, 13, 63, 63, 0, 0, image.getWidth(), image.getHeight(), null);

    			g2.setColor(new Color(1.0f,1.0f,1.0f,0.3f));
    	        g2.setStroke(new BasicStroke(2.0f));
    			g2.drawLine((int)(getWidth()*0.7f/2), 75/2, (int)(getWidth()*0.7f/2) + (int)(getWidth()*0.3f), 75/2);
    			
    			JLabel label = new JLabel("Current Weight", JLabel.LEFT);
    			label.setFont(FONT_HELVETICA_NEUE_THIN.deriveFont(20.0f));
    			Dimension size = label.getPreferredSize();
    			label.setBounds(getWidth()/2 - (int)(getWidth()*0.3f)/2 + (int)((getWidth()*0.3f/2) - size.width)/2, (75/2 - size.height)/2, size.width, size.height);
    			label.setForeground(Color.WHITE);
    			this.add(label);
    			
    			label = new JLabel("Target Weight", JLabel.LEFT);
    			label.setFont(FONT_HELVETICA_NEUE_THIN.deriveFont(20.0f));
    			size = label.getPreferredSize();
    			label.setBounds(getWidth()/2 + (int)((getWidth()*0.3f/2) - size.width)/2, (75/2 - size.height)/2, size.width, size.height);
    			label.setForeground(Color.WHITE);
    			this.add(label);

    			image = null;
				try {
					image = ImageIO.read(new File("UI/user.png"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    			g2.drawImage(image, (getWidth()-image.getWidth())/2, (getHeight()-image.getHeight())/2, null);
    		}
    	};
    	
    	this.add(bannerPanel);
    	
    	JPanel weekProgressPanel = new JPanel() {
    		@Override
    		protected void paintComponent(Graphics g) {
    			super.paintComponent(g);
    			
    			this.setLayout(null);
    			this.setOpaque(false);
    			
    			BufferedImage image = null;
				try {
					image = ImageIO.read(new File("UI/weekprogress-bg.png"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    			this.setSize(image.getWidth(), 150);
    	    	this.setLocation((1480-this.getWidth())/2,75);
    			g.drawImage(image, 0, 60, image.getWidth(), image.getHeight(), null);
    			
    			JLabel label;
    			Dimension size;
    			
    			BufferedImage checkmark = null, exclmark = null, xmark = null;
				try {
					checkmark = ImageIO.read(new File("UI/checkmark.png"));
					exclmark = ImageIO.read(new File("UI/exclmark.png"));
					xmark = ImageIO.read(new File("UI/xmark.png"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    		}
    	};
    	
    	this.add(weekProgressPanel);
    	

    	JPanel calorieTrackingPanel = new JPanel() {
    		@Override
    		protected void paintComponent(Graphics g) {
    			super.paintComponent(g);
    			
    			this.setLayout(null);
    			this.setOpaque(false);
    			this.setSize(1480/3, 800 - 75 - 150 - 20);
    	    	this.setLocation((1480 - (getWidth()*3))/2 + getWidth()*0,75 + 150);
    			
    			Graphics2D g2 = (Graphics2D) g;
    			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    			
    			BufferedImage image = null;
				try {
					image = ImageIO.read(new File("UI/panel-head-bg.png"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    	    	
    			g2.setColor(new Color(1.0f,1.0f,1.0f,0.3f));
    	        g2.setStroke(new BasicStroke(1.0f));
				g2.drawLine(getWidth()-1, 0, getWidth()-1, getHeight());
				g2.drawImage(image, (getWidth()-image.getWidth())/2, 0, null);

    			JLabel label = new JLabel("Calorie Tracking", JLabel.LEFT);
    			label.setFont(FONT_HELVETICA_NEUE_THIN.deriveFont(30.0f));
    			Dimension size = label.getPreferredSize();
    			label.setBounds(getWidth()*0 + (getWidth() - size.width)/2, (52-size.height)/2, size.width, size.height);
    			label.setForeground(Color.WHITE);
    			this.add(label);

    			label = new JLabel("Today's Plan", JLabel.LEFT);
    			label.setFont(FONT_HELVETICA_NEUE_THIN.deriveFont(24.0f));
    			size = label.getPreferredSize();
    			label.setBounds((getWidth() - size.width)/2, 52 + (60-size.height)/2, size.width, size.height);
    			label.setForeground(Color.WHITE);
    			this.add(label);
	    		
    			image = null;
				try {
					image = ImageIO.read(new File("UI/shadow-dn.png"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    			g2.drawImage(image, getWidth()*0, 112, getWidth() - 1, image.getHeight(), null);
    			
    			image = null;
				try {
					image = ImageIO.read(new File("UI/shadow-up.png"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    			g2.drawImage(image, getWidth()*0, 112+130, getWidth() - 1, image.getHeight(), null);

	   			
    			label = new JLabel("Today's Progress", JLabel.LEFT);
    			label.setFont(FONT_HELVETICA_NEUE_THIN.deriveFont(24.0f));
    			size = label.getPreferredSize();
    			label.setBounds(getWidth()*0 + (getWidth() - size.width)/2, 112+130 + (100-size.height)/2, size.width, size.height);
    			label.setForeground(Color.WHITE);
    			this.add(label);
    			
    			image = null;
				try {
					image = ImageIO.read(new File("UI/shadow-dn.png"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    			g2.drawImage(image, 0, 112+120+120, this.getWidth() - 1, image.getHeight(), null);

    			label = new JLabel("Calories Eaten", JLabel.LEFT);
    			label.setFont(FONT_HELVETICA_NEUE_THIN.deriveFont(20.0f));
    			size = label.getPreferredSize();
    			label.setBounds(getWidth()/5/2, 112+120+120+(55-size.height)/2, size.width, size.height);
    			label.setForeground(new Color(119, 114, 255, 255));
    			this.add(label);
    			
    			label = new JLabel("Calories Burned", JLabel.LEFT);
    			label.setFont(FONT_HELVETICA_NEUE_THIN.deriveFont(20.0f));
    			size = label.getPreferredSize();
    			label.setBounds(getWidth()*0 + getWidth()/5/2, 112+120+120+55+(55-size.height)/2, size.width, size.height);
    			label.setForeground(new Color(106, 185, 255, 255));
    			this.add(label);

    			image = null;
				try {
					image = ImageIO.read(new File("UI/shadow-up.png"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    			g2.drawImage(image, getWidth()*0, 112+120+120+110, this.getWidth() - 1, image.getHeight(), null);

    			image = null;
				try {
					image = ImageIO.read(new File("UI/panel-btn1-bg.png"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    			g2.drawImage(image, getWidth()*0 + (getWidth()-image.getWidth())/2, getHeight()-image.getHeight(), null);

    			label = new JLabel("Customize My Plans", JLabel.LEFT);
    			label.setFont(FONT_HELVETICA_NEUE_THIN.deriveFont(20.0f));
    			size = label.getPreferredSize();
    			label.setBounds(getWidth()*0 + (getWidth()-size.width)/2, getHeight()-image.getHeight()+(31-size.height)/2, size.width, size.height);
    			label.setForeground(Color.WHITE);
    			this.add(label);
    		}
    	};
    	
    	this.add(calorieTrackingPanel);
    	

    	JPanel trainrFeedbackPanel = new JPanel() {
    		@Override
    		protected void paintComponent(Graphics g) {
    			super.paintComponent(g);
    			
    			this.setLayout(null);
    			this.setOpaque(false);
    			this.setSize(1480/3, 800 - 75 - 150 - 20);
    	    	this.setLocation((1480 - getWidth()*3)/2 + getWidth()*1,75 + 150);
    			
    			Graphics2D g2 = (Graphics2D) g;
    			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    			
    			BufferedImage image = null;
				try {
					image = ImageIO.read(new File("UI/panel-head-bg.png"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

    			g2.setColor(new Color(1.0f,1.0f,1.0f,0.3f));
    	        g2.setStroke(new BasicStroke(1.0f));
				g2.drawLine(0, 0, 0, getHeight());
				g2.drawLine(getWidth()-1, 0, getWidth()-1, getHeight());
				g2.drawImage(image, (getWidth()-image.getWidth())/2, 0, null);

    			JLabel label = new JLabel("TRAINR Feedback", JLabel.LEFT);
    			label.setFont(FONT_HELVETICA_NEUE_THIN.deriveFont(30.0f));
    			Dimension size = label.getPreferredSize();
    			label.setBounds((getWidth() - size.width)/2, (52-size.height)/2, size.width, size.height);
    			label.setForeground(Color.WHITE);
    			this.add(label);
    			
    			g2.setColor(Color.WHITE);
    			g2.draw(new Ellipse2D.Double(getWidth()-40-50, 52 + 15 + 100 + (100-50)/2, 50, 50));
    			
    			label = new JLabel("MS", JLabel.LEFT);
    			label.setFont(FONT_HELVETICA_NEUE_THIN.deriveFont(22.0f));
    			size = label.getPreferredSize();
    			label.setBounds(getWidth()-40-50 + (50-size.width)/2, 52+15+100 + (100-size.height)/2, size.width, size.height);
    			label.setForeground(Color.WHITE);
    			this.add(label);
    		}
    	};
    	
    	this.add(trainrFeedbackPanel);
	}
    
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		this.setLayout(null);
		this.setSize(1480,800);
    	this.setLocation(0,0);
		
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		BufferedImage bgImage = null;
		try {
			bgImage = ImageIO.read(new File("UI/bg.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		g2.drawImage(bgImage, 0, 0, getWidth(), getHeight(), 0, 0, bgImage.getWidth(), bgImage.getHeight(), null);
	}
}