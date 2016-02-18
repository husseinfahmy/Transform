/**
 * 
 */
package ca.uwo.csd.cs2212.team01;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.*;

import javax.imageio.ImageIO;
import javax.swing.*;

/*import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;

import java.awt.Color;
import javax.swing.GroupLayout;

import java.awt.BorderLayout;*/

/**
 * @author Kamal
 *
 */
public class MainWindow extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int posX = 0, posY = 0;
    
	public MainWindow() {
		this.initUI();
	}
    
    private void initUI() {
    	this.setTitle("TRAINR");
    	this.setSize(1480, 800);
    	this.setUndecorated(true);
    	this.setLocationRelativeTo(null);

    	this.addMouseListener(new MouseAdapter()
    	{
    	   public void mousePressed(MouseEvent e)
    	   {
    	      posX = e.getX();
    	      posY = e.getY();
    	   }
    	});
    	
    	this.addMouseMotionListener(new MouseAdapter()
    	{
    	     public void mouseDragged(MouseEvent evt)
    	     {
    			//sets frame position when mouse dragged			
    			setLocation(evt.getXOnScreen()-posX,evt.getYOnScreen()-posY);
    						
    	     }
    	});
    	
    	// changed EXIT_ON_CLOSE to DISPOSE_ON_CLOSE, prevents errors when using JFrame
    	this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    	
    	JPanel mainPanel = new JPanel() {
    		@Override
    		protected void paintComponent(Graphics g) {
    			super.paintComponent(g);
    			BufferedImage bgImage = null;
				try {
					bgImage = ImageIO.read(new File("01.png"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    			g.drawImage(bgImage, 0, 0, getWidth() - 1, getHeight() - 1, 0, 0, bgImage.getWidth() - 1, bgImage.getHeight() - 1, null);
    		}
    	};
    	
    	this.add(mainPanel);
	}
}