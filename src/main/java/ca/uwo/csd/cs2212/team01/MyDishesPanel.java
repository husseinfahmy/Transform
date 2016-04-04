
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

public class MyDishesPanel extends JPanel implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private MainWindow mainWindow;
	
	private JButton[] listRemoveButton, listDisplayButton;
	private JLabel[] listLabel;
	private JLabel noDishes;
	
	private int myDishesIndex;
	
	public MyDishesPanel(MainWindow mainWindow) {
		this.mainWindow = mainWindow;
		
		this.setMyDishesIndex(0);

		this.setLayout(null);
		this.setOpaque(false);
    	this.setLocation(1480*2/3 + 30, 75);
    	this.setSize(1480/3 - 60, 800 - 75);
    	
    	this.initUI();
	}
	
	public void initUI() {
		JLabel label = new JLabel("My Dishes", JLabel.LEFT);
		label.setFont(mainWindow.FONT_HELVETICA_NEUE_THIN.deriveFont(30.0f));
		Dimension size = label.getPreferredSize();
		label.setBounds((getWidth() - size.width)/2, 30 + (60-size.height)/2, size.width, size.height);
		label.setForeground(new Color(255,255,255,200));
		this.add(label);

		noDishes = new JLabel("No Meals");
		noDishes.setFont(mainWindow.FONT_HELVETICA_NEUE_THIN.deriveFont(25.0f));
		size = noDishes.getPreferredSize();
		noDishes.setBounds((getWidth() - size.width)/2, 68 + (getHeight()-size.height-68)/2, size.width, size.height);
		noDishes.setForeground(new Color(255,255,255,200));
		
		listRemoveButton = new JButton[7];
		listDisplayButton = new JButton[7];
		listLabel = new JLabel[7];
		
		for(int i = 0; i < 7; i++) {
			listLabel[i] = new JLabel();
			listLabel[i].setFont(mainWindow.FONT_HELVETICA_NEUE_THIN.deriveFont(20.0f));
			listLabel[i].setForeground(new Color(255,255,255,200));
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
			listRemoveButton[i].setBounds((getWidth() - 200)/2 + 200 + (50-30)/2, 30+68+(getHeight()-(30+68+70*7-10))/2 + (60-30)/2 + i*70, 30, 30);
			listRemoveButton[i].addActionListener(new ButtonActionListener(9, i, mainWindow));
			
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
			listDisplayButton[i].addActionListener(new ButtonActionListener(11, i, mainWindow));
		}
	}
	
	public int getScrollIndex() { return this.getMyDishesIndex(); }
	
	public void removeDish(int index) {
		if (this.mainWindow.getMyMeals().size() <= getMyDishesIndex()+index) return;
		this.mainWindow.getMyDishes().remove(getMyDishesIndex()+index);
		this.repaint();
	}
    
    private void removeListFromPanel() {
    	for(int i = 0; i < 7; i++) {
    		this.remove(listRemoveButton[i]);
    		this.remove(listDisplayButton[i]);
    		this.remove(listLabel[i]);
    	}
    	this.remove(noDishes);
    }
	
    /* (non-Javadoc)
     * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
     */
    @Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		this.setLayout(null);
		this.setOpaque(false);
    	this.setLocation(1480*2/3 + 30, 75);
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

		Meal myDish;
		LinkedList<Meal> myDishes = mainWindow.getMyDishes();
		
		if (myDishes.size() <= 0) this.add(noDishes);
		
		for(int i = 0; i < 7 && getMyDishesIndex()+i < myDishes.size(); i++) {
			myDish = myDishes.get(myDishes.size()-1-(getMyDishesIndex()+i));
			
			g.setColor(new Color(255,255,255,120));
			listLabel[i].setText(myDish.getName());
			Dimension size = listLabel[i].getPreferredSize();
			listLabel[i].setBounds((getWidth() - size.width)/2, 30+68+(getHeight()-(30+68+70*7-10))/2 + (60-size.height)/2 + i*70, size.width, size.height);
			this.add(listLabel[i]);
			
			g.drawImage(image, (getWidth() - 200)/2 + 200 + (50-30)/2, 30+68+(getHeight()-(30+68+70*7-10))/2 + (60-30)/2 + i*70, null);
			g.drawRoundRect((getWidth() - 200)/2, 30+68+(getHeight()-(30+68+70*7-10))/2+i*70, 200, 60, 15, 15);
			
			this.add(listRemoveButton[i]);
			this.add(listDisplayButton[i]);
		}
    }

	public int getMyDishesIndex() {
		return myDishesIndex;
	}

	public void setMyDishesIndex(int myDishesIndex) {
		this.myDishesIndex = myDishesIndex;
	}
}

