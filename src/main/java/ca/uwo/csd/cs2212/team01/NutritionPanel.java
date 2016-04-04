
package ca.uwo.csd.cs2212.team01;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
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
import javax.swing.JTextArea;

public class NutritionPanel extends JPanel implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private MainWindow mainWindow;
	
	private JButton servingUnit;
	private JTextArea servingSizeInput, fatsInput, proteinInput, carbsInput, calInput, nameInput;
	
	private boolean cupServings, mealScreen;
	
	private int nameFillHeight, servingInputHeight;
	private int[] lineHeight;
	
	public NutritionPanel(MainWindow mainWindow, boolean mealScreen) {
		this.mainWindow = mainWindow;
		
		this.setMealScreen(mealScreen);
		this.cupServings = false;
		
		this.setLayout(null);
		this.setOpaque(false);
    	this.setLocation(15, 75);
    	this.setSize(1480/3 - 30, 800-75);

    	int currHeight = 0;
    	lineHeight = new int[5];
    	
    	currHeight = 0;
		
		JLabel label = new JLabel("1", JLabel.LEFT);
		label.setFont(mainWindow.FONT_HELVETICA_NEUE_THIN.deriveFont(30.0f));
		Dimension size = label.getPreferredSize();
		label.setBounds((getWidth() - size.width)/2, (80-size.height)/2, size.width, size.height);
		label.setForeground(Color.WHITE);
		this.add(label);
		
		currHeight += 80;
		
		if (mealScreen) label = new JLabel("What food item are you having?", JLabel.LEFT);
		else label = new JLabel("What ingredient are you adding?", JLabel.LEFT);
		label.setFont(mainWindow.FONT_HELVETICA_NEUE_THIN.deriveFont(25.0f));
		size = label.getPreferredSize();
		label.setBounds((getWidth() - size.width)/2, currHeight, size.width, size.height);
		label.setForeground(new Color(255,255,255,150));
		this.add(label);
		
		currHeight += size.height + 15;
		
		nameFillHeight = currHeight;
		
		label = new JLabel("Enter Name of Food Item", JLabel.LEFT);
		label.setFont(mainWindow.FONT_HELVETICA_NEUE_THIN.deriveFont(Font.ITALIC, 22.0f));
		size = label.getPreferredSize();
		label.setBounds((getWidth() - size.width)/2, currHeight + (60-size.height)/2, size.width, size.height);
		label.setForeground(new Color(255,255,255,75));
		//this.add(label);
    	
		nameInput = new JTextArea("Enter Name of Food Item");
    	nameInput.setOpaque(false);
    	nameInput.setFont(mainWindow.FONT_HELVETICA_NEUE_THIN.deriveFont(27.0f));
		size = nameInput.getPreferredSize();
		nameInput.setBounds(100, currHeight + (60-size.height)/2, getWidth(), size.height);
		nameInput.setCaretColor(new Color(255,255,255,200));
		nameInput.setForeground(new Color(255,255,255,200));
		this.add(nameInput);
		
		currHeight += 60 + 15;
		
		label = new JLabel("<html>Fill out the <b>Nutrition Facts</b> below:</html>", JLabel.LEFT);
		label.setFont(mainWindow.FONT_HELVETICA_NEUE_THIN.deriveFont(30.0f));
		size = label.getPreferredSize();
		label.setBounds((getWidth() - size.width)/2, currHeight, size.width, size.height);
		label.setForeground(new Color(255,255,255,200));
		this.add(label);
		
		currHeight += size.height + 15;
		
		servingInputHeight = currHeight;
		
		label = new JLabel("Serving Size:", JLabel.LEFT);
		label.setFont(mainWindow.FONT_HELVETICA_NEUE_THIN.deriveFont(27.0f));
		size = label.getPreferredSize();
		label.setBounds(0, currHeight + (60-size.height)/2, size.width, size.height);
		label.setForeground(new Color(255,255,255,150));
		this.add(label);
    	
		servingSizeInput = new JTextArea("0");
    	servingSizeInput.setOpaque(false);
    	servingSizeInput.setFont(mainWindow.FONT_HELVETICA_NEUE_THIN.deriveFont(27.0f));
		size = servingSizeInput.getPreferredSize();
		servingSizeInput.setBounds(getWidth()- 50 - 50, currHeight + (60-size.height)/2, 50, size.height);
		servingSizeInput.setCaretColor(new Color(255,255,255,150));
		servingSizeInput.setForeground(new Color(255,255,255,150));
		this.add(servingSizeInput);

    	if (cupServings) servingUnit = new JButton("Cup");
    	else servingUnit = new JButton("g");
		
		servingUnit.setFont(mainWindow.FONT_HELVETICA_NEUE_THIN.deriveFont(27.0f));
		servingUnit.setBackground(null);
		servingUnit.setBorder(null);
		servingUnit.setFocusPainted(false);
		servingUnit.setMargin(new Insets(0, 0, 0, 0));
		servingUnit.setContentAreaFilled(false);
		servingUnit.setBorderPainted(false);
		servingUnit.setOpaque(false);
		servingUnit.setForeground(new Color(255,255,255,150));
		servingUnit.setFocusable(false);
		size = servingUnit.getPreferredSize();
		servingUnit.setBounds(getWidth() - 50 + (50-size.width)/2, currHeight + (60-size.height)/2, size.width, size.height);
        servingUnit.addActionListener(new ButtonActionListener(6, 0, mainWindow));
		this.add(servingUnit);
		
		currHeight += 60;
		
		lineHeight[0] = currHeight;
		
		label = new JLabel("Amount per Serving", JLabel.LEFT);
		label.setFont(mainWindow.FONT_HELVETICA_NEUE_THIN.deriveFont(25.0f));
		size = label.getPreferredSize();
		label.setBounds((getWidth()-size.width)/2, currHeight + (60-size.height)/2, size.width, size.height);
		label.setForeground(new Color(255,255,255,75));
		this.add(label);
		
		currHeight += 60;
		
		lineHeight[1] = currHeight;
		
		label = new JLabel("Calories:", JLabel.LEFT);
		label.setFont(mainWindow.FONT_HELVETICA_NEUE_THIN.deriveFont(Font.BOLD, 27.0f));
		size = label.getPreferredSize();
		label.setBounds(0, currHeight + (60-size.height)/2, size.width, size.height);
		label.setForeground(new Color(255,255,255,200));
		this.add(label);
    	
		calInput = new JTextArea("0");
    	calInput.setOpaque(false);
    	calInput.setFont(mainWindow.FONT_HELVETICA_NEUE_THIN.deriveFont(27.0f));
		size = calInput.getPreferredSize();
		calInput.setBounds(getWidth()- 50 - 50, currHeight + (60-size.height)/2, 50, size.height);
		calInput.setCaretColor(new Color(255,255,255,150));
		calInput.setForeground(new Color(255,255,255,150));
		this.add(calInput);
		
		label = new JLabel("Cal", JLabel.LEFT);
		label.setFont(mainWindow.FONT_HELVETICA_NEUE_THIN.deriveFont(27.0f));
		size = label.getPreferredSize();
		label.setBounds(getWidth()-50 + (50-size.width)/2, currHeight + (60-size.height)/2, size.width, size.height);
		label.setForeground(new Color(255,255,255,150));
		this.add(label);
		
		currHeight += 60;
		
		lineHeight[2] = currHeight;
		
		label = new JLabel("Total Carbs:", JLabel.LEFT);
		label.setFont(mainWindow.FONT_HELVETICA_NEUE_THIN.deriveFont(27.0f));
		size = label.getPreferredSize();
		label.setBounds(0, currHeight + (60-size.height)/2, size.width, size.height);
		label.setForeground(new Color(255,255,255,150));
		this.add(label);
    	
		carbsInput = new JTextArea("0");
    	carbsInput.setOpaque(false);
    	carbsInput.setFont(mainWindow.FONT_HELVETICA_NEUE_THIN.deriveFont(27.0f));
		size = carbsInput.getPreferredSize();
		carbsInput.setBounds(getWidth()- 50 - 50, currHeight + (60-size.height)/2, 50, size.height);
		carbsInput.setCaretColor(new Color(255,255,255,150));
		carbsInput.setForeground(new Color(255,255,255,150));
		this.add(carbsInput);
		
		label = new JLabel("g", JLabel.LEFT);
		label.setFont(mainWindow.FONT_HELVETICA_NEUE_THIN.deriveFont(27.0f));
		size = label.getPreferredSize();
		label.setBounds(getWidth()-50 + (50-size.width)/2, currHeight + (60-size.height)/2, size.width, size.height);
		label.setForeground(new Color(255,255,255,150));
		this.add(label);
		
		currHeight += 60;
		
		lineHeight[3] = currHeight;
		
		label = new JLabel("Total Protein:", JLabel.LEFT);
		label.setFont(mainWindow.FONT_HELVETICA_NEUE_THIN.deriveFont(27.0f));
		size = label.getPreferredSize();
		label.setBounds(0, currHeight + (60-size.height)/2, size.width, size.height);
		label.setForeground(new Color(255,255,255,150));
		this.add(label);
    	
		proteinInput = new JTextArea("0");
    	proteinInput.setOpaque(false);
    	proteinInput.setFont(mainWindow.FONT_HELVETICA_NEUE_THIN.deriveFont(27.0f));
		size = proteinInput.getPreferredSize();
		proteinInput.setBounds(getWidth()- 50 - 50, currHeight + (60-size.height)/2, 50, size.height);
		proteinInput.setCaretColor(new Color(255,255,255,150));
		proteinInput.setForeground(new Color(255,255,255,150));
		this.add(proteinInput);
		
		label = new JLabel("g", JLabel.LEFT);
		label.setFont(mainWindow.FONT_HELVETICA_NEUE_THIN.deriveFont(27.0f));
		size = label.getPreferredSize();
		label.setBounds(getWidth()-50 + (50-size.width)/2, currHeight + (60-size.height)/2, size.width, size.height);
		label.setForeground(new Color(255,255,255,150));
		this.add(label);
		
		currHeight += 60;
		
		lineHeight[4] = currHeight;
		
		label = new JLabel("Total Fats:", JLabel.LEFT);
		label.setFont(mainWindow.FONT_HELVETICA_NEUE_THIN.deriveFont(27.0f));
		size = label.getPreferredSize();
		label.setBounds(0, currHeight + (60-size.height)/2, size.width, size.height);
		label.setForeground(new Color(255,255,255,150));
		this.add(label);
    	
		fatsInput = new JTextArea("0");
    	fatsInput.setOpaque(false);
    	fatsInput.setFont(mainWindow.FONT_HELVETICA_NEUE_THIN.deriveFont(27.0f));
		size = fatsInput.getPreferredSize();
		fatsInput.setBounds(getWidth()- 50 - 50, currHeight + (60-size.height)/2, 50, size.height);
		fatsInput.setCaretColor(new Color(255,255,255,150));
		fatsInput.setForeground(new Color(255,255,255,150));
		this.add(fatsInput);
		
		label = new JLabel("g", JLabel.LEFT);
		label.setFont(mainWindow.FONT_HELVETICA_NEUE_THIN.deriveFont(27.0f));
		size = label.getPreferredSize();
		label.setBounds(getWidth()-50 + (50-size.width)/2, currHeight + (60-size.height)/2, size.width, size.height);
		label.setForeground(new Color(255,255,255,150));
		this.add(label);
		
		currHeight += 60;
		
		JButton button = new JButton("or Auto-Fill by Uploading A Photo");
		button.setFont(mainWindow.FONT_HELVETICA_NEUE_THIN.deriveFont(27.0f));
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
		button.setBounds(0, getHeight() - 60 - 15, getWidth(), 60);
		button.addActionListener(new ButtonActionListener(6, 1, mainWindow));
		this.add(button);
	}
    
	public void clearTextFields() {
		servingSizeInput.setText("0");
		fatsInput.setText("0");
		proteinInput.setText("0");
		carbsInput.setText("0");
		calInput.setText("0");
		nameInput.setText("Enter Name of Food Item");
	}

    public String getFoodName() { return this.nameInput.getText(); }
    public String getServingSize() { return this.servingSizeInput.getText(); }
    public String getCalories() { return this.calInput.getText(); }
    public String getProteins() { return this.proteinInput.getText(); }
    public String getCarbs() { return this.carbsInput.getText(); }
    public String getFats() { return this.fatsInput.getText(); }

    public String getServingUnit() { return this.servingUnit.getText(); }
    public void setServingUnit(String newUnit) {
    	this.servingUnit.setText(newUnit);
    	Dimension size = this.servingUnit.getPreferredSize();
    	this.servingUnit.setBounds(getWidth() - 50 + (50-size.width)/2, servingInputHeight + (60-size.height)/2, size.width, size.height);
    }
    
    /* (non-Javadoc)
     * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
     */
    @Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		this.setLayout(null);
		this.setOpaque(false);
    	this.setLocation(15, 75);
    	this.setSize(1480/3 - 30, 800-75);
		
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		BufferedImage image = null;
		try {
			image = ImageIO.read(new File("UI/circle-bg.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		g.drawImage(image, (getWidth()-image.getWidth())/2, (80-image.getHeight())/2, null);
		
		g.setColor(new Color(255,255,255,75));
		g.fillRect(15, nameFillHeight, getWidth(), 60);

		for(int i = 0; i < 5; i++) g.drawLine(0, lineHeight[i], getWidth(), lineHeight[i]);
		
		//g.setColor(new Color(255,255,255,150));
		//g.fillRoundRect(0, getHeight()-15-60, getWidth(), 60, 8, 8);image = null;
		try {
			image = ImageIO.read(new File("UI/btn-bg.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		g.drawImage(image, 0,  getHeight() - 60 - 15, null);
	}

	public boolean isMealScreen() {
		return mealScreen;
	}

	public void setMealScreen(boolean mealScreen) {
		this.mealScreen = mealScreen;
	}
}

