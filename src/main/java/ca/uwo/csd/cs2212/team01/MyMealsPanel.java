
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

public class MyMealsPanel extends JPanel implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private MainWindow mainWindow;
	
	private JLabel noMeals;
	private JButton[] listRemoveButton, listDisplayButton, scrollButtons;
	private JLabel[][] listLabel;
	
	private int myMealsIndex;
	
	public MyMealsPanel(MainWindow mainWindow) {
		this.mainWindow = mainWindow;
		this.setMyMealsIndex(0);

		this.setLayout(null);
		this.setOpaque(false);
    	this.setLocation(30, 75);
    	this.setSize(1480/3 - 60, 800 - 75);
    	
    	this.initUI();
	}
	
	public void initUI() {
		JLabel label = new JLabel("My Meals", JLabel.LEFT);
		label.setFont(mainWindow.FONT_HELVETICA_NEUE_THIN.deriveFont(30.0f));
		Dimension size = label.getPreferredSize();
		label.setBounds((getWidth() - size.width)/2, 30 + (60-size.height)/2, size.width, size.height);
		label.setForeground(new Color(255,255,255,200));
		this.add(label);

		noMeals = new JLabel("No Meals");
		noMeals.setFont(mainWindow.FONT_HELVETICA_NEUE_THIN.deriveFont(25.0f));
		size = noMeals.getPreferredSize();
		noMeals.setBounds((getWidth() - size.width)/2, 68 + (getHeight()-size.height-68)/2, size.width, size.height);
		noMeals.setForeground(new Color(255,255,255,200));
		
		listDisplayButton = new JButton[7];
		listRemoveButton = new JButton[7];
		listLabel = new JLabel[2][7];
		scrollButtons = new JButton[2];

		scrollButtons[0] = new JButton("v");
		scrollButtons[1] = new JButton("^");
		
		for(int i = 0; i < 2; i++) {
			scrollButtons[i].setFont(mainWindow.FONT_HELVETICA_NEUE_THIN.deriveFont(50.0f));
			scrollButtons[i].setForeground(new Color(255,255,255,200));
			scrollButtons[i].setBackground(null);
			scrollButtons[i].setBorder(null);
			scrollButtons[i].setFocusPainted(false);
			scrollButtons[i].setMargin(new Insets(0, 0, 0, 0));
			scrollButtons[i].setContentAreaFilled(false);
			scrollButtons[i].setBorderPainted(false);
			scrollButtons[i].setOpaque(false);
			//scrollButtons[i].setForeground(new Color(255,255,255,200));
			scrollButtons[i].setFocusable(false);
			//size = scrollButtons[i].getPreferredSize();
			scrollButtons[i].setBounds((getWidth() - size.width*2 - 30)/2 + i*(size.width+30), 30+68+(getHeight()-(30+68+70*7-10))/2+7*70, size.width, size.height);
			scrollButtons[i].addActionListener(new ButtonActionListener(8, i, mainWindow));
		}
		
		for(int i = 0; i < 7; i++) {
			listLabel[0][i] = new JLabel();
			listLabel[0][i].setFont(mainWindow.FONT_HELVETICA_NEUE_THIN.deriveFont(20.0f));
			listLabel[0][i].setForeground(new Color(255,255,255,200));
			//this.add(listLabel[i]);
			
			listLabel[1][i] = new JLabel();
			listLabel[1][i].setFont(mainWindow.FONT_HELVETICA_NEUE_THIN.deriveFont(20.0f));
			listLabel[1][i].setForeground(new Color(106, 185, 255, 255));
			//this.add(listLabel[i]);
			
			listRemoveButton[i] = new JButton();
			listRemoveButton[i].setBackground(null);
			listRemoveButton[i].setBorder(null);
			listRemoveButton[i].setFocusPainted(false);
			listRemoveButton[i].setMargin(new Insets(0, 0, 0, 0));
			listRemoveButton[i].setContentAreaFilled(false);
			listRemoveButton[i].setBorderPainted(false);
			listRemoveButton[i].setOpaque(false);
			//listRemoveButton[i].setForeground(new Color(255,255,255,200));
			listRemoveButton[i].setFocusable(false);
			//size = listRemoveButton[i].getPreferredSize();
			listRemoveButton[i].setBounds((getWidth() - 200)/2 - 50 + (50-30)/2, 30+68+(getHeight()-(30+68+70*7-10))/2 + (60-30)/2 + i*70, 30, 30);
			listRemoveButton[i].addActionListener(new ButtonActionListener(8, i, mainWindow));
			
			listDisplayButton[i] = new JButton();
			listDisplayButton[i].setBackground(null);
			listDisplayButton[i].setBorder(null);
			listDisplayButton[i].setFocusPainted(false);
			listDisplayButton[i].setMargin(new Insets(0, 0, 0, 0));
			listDisplayButton[i].setContentAreaFilled(false);
			listDisplayButton[i].setBorderPainted(false);
			listDisplayButton[i].setOpaque(false);
			//listDisplayButton[i].setForeground(new Color(255,255,255,200));
			listDisplayButton[i].setFocusable(false);
			//size = listDisplayButton[i].getPreferredSize();
			listDisplayButton[i].setBounds((getWidth() - 200)/2, 30+68+(getHeight()-(30+68+70*7-10))/2+i*70, 200, 60);
			listDisplayButton[i].addActionListener(new ButtonActionListener(10, i, mainWindow));
		}
	}
	
	public int getScrollIndex() { return this.getMyMealsIndex(); }
	
	public void removeMeal(int index) {
		if (this.mainWindow.getMyMeals().size() <= getMyMealsIndex()+index) return;
		this.mainWindow.getMyMeals().remove(getMyMealsIndex()+index);
		this.repaint();
	}
    
    private void removeListFromPanel() {
    	for(int i = 0; i < 7; i++) {
    		this.remove(listDisplayButton[i]);
    		this.remove(listRemoveButton[i]);
    		this.remove(listLabel[0][i]);
    		this.remove(listLabel[1][i]);
    	}
    	for(int i = 0; i < 2; i++) {
    		this.remove(scrollButtons[i]);
    	}
    	this.remove(noMeals);
    }
	
    /* (non-Javadoc)
     * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
     */
    @Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		this.setLayout(null);
		this.setOpaque(false);
    	this.setLocation(30, 75);
    	this.setSize(1480/3 - 60, 800 - 75);

    	removeListFromPanel();
    	
		BufferedImage image = null;
		try {
			image = ImageIO.read(new File("UI/head-bg.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		g.drawImage(image, 0, 30, null);
		
		image = null;
		try {
			image = ImageIO.read(new File("UI/xmark.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Meal myMeal;
		LinkedList<Meal> myMeals = mainWindow.getMyMeals();
		
		if (myMeals.size() <= 0) this.add(noMeals);
		
		for(int i = 0; i < 7 && myMealsIndex+i < myMeals.size(); i++) {
			myMeal = myMeals.get(myMeals.size()-1-(myMealsIndex+i));
			
			g.setColor(new Color(255,255,255,120));
			listLabel[0][i].setText(myMeal.getName());
			Dimension size = listLabel[0][i].getPreferredSize();
			listLabel[0][i].setBounds((getWidth() - size.width)/2, 30+68+(getHeight()-(30+68+70*7-10))/2 + (60-size.height*2)/2 + i*70, size.width, size.height);
			this.add(listLabel[0][i]);
			
			listLabel[1][i].setText("( + "+(int)myMeal.getCalories()+" Cal )");
			size = listLabel[1][i].getPreferredSize();
			listLabel[1][i].setBounds((getWidth() - size.width)/2, 30+68+(getHeight()-(30+68+70*7-10))/2 + size.height + (60-size.height*2)/2 + i*70, size.width, size.height);
			this.add(listLabel[1][i]);
			
			g.drawImage(image, (getWidth() - 200)/2 - 50 + (50-30)/2, 30+68+(getHeight()-(30+68+70*7-10))/2 + (60-30)/2 + i*70, null);
			g.drawRoundRect((getWidth() - 200)/2, 30+68+(getHeight()-(30+68+70*7-10))/2+i*70, 200, 60, 15, 15);
			
			this.add(listRemoveButton[i]);
			this.add(listDisplayButton[i]);
		}

		//this.add(scrollButtons[0]);
		//this.add(scrollButtons[1]);
    }

	public int getMyMealsIndex() {
		return myMealsIndex;
	}

	public void setMyMealsIndex(int myMealsIndex) {
		this.myMealsIndex = myMealsIndex;
	}
}

