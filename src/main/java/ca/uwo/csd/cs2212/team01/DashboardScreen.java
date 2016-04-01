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
 * @author team01
 *
 */
public class DashboardScreen extends JPanel implements Serializable {
	private static final long serialVersionUID = 1L;
	private MainWindow mainWindow;
	
	private int activityPanelDayIndex;
	private Day activityPanelDay = null;
	
	private JPanel bannerPanel;
	private JLabel refreshDesc, mealsLabel, workoutsLabel, calDeficitLabel, activityDayLabel;
	
	private JButton[] navActivities;
	
	private int dashboardPanels = 0, activityTrackingPanels = 0, lifetimeTotals = 0;
	
	/**
	 * Class Constructor
	 * @param mainWindow
	 */
	public DashboardScreen(MainWindow mainWindow) {
		this.mainWindow = mainWindow;
		
		setActivityPanelDayIndex(6);
		setActivityPanelDay(mainWindow.getPreferences().getDays().getLast());
		
		this.setLayout(null);
		this.setOpaque(false);
    	this.setLocation(0, 0);
    	this.setSize(1480, 800);
    	
    	navActivities = new JButton[2];
    	
		this.initUI();
	}
	
    /**
     * Renders the Dashboard Screen
     */
    public void initUI() {
    	bannerPanel = new JPanel() {
    		@Override
    		protected void paintComponent(Graphics g) {
    			//super.paintComponent(g);
    			
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
    			
    			JButton button = new JButton();
    			button.setBackground(null);
    			button.setBorder(null);
    			button.setFocusPainted(false);
    			button.setMargin(new Insets(0, 0, 0, 0));
    			button.setContentAreaFilled(false);
    			button.setBorderPainted(false);
    			button.setOpaque(false);
    			button.setFocusable(false);
    			button.setBounds(13, 13, image.getWidth(), image.getHeight());
    			button.addActionListener(new ButtonActionListener(14, 0, mainWindow));
    			this.add(button);
				
    			JButton weighBtn = new JButton("Weigh Myself");
    			weighBtn.setBackground(null);
    			weighBtn.setFont(mainWindow.FONT_HELVETICA_NEUE_THIN.deriveFont(20.0f));
    			weighBtn.setForeground(new Color(255,255,255,150));
    			weighBtn.setBorder(null);
    			weighBtn.setFocusPainted(false);
    			weighBtn.setMargin(new Insets(0, 0, 0, 0));
    			weighBtn.setContentAreaFilled(false);
    			weighBtn.setBorderPainted(false);
    			weighBtn.setOpaque(false);
    			weighBtn.setFocusable(false);
    	        weighBtn.addActionListener(new ButtonActionListener(3, 0, mainWindow));
    	        this.add(weighBtn);
				
    			JLabel btnDesc = new JLabel("Weigh Myself", JLabel.LEFT);
    			btnDesc.setFont(mainWindow.FONT_HELVETICA_NEUE_THIN.deriveFont(20.0f));
    			Dimension size = btnDesc.getPreferredSize();
    			weighBtn.setSize(size.width, size.height);
    			weighBtn.setLocation(63+13, (getHeight()-size.height)/2);

			    g2.draw(new RoundRectangle2D.Float(63+13 - 8, (getHeight()-size.height)/2 - 3, size.width + 16, size.height+6, 8, 8));
    			
    			g2.setColor(new Color(1.0f,1.0f,1.0f,0.3f));
    	        g2.setStroke(new BasicStroke(2.0f));
    			g2.drawLine((int)(getWidth()*0.7f/2), 75/2, (int)(getWidth()*0.7f/2) + (int)(getWidth()*0.3f), 75/2);
    			
    			JLabel label = new JLabel("Current Weight", JLabel.LEFT);
    			label.setFont(mainWindow.FONT_HELVETICA_NEUE_THIN.deriveFont(20.0f));
    			size = label.getPreferredSize();
    			label.setBounds(getWidth()/2 - (int)(getWidth()*0.3f)/2 + (int)((getWidth()*0.3f/2) - size.width)/2, (75/2 - size.height)/2, size.width, size.height);
    			label.setForeground(Color.WHITE);
    			this.add(label);
    			
    			label = new JLabel("Target Weight", JLabel.LEFT);
    			label.setFont(mainWindow.FONT_HELVETICA_NEUE_THIN.deriveFont(20.0f));
    			size = label.getPreferredSize();
    			label.setBounds(getWidth()/2 + (int)((getWidth()*0.3f/2) - size.width)/2, (75/2 - size.height)/2, size.width, size.height);
    			label.setForeground(Color.WHITE);
    			this.add(label);
    			
    			label = new JLabel(mainWindow.getVirtualTrainer().getCurrentWeight() + "", JLabel.LEFT);
    			label.setFont(mainWindow.FONT_HELVETICA_NEUE_THIN.deriveFont(25.0f));
    			size = label.getPreferredSize();
    			label.setBounds(getWidth()/2 - (int)(getWidth()*0.3f)/2 + (int)((getWidth()*0.3f)/2 - size.width)/2, 75/2 + (75/2 - size.height)/2, size.width, size.height);
    			label.setForeground(Color.WHITE);
    			this.add(label);
    			
    			label = new JLabel(mainWindow.getVirtualTrainer().getTargetWeight() + "", JLabel.LEFT);
    			label.setFont(mainWindow.FONT_HELVETICA_NEUE_THIN.deriveFont(25.0f));
    			size = label.getPreferredSize();
    			label.setBounds(getWidth()/2 + (int)((getWidth()*0.3f)/2 - size.width)/2, 75/2 + (75/2 - size.height)/2, size.width, size.height);
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
				
    			refreshBtn.setLocation(getWidth()-image.getWidth()-13-50-13, (getHeight()-image.getHeight())/2);
    	        refreshBtn.addActionListener(new ButtonActionListener(1, 0, mainWindow));
    	        this.add(refreshBtn);
    	        
    			g2.drawImage(image, getWidth()-image.getWidth()-13-50-13, (getHeight()-image.getHeight())/2, null);

    			refreshDesc = new JLabel("<html>Last Refreshed:<br>" + mainWindow.lastRefreshed().getTXTone().get(0) + "</html>", JLabel.LEFT);
    			refreshDesc.setFont(mainWindow.FONT_HELVETICA_NEUE_THIN.deriveFont(14.0f));
    			size = refreshDesc.getPreferredSize();
    			refreshDesc.setBounds(getWidth()-image.getWidth()-13-50-13-size.width-13, (getHeight()-size.height)/2, size.width, size.height);
    			refreshDesc.setForeground(Color.WHITE);
    			this.add(refreshDesc);
				
    			image = null;
				try {
					image = ImageIO.read(new File("UI/exit-icon.png"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
    			JButton exitBtn = new JButton();
    			exitBtn.setBackground(null);
    			exitBtn.setBorder(null);
    			exitBtn.setFocusPainted(false);
    			exitBtn.setMargin(new Insets(0, 0, 0, 0));
    			exitBtn.setContentAreaFilled(false);
    			exitBtn.setBorderPainted(false);
    			exitBtn.setOpaque(false);
    			exitBtn.setFocusable(false);
    			exitBtn.setSize(image.getWidth(), image.getHeight());
    			exitBtn.setLocation(getWidth()-image.getWidth()-13, 13);
    	        exitBtn.addActionListener(new ButtonActionListener(2, 0, mainWindow));
    	        this.add(exitBtn);
				
    			g2.drawImage(image, getWidth()-image.getWidth()-13, 13, null);
    		}
    	};
    	bannerPanel.setBounds(0,0, getWidth(), 75);
    	this.add(bannerPanel);
    	
    	JPanel weekProgressPanel = new JPanel() {
    		@Override
    		protected void paintComponent(Graphics g) {
    			//super.paintComponent(g);
    			
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
    	    	//this.setLocation((1480-this.getWidth())/2,75);
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

				int value = 0;
				LinkedList<Day> days = mainWindow.getDays();
				Day day = null;
				
    			for(int i = 0; i < 7; i++) {
    				if (i == 6) label = new JLabel("<html><b>Today</b></html>", JLabel.LEFT);
    				else {
    					day = days.get(i);
    					value = (int)day.getDailyCalDiff();
    					if (day.getPlan() == null || day.getPlan().getMeals().size() == 0)
    						g.drawImage(exclmark, (this.getWidth()/7)*i + ((this.getWidth()/7)-exclmark.getWidth())/2, (60-exclmark.getHeight())/2, exclmark.getWidth(), exclmark.getHeight(), null);
    					else if (value >= 0) g.drawImage(xmark, (this.getWidth()/7)*i + ((this.getWidth()/7)-xmark.getWidth())/2, (60-xmark.getHeight())/2, xmark.getWidth(), xmark.getHeight(), null);
    					else if (value <= -480)
    						g.drawImage(checkmark, (this.getWidth()/7)*i + ((this.getWidth()/7)-checkmark.getWidth())/2, (60-checkmark.getHeight())/2, checkmark.getWidth(), checkmark.getHeight(), null);
    					else g.drawImage(exclmark, (this.getWidth()/7)*i + ((this.getWidth()/7)-exclmark.getWidth())/2, (60-exclmark.getHeight())/2, exclmark.getWidth(), exclmark.getHeight(), null);
    					
    					if (i == 5) label = new JLabel("Yesterday", JLabel.LEFT);
    					else label = new JLabel(mainWindow.getDayOfWeek(day.getDate()), JLabel.LEFT);
    				}
    				
    				label.setFont(mainWindow.FONT_HELVETICA_NEUE_THIN.deriveFont(20.0f));
        			size = label.getPreferredSize();
        			label.setBounds((this.getWidth()/7)*i + ((this.getWidth()/7)-size.width)/2, 60 + (35-size.height)/2, size.width, size.height);
        			label.setForeground(Color.WHITE);
        			this.add(label);
        			

    				if (i == 6) {
    					label = new JLabel("In Progress", JLabel.LEFT);
    					label.setFont(mainWindow.FONT_HELVETICA_NEUE_ITALIC.deriveFont(25.0f));
    				}else if (day.getPlan() == null || day.getPlan().getMeals().size() == 0) {
						g.setColor(new Color(255,255,255,150));
						label = new JLabel("No Plan", JLabel.LEFT);
	    				label.setFont(mainWindow.FONT_HELVETICA_NEUE_THIN.deriveFont(20.0f));
						size = label.getPreferredSize();
						((Graphics2D) g).draw(new RoundRectangle2D.Float((this.getWidth()/7)*i + ((this.getWidth()/7)-size.width-15)/2, 60+35 + (55-size.height-10)/2, size.width+15, size.height+10, 10, 10));
					}else {
    					label = new JLabel("<html>" + (value > 0 ? "+" : "") + value + "<small style='font-size:18pt'> cal</small></html>", JLabel.LEFT);
    					label.setFont(mainWindow.FONT_HELVETICA_NEUE_THIN.deriveFont(30.0f));
					}
    				
    				size = label.getPreferredSize();
        			label.setBounds((this.getWidth()/7)*i + ((this.getWidth()/7)-size.width)/2, 60+35 + (55-size.height)/2, size.width, size.height);
        			label.setForeground(Color.WHITE);
        			this.add(label);
        		}
    		}
    	};
    	weekProgressPanel.setBounds((getWidth()-1453)/2,75, getWidth(), 75);
    	this.add(weekProgressPanel);
    	
    	Preferences prefs = this.mainWindow.getPreferences();
    	
    	dashboardPanels = lifetimeTotals = 0;
    	for(int i = 0; i < 3; i++) {
    		if (prefs.getDashboardPanelsToggler(i)) dashboardPanels++;
    		if (prefs.getActivityTrackingPanelsToggler(i)) activityTrackingPanels++;
    	}
    	for(int i = 0; i < 4; i++) {
    		if (prefs.getLifetimeTotalsToggler(i)) lifetimeTotals++;
    	}
    	
    	JPanel calorieTrackingPanel = new JPanel() {
    		@Override
    		protected void paintComponent(Graphics g) {
    			//super.paintComponent(g);
    			
    			this.setLayout(null);
    			this.setOpaque(false);
    			this.setSize(1480/3, 800 - 75 - 150 - 20);
    	    	//this.setLocation(getDashboardPanelLocation(0));
    			
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
    			label.setFont(mainWindow.FONT_HELVETICA_NEUE_THIN.deriveFont(30.0f));
    			Dimension size = label.getPreferredSize();
    			label.setBounds(getWidth()*0 + (getWidth() - size.width)/2, (52-size.height)/2, size.width, size.height);
    			label.setForeground(Color.WHITE);
    			this.add(label);

    			label = new JLabel("Today's Plan", JLabel.LEFT);
    			label.setFont(mainWindow.FONT_HELVETICA_NEUE_THIN.deriveFont(24.0f));
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

				Feedback feedbackMeals = mainWindow.getDays().getLast().todaysMeals();
	   			Feedback feedbackWorkouts = mainWindow.getDays().getLast().todaysWorkouts();
	   			LinkedList<String> txtOne, txtTwo;
	   			
	   			if ((feedbackMeals != null && feedbackMeals.getTXTone().size() > 0) || (feedbackWorkouts != null && feedbackWorkouts.getTXTone().size() > 0)) {
					Plan plan = mainWindow.getDays().getLast().getPlan();
					
	   				mealsLabel = new JLabel(plan.getMeals().size() + " Meals" + " - " + plan.getWorkouts().size() + " Workouts = " + plan.getPredictedCalorieBurn() + " cal");
	   				mealsLabel.setFont(mainWindow.FONT_HELVETICA_NEUE_THIN.deriveFont(24.0f));
	   				mealsLabel.setForeground(Color.WHITE);
					size = mealsLabel.getPreferredSize();
					mealsLabel.setBounds((getWidth() - size.width)/2,  112 + (130-size.height)/2, size.width, size.height);
					this.add(mealsLabel);

	    			g2.setColor(new Color(1.0f,1.0f,1.0f,0.5f));
					g2.draw(new RoundRectangle2D.Float((getWidth() - (size.width+30))/2,  112 + (130-(size.height+15))/2, size.width + 30, size.height+15, 10, 10));
					
	   				/*if (feedbackMeals != null) {
						txtOne = feedbackMeals.getTXTone();
		
			   			for (int i = 0; i < feedbackMeals.getTXTone().size(); i++ ) {
			    			label = new JLabel("<html><font color='#ffffff'>" + feedbackMeals.getTXTone().get(i) + "</font><font color='#7772FF'>" + feedbackMeals.getTXTtwo().get(i) + "</font></html>", JLabel.LEFT);
			    			label.setFont(mainWindow.FONT_HELVETICA_NEUE_THIN.deriveFont(20.0f));
			    			size = label.getPreferredSize();
			    			
		   			        g2.draw(new RoundRectangle2D.Float((getWidth()/2 - size.width)/2 - 8, 112+image.getHeight() + (130-image.getHeight() - (size.height+10)*3)/2 + (size.height+10)*i - 3, size.width + 16, size.height+6, 8, 8));
			    			
			    			label.setBounds((getWidth()/2 - size.width)/2, 112+image.getHeight() + (130-image.getHeight() - (size.height+10)*3)/2 + (size.height+10)*i, size.width, size.height);
			    			label.setForeground(Color.WHITE);
			    			this.add(label);
			   			}
		   			}
		   			
		   			if (feedbackWorkouts != null) {
			   			txtOne =  feedbackWorkouts.getTXTone();
			   			txtTwo =  feedbackWorkouts.getTXTtwo();
		
			   			for (int i = 0; i < txtOne.size() && i < txtTwo.size(); i++ ) {
		   					label = new JLabel("<html><font color='#ffffff'>" + txtOne.get(i) + "</font><font color='#6AB9FF'>" + txtTwo.get(i) + "</font></html>", JLabel.LEFT);
			    			label.setFont(mainWindow.FONT_HELVETICA_NEUE_THIN.deriveFont(20.0f));
			    			size = label.getPreferredSize();
		
		   			        g2.draw(new RoundRectangle2D.Float(getWidth()/2 + (getWidth()/2 - size.width)/2 - 8, 112+image.getHeight() + (130-image.getHeight() - (size.height+10)*3)/2 + (size.height+10)*i - 3, size.width + 16, size.height+6, 8, 8));
			    			
			    			label.setBounds(getWidth()/2 + (getWidth()/2 - size.width)/2, 112+image.getHeight() + (130-image.getHeight() - (size.height+10)*3)/2 + (size.height+10)*i, size.width, size.height);
			    			label.setForeground(Color.WHITE);
			    			this.add(label);
			   			}
		   			}*/
	   			}else {
	    			label = new JLabel("No Plans", JLabel.LEFT);
	    			label.setFont(mainWindow.FONT_HELVETICA_NEUE_THIN.deriveFont(24.0f));
	    			size = label.getPreferredSize();
	    			label.setBounds((getWidth() - size.width)/2, 112 + (130-size.height)/2, size.width, size.height);
	    			label.setForeground(new Color(1.0f,1.0f,1.0f,0.6f));
	    			this.add(label);
	   			}
	   			
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

    			if (totalCalEat > totalCalBurn) scaleMaxCal = totalCalEat*scaleFactor;
    			else scaleMaxCal = totalCalBurn*scaleFactor;
    			
    			label = new JLabel("Today's Progress", JLabel.LEFT);
    			label.setFont(mainWindow.FONT_HELVETICA_NEUE_THIN.deriveFont(24.0f));
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
    			label.setFont(mainWindow.FONT_HELVETICA_NEUE_THIN.deriveFont(20.0f));
    			size = label.getPreferredSize();
    			label.setBounds(getWidth()/5/2, 112+120+120+(55-size.height)/2, size.width, size.height);
    			label.setForeground(new Color(119, 114, 255, 255));
    			this.add(label);
    			
    			g2.setStroke(new BasicStroke(2.0f));
    			
    			g2.setColor(new Color(119, 114, 255, 255));
				if (scaleMaxCal > 0) g2.drawLine((getWidth()/axisData.length)/2, 112+120+120+(55-size.height)/2 + size.height + 5, getWidth() - 1 - (int)((getWidth() - getWidth()/5/2 - 1)*(scaleMaxCal-totalCalEat)/scaleMaxCal), 112+120+120+(55-size.height)/2 + size.height + 5);

    			label = new JLabel("Calories Burned", JLabel.LEFT);
    			label.setFont(mainWindow.FONT_HELVETICA_NEUE_THIN.deriveFont(20.0f));
    			size = label.getPreferredSize();
    			label.setBounds(getWidth()*0 + getWidth()/5/2, 112+120+120+55+(55-size.height)/2, size.width, size.height);
    			label.setForeground(new Color(106, 185, 255, 255));
    			this.add(label);

    			g2.setColor(new Color(106, 185, 255, 255));
				if (scaleMaxCal > 0) g2.drawLine((getWidth()/5)/2, 112+120+120+55+(55-size.height)/2 - 5, getWidth() - 1 - (int)((getWidth() - getWidth()/5/2 - 1)*(scaleMaxCal-totalCalBurn)/scaleMaxCal), 112+120+120+55+(55-size.height)/2 - 5);
    			
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
    			label.setFont(mainWindow.FONT_HELVETICA_NEUE_THIN.deriveFont(20.0f));
    			size = label.getPreferredSize();
    			label.setBounds(getWidth()*0 + (getWidth()-size.width)/2, getHeight()-image.getHeight()+(31-size.height)/2, size.width, size.height);
    			label.setForeground(Color.WHITE);
    			this.add(label);
    		}
    	};
    	
    	if (this.mainWindow.getPreferences().getDashboardPanelsToggler(0)) {
			calorieTrackingPanel.setBounds(getDashboardPanelLocation(0).x, getDashboardPanelLocation(0).y, 1480/3, 800 - 75 - 150 - 20);
    		this.add(calorieTrackingPanel);
    		
    	}
    	
    	JPanel trainrFeedbackPanel = new JPanel() {
    		@Override
    		protected void paintComponent(Graphics g) {
    			//super.paintComponent(g);
    			
    			this.setLayout(null);
    			this.setOpaque(false);
    			this.setSize(1480/3, 800 - 75 - 150 - 20);
    	    	this.setLocation(getDashboardPanelLocation(1));
    	    	
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

    			JLabel label = new JLabel("Feedback", JLabel.LEFT);
    			label.setFont(mainWindow.FONT_HELVETICA_NEUE_THIN.deriveFont(30.0f));
    			Dimension size = label.getPreferredSize();
    			label.setBounds((getWidth() - size.width)/2, (52-size.height)/2, size.width, size.height);
    			label.setForeground(Color.WHITE);
    			this.add(label);

    			if (!mainWindow.getPreferences().isTutorialMode()) {
		   			//Display Feedback:
		   			//Milestone Progress Feedback:
		   			Feedback feedback = mainWindow.getVirtualTrainer().getmsFeedback();
		   			
		   			String output = null;
		   			if (feedback.getTextCode() == 1) output = feedback.getTXTone().getFirst();
		   			else if (feedback.getTextCode() == 2) output = feedback.getTXTone().getFirst();
		   			else output = null;
		   			
		   			label = new JLabel("<html><center>" + output + "</center></html>", JLabel.LEFT);
	    			label.setFont(mainWindow.FONT_HELVETICA_NEUE_THIN.deriveFont(22.0f));
	    			size = label.getPreferredSize();
	    			label.setBounds((getWidth() - size.width)/2, 52 + 15 + (100-size.height)/2, size.width, size.height);
	    			label.setForeground(new Color(1.0f,1.0f,1.0f,0.6f));
	    			this.add(label);
	    			
	    			if (feedback.getTextCode() == 2) {
	    				image = null;
	    				try {
	    					image = ImageIO.read(new File("UI/star-ms.png"));
	    				} catch (IOException e) {
	    					// TODO Auto-generated catch block
	    					e.printStackTrace();
	    				}
	    				//g2.drawImage(image, (getWidth()-400)/2, 52+15+100+(100-75)/2, null);
	    				
	    				output = String.format("%.1f", feedback.getFirstValues().get(2))+" lbs to go!";
	    	   			label = new JLabel(output, JLabel.LEFT);
	        			label.setFont(mainWindow.FONT_HELVETICA_NEUE_THIN.deriveFont(22.0f));
	        			size = label.getPreferredSize();
	    				g2.drawImage(image, (getWidth()-400)/2, 52+15+100+(100-75)/2 - size.height/2, null);
	        			label.setBounds(40, 52 + 15 + 100 + 100/2 - size.height - 3, size.width, size.height);
	        			label.setForeground(new Color(106, 185, 255, 255));
	        			this.add(label);
	        			g2.setColor(new Color(106, 185, 255, 255));
	        			g2.drawLine(40, 52 + 15 + 100 + 100/2, (getWidth()-40-50) - (int)((getWidth()-40*2-50)*feedback.getFirstValues().get(2)/2), 52 + 15 + 100 + 100/2);
	
	        			/*g2.setColor(Color.WHITE);
	        			g2.draw(new Ellipse2D.Double(getWidth()-40-50, 52 + 15 + 100 + (100-50)/2, 50, 50));
	        			
	        			label = new JLabel("MS", JLabel.LEFT);
	        			label.setFont(mainWindow.FONT_HELVETICA_NEUE_THIN.deriveFont(22.0f));
	        			size = label.getPreferredSize();
	        			label.setBounds(getWidth()-40-50 + (50-size.width)/2, 52+15+100 + (100-size.height)/2, size.width, size.height);
	        			label.setForeground(Color.WHITE);
	        			this.add(label);*/
	    			}
		   			
		   			//Feedback on user's current Calorie Difference for today:
		   			/*feedback = mainWindow.getVirtualTrainer().updateTodaysProgress(mainWindow.getDays().getLast());
		   			
		   			if(feedback.getTextCode() == 1) output = feedback.getTXTone().getFirst();
		   			else if (feedback.getTextCode() == 2) output = feedback.getTXTone().getFirst() + feedback.getFirstValues().get(2);
		   			else output = null;
		   			
		   			label = new JLabel("<html><center>" + output + "</center></html>", JLabel.LEFT);
	    			label.setFont(mainWindow.FONT_HELVETICA_NEUE_THIN.deriveFont(22.0f));
	    			size = label.getPreferredSize();
	    			label.setBounds((getWidth() - size.width)/2, 52+15+100+100 + (100-size.height)/2, size.width, size.height);
	    			label.setForeground(new Color(1.0f,1.0f,1.0f,0.6f));
	    			this.add(label);*/
		   			
		   			//Feedback on user's past week's performance:
		   			feedback = mainWindow.updateWeeklyProgress();
		   			
		   			if(feedback.getTextCode() == 1) output = feedback.getTXTone().getFirst();
		   			else if (feedback.getTextCode() == 2) output = feedback.getTXTone().getFirst() + feedback.getFirstValues().get(2);
		   			else output = null;
		   			
		   			label = new JLabel("<html><center>" + output + "</center></html>", JLabel.LEFT);
	    			label.setFont(mainWindow.FONT_HELVETICA_NEUE_THIN.deriveFont(22.0f));
	    			size = label.getPreferredSize();
	    			label.setBounds((getWidth() - size.width)/2, 52+15+100+50 + (200-size.height)/2, size.width, size.height);
	    			label.setForeground(new Color(1.0f,1.0f,1.0f,0.6f));
	    			this.add(label);

    				image = null;
    				try {
    					image = ImageIO.read(new File("UI/swag-arrow.png"));
    				} catch (IOException e) {
    					// TODO Auto-generated catch block
    					e.printStackTrace();
    				}
    				
    				int[] ret;
    				
    				ret = mainWindow.getPreferences().getTp().updateStreaks(mainWindow.getPreferences().getDays());
    				
    				if (ret[0] != 0) {
    					g2.drawImage(image, (getWidth()-400)/2, getHeight()-15-90, null);
    					if (ret[1] == 1) {
    			   			label = new JLabel("<html><center>Congratulations!<br>You broke your streak record!<br>You're on a " + ret[0] + " day streak!</center></html>", JLabel.LEFT);
    		    			label.setFont(mainWindow.FONT_HELVETICA_NEUE_THIN.deriveFont(22.0f));
    		    			size = label.getPreferredSize();
    		    			label.setBounds((getWidth()-size.width)/2, getHeight()-15-90 + (90-size.height)/2, size.width, size.height);
    		    			label.setForeground(new Color(1.0f,1.0f,1.0f,0.6f));
    		    			this.add(label);
    					}else {
    			   			label = new JLabel("You're on a " + ret[0] + " day streak!", JLabel.LEFT);
    		    			label.setFont(mainWindow.FONT_HELVETICA_NEUE_THIN.deriveFont(22.0f));
    		    			size = label.getPreferredSize();
    		    			label.setBounds((getWidth()-size.width)/2, getHeight()-15-90 + (90-size.height)/2, size.width, size.height);
    		    			label.setForeground(new Color(1.0f,1.0f,1.0f,0.6f));
    		    			this.add(label);
    					}
    				}
    			}
    		}
    	};
    	if (this.mainWindow.getPreferences().getDashboardPanelsToggler(1)) {
    		trainrFeedbackPanel.setBounds(getDashboardPanelLocation(1).x, getDashboardPanelLocation(1).y, 1480/3, 800 - 75 - 150 - 20);
    		this.add(trainrFeedbackPanel);
    		
    	}
    	
    	JPanel activityTrackingPanel = new JPanel() {
    		@Override
    		protected void paintComponent(Graphics g) {
    			//super.paintComponent(g);
    			
    			this.setLayout(null);
    			this.setOpaque(false);
    			this.setSize(1480/3, 800 - 75 - 150 - 20);
    	    	this.setLocation(getDashboardPanelLocation(2));
    	    	
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

    			Day lastDay = activityPanelDay;
    			JLabel label = new JLabel("Activity Tracking", JLabel.LEFT);
    			label.setFont(mainWindow.FONT_HELVETICA_NEUE_THIN.deriveFont(30.0f));
    			Dimension size = label.getPreferredSize();
    			label.setBounds((getWidth() - size.width)/2, (52-size.height)/2, size.width, size.height);
    			label.setForeground(Color.WHITE);
    			this.add(label);
    			
    			label = new JLabel("<html><div style='text-align:center;'>Active<br>Activity</div></html>", JLabel.CENTER);
    			label.setFont(mainWindow.FONT_HELVETICA_NEUE_THIN.deriveFont(25.0f));
    			size = label.getPreferredSize();
    			label.setBounds((getWidth()/3 - size.width)/2, 52 + (80-size.height)/2, size.width, size.height);
    			label.setForeground(Color.WHITE);
    			this.add(label);
    			
    			label = new JLabel("<html><div style='text-align:center;'>Sedentary<br>Activity</div></html>", JLabel.CENTER);
    			label.setFont(mainWindow.FONT_HELVETICA_NEUE_THIN.deriveFont(25.0f));
    			size = label.getPreferredSize();
    			label.setBounds(getWidth()*2/3 + (getWidth()/3 - size.width)/2, 52 + (80-size.height)/2, size.width, size.height);
    			label.setForeground(Color.WHITE);
    			this.add(label);
    			
    			if (mainWindow.getPreferences().getActivityTrackingPanelsToggler(0)) {
	    			int totalActiveMin = lastDay.getTotalActiveMin();
	    			int totalSedMin = lastDay.getTotalSedMin();
	
	    	        // draw Arc2D.Double
	    	        g2.setStroke(new BasicStroke(8.0f));
	    	        g2.setColor(Color.WHITE);
	    	        g2.draw(new Arc2D.Double((getWidth()-100)/2, 52+80, 100, 100, 90, 360, Arc2D.OPEN));
	    	        
	    	        g2.setStroke(new BasicStroke(12.0f));
	    	        g2.setPaint(new GradientPaint(0, 0, new Color(143, 183, 238, 255),
	    	        		0, getHeight() / 2, new Color(84, 142, 250, 255)));
	    	        if ((totalActiveMin+totalSedMin) != 0) g2.draw(new Arc2D.Double((getWidth()-100)/2, 52+80, 100, 100, 180+(360*totalActiveMin/(totalActiveMin+totalSedMin)/2), 360*totalSedMin/(totalActiveMin+totalSedMin), Arc2D.OPEN));
	
	    			g2.setColor(new Color(1.0f,1.0f,1.0f,0.4f));
	    	        g2.setStroke(new BasicStroke(2.0f));
	    	        
	    			label = new JLabel(totalActiveMin + "", JLabel.CENTER);
	    			label.setFont(mainWindow.FONT_HELVETICA_NEUE_THIN.deriveFont(30.0f));
	    			size = label.getPreferredSize();
	    			label.setBounds((getWidth()/3 - size.width)/2, 52+80 + (100-size.height)/2, size.width, size.height);
	    			label.setForeground(Color.WHITE);
	    			this.add(label);
	
	    			g2.drawLine((getWidth()/3 - size.width)/2 + size.width + 10, 52+80 + (100-2)/2, (getWidth()-100)/2 - 10 - 6, 52+80 + (100-2)/2);
	    			
	    			label = new JLabel("min", JLabel.CENTER);
	    			label.setFont(mainWindow.FONT_HELVETICA_NEUE_THIN.deriveFont(18.0f));
	    			Dimension size2 = label.getPreferredSize();
	    			label.setBounds((getWidth()/3 - size2.width)/2, 52+80 + (100-size.height)/2 + size.height + 1, size2.width, size2.height);
	    			label.setForeground(Color.WHITE);
	    			this.add(label);
	    			
	    			label = new JLabel(totalSedMin + "", JLabel.CENTER);
	    			label.setFont(mainWindow.FONT_HELVETICA_NEUE_THIN.deriveFont(30.0f));
	    			size = label.getPreferredSize();
	    			label.setBounds(getWidth()*2/3 + (getWidth()/3 - size.width)/2, 52+80 + (100-size.height)/2, size.width, size.height);
	    			label.setForeground(Color.WHITE);
	    			this.add(label);
	    			
	    			g2.drawLine((getWidth()-100)/2 + 100 + 10 + 6, 52+80 + (100-2)/2, getWidth()*2/3 + (getWidth()/3 - size.width)/2 - 10, 52+80 + (100-2)/2);
	    			
	    			label = new JLabel("min", JLabel.CENTER);
	    			label.setFont(mainWindow.FONT_HELVETICA_NEUE_THIN.deriveFont(18.0f));
	    			size2 = label.getPreferredSize();
	    			label.setBounds(getWidth()*2/3 + (getWidth()/3 - size2.width)/2, 52+80 + (100-size.height)/2 + size.height + 1, size2.width, size2.height);
	    			label.setForeground(Color.WHITE);
	    			this.add(label);
    			}
    			
    			if (mainWindow.getPreferences().getActivityTrackingPanelsToggler(1)) {
	    	        image = null;
					try {
						image = ImageIO.read(new File("UI/heart-outline.png"));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					g2.drawImage(image, (getWidth()-image.getWidth())/2, 52+80+getActivityTrackingPanelsLocationY(1) + (160 - image.getHeight())/2, null);
	
	    			g2.setColor(new Color(1.0f,1.0f,1.0f,0.4f));
	    	        g2.setStroke(new BasicStroke(2.0f));
	    			
	    			label = new JLabel(lastDay.getActiveHR() + "", JLabel.CENTER);
	    			label.setFont(mainWindow.FONT_HELVETICA_NEUE_THIN.deriveFont(30.0f));
	    			size = label.getPreferredSize();
	    			label.setBounds((getWidth()/3 - size.width)/2, 52+80+getActivityTrackingPanelsLocationY(1) + (160-size.height)/2, size.width, size.height);
	    			label.setForeground(Color.WHITE);
	    			this.add(label);
	
	    			g2.drawLine((getWidth()/3 - size.width)/2 + size.width + 10, 52+80+getActivityTrackingPanelsLocationY(1) + (160-2)/2, (getWidth()-image.getWidth())/2 - 10, 52+80+getActivityTrackingPanelsLocationY(1) + (160-2)/2);
	    			
	    			label = new JLabel("BPM", JLabel.CENTER);
	    			label.setFont(mainWindow.FONT_HELVETICA_NEUE_THIN.deriveFont(18.0f));
	    			Dimension size2 = label.getPreferredSize();
	    			label.setBounds((getWidth()/3 - size2.width)/2, 52+80+getActivityTrackingPanelsLocationY(1) + (160-size.height)/2 + size.height + 1, size2.width, size2.height);
	    			label.setForeground(Color.WHITE);
	    			this.add(label);
	    			
	    			label = new JLabel(lastDay.getRestingHR() + "", JLabel.CENTER);
	    			label.setFont(mainWindow.FONT_HELVETICA_NEUE_THIN.deriveFont(30.0f));
	    			size = label.getPreferredSize();
	    			label.setBounds(getWidth()*2/3 + (getWidth()/3 - size.width)/2, 52+80+getActivityTrackingPanelsLocationY(1) + (160-size.height)/2, size.width, size.height);
	    			label.setForeground(Color.WHITE);
	    			this.add(label);
	
	    			g2.drawLine((getWidth()-image.getWidth())/2 + image.getWidth() + 10, 52+80+getActivityTrackingPanelsLocationY(1) + (160-2)/2, getWidth()*2/3 + (getWidth()/3 - size.width)/2 - 10, 52+80+getActivityTrackingPanelsLocationY(1) + (160-2)/2);
	    			
	    			label = new JLabel("BPM", JLabel.CENTER);
	    			label.setFont(mainWindow.FONT_HELVETICA_NEUE_THIN.deriveFont(18.0f));
	    			size2 = label.getPreferredSize();
	    			label.setBounds(getWidth()*2/3 + (getWidth()/3 - size2.width)/2, 52+80+getActivityTrackingPanelsLocationY(1) + (160-size.height)/2 + size.height + 1, size2.width, size2.height);
	    			label.setForeground(Color.WHITE);
	    			this.add(label);
    			}
				
    			if (mainWindow.getPreferences().getActivityTrackingPanelsToggler(2)) {
	    			int totalFloors = lastDay.getTotalFloors();
	    			int totalSteps = lastDay.getTotalSteps();
	    			int totalDist = lastDay.getTotalDist();
	    			
	    			Feedback feedback = lastDay.todaysProgess();
	    			int totalCal = Math.round(feedback.getFirstValues().get(1));
	    			
					int arcSize = (getWidth() - 20*(4+1))/4;
					
	    			if (mainWindow.getPreferences().getLifetimeTotalsToggler(0)) {
		    	        // draw Arc2D.Double
		    	        g2.setStroke(new BasicStroke(1.0f));
		    	        g2.setColor(Color.WHITE);
		    	        g2.draw(new Ellipse2D.Double(getLifetimeTotalLocationX(0), 52+80+getActivityTrackingPanelsLocationY(2), arcSize, arcSize));
		
		    			label = new JLabel(totalDist + "", JLabel.LEFT);
		    			label.setFont(mainWindow.FONT_HELVETICA_NEUE_THIN.deriveFont(20.0f));
		    			size = label.getPreferredSize();
		    			label.setBounds(getLifetimeTotalLocationX(0) + (arcSize-size.width)/2, 52+80+getActivityTrackingPanelsLocationY(2) + (arcSize*2/3 - size.height)/2, size.width, size.height);
		    			label.setForeground(Color.WHITE);
		    			this.add(label);
		    			
		    			label = new JLabel("Km", JLabel.LEFT);
		    			label.setFont(mainWindow.FONT_HELVETICA_NEUE_THIN.deriveFont(18.0f));
		    			size = label.getPreferredSize();
		    			label.setBounds(getLifetimeTotalLocationX(0) + (arcSize-size.width)/2, 52+80+getActivityTrackingPanelsLocationY(2) + arcSize/2 + (arcSize/2 - size.height - 6)/2, size.width, size.height);
		    			label.setForeground(Color.WHITE);
		    			this.add(label);
	    			}
	
	    			if (mainWindow.getPreferences().getLifetimeTotalsToggler(1)) {
		    	        // draw Arc2D.Double
		    	        g2.setStroke(new BasicStroke(1.0f));
		    	        g2.setColor(Color.WHITE);
		    	        g2.draw(new Ellipse2D.Double(getLifetimeTotalLocationX(1), 52+80+getActivityTrackingPanelsLocationY(2), arcSize, arcSize));
		
		    			label = new JLabel(totalSteps + "", JLabel.LEFT);
		    			label.setFont(mainWindow.FONT_HELVETICA_NEUE_THIN.deriveFont(20.0f));
		    			size = label.getPreferredSize();
		    			label.setBounds(getLifetimeTotalLocationX(1) + (arcSize-size.width)/2, 52+80+getActivityTrackingPanelsLocationY(2) + (arcSize*2/3 - size.height)/2, size.width, size.height);
		    			label.setForeground(Color.WHITE);
		    			this.add(label);
		    			
		    			label = new JLabel("Steps", JLabel.LEFT);
		    			label.setFont(mainWindow.FONT_HELVETICA_NEUE_THIN.deriveFont(18.0f));
		    			size = label.getPreferredSize();
		    			label.setBounds(getLifetimeTotalLocationX(1) + (arcSize-size.width)/2, 52+80+getActivityTrackingPanelsLocationY(2) + arcSize/2 + (arcSize/2 - size.height - 6)/2, size.width, size.height);
		    			label.setForeground(Color.WHITE);
		    			this.add(label);
	    			}
	    			
	    			if (mainWindow.getPreferences().getLifetimeTotalsToggler(2)) {
		    	        // draw Arc2D.Double
		    	        g2.setStroke(new BasicStroke(1.0f));
		    	        g2.setColor(Color.WHITE);
		    	        g2.draw(new Ellipse2D.Double(getLifetimeTotalLocationX(2), 52+80+getActivityTrackingPanelsLocationY(2), arcSize, arcSize));
		
		    			label = new JLabel(totalFloors + "", JLabel.LEFT);
		    			label.setFont(mainWindow.FONT_HELVETICA_NEUE_THIN.deriveFont(20.0f));
		    			size = label.getPreferredSize();
		    			label.setBounds(getLifetimeTotalLocationX(2) + (arcSize-size.width)/2, 52+80+getActivityTrackingPanelsLocationY(2) + (arcSize*2/3 - size.height)/2, size.width, size.height);
		    			label.setForeground(Color.WHITE);
		    			this.add(label);
		    			
		    			label = new JLabel("Floors", JLabel.LEFT);
		    			label.setFont(mainWindow.FONT_HELVETICA_NEUE_THIN.deriveFont(18.0f));
		    			size = label.getPreferredSize();
		    			label.setBounds(getLifetimeTotalLocationX(2) + (arcSize-size.width)/2, 52+80+getActivityTrackingPanelsLocationY(2) + arcSize/2 + (arcSize/2 - size.height - 6)/2, size.width, size.height);
		    			label.setForeground(Color.WHITE);
		    			this.add(label);
	    			}
	
	    			if (mainWindow.getPreferences().getLifetimeTotalsToggler(3)) {
		    	        // draw Arc2D.Double
		    	        g2.setStroke(new BasicStroke(1.0f));
		    	        g2.setColor(Color.WHITE);
		    	        g2.draw(new Ellipse2D.Double(getLifetimeTotalLocationX(3), 52+80+getActivityTrackingPanelsLocationY(2), arcSize, arcSize));
		    	        
		    			label = new JLabel(totalCal + "", JLabel.LEFT);
		    			label.setFont(mainWindow.FONT_HELVETICA_NEUE_THIN.deriveFont(20.0f));
		    			size = label.getPreferredSize();
		    			label.setBounds(getLifetimeTotalLocationX(3) + (arcSize-size.width)/2, 52+80+getActivityTrackingPanelsLocationY(2) + (arcSize*2/3 - size.height)/2, size.width, size.height);
		    			label.setForeground(Color.WHITE);
		    			this.add(label);
		    			
		    			label = new JLabel("<html><p style='text-align:center;'>Calories<br>Burned</p></html>", JLabel.LEFT);
		    			label.setFont(mainWindow.FONT_HELVETICA_NEUE_THIN.deriveFont(16.0f));
		    			size = label.getPreferredSize();
		    			label.setBounds(getLifetimeTotalLocationX(3) + (arcSize-size.width)/2, 52+80+getActivityTrackingPanelsLocationY(2) + arcSize/2 + (arcSize/2 - size.height - 6)/2, size.width, size.height);
		    			label.setForeground(Color.WHITE);
		    			this.add(label);
	    			}
    			}
    			
    			navActivities[0] = new JButton("<");
    			navActivities[0].setFont(mainWindow.FONT_HELVETICA_NEUE_THIN.deriveFont(30.0f));
    			navActivities[0].setBackground(null);
    			navActivities[0].setBorder(null);
    			navActivities[0].setFocusPainted(false);
    			navActivities[0].setMargin(new Insets(0, 0, 0, 0));
    			navActivities[0].setContentAreaFilled(false);
    			navActivities[0].setBorderPainted(false);
    			navActivities[0].setOpaque(false);
    			navActivities[0].setForeground(new Color(255,255,255,220));
    			navActivities[0].setFocusable(false);
    			size = navActivities[0].getPreferredSize();
    			navActivities[0].setBounds(60, getHeight() - 50 + (50-size.height)/2, size.width, size.height);
    	        navActivities[0].addActionListener(new ButtonActionListener(29, 0, mainWindow));
    	        this.add(navActivities[0]);
    			
    			activityDayLabel = new JLabel(MainWindow.fmActivityDay.format(getActivityPanelDay().getDate().getTime()));
    			activityDayLabel.setFont(mainWindow.FONT_HELVETICA_NEUE_THIN.deriveFont(30.0f));
    			size = activityDayLabel.getPreferredSize();
    			activityDayLabel.setBounds((getWidth() - size.width)/2, getHeight() - 50 + (50-size.height)/2, size.width, size.height);
    			activityDayLabel.setForeground(Color.WHITE);
    			this.add(activityDayLabel);
    	        
    			navActivities[1] = new JButton(">");
    			navActivities[1].setFont(mainWindow.FONT_HELVETICA_NEUE_THIN.deriveFont(30.0f));
    			navActivities[1].setBackground(null);
    			navActivities[1].setBorder(null);
    			navActivities[1].setFocusPainted(false);
    			navActivities[1].setMargin(new Insets(0, 0, 0, 0));
    			navActivities[1].setContentAreaFilled(false);
    			navActivities[1].setBorderPainted(false);
    			navActivities[1].setOpaque(false);
    			navActivities[1].setForeground(new Color(255,255,255,220));
    			navActivities[1].setFocusable(false);
    			size = navActivities[1].getPreferredSize();
    			navActivities[1].setBounds(getWidth() - 60 - size.width, getHeight() - 50 + (50-size.height)/2, size.width, size.height);
    	        navActivities[1].addActionListener(new ButtonActionListener(29, 1, mainWindow));
    	        this.add(navActivities[1]);
    	        
    	        // reset stroke to default
    	        g2.setStroke(new BasicStroke(1.0f));
    		}
    	};
    	if (this.mainWindow.getPreferences().getDashboardPanelsToggler(2)) {
    		activityTrackingPanel.setBounds(getDashboardPanelLocation(2).x, getDashboardPanelLocation(2).y, 1480/3, 800 - 75 - 150 - 20);
    		this.add(activityTrackingPanel);
    		
    	}
	}
    
    public Point getDashboardPanelLocation(int index) {
    	int x = 0;
    	Preferences prefs = this.mainWindow.getPreferences();

    	if(prefs.getDashboardPanelsToggler(0) && index > 0) x += getWidth()/3;
    	if(prefs.getDashboardPanelsToggler(1) && index > 1) x += getWidth()/3;
    	
    	return new Point((getWidth() - getWidth()*dashboardPanels/3)/2 + x,75 + 150);
    }

    public int getLifetimeTotalLocationX(int index) {
    	int x = 0;
        
		int arcSize = (getWidth()/3 - 20*(4+1))/4;
		
    	Preferences prefs = this.mainWindow.getPreferences();

    	if(prefs.getLifetimeTotalsToggler(0) && index > 0) x += 20 + arcSize;
    	if(prefs.getLifetimeTotalsToggler(1) && index > 1) x += 20 + arcSize;
    	if(prefs.getLifetimeTotalsToggler(2) && index > 2) x += 20 + arcSize;
    	
    	return ((getWidth()/3 - (arcSize*lifetimeTotals + 20*(lifetimeTotals-1)))/2 + x);
    }

    public int getActivityTrackingPanelsLocationY(int index) {
    	int y = 0;
		
    	Preferences prefs = this.mainWindow.getPreferences();

    	if(prefs.getActivityTrackingPanelsToggler(0) && index > 0) y += 100;
    	if(prefs.getActivityTrackingPanelsToggler(1) && index > 1) y += 160;
    	
    	return y;
    }

    /**
     * Dashboard Banner
     * @return
     */
    public JPanel getBannerPanel() { return this.bannerPanel; }
    
    /**
     * Dashboard Refresh Button Last-up-date Time Description Panel
     * @return
     */
    public JLabel getRefreshDesc() { return this.refreshDesc; }
    
	/* (non-Javadoc)
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 */
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		this.setLayout(null);
		this.setOpaque(false);
		this.setSize(1480,800);
    	this.setLocation(0,0);
		
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		BufferedImage bgImage = null;
		try {
			if (mainWindow.getPreferences().isTutorialMode()) bgImage = ImageIO.read(new File("UI/bg-tut.png"));
			else bgImage = ImageIO.read(new File("UI/bg.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		g2.drawImage(bgImage, 0, 0, getWidth(), getHeight(), 0, 0, bgImage.getWidth(), bgImage.getHeight(), null);
	}

	public void setActivityPanelPrevDay() {
		this.setActivityPanelDayIndex(this.getActivityPanelDayIndex() - 1);
		if(this.getActivityPanelDayIndex() < 0) this.setActivityPanelDayIndex(0);
	}

	public void setActivityPanelNextDay() {
		this.setActivityPanelDayIndex(this.getActivityPanelDayIndex() + 1);
		if(this.getActivityPanelDayIndex() > 6) this.setActivityPanelDayIndex(6);
	}

	public Day getActivityPanelDay() {
		return activityPanelDay;
	}

	public void setActivityPanelDay(Day activityPanelDay) {
		this.activityPanelDay = activityPanelDay;
	}

	public int getActivityPanelDayIndex() {
		return activityPanelDayIndex;
	}

	public void setActivityPanelDayIndex(int activityPanelDayIndex) {
		this.activityPanelDayIndex = activityPanelDayIndex;
	}
}