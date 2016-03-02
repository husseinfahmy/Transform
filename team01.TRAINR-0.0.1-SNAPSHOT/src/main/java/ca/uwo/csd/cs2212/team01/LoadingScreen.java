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
public class LoadingScreen extends JPanel {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Font FONT_HELVETICA_NEUE_THIN = null, FONT_HELVETICA_NEUE_ITALIC = null, FONT_HELVETICA_NEUE_BOLD = null;
	private MainWindow mainWindow;
	
	private JPanel titlePanel;
	
	public LoadingScreen(MainWindow mainWindow) {
		this.mainWindow = mainWindow;
		
    	//this.setLayout(null);
		this.setOpaque(false);
    	this.setLocation(0, 0);
    	this.setSize(1480, 800);
    	
    	this.createFonts();
    	
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
    	
    	JPanel loggingInPanel = new JPanel() {
    		@Override
    		protected void paintComponent(Graphics g) {
    			super.paintComponent(g);
    			
    			this.setLayout(null);
    			this.setOpaque(false);
    			
    			Graphics2D g2 = (Graphics2D) g;
    			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    			
    			JLabel label = new JLabel("Logging In...", JLabel.LEFT);
    			label.setFont(FONT_HELVETICA_NEUE_THIN.deriveFont(45.0f));
    			Dimension size = label.getPreferredSize();
    			label.setBounds(0, 0, size.width, size.height);
    			label.setForeground(new Color(255,255,255,150));

    			this.setSize(size.width, size.height);
    			this.setLocation(1480/2 + (1480/2-getWidth())/2, (800-getHeight())/2);
    			
    			this.add(label);
    		}
    	};
    	this.add(loggingInPanel);
	}
    
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