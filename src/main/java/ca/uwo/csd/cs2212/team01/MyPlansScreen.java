package ca.uwo.csd.cs2212.team01;

import java.awt.Color;
import java.awt.Dimension;
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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class MyPlansScreen extends JPanel implements Serializable{
	private static final long serialVersionUID = 1L;
	private MainWindow mainWindow;
	
	private int weekIndex;
	
	private JLabel[] dayOfWeekLabel, mealsLabel, workoutsLabel, calDeficitLabel, feedbackLabel;
	
	private JButton prevWeek, nextWeek;
	private JButton[] editPlanBtn;
	private JButton[][] editBtnNoPlan, editBtn;
	
	/**
	 * Class Constructor
	 * @param mainWindow
	 */
	public MyPlansScreen(MainWindow mainWindow) {
		this.mainWindow = mainWindow;
		
		this.weekIndex = 0;

		this.setLayout(null);
		this.setOpaque(false);
    	this.setLocation(0, 0);
    	this.setSize(1480, 800);
    	
    	dayOfWeekLabel = new JLabel[7];
    	editPlanBtn = new JButton[7];
    	editBtnNoPlan = new JButton[2][7];
    	editBtn = new JButton[2][7];

    	mealsLabel = new JLabel[7];
    	workoutsLabel = new JLabel[7];
    	calDeficitLabel = new JLabel[7];
    	feedbackLabel = new JLabel[7];
    	
		this.initUI();
	}
	
    /**
     * Renders the Dashboard Screen
     */
    private void initUI() {
    	JPanel bannerPanel = new JPanel() {
    		@Override
    		protected void paintComponent(Graphics g) {
    			//super.paintComponent(g);
    			
    			this.setLayout(null);
    			this.setOpaque(false);
    	    	this.setLocation(0, 0);
    			
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
    			g2.drawImage(image, 0, 0, image.getWidth(), image.getHeight(), null);
    			
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
    		    
    			JLabel label = new JLabel("My Plans");
    			label.setFont(mainWindow.FONT_HELVETICA_NEUE_THIN.deriveFont(30.0f));
    			label.setForeground(new Color(255,255,255,200));
    			Dimension size = label.getPreferredSize();
    			label.setBounds((getWidth()-size.width)/2, (getHeight()-size.height)/2, size.width, size.height);
    			this.add(label);
    	        
    			image = null;
    			try {
    				image = ImageIO.read(new File("UI/exit-icon.png"));
    			} catch (IOException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
    			
    			JButton exitBtn = new JButton("X");
    			exitBtn.setFont(mainWindow.FONT_HELVETICA_NEUE_THIN.deriveFont(25.0f));
    			exitBtn.setBackground(null);
    			exitBtn.setBorder(null);
    			exitBtn.setFocusPainted(false);
    			exitBtn.setMargin(new Insets(0, 0, 0, 0));
    			exitBtn.setContentAreaFilled(false);
    			exitBtn.setBorderPainted(false);
    			exitBtn.setOpaque(false);
    			exitBtn.setForeground(new Color(255,255,255,220));
    			exitBtn.setFocusable(false);
    			size = exitBtn.getPreferredSize();
    			exitBtn.setBounds(getWidth()-size.width-12, 6, size.width, size.height);
    	        exitBtn.addActionListener(new ButtonActionListener(2, 0, mainWindow));
    	        this.add(exitBtn);
    		}
    	};
    	bannerPanel.setBounds(0, 0, getWidth(), 75);
    	this.add(bannerPanel);
		
		prevWeek = new JButton();
		prevWeek.setFont(mainWindow.FONT_HELVETICA_NEUE_THIN.deriveFont(20.0f));
		prevWeek.setBackground(null);
		prevWeek.setBorder(null);
		prevWeek.setFocusPainted(false);
		prevWeek.setMargin(new Insets(0, 0, 0, 0));
		prevWeek.setContentAreaFilled(false);
		prevWeek.setBorderPainted(false);
		prevWeek.setOpaque(false);
		prevWeek.setForeground(new Color(255,255,255,220));
		prevWeek.setFocusable(false);
		//Dimension size = prevWeek.getPreferredSize();
		//prevWeek.setBounds(15, 75+15, (275-30-40)/2, 35);
        prevWeek.addActionListener(new ButtonActionListener(15, 0, mainWindow));
        this.add(prevWeek);
		
		nextWeek = new JButton();
		nextWeek.setFont(mainWindow.FONT_HELVETICA_NEUE_THIN.deriveFont(20.0f));
		nextWeek.setBackground(null);
		nextWeek.setBorder(null);
		nextWeek.setFocusPainted(false);
		nextWeek.setMargin(new Insets(0, 0, 0, 0));
		nextWeek.setContentAreaFilled(false);
		nextWeek.setBorderPainted(false);
		nextWeek.setOpaque(false);
		nextWeek.setForeground(new Color(255,255,255,220));
		nextWeek.setFocusable(false);
		//Dimension size = nextWeek.getPreferredSize();
		//nextWeek.setBounds(275 - 15 - (275-30-40)/2, 75+15, (275-30-40)/2, 35);
        nextWeek.addActionListener(new ButtonActionListener(15, 1, mainWindow));
        this.add(nextWeek);
    	
    	for(int i = 0; i < 7; i++) {
			dayOfWeekLabel[i] = new JLabel();
			dayOfWeekLabel[i].setFont(mainWindow.FONT_HELVETICA_NEUE_THIN.deriveFont(30.0f));
			dayOfWeekLabel[i].setForeground(new Color(255,255,255,200));
			this.add(dayOfWeekLabel[i]);
	    	
	    	mealsLabel[i] = new JLabel();
	    	mealsLabel[i].setFont(mainWindow.FONT_HELVETICA_NEUE_THIN.deriveFont(25.0f));
	    	mealsLabel[i].setForeground(new Color(255,255,255,200));
	    	
	    	workoutsLabel[i] = new JLabel();
	    	workoutsLabel[i].setFont(mainWindow.FONT_HELVETICA_NEUE_THIN.deriveFont(25.0f));
	    	workoutsLabel[i].setForeground(new Color(255,255,255,200));
	    	
	    	calDeficitLabel[i] = new JLabel();
	    	calDeficitLabel[i].setFont(mainWindow.FONT_HELVETICA_NEUE_THIN.deriveFont(25.0f));
	    	calDeficitLabel[i].setForeground(new Color(180,180,180,200));
			
			editPlanBtn[i] = new JButton();
			editPlanBtn[i].setHorizontalAlignment(SwingConstants.CENTER);
			editPlanBtn[i].setFont(mainWindow.FONT_HELVETICA_NEUE_THIN.deriveFont(18.0f));
			editPlanBtn[i].setBackground(null);
			editPlanBtn[i].setBorder(null);
			editPlanBtn[i].setFocusPainted(false);
			editPlanBtn[i].setMargin(new Insets(0, 0, 0, 0));
			editPlanBtn[i].setContentAreaFilled(false);
			editPlanBtn[i].setBorderPainted(false);
			editPlanBtn[i].setOpaque(false);
			editPlanBtn[i].setForeground(new Color(255,255,255,220));
			editPlanBtn[i].setFocusable(false);
			//Dimension size = editPlanBtn[i].getPreferredSize();
			//editPlanBtn[i].setBounds(200 + (75-55)/2,  75+75+i*(60+30) + (60-35)/2, 55, 35);
	        editPlanBtn[i].addActionListener(new ButtonActionListener(16, i, mainWindow));
	        this.add(editPlanBtn[i]);
			
			editBtnNoPlan[0][i] = new JButton();
			editBtnNoPlan[0][i].setBackground(null);
			editBtnNoPlan[0][i].setBorder(null);
			editBtnNoPlan[0][i].setFocusPainted(false);
			editBtnNoPlan[0][i].setMargin(new Insets(0, 0, 0, 0));
			editBtnNoPlan[0][i].setContentAreaFilled(false);
			editBtnNoPlan[0][i].setBorderPainted(false);
			editBtnNoPlan[0][i].setOpaque(false);
			editBtnNoPlan[0][i].setFocusable(false);
	        editBtnNoPlan[0][i].addActionListener(new ButtonActionListener(17, i, mainWindow));
	        //this.add(editBtnNoPlan[0][i]);
			
			editBtnNoPlan[1][i] = new JButton();
			editBtnNoPlan[1][i].setBackground(null);
			editBtnNoPlan[1][i].setBorder(null);
			editBtnNoPlan[1][i].setFocusPainted(false);
			editBtnNoPlan[1][i].setMargin(new Insets(0, 0, 0, 0));
			editBtnNoPlan[1][i].setContentAreaFilled(false);
			editBtnNoPlan[1][i].setBorderPainted(false);
			editBtnNoPlan[1][i].setOpaque(false);
			editBtnNoPlan[1][i].setFocusable(false);
	        editBtnNoPlan[1][i].addActionListener(new ButtonActionListener(19, i, mainWindow));
	        //this.add(editBtnNoPlan[1][i]);
			
			editBtn[0][i] = new JButton();
			editBtn[0][i].setBackground(null);
			editBtn[0][i].setBorder(null);
			editBtn[0][i].setFocusPainted(false);
			editBtn[0][i].setMargin(new Insets(0, 0, 0, 0));
			editBtn[0][i].setContentAreaFilled(false);
			editBtn[0][i].setBorderPainted(false);
			editBtn[0][i].setOpaque(false);
			editBtn[0][i].setFocusable(false);
	        editBtn[0][i].addActionListener(new ButtonActionListener(17, i, mainWindow));
	        //this.add(editBtn[0][i]);
			
			editBtn[1][i] = new JButton();
			editBtn[1][i].setBackground(null);
			editBtn[1][i].setBorder(null);
			editBtn[1][i].setFocusPainted(false);
			editBtn[1][i].setMargin(new Insets(0, 0, 0, 0));
			editBtn[1][i].setContentAreaFilled(false);
			editBtn[1][i].setBorderPainted(false);
			editBtn[1][i].setOpaque(false);
			editBtn[1][i].setFocusable(false);
	        editBtn[1][i].addActionListener(new ButtonActionListener(18, i, mainWindow));
	        //this.add(editBtn[1][i]);
	    	
	    	feedbackLabel[i] = new JLabel();
	    	feedbackLabel[i].setFont(mainWindow.FONT_HELVETICA_NEUE_THIN.deriveFont(20.0f));
	    	feedbackLabel[i].setForeground(new Color(255,255,255,200));
    	}
	    
		JLabel label = new JLabel("Feedback");
		label.setFont(mainWindow.FONT_HELVETICA_NEUE_THIN.deriveFont(30.0f));
		Dimension size = label.getPreferredSize();
		label.setBounds(275 + 600 + 25 + 30 + (getWidth()-(275 + 600 + 25) - 60 - size.width)/2, 75+15 + (60-size.height)/2, size.width, size.height);
		label.setForeground(new Color(255,255,255,200));
		this.add(label);
    }
    
    public void prevWeek() {
    	if (this.getWeekIndex() > 0) {
	    	this.setWeekIndex(this.getWeekIndex() - 1);
	    	this.repaint();
    	}
    }
    
    public void nextWeek() {
    	if (this.getWeekIndex() < 2) {
	    	this.setWeekIndex(this.getWeekIndex() + 1);
	    	this.repaint();
    	}
    }
    
    private void removeListFromPanel() {
    	for(int i = 0; i < 7; i++) {
    		//this.remove(editPlanBtn[i]);
    		this.remove(editBtnNoPlan[0][i]);
    		this.remove(editBtnNoPlan[1][i]);
    		
    		this.remove(editBtn[0][i]);
    		this.remove(editBtn[1][i]);

    		this.remove(mealsLabel[i]);
    		this.remove(workoutsLabel[i]);
    		this.remove(calDeficitLabel[i]);
    		
    		this.remove(feedbackLabel[i]);
    	}
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
		
    	removeListFromPanel();
    	
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
			image = ImageIO.read(new File("UI/day-sum-bg.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		BufferedImage addicon = null;
		try {
			addicon = ImageIO.read(new File("UI/add-icon.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		BufferedImage refreshicon = null;
		try {
			refreshicon = ImageIO.read(new File("UI/refresh-icon.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		BufferedImage exclmark = null;
		try {
			exclmark = ImageIO.read(new File("UI/exclmark.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		BufferedImage checkmark = null;
		try {
			checkmark = ImageIO.read(new File("UI/checkmark.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Day day;
		Plan plan;
		Dimension size;
		
		g2.setColor(new Color(255,255,255,150));
		g2.drawLine(15 + (275-30-40)/2, 75+15 + 35/2, 275 - 15 - (275-30-40)/2, 75+15 + 35/2);
		
		g2.drawLine(275 + image.getWidth() + 25, 75+60, 275 + image.getWidth() + 25, getHeight()-15);
		
		boolean goodWeek = true;
		
		for(int i = 0; i < 7; i++) {
			if (getWeekIndex() == 0) {
				if (i == 0) day = this.mainWindow.getDays().getLast();
				else day = this.mainWindow.getFutureDays().get(i+7*getWeekIndex()-1);
			}else day = this.mainWindow.getFutureDays().get(i+7*getWeekIndex()-1);
			
			if (i == 0) {
				prevWeek.setText((getWeekIndex() <= 0 ? "": "< ") + MainWindow.fmDay.format(day.getDate()));
				prevWeek.setBounds(15, 75+15, (275-30-40)/2, 35);
			}else if (i == 6) {
				nextWeek.setText(MainWindow.fmDay.format(day.getDate()) + (getWeekIndex() >= 2 ? "": " >"));
				nextWeek.setBounds(275 - 15 - (275-30-40)/2, 75+15, (275-30-40)/2, 35);
			}
			
			plan = day.getPlan();
			
			dayOfWeekLabel[i].setText(MainWindow.fmDayofWeek.format(day.getDate()));
			size = dayOfWeekLabel[i].getPreferredSize();
			dayOfWeekLabel[i].setBounds((190-size.width)/2, 75+75+i*(60+30) + (60-size.height)/2, size.width, size.height);

			g2.drawRoundRect(275 - 75 - 10, 75+75+i*(60+30) + (60-30)/2, 75, 30, 15, 15);
			
			g2.drawImage(image, 275, 75+75+i*(60+30), null);
			
			if (plan == null) {
				editPlanBtn[i].setText("No Plan");
				editPlanBtn[i].setBounds(275 - 75 - 10,  75+75+i*(60+30) + (60-30)/2, 75, 30);
				
				g2.drawImage(addicon, 275 + 15, 75+75+i*(60+30) + (60-30)/2, null);
		        editBtnNoPlan[0][i].setBounds(275 + 15,  75+75+i*(60+30) + (60-30)/2, 30, 30);
		        this.add(editBtnNoPlan[0][i]);
		        
				g2.drawImage(refreshicon, 275 + 30 + 30, 75+75+i*(60+30) + (60-30)/2, null);
		        editBtnNoPlan[1][i].setBounds(275 + 30 + 30,  75+75+i*(60+30) + (60-30)/2, 30, 30);
		        this.add(editBtnNoPlan[1][i]);
		        
		        feedbackLabel[i].setText("< This day needs a plan");
				size = feedbackLabel[i].getPreferredSize();
		    	feedbackLabel[i].setBounds(275 + 600 + 25 + 30, 75+75+i*(60+30) + (60-size.height)/2, size.width, size.height);
		    	this.add(feedbackLabel[i]);
			}else {
				editPlanBtn[i].setText("Edit");
				editPlanBtn[i].setBounds(275 - 75 - 10,  75+75+i*(60+30) + (60-30)/2, 75, 30);
				
				mealsLabel[i].setText(plan.getMeals().size() + " Meals");
				size = mealsLabel[i].getPreferredSize();
				mealsLabel[i].setBounds(275 + 125 - size.width - 15,  75+75+i*(60+30) + (60-size.height)/2, size.width, size.height);
				this.add(mealsLabel[i]);
				
				g2.drawImage(addicon, 275 + 125, 75+75+i*(60+30) + (60-30)/2, null);
				editBtn[0][i].setBounds(275 + 125,  75+75+i*(60+30) + (60-30)/2, 30, 30);
		        this.add(editBtn[0][i]);
		        
		        g2.drawLine(275 + 125 + 30 + 15, 75+75+i*(60+30) + 60/2, 275 + 125 + 30 + 50 - 15, 75+75+i*(60+30) + 60/2);
				
				workoutsLabel[i].setText(plan.getWorkouts().size() + " Workouts");
				size = workoutsLabel[i].getPreferredSize();
				workoutsLabel[i].setBounds(275 + 125 + 30 + 50 + 150 - 15 - size.width,  75+75+i*(60+30) + (60-size.height)/2, size.width, size.height);
				this.add(workoutsLabel[i]);
				
				g2.drawImage(addicon, 275 + 125 + 30 + 50 + 150, 75+75+i*(60+30) + (60-30)/2, null);
				editBtn[1][i].setBounds(275 + 125 + 30 + 50 + 150,  75+75+i*(60+30) + (60-30)/2, 30, 30);
		        this.add(editBtn[1][i]);

		        g2.drawLine(275 + 125 + 30 + 50 + 150 + 30 + 15, 75+75+i*(60+30) + 60/2 - 3, 275 + 125 + 30 + 50 + 150 + 30 + 50 - 15, 75+75+i*(60+30) + 60/2 - 3);
		        g2.drawLine(275 + 125 + 30 + 50 + 150 + 30 + 15, 75+75+i*(60+30) + 60/2 + 3, 275 + 125 + 30 + 50 + 150 + 30 + 50 - 15, 75+75+i*(60+30) + 60/2 + 3);
				
				calDeficitLabel[i].setText(plan.getPredictedCalorieBurn() + " cal");
				size = calDeficitLabel[i].getPreferredSize();
				calDeficitLabel[i].setBounds(275 + image.getWidth() + 25 - 15 - 30 - 15 - size.width,  75+75+i*(60+30) + (60-size.height)/2, size.width, size.height);
				this.add(calDeficitLabel[i]);

				if (plan.getPredictedCalorieBurn() <= -500) g2.drawImage(checkmark, 275 + image.getWidth() + 25 - 15 - 30, 75+75+i*(60+30) + (60-30)/2, null);
				else g2.drawImage(exclmark, 275 + image.getWidth() + 25 - 15 - 30, 75+75+i*(60+30) + (60-30)/2, null);
				
				if (plan.getMeals().size() == 0 && plan.getWorkouts().size() == 0) {
					feedbackLabel[i].setText("<html>&#60; Add either Meals or Workouts that will result in a -500<br> calorie difference</html>");
					size = feedbackLabel[i].getPreferredSize();
			    	feedbackLabel[i].setBounds(275 + 600 + 25 + 30, 75+75+i*(60+30) + (60-size.height)/2, size.width, size.height);
			    	this.add(feedbackLabel[i]);
			    	goodWeek = false;
				}else if (plan.getMeals().size() == 0) {
					feedbackLabel[i].setText("< Please specify what meals you plan on eating for this day");
					size = feedbackLabel[i].getPreferredSize();
			    	feedbackLabel[i].setBounds(275 + 600 + 25 + 30, 75+75+i*(60+30) + (60-size.height)/2, size.width, size.height);
			    	this.add(feedbackLabel[i]);
			    	goodWeek = false;
				}else if (plan.getPredictedCalorieBurn() > -500) {
					feedbackLabel[i].setText("<html>&#60; This day’s calorie difference isn’t at least -500! Reduce<br>"
							+ " your calorie intake (Meals) or  Add a Workout with a high<br> enough Calorie Burn Goal</html>");
					size = feedbackLabel[i].getPreferredSize();
			    	feedbackLabel[i].setBounds(275 + 600 + 25 + 30, 75+75+i*(60+30) + (60-size.height)/2, size.width, size.height);
			    	this.add(feedbackLabel[i]);
			    	goodWeek = false;
				}
			}
		}
		
		if (goodWeek) {
			feedbackLabel[0].setText("This week looks good!");
			size = feedbackLabel[0].getPreferredSize();
	    	feedbackLabel[0].setBounds(275 + 600 + 25 + 30, 75+75+15, size.width, size.height);
	    	this.add(feedbackLabel[0]);
		}
		
		g2.setColor(new Color(255,255,255,75));
		g2.fillRect(275 + image.getWidth() + 25 + 30, 75+15, getWidth()-(275 + image.getWidth() + 25) - 60, 60);
	}

	public int getWeekIndex() {
		return weekIndex;
	}

	public void setWeekIndex(int weekIndex) {
		this.weekIndex = weekIndex;
	}
}
