package ca.uwo.csd.cs2212.team01;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class FoodServingSizePanel extends JPanel implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private MainWindow mainWindow;
	
	private JLabel feedbackLabel;
	private JTextArea foodServingSizeInput;
	private JButton foodServingUnit;
	
	private boolean foodCupServings, mealScreen;
	
	public FoodServingSizePanel(MainWindow mainWindow, boolean mealScreen) {
		this.mainWindow = mainWindow;
		
		this.setMealScreen(mealScreen);
		this.foodCupServings = false;
		
		this.setLayout(null);
		this.setOpaque(false);
    	this.setLocation(1480/3 + 15, 75);
    	this.setSize(1480/3 - 30, 800-75);

    	int currHeight = 0;
		
		JLabel label = new JLabel("2", JLabel.LEFT);
		label.setFont(mainWindow.FONT_HELVETICA_NEUE_THIN.deriveFont(30.0f));
		Dimension size = label.getPreferredSize();
		label.setBounds((getWidth() - size.width)/2, (80-size.height)/2, size.width, size.height);
		label.setForeground(Color.WHITE);
		this.add(label);
		
		currHeight += 80;
		
		if (mealScreen) label = new JLabel("What serving size are you having?", JLabel.LEFT);
		else label = new JLabel("How much are you adding?", JLabel.LEFT);
		label.setFont(mainWindow.FONT_HELVETICA_NEUE_THIN.deriveFont(25.0f));
		size = label.getPreferredSize();
		label.setBounds((getWidth() - size.width)/2, currHeight, size.width, size.height);
		label.setForeground(new Color(255,255,255,150));
		this.add(label);
    	
		foodServingSizeInput = new JTextArea();
		foodServingSizeInput.setOpaque(false);
		foodServingSizeInput.setFont(mainWindow.FONT_HELVETICA_NEUE_THIN.deriveFont(27.0f));
		size = foodServingSizeInput.getPreferredSize();
		foodServingSizeInput.setBounds(0, (getHeight()-100)/2 + (100-size.height)/2, 60, 100);
		foodServingSizeInput.setCaretColor(new Color(255,255,255,150));
		foodServingSizeInput.setForeground(new Color(255,255,255,150));
		this.add(foodServingSizeInput);

    	if (foodCupServings) foodServingUnit = new JButton("Cup");
    	else foodServingUnit = new JButton("g");
		
		foodServingUnit.setFont(mainWindow.FONT_HELVETICA_NEUE_THIN.deriveFont(35.0f));
		foodServingUnit.setBackground(null);
		foodServingUnit.setBorder(null);
		foodServingUnit.setFocusPainted(false);
		foodServingUnit.setMargin(new Insets(0, 0, 0, 0));
		foodServingUnit.setContentAreaFilled(false);
		foodServingUnit.setBorderPainted(false);
		foodServingUnit.setOpaque(false);
		foodServingUnit.setForeground(new Color(255,255,255,150));
		foodServingUnit.setFocusable(false);
		size = foodServingUnit.getPreferredSize();
		foodServingUnit.setBounds(60 + 15, (getHeight()-100)/2 + (100-size.height)/2, size.width, size.height);
        foodServingUnit.addActionListener(new ButtonActionListener(6, 2, mainWindow));
		this.add(foodServingUnit);

		JButton button = new JButton("Add >");
		button.setFont(mainWindow.FONT_HELVETICA_NEUE_THIN.deriveFont(Font.ITALIC, 35.0f));
		button.setBackground(null);
		button.setBorder(null);
		button.setFocusPainted(false);
		button.setMargin(new Insets(0, 0, 0, 0));
		button.setContentAreaFilled(false);
		button.setBorderPainted(false);
		button.setOpaque(false);
		button.setForeground(new Color(255,255,255,200));
		button.setFocusable(false);
		size = button.getPreferredSize();
		button.setBounds(60 + 15 + 15 + 50 + 10, (getHeight()-100)/2 + (100-size.height)/2, size.width, size.height);
		button.addActionListener(new ButtonActionListener(6, 3, mainWindow));
		this.add(button);
		
		feedbackLabel = new JLabel();
		feedbackLabel.setFont(mainWindow.FONT_HELVETICA_NEUE_THIN.deriveFont(27.0f));
		size = label.getPreferredSize();
		feedbackLabel.setBounds((getWidth()-size.width)/2, getHeight() - 60 - 15 + (60-size.height)/2, size.width, size.height);
		feedbackLabel.setForeground(new Color(255,255,255,150));
		this.add(feedbackLabel);
	}
    
	public void clearTextFields() {
		foodServingSizeInput.setText("");
	}
    
    public void clearFeedbackLabel() { this.feedbackLabel.setText(""); }
    public void setFeedbackLabel(String feedback) {
    	feedbackLabel.setText(feedback);
		Dimension size = feedbackLabel.getPreferredSize();
		feedbackLabel.setBounds((getWidth()-size.width)/2, feedbackLabel.getY(), size.width, size.height);
    }

    public String getFoodServingSize() { return this.foodServingSizeInput.getText(); }
    public String getFoodServingUnit() { return this.foodServingUnit.getText(); }
    public void setFoodServingUnit(String newUnit) {
    	this.foodServingUnit.setText(newUnit);
    	Dimension size = this.foodServingUnit.getPreferredSize();
    	this.foodServingUnit.setBounds(60 + 15, (getHeight()-100)/2 + (100-size.height)/2, size.width, size.height);
    }
    
    /* (non-Javadoc)
     * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
     */
    @Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		this.setLayout(null);
		this.setOpaque(false);
    	this.setLocation(1480/3 + 15, 75);
    	this.setSize(1480/3 - 30, 800-75);
    	
		BufferedImage image = null;
		try {
			image = ImageIO.read(new File("UI/circle-bg.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		g.drawImage(image, (getWidth()-image.getWidth())/2, (80-image.getHeight())/2, null);
		
		g.setColor(new Color(255,255,255,75));
		g.drawLine(60, (getHeight()-100)/2, 60, (getHeight()-100)/2 + 100);
    }

	public boolean isMealScreen() {
		return mealScreen;
	}

	public void setMealScreen(boolean mealScreen) {
		this.mealScreen = mealScreen;
	}
}