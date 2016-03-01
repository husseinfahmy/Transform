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
	
	public DashboardScreen(MainWindow mainWindow) {
		this.mainWindow = mainWindow;
		
		this.setOpaque(false);
    	this.setLocation(0, 0);
    	this.setSize(1480, 800);
    	
		this.initUI();
	}
	
    private void initUI() {
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