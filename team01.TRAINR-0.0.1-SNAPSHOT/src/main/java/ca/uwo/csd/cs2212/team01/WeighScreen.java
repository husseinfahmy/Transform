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
public class WeighScreen extends JPanel {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Font FONT_HELVETICA_NEUE_THIN = null, FONT_HELVETICA_NEUE_ITALIC = null, FONT_HELVETICA_NEUE_BOLD = null;
	private MainWindow mainWindow;

	private WeighPanel setWeighPanel;
	
	public WeighScreen(MainWindow mainWindow) {
		this.mainWindow = mainWindow;
		
    	//this.setLayout(null);
		this.setOpaque(false);
    	this.setLocation(0, 0);
    	this.setSize(1480, 800);
    	
    	this.createFonts();
    	
		this.initUI();
	}
	
    private void initUI() {
    	JPanel bannerPanel = new JPanel() {
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
		
    	this.setWeighPanel = new WeighPanel(mainWindow, FONT_HELVETICA_NEUE_THIN);
    	
    	this.add(setWeighPanel);
	}
    
    public WeighPanel getSetWeighPanel() { return this.setWeighPanel; }
    
    private void createFonts() {
    	GraphicsEnvironment genv = GraphicsEnvironment.getLocalGraphicsEnvironment();
    	
    	//Font font = null;
		try {
			FONT_HELVETICA_NEUE_THIN = Font.createFont(Font.TRUETYPE_FONT, new File("FONTS/HelveticaNeueThin.ttf"));
			//FONT_HELVETICA_NEUE_BOLD = Font.createFont(Font.TRUETYPE_FONT, new File("FONTS/HelveticaNeueBold.ttf"));
		} catch (FontFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		genv.registerFont(FONT_HELVETICA_NEUE_THIN);
		
		try {
			FONT_HELVETICA_NEUE_ITALIC = Font.createFont(Font.TRUETYPE_FONT, new File("FONTS/HelveticaNeueThinItalic.ttf"));
		} catch (FontFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		genv.registerFont(FONT_HELVETICA_NEUE_ITALIC);
		
		/*try {
			FONT_HELVETICA_NEUE_BOLD = Font.createFont(Font.TRUETYPE_FONT, new File("FONTS/HelveticaNeue.ttf"));
		} catch (FontFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
    	//GraphicsEnvironment genv = GraphicsEnvironment.getLocalGraphicsEnvironment();
    	//genv.registerFont(font);
    	//font = font.deriveFont(12f);
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
