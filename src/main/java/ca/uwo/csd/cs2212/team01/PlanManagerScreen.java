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
import java.util.LinkedList;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class PlanManagerScreen extends JPanel implements Serializable{
	private static final long serialVersionUID = 1L;
	private MainWindow mainWindow;
	
	private Day day;
	private int screen, myMealsIndex, myWorkoutsIndex;
	
	private JTextArea calorieInput;
	
	private JLabel mealsLabel, workoutsLabel, calDeficitLabel;
	private JLabel[][][] listLabel;
	private JLabel[] listBMPLabel;
	
	private JButton[] editBtn;
	private JButton[][] listRemoveButton;
	
	/**
	 * Class Constructor
	 * @param mainWindow
	 */
	public PlanManagerScreen(MainWindow mainWindow) {
		this.mainWindow = mainWindow;
		
		this.day = null;
		this.setMyMealsIndex(0);
		this.setMyWorkoutsIndex(0);
		
		this.setLayout(null);
		this.setSize(1480,800);
    	this.setLocation(0,0);
    	
    	this.editBtn = new JButton[2];

		listRemoveButton = new JButton[2][7];
		listLabel = new JLabel[2][2][7];
		listBMPLabel = new JLabel[2];

		listBMPLabel[0] = new JLabel("BMP");
		listBMPLabel[0].setFont(mainWindow.FONT_HELVETICA_NEUE_THIN.deriveFont(20.0f));
		listBMPLabel[0].setForeground(new Color(255,255,255, 200));
		Dimension size = listBMPLabel[0].getPreferredSize();
		listBMPLabel[0].setBounds(getWidth()/2 + 75 + 125 + 30 + 50 + (195-200)/2 + (200 - size.width)/2, 75+60+30 + (60-size.height*2)/2, size.width, size.height);
		this.add(listBMPLabel[0]);

		listBMPLabel[1] = new JLabel("( - 1600 Cal )");
		listBMPLabel[1].setFont(mainWindow.FONT_HELVETICA_NEUE_THIN.deriveFont(20.0f));
		listBMPLabel[1].setForeground(new Color(106, 185, 255, 200));
		size = listBMPLabel[1].getPreferredSize();
		listBMPLabel[1].setBounds(getWidth()/2 + 75 + 125 + 30 + 50 + (195-200)/2 + (200 - size.width)/2, 75+60+30 + size.height + (60-size.height*2)/2, size.width, size.height);
		this.add(listBMPLabel[1]);

		for(int i = 0; i < 7; i++) {
			for(int x = 0; x < 2; x++) {
				listLabel[x][0][i] = new JLabel();
				listLabel[x][0][i].setFont(mainWindow.FONT_HELVETICA_NEUE_THIN.deriveFont(20.0f));
				listLabel[x][0][i].setForeground(new Color(255,255,255,200));
				//this.add(listLabel[i]);
				
				listLabel[x][1][i] = new JLabel();
				listLabel[x][1][i].setFont(mainWindow.FONT_HELVETICA_NEUE_THIN.deriveFont(20.0f));
				listLabel[x][1][i].setForeground(new Color(106, 185, 255, 255));
				//this.add(listLabel[i]);
			}
			
			listRemoveButton[0][i] = new JButton();
			listRemoveButton[0][i].setBackground(null);
			listRemoveButton[0][i].setBorder(null);
			listRemoveButton[0][i].setFocusPainted(false);
			listRemoveButton[0][i].setMargin(new Insets(0, 0, 0, 0));
			listRemoveButton[0][i].setContentAreaFilled(false);
			listRemoveButton[0][i].setBorderPainted(false);
			listRemoveButton[0][i].setOpaque(false);
			//listRemoveButton[0][i].setForeground(new Color(255,255,255,200));
			listRemoveButton[0][i].setFocusable(false);
			//size = listRemoveButton[0][i].getPreferredSize();
			listRemoveButton[0][i].setBounds(getWidth()/2 + 75 + (170-200)/2 - 50 + (50-30)/2, 75+60+30 + (60-30)/2 + i*70, 30, 30);
			listRemoveButton[0][i].addActionListener(new ButtonActionListener(20, i, mainWindow));
			
			listRemoveButton[1][i] = new JButton();
			listRemoveButton[1][i].setBackground(null);
			listRemoveButton[1][i].setBorder(null);
			listRemoveButton[1][i].setFocusPainted(false);
			listRemoveButton[1][i].setMargin(new Insets(0, 0, 0, 0));
			listRemoveButton[1][i].setContentAreaFilled(false);
			listRemoveButton[1][i].setBorderPainted(false);
			listRemoveButton[1][i].setOpaque(false);
			//listRemoveButton[1][i].setForeground(new Color(255,255,255,200));
			listRemoveButton[1][i].setFocusable(false);
			//size = listRemoveButton[1][i].getPreferredSize();
			listRemoveButton[1][i].setBounds(getWidth()/2 + 75 + 125 + 30 + 50 + (195-200)/2 + 200 + (50-30)/2, 75+60+30 + (60-30)/2 + (i+1)*70, 30, 30);
			listRemoveButton[1][i].addActionListener(new ButtonActionListener(21, i, mainWindow));
		}
		
		//this.initUI();
	}
	
	public void initUI() {
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
			    
    			button = new JButton("< " + MainWindow.fmDayofWeek.format(day.getDate()));
    			button.setFont(mainWindow.FONT_HELVETICA_NEUE_THIN.deriveFont(25.0f));
    			button.setBackground(null);
    			button.setBorder(null);
    			button.setFocusPainted(false);
    			button.setMargin(new Insets(0, 0, 0, 0));
    			button.setContentAreaFilled(false);
    			button.setBorderPainted(false);
    			button.setOpaque(false);
    			button.setForeground(new Color(255,255,255,200));
    			button.setFocusable(false);
    			Dimension size = button.getPreferredSize();
    			button.setBounds(13 + image.getWidth() + 15, 15, size.width, getHeight()-30);
    			button.addActionListener(new ButtonActionListener(13, 5, mainWindow));
    			this.add(button);
    		    
    			if (screen != 2) {
	    			JLabel label = new JLabel("Add meals to this Plan from:");
	    			label.setFont(mainWindow.FONT_HELVETICA_NEUE_THIN.deriveFont(30.0f));
	    			label.setForeground(new Color(255,255,255,150));
	    			size = label.getPreferredSize();
	    			label.setBounds(getWidth()/2 - size.width, (getHeight()-size.height)/2, size.width, size.height);
	    			this.add(label);
    			}
    			
    			image = null;
				try {
					image = ImageIO.read(new File("UI/btn-esm-bg.png"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

    			if (screen == 2) {
        			JLabel label = new JLabel("Add a Workout to this Plan");
        			label.setFont(mainWindow.FONT_HELVETICA_NEUE_THIN.deriveFont(30.0f));
        			label.setForeground(new Color(255,255,255,200));
        			size = label.getPreferredSize();
        			label.setBounds((getWidth()-size.width)/2, (getHeight()-size.height)/2, size.width, size.height);
        			this.add(label);
    			}else {
    				if (screen == 0) g2.drawImage(image, getWidth()/2 + 15, 15, null);

        			g2.setColor(Color.WHITE);
        			
			    	g2.drawRoundRect(getWidth()/2 + 15, 15, 200, getHeight()-30, 15, 15);
				    
	    			button = new JButton("My Meals");
	    			button.setFont(mainWindow.FONT_HELVETICA_NEUE_THIN.deriveFont(25.0f));
	    			button.setBackground(null);
	    			button.setBorder(null);
	    			button.setFocusPainted(false);
	    			button.setMargin(new Insets(0, 0, 0, 0));
	    			button.setContentAreaFilled(false);
	    			button.setBorderPainted(false);
	    			button.setOpaque(false);
	    			button.setForeground(new Color(255,255,255,200));
	    			button.setFocusable(false);
	    			//Dimension size = button.getPreferredSize();
	    			button.setBounds(getWidth()/2 + 15, 15, 200, getHeight()-30);
	    			button.addActionListener(new ButtonActionListener(15, 2, mainWindow));
	    			this.add(button);
	    			
	    			if (screen == 1) g2.drawImage(image, getWidth()/2 + 30 + 200, 15, null);
	    			
			    	g2.drawRoundRect(getWidth()/2 + 30 + 200, 15, 200, getHeight()-30, 15, 15);
	    			
	    			button = new JButton("My Dishes");
	    			button.setFont(mainWindow.FONT_HELVETICA_NEUE_THIN.deriveFont(25.0f));
	    			button.setBackground(null);
	    			button.setBorder(null);
	    			button.setFocusPainted(false);
	    			button.setMargin(new Insets(0, 0, 0, 0));
	    			button.setContentAreaFilled(false);
	    			button.setBorderPainted(false);
	    			button.setOpaque(false);
	    			button.setForeground(new Color(255,255,255,200));
	    			button.setFocusable(false);
	    			//size = button.getPreferredSize();
	    			button.setBounds(getWidth()/2 + 30 + 200, 15, 200, getHeight()-30);
	    			button.addActionListener(new ButtonActionListener(15, 3, mainWindow));
	    			this.add(button);
    			}
    	        
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
		
    	if (screen == 0) { // Add Meals to Plan
    		
    	}else if (screen == 1) { // Add Dishes to Meal to Plan
    		
    	}else if (screen == 2) { // Add Workouts to Plan
			JLabel label = new JLabel("Specify a Calorie Burn Goal to add a Workout to your Plan >");
			label.setFont(mainWindow.FONT_HELVETICA_NEUE_THIN.deriveFont(25.0f));
			label.setForeground(new Color(255,255,255,200));
			Dimension size = label.getPreferredSize();
			label.setBounds((getWidth()/2-size.width)/2, 75 + 15 + (60-size.height)/2, size.width, size.height);
			this.add(label);
			
			label = new JLabel("I will burn:");
			label.setFont(mainWindow.FONT_HELVETICA_NEUE_THIN.deriveFont(25.0f));
			label.setForeground(new Color(255,255,255,150));
			size = label.getPreferredSize();
			label.setBounds((getWidth()/2-size.width)/2, 75 + 15 + 60 + (60-size.height)/2, size.width, size.height);
			this.add(label);
	    	
			calorieInput = new JTextArea();
			calorieInput.setOpaque(false);
			calorieInput.setFont(mainWindow.FONT_HELVETICA_NEUE_THIN.deriveFont(27.0f));
			size = calorieInput.getPreferredSize();
			calorieInput.setBounds((getWidth()/2 - 209)/2 + 50,  75 + 15 + 120 + (75-size.height)/2, 209, size.height);
			calorieInput.setCaretColor(new Color(255,255,255,200));
			calorieInput.setForeground(new Color(255,255,255,200));
			this.add(calorieInput);
			
			label = new JLabel("Calories");
			label.setFont(mainWindow.FONT_HELVETICA_NEUE_THIN.deriveFont(25.0f));
			label.setForeground(new Color(255,255,255,150));
			size = label.getPreferredSize();
			label.setBounds((getWidth()/2-size.width)/2, 75 + 15 + 60 + 75 + 60 + (60-size.height)/2, size.width, size.height);
			this.add(label);
			
			JButton exitBtn = new JButton("Add Workout");
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
			exitBtn.setBounds((getWidth()/2-209)/2, 75 + 15 + 255, 209, 60);
	        exitBtn.addActionListener(new ButtonActionListener(15, 4, mainWindow));
	        this.add(exitBtn);
    	}
    	
    	mealsLabel = new JLabel();
    	mealsLabel.setFont(mainWindow.FONT_HELVETICA_NEUE_THIN.deriveFont(25.0f));
    	mealsLabel.setForeground(new Color(255,255,255,200));
    	
    	workoutsLabel = new JLabel();
    	workoutsLabel.setFont(mainWindow.FONT_HELVETICA_NEUE_THIN.deriveFont(25.0f));
    	workoutsLabel.setForeground(new Color(255,255,255,200));
    	
    	calDeficitLabel = new JLabel();
    	calDeficitLabel.setFont(mainWindow.FONT_HELVETICA_NEUE_THIN.deriveFont(25.0f));
    	calDeficitLabel.setForeground(new Color(180,180,180,200));
		
		editBtn[0] = new JButton();
		editBtn[0].setBackground(null);
		editBtn[0].setBorder(null);
		editBtn[0].setFocusPainted(false);
		editBtn[0].setMargin(new Insets(0, 0, 0, 0));
		editBtn[0].setContentAreaFilled(false);
		editBtn[0].setBorderPainted(false);
		editBtn[0].setOpaque(false);
		editBtn[0].setFocusable(false);
        editBtn[0].addActionListener(new ButtonActionListener(15, 2, mainWindow));
        //this.add(editBtn[0]);
		
		editBtn[1] = new JButton();
		editBtn[1].setBackground(null);
		editBtn[1].setBorder(null);
		editBtn[1].setFocusPainted(false);
		editBtn[1].setMargin(new Insets(0, 0, 0, 0));
		editBtn[1].setContentAreaFilled(false);
		editBtn[1].setBorderPainted(false);
		editBtn[1].setOpaque(false);
		editBtn[1].setFocusable(false);
        editBtn[1].addActionListener(new ButtonActionListener(15, 5, mainWindow));
        //this.add(editBtn[1]);
	}

	public void addWorkout() {
		Workout workout = new Workout(Integer.parseInt(this.calorieInput.getText()));
		this.day.getPlan().addWorkout(workout);

    	this.repaint();
	}
	
    public void toggleMealWorkoutScreen(int screen) {
    	if (this.screen == screen) return;
    	
    	this.screen = screen;
    	
    	this.initUI();
    	this.repaint();
    }
    
    private void removeListFromPanel() {
    	for(int i = 0; i < 7; i++) {
    		//this.remove(editPlanBtn[i]);
    		this.remove(listRemoveButton[0][i]);
    		this.remove(listRemoveButton[1][i]);

    		this.remove(listLabel[0][0][i]);
    		this.remove(listLabel[0][1][i]);
    		this.remove(listLabel[1][0][i]);
    		this.remove(listLabel[1][1][i]);
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
		
    	this.removeListFromPanel();
    	
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

		if (screen == 2) {
			g2.setColor(new Color(255,255,255,75));
			g2.fillRect((getWidth()/2 - 209)/2,  75 + 15 + 120, 209, 75);
			
			image = null;
			try {
				image = ImageIO.read(new File("UI/btn-itemdisplay-bg.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			g.drawImage(image, (getWidth()/2 - image.getWidth())/2,  75 + 15 + 255, null);
		}

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
		
		BufferedImage xmark = null;
		try {
			xmark = ImageIO.read(new File("UI/xmark.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		g2.setColor(new Color(255,255,255,150));
		
		if (day != null && day.getPlan() != null) {
			g2.drawImage(image, getWidth()/2 + 75, 75+15, null);
			
	    	mealsLabel.setText(day.getPlan().getMeals().size() + " Meals");
			Dimension size = mealsLabel.getPreferredSize();
			mealsLabel.setBounds(getWidth()/2 + 75 + 125 - size.width - 15,  75+15 + (60-size.height)/2, size.width, size.height);
			this.add(mealsLabel);
			
			g2.drawImage(addicon, getWidth()/2 + 75 + 125, 75+15 + (60-30)/2, null);
			editBtn[0].setBounds(getWidth()/2 + 75 + 125,  75+15 + (60-30)/2, 30, 30);
	        this.add(editBtn[0]);
	        
	        g2.drawLine(getWidth()/2 + 75 + 125 + 30 + 15, 75+15 + 60/2, getWidth()/2 + 75 + 125 + 30 + 50 - 15, 75+15 + 60/2);
			
			workoutsLabel.setText(day.getPlan().getWorkouts().size() + " Workouts");
			size = workoutsLabel.getPreferredSize();
			workoutsLabel.setBounds(getWidth()/2 + 75 + 125 + 30 + 50 + 150 - 15 - size.width,  75+15 + (60-size.height)/2, size.width, size.height);
			this.add(workoutsLabel);
			
			g2.drawImage(addicon, getWidth()/2 + 75 + 125 + 30 + 50 + 150, 75+15 + (60-30)/2, null);
			editBtn[1].setBounds(getWidth()/2 + 75 + 125 + 30 + 50 + 150,  75+15 + (60-30)/2, 30, 30);
	        this.add(editBtn[1]);
	        
	        g2.drawLine(getWidth()/2 + 75 + 125 + 30 + 50 + 150 + 30 + 15, 75+15 + 60/2 - 3, getWidth()/2 + 75 + 125 + 30 + 50 + 150 + 30 + 50 - 15, 75+15 + 60/2 - 3);
	        g2.drawLine(getWidth()/2 + 75 + 125 + 30 + 50 + 150 + 30 + 15, 75+15 + 60/2 + 3, getWidth()/2 + 75 + 125 + 30 + 50 + 150 + 30 + 50 - 15, 75+15 + 60/2 + 3);
			
			calDeficitLabel.setText(day.getPlan().getPredictedCalorieBurn() + " cal");
			size = calDeficitLabel.getPreferredSize();
			calDeficitLabel.setBounds(getWidth()/2 + 75 + image.getWidth() + 25 - 15 - 30 - 15 - size.width,  75+15 + (60-size.height)/2, size.width, size.height);
			this.add(calDeficitLabel);
			
			if (day.getPlan().getPredictedCalorieBurn() <= -500) g2.drawImage(checkmark, getWidth()/2 + 75 + image.getWidth() + 25 - 15 - 30, 75+15 + (60-30)/2, null);
			else g2.drawImage(exclmark, getWidth()/2 + 75 + image.getWidth() + 25 - 15 - 30, 75+15 + (60-30)/2, null);
			
			Meal myMeal;
			LinkedList<Meal> myMeals = day.getPlan().getMeals();
			
			for(int i = 0; i < 7 && getMyMealsIndex()+i < myMeals.size(); i++) {
				myMeal = myMeals.get(getMyMealsIndex()+i);
				
				g.setColor(new Color(255,255,255,120));
				listLabel[0][0][i].setText(myMeal.getName());
				size = listLabel[0][0][i].getPreferredSize();
				listLabel[0][0][i].setBounds(getWidth()/2 + 75 + (170-200)/2 + (200 - size.width)/2, 75+60+30 + (60-size.height*2)/2 + i*70, size.width, size.height);
				this.add(listLabel[0][0][i]);
				
				listLabel[0][1][i].setText("( + "+myMeal.getCalories()+" Cal )");
				size = listLabel[0][1][i].getPreferredSize();
				listLabel[0][1][i].setBounds(getWidth()/2 + 75 + (170-200)/2 + (200 - size.width)/2, 75+60+30 + size.height + (60-size.height*2)/2 + i*70, size.width, size.height);
				this.add(listLabel[0][1][i]);
				
				g.drawImage(xmark,getWidth()/2 + 75 + (170-200)/2 - 50 + (50-30)/2, 75+60+30 + (60-30)/2 + i*70, null);
				g.drawRoundRect(getWidth()/2 + 75 + (170-200)/2, 75+60+30+i*70, 200, 60, 15, 15);
				
				this.add(listRemoveButton[0][i]);
			}
			
			Workout myWorkout;
			LinkedList<Workout> myWorkouts = day.getPlan().getWorkouts();

			g.drawRoundRect(getWidth()/2 + 75 + 125 + 30 + 50 + (195-200)/2, 75+60+30, 200, 60, 15, 15);
			
			for(int i = 0; i < 7 && getMyWorkoutsIndex()+i < myWorkouts.size(); i++) {
				myWorkout = myWorkouts.get(getMyWorkoutsIndex()+i);
				
				g.setColor(new Color(255,255,255,120));
				listLabel[1][0][i].setText("Workout " + (getMyWorkoutsIndex()+i));
				size = listLabel[1][0][i].getPreferredSize();
				listLabel[1][0][i].setBounds(getWidth()/2 + 75 + 125 + 30 + 50 + (195-200)/2 + (200 - size.width)/2, 75+60+30 + (60-size.height*2)/2 + (i+1)*70, size.width, size.height);
				this.add(listLabel[1][0][i]);
				
				listLabel[1][1][i].setText("( - "+myWorkout.getCalorieBurnGoal()+" Cal )");
				size = listLabel[1][1][i].getPreferredSize();
				listLabel[1][1][i].setBounds(getWidth()/2 + 75 + 125 + 30 + 50 + (195-200)/2 + (200 - size.width)/2, 75+60+30 + size.height + (60-size.height*2)/2 + (i+1)*70, size.width, size.height);
				this.add(listLabel[1][1][i]);
				
				g.drawImage(xmark,getWidth()/2 + 75 + 125 + 30 + 50 + (195-200)/2 + 200 + (50-30)/2, 75+60+30 + (60-30)/2 + (i+1)*70, null);
				g.drawRoundRect(getWidth()/2 + 75 + 125 + 30 + 50 + (195-200)/2, 75+60+30+(i+1)*70, 200, 60, 15, 15);
				
				this.add(listRemoveButton[1][i]);
			}
		}
    }

	public Day getDay() {
		return day;
	}
	
	public void setDay(Day day, int screen) {
		this.day = day;
    	
    	this.initUI();
    	this.repaint();
	}

	public int getMyMealsIndex() {
		return myMealsIndex;
	}

	public void setMyMealsIndex(int myMealsIndex) {
		this.myMealsIndex = myMealsIndex;
	}

	public int getMyWorkoutsIndex() {
		return myWorkoutsIndex;
	}

	public void setMyWorkoutsIndex(int myWorkoutsIndex) {
		this.myWorkoutsIndex = myWorkoutsIndex;
	}

	public void removeMeal(int index) {
		this.day.getPlan().getMeals().remove(index);
		this.repaint();
	}

	public void removeWorkout(int index) {
		this.day.getPlan().getWorkouts().remove(index);
		this.repaint();
	}
}
