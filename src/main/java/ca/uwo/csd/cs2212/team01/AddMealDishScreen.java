package ca.uwo.csd.cs2212.team01;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class AddMealDishScreen extends JPanel {
	private MainWindow mainWindow;
	
	private NutritionPanel nutritionPanel;
	private FoodServingSizePanel servingSizePanel;
	private MealListPanel mealListPanel;
	
	private boolean mealScreen = true;

	/**
	 * Class Constructor
	 * @param mainWindow
	 */
	public AddMealDishScreen(MainWindow mainWindow) {
		this.mainWindow = mainWindow;
		
		this.setOpaque(false);
    	this.setLocation(0, 0);
    	this.setSize(1480, 800);
    	
		this.initUI();
	}
	
    /**
     * Renders the Add Meals & Dishes Screen
     */
    private void initUI() {
    	JPanel bannerPanel = new JPanel() {
    		@Override
    		protected void paintComponent(Graphics g) {
    			super.paintComponent(g);
    			
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
    			
    			image = null;
				try {
					image = ImageIO.read(new File("UI/btn-esm-bg.png"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			    if (mealScreen) g2.drawImage(image, getWidth()/2 - 200 - 15, 15, null);

    			g2.setColor(Color.WHITE);

		    	g2.drawRoundRect(getWidth()/2 - 200 - 15, 15, 200, getHeight()-30, 15, 15);
			    
    			button = new JButton("Meals");
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
    			button.setBounds(getWidth()/2 - 200 - 15, 15, 200, getHeight()-30);
    			button.addActionListener(new ButtonActionListener(6, 5, mainWindow));
    			this.add(button);
    			
    			if (!mealScreen) g2.drawImage(image, getWidth()/2 + 15, 15, null);
    			
		    	g2.drawRoundRect(getWidth()/2 + 15, 15, 200, getHeight()-30, 15, 15);
    			
    			button = new JButton("Dishes");
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
    			button.setBounds(getWidth()/2 + 15, 15, 200, getHeight()-30);
    			button.addActionListener(new ButtonActionListener(6, 6, mainWindow));
    			this.add(button);
    	        
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
    			Dimension size = exitBtn.getPreferredSize();
    			exitBtn.setBounds(getWidth()-size.width-12, 6, size.width, size.height);
    	        exitBtn.addActionListener(new ButtonActionListener(2, 0, mainWindow));
    	        this.add(exitBtn);
    		}
    	};
    	this.add(bannerPanel);
    	
    	nutritionPanel = new NutritionPanel(mainWindow, mealScreen);
    	this.add(nutritionPanel);
    	
    	servingSizePanel = new FoodServingSizePanel(mainWindow, mealScreen);
    	this.add(servingSizePanel);
    	
    	mealListPanel = new MealListPanel(mainWindow, mealScreen);
    	this.add(mealListPanel);
    }
    
    public boolean isMealScreen() { return this.mealScreen; }

    
    public void toggleMealDishScreen(boolean mealScreen) {
    	this.mealScreen = mealScreen;

    	this.remove(nutritionPanel);
    	this.remove(servingSizePanel);
    	this.remove(mealListPanel);
    	
    	nutritionPanel = new NutritionPanel(mainWindow, mealScreen);
    	this.add(nutritionPanel);

    	servingSizePanel = new FoodServingSizePanel(mainWindow, mealScreen);
    	this.add(servingSizePanel);

    	mealListPanel = new MealListPanel(mainWindow, mealScreen);
    	this.add(mealListPanel);
    	
    	this.repaint();
    }
    
    public void clearTextFields() {
    	this.nutritionPanel.clearTextFields();
    	//this.mealListPanel.clearTextFields();
    	this.servingSizePanel.clearTextFields();
    }
    
    public NutritionPanel getNutritionPanel() { return this.nutritionPanel; }
    public FoodServingSizePanel getServingSizePanel() { return this.servingSizePanel; }
    public MealListPanel getMealListPanel() { return this.mealListPanel; }
    
    public void redraw() {
    	//this.removeAll();
    	//this.initUI();
    	this.repaint();
    	//clearTextFields();
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
	}
}
