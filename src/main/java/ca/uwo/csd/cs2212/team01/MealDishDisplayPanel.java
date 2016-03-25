package ca.uwo.csd.cs2212.team01;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.LinkedList;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MealDishDisplayPanel extends JPanel implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private MainWindow mainWindow;
	
	private boolean mealItem;
	private int itemIndex = -1;
	
	private JLabel[] listTitleLabel, mealListLabel;
	
	public MealDishDisplayPanel(MainWindow mainWindow) {
		this.mainWindow = mainWindow;
		
		this.setLayout(null);
		this.setOpaque(false);
    	this.setLocation(1480/3 + 30, 75);
    	this.setSize(1480/3 - 60, 800 - 75);
		
		this.listTitleLabel = new JLabel[2];
		this.mealListLabel = new JLabel[7];
		
		this.initUI();
	}
	
	private void initUI() {
		JLabel label = new JLabel("What's In This?", JLabel.LEFT);
		label.setFont(mainWindow.FONT_HELVETICA_NEUE_THIN.deriveFont(30.0f));
		Dimension size = label.getPreferredSize();
		label.setBounds((getWidth() - size.width)/2, 30 + (60-size.height)/2, size.width, size.height);
		label.setForeground(new Color(255,255,255,200));
		this.add(label);
		
		listTitleLabel[0] = new JLabel();
		listTitleLabel[0].setFont(mainWindow.FONT_HELVETICA_NEUE_THIN.deriveFont(20.0f));
		listTitleLabel[0].setForeground(new Color(255,255,255,200));
		
		listTitleLabel[1] = new JLabel();
		listTitleLabel[1].setFont(mainWindow.FONT_HELVETICA_NEUE_THIN.deriveFont(20.0f));
		listTitleLabel[1].setForeground(new Color(106, 185, 255, 200));
		
		for(int i = 0; i < 7; i++) {
			mealListLabel[i] = new JLabel();
			mealListLabel[i].setFont(mainWindow.FONT_HELVETICA_NEUE_THIN.deriveFont(18.0f));
			mealListLabel[i].setForeground(new Color(255,255,255,150));
		}
		
		JButton button = new JButton("Create New Meal");
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
		size = button.getPreferredSize();
		button.setBounds(0, getHeight() - 60 - 30, 209, 60);
		button.addActionListener(new ButtonActionListener(12, 0, mainWindow));
		this.add(button);

		button = new JButton("Create New Dish");
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
		size = button.getPreferredSize();
		button.setBounds(getWidth()-209, getHeight() - 60 - 30, 209, 60);
		button.addActionListener(new ButtonActionListener(12, 1, mainWindow));
		this.add(button);
	}
	
	public void displayItem(boolean mealItem, int index) {
		this.setMealItem(mealItem);
		this.setItemIndex(index);
		this.repaint();
	}
	
	private void removeItemFromPanel() {
		this.remove(listTitleLabel[0]);
		this.remove(listTitleLabel[1]);
		for(int i = 0; i < 7; i++) this.remove(mealListLabel[i]);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		this.setLayout(null);
		this.setOpaque(false);
    	this.setLocation(1480/3 + 30, 75);
    	this.setSize(1480/3 - 60, 800 - 75);

    	removeItemFromPanel();
    	
		BufferedImage image = null;
		try {
			image = ImageIO.read(new File("UI/head-bg.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		g.drawImage(image, 0, 30, null);
		
		Dimension size;
		
		if (getItemIndex() != -1) {
			Meal myItem;
			
			if (isMealItem()) {
				myItem = mainWindow.getMyMeals().get(itemIndex);
				
				g.setColor(new Color(255,255,255,120));
				listTitleLabel[0].setText(myItem.getName());
				size = listTitleLabel[0].getPreferredSize();
				listTitleLabel[0].setBounds((getWidth() - size.width)/2, 30+68+(getHeight()-(30+68+70*7-10))/2 + (60-size.height*2)/2 - 30, size.width, size.height);
				this.add(listTitleLabel[0]);
				
				listTitleLabel[1].setText("( + "+myItem.getCalories()+" Cal )");
				size = listTitleLabel[1].getPreferredSize();
				listTitleLabel[1].setBounds((getWidth() - size.width)/2, 30+68+(getHeight()-(30+68+70*7-10))/2 + size.height + (60-size.height*2)/2 - 30, size.width, size.height);
				this.add(listTitleLabel[1]);
				
				g.drawRoundRect((getWidth() - 200)/2, 30+68+(getHeight()-(30+68+70*7-10))/2 - 30, 200, 60, 15, 15);
			}else {
				myItem = mainWindow.getMyDishes().get(itemIndex);
				
				g.setColor(new Color(255,255,255,120));
				listTitleLabel[0].setText(myItem.getName());
				size = listTitleLabel[0].getPreferredSize();
				listTitleLabel[0].setBounds((getWidth() - size.width)/2, 30+68+(getHeight()-(30+68+70*7-10))/2 + (60-size.height)/2 - 30, size.width, size.height);
				this.add(listTitleLabel[0]);
				
				g.drawRoundRect((getWidth() - 200)/2, 30+68+(getHeight()-(30+68+70*7-10))/2 - 30, 200, 60, 15, 15);
			}
			
			LinkedList<FoodServing> foodServings = myItem.getFoodServings();
			FoodServing foodServing;
			Macro foodServingMacros;
			
			for(int i = 0; i < foodServings.size(); i++) {
				foodServing = foodServings.get(i);
				foodServingMacros = foodServing.getMacros();
				g.fillRect((getWidth()-(getWidth() - 50))/2, 30+68+(getHeight()-(30+68+70*7-10))/2+60+30+i*55 - 30, getWidth() - 50, 50);
				mealListLabel[i].setText("<html><center>" + foodServing.getFood().getName() + "<br>"
						+ foodServing.getServingSize() + " " + foodServing.getServingUnit() + " | " + foodServingMacros.getCalories() + " cal</center></html>");
				/*listLabel[i].setText("<html><center>" + "name" + i + "<br>"
						+ "size" + i + " " + "unit" + i + " | " + "calories" + i + " cal</center></html>");*/
				//label.setFont(mainWindow.FONT_HELVETICA_NEUE_THIN.deriveFont(18.0f));
				size = mealListLabel[i].getPreferredSize();
				mealListLabel[i].setBounds((getWidth() - size.width)/2, 30+68+(getHeight()-(30+68+70*7-10))/2+60+30+i*55 - 30, size.width, size.height);
				//label.setForeground(new Color(255,255,255,150));
				this.add(mealListLabel[i]);
			}
		}

		image = null;
		try {
			image = ImageIO.read(new File("UI/btn-itemdisplay-bg.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		g.drawImage(image, 0,  getHeight() - 60 - 30, null);
		g.drawImage(image, getWidth()-image.getWidth(),  getHeight() - 60 - 30, null);
	}

	public int getItemIndex() {
		return itemIndex;
	}

	public void setItemIndex(int itemIndex) {
		this.itemIndex = itemIndex;
	}

	public boolean isMealItem() {
		return mealItem;
	}

	public void setMealItem(boolean mealItem) {
		this.mealItem = mealItem;
	}
}
