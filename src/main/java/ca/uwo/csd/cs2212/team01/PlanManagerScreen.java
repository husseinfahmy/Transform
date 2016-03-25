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
	
	private Meal addDishesMeal;
	private Day day;
	private int screen, myDayMealsIndex, myDayWorkoutsIndex;
	private int[] myMealsDishesIndex;
	
	private JTextArea calorieInput, nameInput;
	
	private JLabel mealsLabel, workoutsLabel, calDeficitLabel, addMealLabel;
	private JLabel[][][] listLabel, myListLabel;
	private JLabel[] listBMPLabel, addWorkoutLabel;
	
	private JButton addWorkoutBtn, addMealBtn;
	private JButton[] editBtn, myListAddButton;
	private JButton[][] listRemoveButton, myListAddRemoveButton;

	/**
	 * Class Constructor
	 * @param mainWindow
	 */
	public PlanManagerScreen(MainWindow mainWindow) {
		this.mainWindow = mainWindow;
		
		this.day = null;
		myMealsDishesIndex = new int[2];
		this.myMealsDishesIndex[0] = this.myMealsDishesIndex[1] = 0;
		this.myDayMealsIndex = 0;
		this.myDayWorkoutsIndex = 0;
		
		this.setLayout(null);
		this.setSize(1480,800);
    	this.setLocation(0,0);
		
		this.initUI();
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
        
        addWorkoutLabel = new JLabel[3];
    	
    	editBtn = new JButton[2];

    	myListLabel = new JLabel[2][2][7];
    	myListAddButton = new JButton[7];
    	
		listRemoveButton = new JButton[2][7];
    	myListAddRemoveButton = new JButton[2][7];
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
			myListAddButton[i] = new JButton();
			myListAddButton[i].setBackground(null);
			myListAddButton[i].setBorder(null);
			myListAddButton[i].setFocusPainted(false);
			myListAddButton[i].setMargin(new Insets(0, 0, 0, 0));
			myListAddButton[i].setContentAreaFilled(false);
			myListAddButton[i].setBorderPainted(false);
			myListAddButton[i].setOpaque(false);
			//myListAddButton[i].setForeground(new Color(255,255,255,200));
			myListAddButton[i].setFocusable(false);
			//size = myListAddButton[i].getPreferredSize();
			myListAddButton[i].setBounds((getWidth()/2 - 200)/2 + 200 + (50-30)/2, 75+15+60+i*70, 200, 60);
			myListAddButton[i].addActionListener(new ButtonActionListener(22, i, mainWindow));
			
			for(int x = 0; x < 2; x++) {

				myListLabel[x][0][i] = new JLabel();
				myListLabel[x][0][i].setFont(mainWindow.FONT_HELVETICA_NEUE_THIN.deriveFont(20.0f));
				myListLabel[x][0][i].setForeground(new Color(255,255,255,200));
				//this.add(listLabel[i]);
				
				myListLabel[x][1][i] = new JLabel();
				myListLabel[x][1][i].setFont(mainWindow.FONT_HELVETICA_NEUE_THIN.deriveFont(20.0f));
				myListLabel[x][1][i].setForeground(new Color(106, 185, 255, 255));
				//this.add(listLabel[i]);
				
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

			myListAddRemoveButton[0][i] = new JButton();
			myListAddRemoveButton[0][i].setBackground(null);
			myListAddRemoveButton[0][i].setBorder(null);
			myListAddRemoveButton[0][i].setFocusPainted(false);
			myListAddRemoveButton[0][i].setMargin(new Insets(0, 0, 0, 0));
			myListAddRemoveButton[0][i].setContentAreaFilled(false);
			myListAddRemoveButton[0][i].setBorderPainted(false);
			myListAddRemoveButton[0][i].setOpaque(false);
			//myListAddRemoveButton[0][i].setForeground(new Color(255,255,255,200));
			myListAddRemoveButton[0][i].setFocusable(false);
			//size = myListAddRemoveButton[0][i].getPreferredSize();
			myListAddRemoveButton[0][i].setBounds((getWidth()/2/2 - 200)/2 + 200 + (50-30)/2, 75+15+60 + (60-30)/2 + i*70, 30, 30);
			myListAddRemoveButton[0][i].addActionListener(new ButtonActionListener(23, i, mainWindow));

			myListAddRemoveButton[1][i] = new JButton();
			myListAddRemoveButton[1][i].setBackground(null);
			myListAddRemoveButton[1][i].setBorder(null);
			myListAddRemoveButton[1][i].setFocusPainted(false);
			myListAddRemoveButton[1][i].setMargin(new Insets(0, 0, 0, 0));
			myListAddRemoveButton[1][i].setContentAreaFilled(false);
			myListAddRemoveButton[1][i].setBorderPainted(false);
			myListAddRemoveButton[1][i].setOpaque(false);
			//myListAddRemoveButton[1][i].setForeground(new Color(255,255,255,200));
			myListAddRemoveButton[1][i].setFocusable(false);
			//size = myListAddRemoveButton[1][i].getPreferredSize();
			myListAddRemoveButton[1][i].setBounds(getWidth()/2/2 + (getWidth()/2/2 - 200)/2 + 200 + (50-30)/2, 75+15+60 + (60-30)/2 + i*70, 30, 30);
			myListAddRemoveButton[1][i].addActionListener(new ButtonActionListener(24, i, mainWindow));
		}
		
    	//if (screen == 0) { // Add Meals to Plan
			addMealLabel = new JLabel("Click on a \"+\" to add a Meal to your Plan");
			addMealLabel.setFont(mainWindow.FONT_HELVETICA_NEUE_THIN.deriveFont(30.0f));
			addMealLabel.setForeground(new Color(255,255,255,200));
			size = addMealLabel.getPreferredSize();
			addMealLabel.setBounds((getWidth()/2 - size.width)/2, 75+15+(60-size.height)/2, size.width, size.height);
			//this.add(label);
    	//}else if (screen == 1) { // Add Dishes to Meal to Plan
			nameInput = new JTextArea();
			nameInput.setOpaque(false);
			nameInput.setFont(mainWindow.FONT_HELVETICA_NEUE_THIN.deriveFont(27.0f));
			size = nameInput.getPreferredSize();
			nameInput.setBounds(50 + getWidth()/2/2 + (getWidth()/2/2 - 300)/2, getHeight() - 15 - 40 + (40-size.height)/2, 300, size.height);
			nameInput.setCaretColor(new Color(255,255,255,200));
			nameInput.setForeground(new Color(255,255,255,200));
    	//}else if (screen == 2) { // Add Workouts to Plan
			addWorkoutLabel[0] = new JLabel("Specify a Calorie Burn Goal to add a Workout to your Plan >");
			addWorkoutLabel[0].setFont(mainWindow.FONT_HELVETICA_NEUE_THIN.deriveFont(25.0f));
			addWorkoutLabel[0].setForeground(new Color(255,255,255,200));
			size = addWorkoutLabel[0].getPreferredSize();
			addWorkoutLabel[0].setBounds((getWidth()/2-size.width)/2, 75 + 15 + (60-size.height)/2, size.width, size.height);
			
			addWorkoutLabel[1] = new JLabel("I will burn:");
			addWorkoutLabel[1].setFont(mainWindow.FONT_HELVETICA_NEUE_THIN.deriveFont(25.0f));
			addWorkoutLabel[1].setForeground(new Color(255,255,255,150));
			size = addWorkoutLabel[1].getPreferredSize();
			addWorkoutLabel[1].setBounds((getWidth()/2-size.width)/2, 75 + 15 + 60 + (60-size.height)/2, size.width, size.height);

			calorieInput = new JTextArea();
			calorieInput.setOpaque(false);
			calorieInput.setFont(mainWindow.FONT_HELVETICA_NEUE_THIN.deriveFont(27.0f));
			size = calorieInput.getPreferredSize();
			calorieInput.setBounds((getWidth()/2 - 209)/2 + 50,  75 + 15 + 120 + (75-size.height)/2, 209, size.height);
			calorieInput.setCaretColor(new Color(255,255,255,200));
			calorieInput.setForeground(new Color(255,255,255,200));
			
			addWorkoutLabel[2] = new JLabel("Calories");
			addWorkoutLabel[2].setFont(mainWindow.FONT_HELVETICA_NEUE_THIN.deriveFont(25.0f));
			addWorkoutLabel[2].setForeground(new Color(255,255,255,150));
			size = addWorkoutLabel[2].getPreferredSize();
			addWorkoutLabel[2].setBounds((getWidth()/2-size.width)/2, 75 + 15 + 60 + 75 + 60 + (60-size.height)/2, size.width, size.height);
			
			addWorkoutBtn = new JButton("Add Workout");
			addWorkoutBtn.setFont(mainWindow.FONT_HELVETICA_NEUE_THIN.deriveFont(25.0f));
			addWorkoutBtn.setBackground(null);
			addWorkoutBtn.setBorder(null);
			addWorkoutBtn.setFocusPainted(false);
			addWorkoutBtn.setMargin(new Insets(0, 0, 0, 0));
			addWorkoutBtn.setContentAreaFilled(false);
			addWorkoutBtn.setBorderPainted(false);
			addWorkoutBtn.setOpaque(false);
			addWorkoutBtn.setForeground(new Color(255,255,255,220));
			addWorkoutBtn.setFocusable(false);
			size = addWorkoutBtn.getPreferredSize();
			addWorkoutBtn.setBounds((getWidth()/2-209)/2, 75 + 15 + 255, 209, 60);
	        addWorkoutBtn.addActionListener(new ButtonActionListener(15, 4, mainWindow));

			addMealBtn = new JButton("Add >");
			addMealBtn.setFont(mainWindow.FONT_HELVETICA_NEUE_THIN.deriveFont(30.0f));
			addMealBtn.setBackground(null);
			addMealBtn.setBorder(null);
			addMealBtn.setFocusPainted(false);
			addMealBtn.setMargin(new Insets(0, 0, 0, 0));
			addMealBtn.setContentAreaFilled(false);
			addMealBtn.setBorderPainted(false);
			addMealBtn.setOpaque(false);
			addMealBtn.setForeground(new Color(255,255,255,220));
			addMealBtn.setFocusable(false);
			size = addMealBtn.getPreferredSize();
			addMealBtn.setBounds(getWidth()/2 + 75 - 30 - size.width, 75+15 + (60-size.height)/2, size.width, size.height);
	        addMealBtn.addActionListener(new ButtonActionListener(15, 6, mainWindow));
    	//}
    	
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
	
	public void addDishesMeal() {
		if (this.addDishesMeal == null) return;
		if (this.day.getPlan().getMeals().size() < 7) {
			this.day.getPlan().addMeal(this.addDishesMeal);
			this.addDishesMeal = new Meal();
			this.toggleMealWorkoutScreen(screen);
		}
	}
	
	public void addMeal(Meal meal) {
		if (meal == null) return;
		if (this.day.getPlan().getMeals().size() < 7) {
			this.day.getPlan().addMeal(meal);
			this.toggleMealWorkoutScreen(screen);
		}
	}
	
	public void addDish(Meal dish) {
		FoodServing newFoodServing = new FoodServing(dish, 10, "g");
		if (this.addDishesMeal.getFoodServings().size() < 7) {
			this.addDishesMeal.addFoodServing(newFoodServing);
			toggleMealWorkoutScreen(screen);
		}
	}
	
	public void removeDish(int index) {
		this.addDishesMeal.removeFoodServing(index);
		toggleMealWorkoutScreen(screen);
	}

	public void addWorkout() {
		if (this.day.getPlan().getWorkouts().size() < 7) {
			this.day.getPlan().addWorkout(new Workout(Integer.parseInt(this.calorieInput.getText())));
			this.toggleMealWorkoutScreen(screen);
		}
	}
	
    public void toggleMealWorkoutScreen(int screen) {
    	this.screen = screen;
    	
    	removeListFromPanel();
    	
    	if (screen == 1 && this.addDishesMeal == null) this.addDishesMeal = new Meal();

		Dimension size;
		
		if (this.screen == 0) {
			this.add(addMealLabel);
			
			Meal myMeal;
			LinkedList<Meal> myMeals = this.mainWindow.getMyMeals();
			
			for(int i = 0; i < 7 && this.myMealsDishesIndex[0]+i < myMeals.size(); i++) {
				myMeal = myMeals.get(this.myMealsDishesIndex[0]+i);
				
				myListLabel[0][0][i].setText(myMeal.getName());
				size = myListLabel[0][0][i].getPreferredSize();
				myListLabel[0][0][i].setBounds((getWidth()/2 - size.width)/2, 75+15+60 + (60-size.height*2)/2 + i*70, size.width, size.height);
				this.add(myListLabel[0][0][i]);
				
				myListLabel[0][1][i].setText("( + "+myMeal.getCalories()+" Cal )");
				size = myListLabel[0][1][i].getPreferredSize();
				myListLabel[0][1][i].setBounds((getWidth()/2 - size.width)/2, 75+15+60 + size.height + (60-size.height*2)/2 + i*70, size.width, size.height);
				this.add(myListLabel[0][1][i]);
				
				this.add(myListAddButton[i]);
			}
		}else if (screen == 1) { // Add Dishes to Plan
			//this.add(addMealLabel);
			
			Meal myMeal;
			LinkedList<Meal> myDishes = this.mainWindow.getMyDishes();
			
			for(int i = 0; i < 7 && this.myMealsDishesIndex[0]+i < myDishes.size(); i++) {
				myMeal = myDishes.get(this.myMealsDishesIndex[0]+i);
				
				myListLabel[0][0][i].setText(myMeal.getName());
				size = myListLabel[0][0][i].getPreferredSize();
				myListLabel[0][0][i].setBounds((getWidth()/2/2 - size.width)/2, 75+15+60 + (60-size.height*2)/2 + i*70, size.width, size.height);
				this.add(myListLabel[0][0][i]);
				
				myListLabel[0][1][i].setText("( + "+myMeal.getCalories()+" Cal )");
				size = myListLabel[0][1][i].getPreferredSize();
				myListLabel[0][1][i].setBounds((getWidth()/2/2 - size.width)/2, 75+15+60 + size.height + (60-size.height*2)/2 + i*70, size.width, size.height);
				this.add(myListLabel[0][1][i]);

				this.add(myListAddRemoveButton[0][i]);
			}
			
			FoodServing foodServing;
			LinkedList<FoodServing> foodServings = this.addDishesMeal.getFoodServings();
			
			for(int i = 0; i < 7 && this.myMealsDishesIndex[1]+i < foodServings.size(); i++) {
				foodServing = foodServings.get(this.myMealsDishesIndex[1]+i);
				
				myListLabel[1][0][i].setText(foodServing.getFood().getName());
				size = myListLabel[1][0][i].getPreferredSize();
				myListLabel[1][0][i].setBounds(getWidth()/2/2 + (getWidth()/2/2 - size.width)/2, 75+15+60 + (60-size.height*2)/2 + i*70, size.width, size.height);
				this.add(myListLabel[1][0][i]);
				
				myListLabel[1][1][i].setText("( + "+foodServing.getMacros().getCalories()+" Cal )");
				size = myListLabel[1][1][i].getPreferredSize();
				myListLabel[1][1][i].setBounds(getWidth()/2/2 + (getWidth()/2/2 - size.width)/2, 75+15+60 + size.height + (60-size.height*2)/2 + i*70, size.width, size.height);
				this.add(myListLabel[1][1][i]);

				this.add(myListAddRemoveButton[1][i]);
			}
			
			this.add(nameInput);
		    this.add(addMealBtn);
		}else if (screen == 2) { // Add Workouts to Plan
			this.add(addWorkoutLabel[0]);
			this.add(addWorkoutLabel[1]);
			this.add(addWorkoutLabel[2]);

			this.add(calorieInput);
			
			this.add(addWorkoutBtn);
    	}
		
		if (day != null && day.getPlan() != null) {
	    	mealsLabel.setText(day.getPlan().getMeals().size() + " Meals");
			size = mealsLabel.getPreferredSize();
			mealsLabel.setBounds(getWidth()/2 + 75 + 125 - size.width - 15,  75+15 + (60-size.height)/2, size.width, size.height);
			this.add(mealsLabel);
			
			editBtn[0].setBounds(getWidth()/2 + 75 + 125,  75+15 + (60-30)/2, 30, 30);
	        this.add(editBtn[0]);
	        
			workoutsLabel.setText(day.getPlan().getWorkouts().size() + " Workouts");
			size = workoutsLabel.getPreferredSize();
			workoutsLabel.setBounds(getWidth()/2 + 75 + 125 + 30 + 50 + 150 - 15 - size.width,  75+15 + (60-size.height)/2, size.width, size.height);
			this.add(workoutsLabel);
			
			editBtn[1].setBounds(getWidth()/2 + 75 + 125 + 30 + 50 + 150,  75+15 + (60-30)/2, 30, 30);
	        this.add(editBtn[1]);
	        
			calDeficitLabel.setText(day.getPlan().getPredictedCalorieBurn() + " cal");
			size = calDeficitLabel.getPreferredSize();
			calDeficitLabel.setBounds(getWidth()/2 + 75 + 600 + 25 - 15 - 30 - 15 - size.width,  75+15 + (60-size.height)/2, size.width, size.height);
			this.add(calDeficitLabel);
			
			Meal myMeal;
			LinkedList<Meal> myMeals = day.getPlan().getMeals();
			
			for(int i = 0; i < 7 && myDayMealsIndex+i < myMeals.size(); i++) {
				myMeal = myMeals.get(myDayMealsIndex+i);
				
				listLabel[0][0][i].setText(myMeal.getName());
				size = listLabel[0][0][i].getPreferredSize();
				listLabel[0][0][i].setBounds(getWidth()/2 + 75 + (170-200)/2 + (200 - size.width)/2, 75+60+30 + (60-size.height*2)/2 + i*70, size.width, size.height);
				this.add(listLabel[0][0][i]);
				
				listLabel[0][1][i].setText("( + "+myMeal.getCalories()+" Cal )");
				size = listLabel[0][1][i].getPreferredSize();
				listLabel[0][1][i].setBounds(getWidth()/2 + 75 + (170-200)/2 + (200 - size.width)/2, 75+60+30 + size.height + (60-size.height*2)/2 + i*70, size.width, size.height);
				this.add(listLabel[0][1][i]);
				
				this.add(listRemoveButton[0][i]);
			}
			
			Workout myWorkout;
			LinkedList<Workout> myWorkouts = day.getPlan().getWorkouts();
			
			for(int i = 0; i < 7 && myDayWorkoutsIndex+i < myWorkouts.size(); i++) {
				myWorkout = myWorkouts.get(myDayWorkoutsIndex+i);
				
				listLabel[1][0][i].setText("Workout " + (myDayWorkoutsIndex+i+1));
				size = listLabel[1][0][i].getPreferredSize();
				listLabel[1][0][i].setBounds(getWidth()/2 + 75 + 125 + 30 + 50 + (195-200)/2 + (200 - size.width)/2, 75+60+30 + (60-size.height*2)/2 + (i+1)*70, size.width, size.height);
				this.add(listLabel[1][0][i]);
				
				listLabel[1][1][i].setText("( - "+myWorkout.getCalorieBurnGoal()+" Cal )");
				size = listLabel[1][1][i].getPreferredSize();
				listLabel[1][1][i].setBounds(getWidth()/2 + 75 + 125 + 30 + 50 + (195-200)/2 + (200 - size.width)/2, 75+60+30 + size.height + (60-size.height*2)/2 + (i+1)*70, size.width, size.height);
				this.add(listLabel[1][1][i]);
				
				this.add(listRemoveButton[1][i]);
			}
		}
		
		this.repaint();
    }
    
    private void removeListFromPanel() {
    	for(int i = 0; i < 7; i++) {
    		this.remove(myListLabel[0][0][i]);
    		this.remove(myListLabel[0][1][i]);
    		this.remove(myListLabel[1][0][i]);
    		this.remove(myListLabel[1][1][i]);
    		this.remove(myListAddButton[i]);
    				
    		//this.remove(editPlanBtn[i]);
    		this.remove(listRemoveButton[0][i]);
    		this.remove(listRemoveButton[1][i]);

    		this.remove(listLabel[0][0][i]);
    		this.remove(listLabel[0][1][i]);
    		this.remove(listLabel[1][0][i]);
    		this.remove(listLabel[1][1][i]);
    		
    		this.remove(myListAddRemoveButton[0][i]);
    		this.remove(myListAddRemoveButton[1][i]);
    	}

	    this.remove(addMealLabel);
	    
		this.remove(addWorkoutLabel[0]);
		this.remove(addWorkoutLabel[1]);
		this.remove(addWorkoutLabel[2]);

		this.remove(calorieInput);
	    this.remove(addWorkoutBtn);
	    
	    this.remove(nameInput);
	    this.remove(addMealBtn);
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
		
		Dimension size;
		
		if (this.screen == 0) {
			LinkedList<Meal> myMeals = this.mainWindow.getMyMeals();
			
			for(int i = 0; i < 7 && this.myMealsDishesIndex[0]+i < myMeals.size(); i++) {
				g.drawImage(addicon,(getWidth()/2 - 200)/2 + 200 + (50-30)/2, 75+15+60 + (60-30)/2 + i*70, null);
				g.drawRoundRect((getWidth()/2 - 200)/2, 75+15+60+i*70, 200, 60, 15, 15);
			}
		}else if (screen == 1) { // Add Dishes to Plan
			LinkedList<Meal> myDishes = this.mainWindow.getMyDishes();
			
			for(int i = 0; i < 7 && this.myMealsDishesIndex[0]+i < myDishes.size(); i++) {
				g.drawImage(addicon,(getWidth()/2/2 - 200)/2 + 200 + (50-30)/2, 75+15+60 + (60-30)/2 + i*70, null);
				g.drawRoundRect((getWidth()/2/2 - 200)/2, 75+15+60+i*70, 200, 60, 15, 15);
			}
			
			LinkedList<FoodServing> foodSerings = this.addDishesMeal.getFoodServings();
			
			for(int i = 0; i < 7 && this.myMealsDishesIndex[1]+i < foodSerings.size(); i++) {
				g.drawImage(xmark,getWidth()/2/2 + (getWidth()/2/2 - 200)/2 + 200 + (50-30)/2, 75+15+60 + (60-30)/2 + i*70, null);
				g.drawRoundRect(getWidth()/2/2 + (getWidth()/2/2 - 200)/2, 75+15+60+i*70, 200, 60, 15, 15);
			}
			
			g.setColor(new Color(255,255,255,75));
			g.fillRect(getWidth()/2/2 + (getWidth()/2/2 - 300)/2, getHeight() - 15 - 40, 300, 40);
		}/*else if (screen == 2) { // Add Workouts to Plan
			LinkedList<Meal> myDishes = this.mainWindow.getMyDishes();
			
			for(int i = 0; i < 7 && myMealsDishesIndex+i < myDishes.size(); i++) {
				g.drawImage(addicon,(getWidth()/2 - 200)/2 + 200 + (50-30)/2, 75+15+60 + (60-30)/2 + i*70, null);
				g.drawRoundRect((getWidth()/2 - 200)/2, 75+15+60+i*70, 200, 60, 15, 15);
			}
    	}*/
		
		if (day != null && day.getPlan() != null) {
			g2.drawImage(image, getWidth()/2 + 75, 75+15, null);
			
			g2.drawImage(addicon, getWidth()/2 + 75 + 125, 75+15 + (60-30)/2, null);
	        
	        g2.drawLine(getWidth()/2 + 75 + 125 + 30 + 15, 75+15 + 60/2, getWidth()/2 + 75 + 125 + 30 + 50 - 15, 75+15 + 60/2);
			
			g2.drawImage(addicon, getWidth()/2 + 75 + 125 + 30 + 50 + 150, 75+15 + (60-30)/2, null);
	        
	        g2.drawLine(getWidth()/2 + 75 + 125 + 30 + 50 + 150 + 30 + 15, 75+15 + 60/2 - 3, getWidth()/2 + 75 + 125 + 30 + 50 + 150 + 30 + 50 - 15, 75+15 + 60/2 - 3);
	        g2.drawLine(getWidth()/2 + 75 + 125 + 30 + 50 + 150 + 30 + 15, 75+15 + 60/2 + 3, getWidth()/2 + 75 + 125 + 30 + 50 + 150 + 30 + 50 - 15, 75+15 + 60/2 + 3);
			
			if (day.getPlan().getPredictedCalorieBurn() <= -500) g2.drawImage(checkmark, getWidth()/2 + 75 + image.getWidth() + 25 - 15 - 30, 75+15 + (60-30)/2, null);
			else g2.drawImage(exclmark, getWidth()/2 + 75 + image.getWidth() + 25 - 15 - 30, 75+15 + (60-30)/2, null);
			
			LinkedList<Meal> myMeals = day.getPlan().getMeals();
			
			for(int i = 0; i < 7 && myDayMealsIndex+i < myMeals.size(); i++) {
				g.setColor(new Color(255,255,255,120));
				
				g.drawImage(xmark,getWidth()/2 + 75 + (170-200)/2 - 50 + (50-30)/2, 75+60+30 + (60-30)/2 + i*70, null);
				g.drawRoundRect(getWidth()/2 + 75 + (170-200)/2, 75+60+30+i*70, 200, 60, 15, 15);
			}
			
			LinkedList<Workout> myWorkouts = day.getPlan().getWorkouts();

			g.drawRoundRect(getWidth()/2 + 75 + 125 + 30 + 50 + (195-200)/2, 75+60+30, 200, 60, 15, 15);
			
			for(int i = 0; i < 7 && myDayWorkoutsIndex+i < myWorkouts.size(); i++) {
				g.setColor(new Color(255,255,255,120));
				g.drawImage(xmark,getWidth()/2 + 75 + 125 + 30 + 50 + (195-200)/2 + 200 + (50-30)/2, 75+60+30 + (60-30)/2 + (i+1)*70, null);
				g.drawRoundRect(getWidth()/2 + 75 + 125 + 30 + 50 + (195-200)/2, 75+60+30+(i+1)*70, 200, 60, 15, 15);
			}
		}
    }

    public Meal getAddDishesMeal() { return this.addDishesMeal; }
    public void setAddDishesMeal(Meal meal) { this.addDishesMeal = meal; }
    public void setAddDishesMealName() { this.addDishesMeal.setName(this.nameInput.getText());}

	public Day getDay() {
		return day;
	}
	
	public void setDay(Day day, int screen) {
		this.day = day;
		this.toggleMealWorkoutScreen(screen);
	}
	
	public int getMyDayMealsIndex() {
		return myDayMealsIndex;
	}

	public void setMyDayMealsIndex(int myDayMealsIndex) {
		this.myDayMealsIndex = myDayMealsIndex;
	}

	public int getMyDayWorkoutsIndex() {
		return myDayWorkoutsIndex;
	}

	public void setMyDayWorkoutsIndex(int myDayWorkoutsIndex) {
		this.myDayWorkoutsIndex = myDayWorkoutsIndex;
	}

	public void removeMeal(int index) {
		this.day.getPlan().removeMeal(index);
		this.toggleMealWorkoutScreen(screen);
	}

	public void removeWorkout(int index) {
		this.day.getPlan().removeWorkout(index);
		this.toggleMealWorkoutScreen(screen);
	}

	public int getMyMealsIndex(int i) {
		return this.myMealsDishesIndex[i];
	}

	public void setMyMealsIndex(int myMealsIndex, int i) {
		this.myMealsDishesIndex[i] = this.myMealsDishesIndex[i];
	}
}
