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
	private JLabel refreshDesc;
	
	public DashboardScreen(MainWindow mainWindow) {
		this.mainWindow = mainWindow;
		
		this.setOpaque(false);
    	this.setLocation(0, 0);
    	this.setSize(1480, 800);
    	
    	this.createFonts();
    	
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
    			g2.drawImage(image, 13, 13, null);

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

    			image = null;
				try {
					image = ImageIO.read(new File("UI/refresh-icon.png"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

    			JButton refreshBtn = new JButton();
    			refreshBtn.setBackground(null);
    			refreshBtn.setBorder(null);
    			refreshBtn.setFocusPainted(false);
    			refreshBtn.setMargin(new Insets(0, 0, 0, 0));
    			refreshBtn.setContentAreaFilled(false);
    			refreshBtn.setBorderPainted(false);
    			refreshBtn.setOpaque(false);
    			refreshBtn.setFocusable(false);
    			refreshBtn.setSize(image.getWidth(), image.getHeight());
    			refreshBtn.setLocation(getWidth()-image.getWidth()-13, (getHeight()-image.getHeight())/2);
    	        refreshBtn.addActionListener(new ButtonActionListener(1, 0, mainWindow));
    	        this.add(refreshBtn);
    	        
    			g2.drawImage(image, getWidth()-image.getWidth()-13, (getHeight()-image.getHeight())/2, null);
    			
    			refreshDesc = new JLabel("<html>Last Refreshed:<br>" + mainWindow.lastRefreshed().getTXTone().get(0) + "</html>", JLabel.LEFT);
    			refreshDesc.setFont(FONT_HELVETICA_NEUE_THIN.deriveFont(14.0f));
    			size = refreshDesc.getPreferredSize();
    			refreshDesc.setBounds(getWidth()-image.getWidth()-13-size.width-13, (getHeight()-size.height)/2, size.width, size.height);
    			refreshDesc.setForeground(Color.WHITE);
    			this.add(refreshDesc);
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

    			Day today = mainWindow.getDays().getLast();
    			Feedback feedback = today.todaysProgess();
    			
    			float totalCalEat = feedback.getFirstValues().get(0), totalTime = today.getDayProgress(), maxTime = mainWindow.MAX_PROGRESS, totalCalBurn = feedback.getFirstValues().get(1);
    			float scaleFactor = maxTime/totalTime, scaleMaxCal = 0;
	   			
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

    			String[] axisData = new String[5];
    			
    			for(int i = 0; i < axisData.length; i++) {
    				if (scaleMaxCal > axisData.length) axisData[i] = (int)(scaleMaxCal*i/4.5f) + "";
    				else axisData[i] = String.format("%.2f", scaleMaxCal*i/4.5f) + "";
    			}
    			
    			LineAxisGraph axisGraph = new LineAxisGraph(axisData, 0, 112+120+120-30, this.getWidth() - 1, 30, axisData.length, true);
    			this.add(axisGraph);
    			
    			label = new JLabel("Calories Eaten", JLabel.LEFT);
    			label.setFont(FONT_HELVETICA_NEUE_THIN.deriveFont(20.0f));
    			size = label.getPreferredSize();
    			label.setBounds(getWidth()/5/2, 112+120+120+(55-size.height)/2, size.width, size.height);
    			label.setForeground(new Color(119, 114, 255, 255));
    			this.add(label);
    			
    			g2.setStroke(new BasicStroke(2.0f));
    			
    			g2.setColor(new Color(119, 114, 255, 255));
				g2.drawLine((getWidth()/axisData.length)/2, 112+120+120+(55-size.height)/2 + size.height + 5, getWidth() - 1 - (int)((getWidth() - getWidth()/5/2 - 1)*(scaleMaxCal-totalCalEat)/scaleMaxCal), 112+120+120+(55-size.height)/2 + size.height + 5);

    			label = new JLabel("Calories Burned", JLabel.LEFT);
    			label.setFont(FONT_HELVETICA_NEUE_THIN.deriveFont(20.0f));
    			size = label.getPreferredSize();
    			label.setBounds(getWidth()*0 + getWidth()/5/2, 112+120+120+55+(55-size.height)/2, size.width, size.height);
    			label.setForeground(new Color(106, 185, 255, 255));
    			this.add(label);

    			g2.setColor(new Color(106, 185, 255, 255));
				g2.drawLine((getWidth()/5)/2, 112+120+120+55+(55-size.height)/2 - 5, getWidth() - 1 - (int)((getWidth() - getWidth()/5/2 - 1)*(scaleMaxCal-totalCalBurn)/scaleMaxCal), 112+120+120+55+(55-size.height)/2 - 5);
    			

    			image = null;
				try {
					image = ImageIO.read(new File("UI/shadow-up.png"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    			g2.drawImage(image, getWidth()*0, 112+120+120+110, this.getWidth() - 1, image.getHeight(), null);

    	    	int hour, minute;
    	    	
    			for(int i = 0; i < axisData.length; i++) {
    	    		hour = (int)Math.floor(maxTime/4.5f/2 + maxTime*i/4.5f)/60;
    	    		minute = (int)(maxTime/4.5f/2 + maxTime*i/4.5f) - (hour*60);
    	    		
    				if (i == 0) axisData[i] = "Wakeup";
    				else if (i == axisData.length-1 && hour >= 24) axisData[i] = "Sleep";
    				else {
        	    		if (hour > 12) axisData[i] = (hour - 12) + ":" + String.format("%02d", minute) + " pm";
        	    		else axisData[i] = hour + ":" + String.format("%02d", minute) + " am";
    				}
    			}
    			
    			axisGraph = new LineAxisGraph(axisData, 0, 112+120+120+110+image.getHeight(), this.getWidth() - 1, 30, axisData.length, false);
    			this.add(axisGraph);
    			
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

	   			//Display Feedback:
	   			//Milestone Progress Feedback:
	   			Feedback feedback = mainWindow.getVirtualTrainer().getmsFeedback();
	   			
	   			String output = null;
	   			if (feedback.getTextCode() == 1) output = feedback.getTXTone().getFirst();
	   			else if (feedback.getTextCode() == 2) output = feedback.getTXTone().getFirst();
	   			else output = null;
	   			
	   			label = new JLabel("<html><center>" + output + "</center></html>", JLabel.LEFT);
    			label.setFont(FONT_HELVETICA_NEUE_THIN.deriveFont(22.0f));
    			size = label.getPreferredSize();
    			label.setBounds((getWidth() - size.width)/2, 52 + 15 + (100-size.height)/2, size.width, size.height);
    			label.setForeground(new Color(1.0f,1.0f,1.0f,0.6f));
    			this.add(label);
    			
    			if (feedback.getTextCode() == 2) {
    				output = String.format("%.1f", feedback.getFirstValues().get(2))+" lbs to go!";
    	   			label = new JLabel(output, JLabel.LEFT);
        			label.setFont(FONT_HELVETICA_NEUE_THIN.deriveFont(22.0f));
        			size = label.getPreferredSize();
        			label.setBounds(40, 52 + 15 + 100 + 100/2 - size.height - 3, size.width, size.height);
        			label.setForeground(new Color(106, 185, 255, 255));
        			this.add(label);
        			g2.setColor(new Color(106, 185, 255, 255));
        			g2.drawLine(40, 52 + 15 + 100 + 100/2, (getWidth()-40-50) - (int)((getWidth()-40*2-50)*(2-feedback.getFirstValues().get(2))/2), 52 + 15 + 100 + 100/2);

        			g2.setColor(Color.WHITE);
        			g2.draw(new Ellipse2D.Double(getWidth()-40-50, 52 + 15 + 100 + (100-50)/2, 50, 50));
        			
        			label = new JLabel("MS", JLabel.LEFT);
        			label.setFont(FONT_HELVETICA_NEUE_THIN.deriveFont(22.0f));
        			size = label.getPreferredSize();
        			label.setBounds(getWidth()-40-50 + (50-size.width)/2, 52+15+100 + (100-size.height)/2, size.width, size.height);
        			label.setForeground(Color.WHITE);
        			this.add(label);
    			}
    			
	   			if(feedback.getButtonCode()==1) System.out.println("Display Customize My Plans button");
	   			System.out.println();
	   			
	   			//Feedback on user's current Calorie Difference for today:
	   			feedback = mainWindow.getVirtualTrainer().updateTodaysProgress(mainWindow.getDays().getLast());
	   			
	   			if(feedback.getTextCode() == 1) output = feedback.getTXTone().getFirst();
	   			else if (feedback.getTextCode() == 2) output = feedback.getTXTone().getFirst() + feedback.getFirstValues().get(2);
	   			else output = null;
	   			
	   			label = new JLabel("<html><center>" + output + "</center></html>", JLabel.LEFT);
    			label.setFont(FONT_HELVETICA_NEUE_THIN.deriveFont(22.0f));
    			size = label.getPreferredSize();
    			label.setBounds((getWidth() - size.width)/2, 52+15+100+100 + (100-size.height)/2, size.width, size.height);
    			label.setForeground(new Color(1.0f,1.0f,1.0f,0.6f));
    			this.add(label);
	   			
	   			//Feedback on user's past week's performance:
	   			feedback = mainWindow.updateWeeklyProgress();

	   			if(feedback.getTextCode() == 1) output = feedback.getTXTone().getFirst();
	   			else if (feedback.getTextCode() == 2) output = feedback.getTXTone().getFirst() + feedback.getFirstValues().get(2);
	   			else output = null;
	   			
	   			label = new JLabel("<html><center>" + output + "</center></html>", JLabel.LEFT);
    			label.setFont(FONT_HELVETICA_NEUE_THIN.deriveFont(22.0f));
    			size = label.getPreferredSize();
    			label.setBounds((getWidth() - size.width)/2, 52+15+100+100+100 + (100-size.height)/2, size.width, size.height);
    			label.setForeground(new Color(1.0f,1.0f,1.0f,0.6f));
    			this.add(label);
    		}
    	};
    	
    	this.add(trainrFeedbackPanel);
    	

    	JPanel activityTrackingPanel = new JPanel() {
    		@Override
    		protected void paintComponent(Graphics g) {
    			super.paintComponent(g);
    			
    			this.setLayout(null);
    			this.setOpaque(false);
    			this.setSize(1480/3, 800 - 75 - 150 - 20);
    	    	this.setLocation((1480 - getWidth()*3)/2 + getWidth()*2,75 + 150);
    			
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
				g2.drawImage(image, (getWidth()-image.getWidth())/2, 0, null);

    			JLabel label = new JLabel("Activity Tracking", JLabel.LEFT);
    			label.setFont(FONT_HELVETICA_NEUE_THIN.deriveFont(30.0f));
    			Dimension size = label.getPreferredSize();
    			label.setBounds((getWidth() - size.width)/2, (52-size.height)/2, size.width, size.height);
    			label.setForeground(Color.WHITE);
    			this.add(label);
    			
    			label = new JLabel("<html><div style='text-align:center;'>Active<br>Activity</div></html>", JLabel.CENTER);
    			label.setFont(FONT_HELVETICA_NEUE_THIN.deriveFont(25.0f));
    			size = label.getPreferredSize();
    			label.setBounds((getWidth()/3 - size.width)/2, 52 + (80-size.height)/2, size.width, size.height);
    			label.setForeground(Color.WHITE);
    			this.add(label);
    			
    			label = new JLabel("<html><div style='text-align:center;'>Sedentary<br>Activity</div></html>", JLabel.CENTER);
    			label.setFont(FONT_HELVETICA_NEUE_THIN.deriveFont(25.0f));
    			size = label.getPreferredSize();
    			label.setBounds(getWidth()*2/3 + (getWidth()/3 - size.width)/2, 52 + (80-size.height)/2, size.width, size.height);
    			label.setForeground(Color.WHITE);
    			this.add(label);

    			Day lastDay = mainWindow.getDays().getLast();
    			
    			int totalActiveMin = lastDay.getTotalActiveMin();
    			int totalSedMin = lastDay.getTotalSedMin();
    			
    			int totalFloors = lastDay.getTotalFloors();
    			int totalSteps = lastDay.getTotalSteps();
    			int totalDist = lastDay.getTotalDist();
    			
    			Feedback feedback = mainWindow.getDays().getLast().todaysProgess();
    			int totalCal = Math.round(feedback.getFirstValues().get(1));

    	        // draw Arc2D.Double
    	        g2.setStroke(new BasicStroke(8.0f));
    	        g2.setColor(Color.WHITE);
    	        g2.draw(new Arc2D.Double((getWidth()-100)/2, 52+80, 100, 100, 90, 360, Arc2D.OPEN));
    	        
    	        g2.setStroke(new BasicStroke(12.0f));
    	        g2.setPaint(new GradientPaint(0, 0, new Color(143, 183, 238, 255),
    	        		0, getHeight() / 2, new Color(84, 142, 250, 255)));
    	        g2.draw(new Arc2D.Double((getWidth()-100)/2, 52+80, 100, 100, 180+(360*totalActiveMin/(totalActiveMin+totalSedMin)/2), 360*totalSedMin/(totalActiveMin+totalSedMin), Arc2D.OPEN));

    			g2.setColor(new Color(1.0f,1.0f,1.0f,0.4f));
    	        g2.setStroke(new BasicStroke(2.0f));
    	        
    			label = new JLabel(totalActiveMin + "", JLabel.CENTER);
    			label.setFont(FONT_HELVETICA_NEUE_THIN.deriveFont(30.0f));
    			size = label.getPreferredSize();
    			label.setBounds((getWidth()/3 - size.width)/2, 52+80 + (100-size.height)/2, size.width, size.height);
    			label.setForeground(Color.WHITE);
    			this.add(label);

    			g2.drawLine((getWidth()/3 - size.width)/2 + size.width + 10, 52+80 + (100-2)/2, (getWidth()-100)/2 - 10 - 6, 52+80 + (100-2)/2);
    			
    			label = new JLabel("min", JLabel.CENTER);
    			label.setFont(FONT_HELVETICA_NEUE_THIN.deriveFont(18.0f));
    			Dimension size2 = label.getPreferredSize();
    			label.setBounds((getWidth()/3 - size2.width)/2, 52+80 + (100-size.height)/2 + size.height + 1, size2.width, size2.height);
    			label.setForeground(Color.WHITE);
    			this.add(label);
    			
    			label = new JLabel(totalSedMin + "", JLabel.CENTER);
    			label.setFont(FONT_HELVETICA_NEUE_THIN.deriveFont(30.0f));
    			size = label.getPreferredSize();
    			label.setBounds(getWidth()*2/3 + (getWidth()/3 - size.width)/2, 52+80 + (100-size.height)/2, size.width, size.height);
    			label.setForeground(Color.WHITE);
    			this.add(label);
    			
    			g2.drawLine((getWidth()-100)/2 + 100 + 10 + 6, 52+80 + (100-2)/2, getWidth()*2/3 + (getWidth()/3 - size.width)/2 - 10, 52+80 + (100-2)/2);
    			
    			label = new JLabel("min", JLabel.CENTER);
    			label.setFont(FONT_HELVETICA_NEUE_THIN.deriveFont(18.0f));
    			size2 = label.getPreferredSize();
    			label.setBounds(getWidth()*2/3 + (getWidth()/3 - size2.width)/2, 52+80 + (100-size.height)/2 + size.height + 1, size2.width, size2.height);
    			label.setForeground(Color.WHITE);
    			this.add(label);
    			
    	        image = null;
				try {
					image = ImageIO.read(new File("UI/heart-outline.png"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				g2.drawImage(image, (getWidth()-image.getWidth())/2, 52+80+100 + (160 - image.getHeight())/2, null);

    			g2.setColor(new Color(1.0f,1.0f,1.0f,0.4f));
    	        g2.setStroke(new BasicStroke(2.0f));
    			
    			label = new JLabel("135", JLabel.CENTER);
    			label.setFont(FONT_HELVETICA_NEUE_THIN.deriveFont(30.0f));
    			size = label.getPreferredSize();
    			label.setBounds((getWidth()/3 - size.width)/2, 52+80+100 + (160-size.height)/2, size.width, size.height);
    			label.setForeground(Color.WHITE);
    			this.add(label);

    			g2.drawLine((getWidth()/3 - size.width)/2 + size.width + 10, 52+80+100 + (160-2)/2, (getWidth()-image.getWidth())/2 - 10, 52+80+100 + (160-2)/2);
    			
    			label = new JLabel("BPM", JLabel.CENTER);
    			label.setFont(FONT_HELVETICA_NEUE_THIN.deriveFont(18.0f));
    			size2 = label.getPreferredSize();
    			label.setBounds((getWidth()/3 - size2.width)/2, 52+80+100 + (160-size.height)/2 + size.height + 1, size2.width, size2.height);
    			label.setForeground(Color.WHITE);
    			this.add(label);
    			
    			label = new JLabel("78", JLabel.CENTER);
    			label.setFont(FONT_HELVETICA_NEUE_THIN.deriveFont(30.0f));
    			size = label.getPreferredSize();
    			label.setBounds(getWidth()*2/3 + (getWidth()/3 - size.width)/2, 52+80+100 + (160-size.height)/2, size.width, size.height);
    			label.setForeground(Color.WHITE);
    			this.add(label);

    			g2.drawLine((getWidth()-image.getWidth())/2 + image.getWidth() + 10, 52+80+100 + (160-2)/2, getWidth()*2/3 + (getWidth()/3 - size.width)/2 - 10, 52+80+100 + (160-2)/2);
    			
    			label = new JLabel("BPM", JLabel.CENTER);
    			label.setFont(FONT_HELVETICA_NEUE_THIN.deriveFont(18.0f));
    			size2 = label.getPreferredSize();
    			label.setBounds(getWidth()*2/3 + (getWidth()/3 - size2.width)/2, 52+80+100 + (160-size.height)/2 + size.height + 1, size2.width, size2.height);
    			label.setForeground(Color.WHITE);
    			this.add(label);
				
				int arcSize = (getWidth() - 40*4)/4;
				
    	        // draw Arc2D.Double
    	        g2.setStroke(new BasicStroke(2.0f));
    	        g2.setColor(Color.WHITE);
    	        g2.draw(new Ellipse2D.Double(40*1 + arcSize*0, 52+80+100+160, arcSize, arcSize));

    			label = new JLabel(totalDist + "", JLabel.LEFT);
    			label.setFont(FONT_HELVETICA_NEUE_THIN.deriveFont(20.0f));
    			size = label.getPreferredSize();
    			label.setBounds(50*1 + arcSize*0 + (arcSize-size.width)/2, 52+80+100+160 + ((arcSize+6)*2/3 - size.height)/2, size.width, size.height);
    			label.setForeground(Color.WHITE);
    			this.add(label);
    			
    			label = new JLabel("Km", JLabel.LEFT);
    			label.setFont(FONT_HELVETICA_NEUE_THIN.deriveFont(18.0f));
    			size = label.getPreferredSize();
    			label.setBounds(40*1 + arcSize*0 + (arcSize-size.width)/2, 52+80+100+160 + arcSize/2 + (arcSize/2 - size.height - 6)/2, size.width, size.height);
    			label.setForeground(Color.WHITE);
    			this.add(label);
    			
    	        // draw Arc2D.Double
    	        g2.setStroke(new BasicStroke(2.0f));
    	        g2.setColor(Color.WHITE);
    	        g2.draw(new Ellipse2D.Double(50*2 + arcSize*1, 52+80+100+160, arcSize, arcSize));

    			label = new JLabel(totalSteps + "", JLabel.LEFT);
    			label.setFont(FONT_HELVETICA_NEUE_THIN.deriveFont(20.0f));
    			size = label.getPreferredSize();
    			label.setBounds(40*2 + arcSize*1 + (arcSize-size.width)/2, 52+80+100+160 + ((arcSize+6)*2/3 - size.height)/2, size.width, size.height);
    			label.setForeground(Color.WHITE);
    			this.add(label);
    			
    			label = new JLabel("Steps", JLabel.LEFT);
    			label.setFont(FONT_HELVETICA_NEUE_THIN.deriveFont(18.0f));
    			size = label.getPreferredSize();
    			label.setBounds(40*2 + arcSize*1 + (arcSize-size.width)/2, 52+80+100+160 + arcSize/2 + (arcSize/2 - size.height - 6)/2, size.width, size.height);
    			label.setForeground(Color.WHITE);
    			this.add(label);
    			
    	        // draw Arc2D.Double
    	        g2.setStroke(new BasicStroke(2.0f));
    	        g2.setColor(Color.WHITE);
    	        g2.draw(new Ellipse2D.Double(50*3 + arcSize*2, 52+80+100+160, arcSize, arcSize));

    			label = new JLabel(totalFloors + "", JLabel.LEFT);
    			label.setFont(FONT_HELVETICA_NEUE_THIN.deriveFont(20.0f));
    			size = label.getPreferredSize();
    			label.setBounds(40*3 + arcSize*2 + (arcSize-size.width)/2, 52+80+100+160 + ((arcSize+6)*2/3 - size.height)/2, size.width, size.height);
    			label.setForeground(Color.WHITE);
    			this.add(label);
    			
    			label = new JLabel("Floors", JLabel.LEFT);
    			label.setFont(FONT_HELVETICA_NEUE_THIN.deriveFont(18.0f));
    			size = label.getPreferredSize();
    			label.setBounds(40*3 + arcSize*2 + (arcSize-size.width)/2, 52+80+100+160 + arcSize/2 + (arcSize/2 - size.height - 6)/2, size.width, size.height);
    			label.setForeground(Color.WHITE);
    			this.add(label);

    			label = new JLabel(totalCal + "", JLabel.LEFT);
    			label.setFont(FONT_HELVETICA_NEUE_THIN.deriveFont(20.0f));
    			size = label.getPreferredSize();
    			label.setBounds(40*4 + arcSize*3 + (arcSize-size.width)/2, 52+80+100+160 + ((arcSize+6)*2/3 - size.height)/2, size.width, size.height);
    			label.setForeground(Color.WHITE);
    			this.add(label);
    			
    			label = new JLabel("Cal Burned", JLabel.LEFT);
    			label.setFont(FONT_HELVETICA_NEUE_THIN.deriveFont(18.0f));
    			size = label.getPreferredSize();
    			label.setBounds(40*4 + arcSize*3 + (arcSize-size.width)/2, 52+80+100+160 + arcSize/2 + (arcSize/2 - size.height - 6)/2, size.width, size.height);
    			label.setForeground(Color.WHITE);
    			this.add(label);
    			
    	        // reset stroke to default
    	        g2.setStroke(new BasicStroke(1.0f));
    		}
    	};
    	
    	this.add(activityTrackingPanel);
	}

    public JPanel getBannerPanel() { return this.bannerPanel; }
    public JLabel getRefreshDesc() { return this.refreshDesc; }
    
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