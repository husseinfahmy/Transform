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
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class MealListPanel extends JPanel implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private MainWindow mainWindow;
	
	private Meal meal = null;
	
	private JButton[] listButton;
	private JLabel[] listLabel;
	private JLabel listTitle;
	
	private JTextArea nameInput;
	
	private int listHeight;
	
	private boolean mealScreen;
	
	public MealListPanel(MainWindow mainWindow, boolean mealScreen) {
		this.mainWindow = mainWindow;
		
		this.mealScreen = mealScreen;
		
		this.setLayout(null);
		this.setOpaque(false);
    	this.setLocation(1480*2/3 + 15,75);
    	this.setSize(1480/3 - 30, 800-75);
    	
    	this.initUI();
	}
	
	public void initUI() {
    	int currHeight = 0;
		
		JLabel label = new JLabel("3", JLabel.LEFT);
		label.setFont(mainWindow.FONT_HELVETICA_NEUE_THIN.deriveFont(30.0f));
		Dimension size = label.getPreferredSize();
		label.setBounds((getWidth() - size.width)/2, (80-size.height)/2, size.width, size.height);
		label.setForeground(Color.WHITE);
		this.add(label);
		
		currHeight += 80;
		
		if (mealScreen) listTitle = new JLabel("This meal has: 0 Calories", JLabel.LEFT);
		else listTitle = new JLabel("<html><center>This meal has:<br>0 Ingredients | 0 Calories</center></html>", JLabel.LEFT);
		listTitle.setFont(mainWindow.FONT_HELVETICA_NEUE_THIN.deriveFont(25.0f));
		size = listTitle.getPreferredSize();
		listTitle.setBounds((getWidth() - size.width)/2, currHeight, size.width, size.height);
		listTitle.setForeground(new Color(255,255,255,150));
		this.add(listTitle);
		
		currHeight += size.height;
		
		listHeight = currHeight;
		
		listButton = new JButton[9];
		listLabel = new JLabel[9];

		for(int i = 0; i < 9; i++) {
			listButton[i] = new JButton();
			listButton[i].setBackground(null);
			listButton[i].setBorder(null);
			listButton[i].setFocusPainted(false);
			listButton[i].setMargin(new Insets(0, 0, 0, 0));
			listButton[i].setContentAreaFilled(false);
			listButton[i].setBorderPainted(false);
			listButton[i].setOpaque(false);
			//listButton[i].setForeground(new Color(255,255,255,200));
			listButton[i].setFocusable(false);
			//size = listButton[i].getPreferredSize();
			listButton[i].setBounds(getWidth() - 50 + (50-30)/2, currHeight + (50-30)/2, 30, 30);
			listButton[i].addActionListener(new ButtonActionListener(7, i, mainWindow));

			listLabel[i] = new JLabel();
			listLabel[i].setFont(mainWindow.FONT_HELVETICA_NEUE_THIN.deriveFont(18.0f));
			listLabel[i].setForeground(new Color(255,255,255,150));
			
			currHeight += 50 + 5;
		}

		setNameInput(new JTextArea());
    	getNameInput().setOpaque(false);
    	getNameInput().setFont(mainWindow.FONT_HELVETICA_NEUE_THIN.deriveFont(27.0f));
		size = getNameInput().getPreferredSize();
		getNameInput().setBounds(100, getHeight() - 60 - 30 - 40 + (40-size.height)/2, getWidth() - 15, size.height);
		getNameInput().setCaretColor(new Color(255,255,255,200));
		getNameInput().setForeground(new Color(255,255,255,200));
		this.add(getNameInput());

		JButton listButton;
		if (mealScreen) listButton = new JButton("Add Meal to My Meals");
		else listButton = new JButton("Add Dish to My Dishes");
		listButton.setFont(mainWindow.FONT_HELVETICA_NEUE_THIN.deriveFont(27.0f));
		listButton.setBackground(null);
		listButton.setBorder(null);
		listButton.setFocusPainted(false);
		listButton.setMargin(new Insets(0, 0, 0, 0));
		listButton.setContentAreaFilled(false);
		listButton.setBorderPainted(false);
		listButton.setOpaque(false);
		listButton.setForeground(new Color(255,255,255,200));
		listButton.setFocusable(false);
		size = listButton.getPreferredSize();
		listButton.setBounds(0, getHeight() - 60 - 15, getWidth(), 60);
		listButton.addActionListener(new ButtonActionListener(6, 4, mainWindow));
		this.add(listButton);
	}
    
    public void toggleScreen() {
    	this.mealScreen = !this.mealScreen;
    	removeListFromPanel();
    	this.redraw();
    }
    
    public void redraw() {
    	//this.removeAll();
    	//this.initUI();
    	this.repaint();
    	//clearTextFields();
    }
	
    public Meal getMeal() { return this.meal; }
    
    public void addFoodServing(FoodServing newFoodServing) {
    	if (meal == null) meal = new Meal();
    	meal.addFoodServing(newFoodServing);
    }
    
    public void removeFoodServing(int index) {
    	if (meal != null) {
    		meal.removeFoodServing(index);
    		this.repaint();
    	}
    }
    
    public void removeList() {
    	this.meal = null;
    	this.repaint();
    }
    
    private void removeListFromPanel() {
    	for(int i = 0; i < 9; i++) {
    		this.remove(listButton[i]);
    		this.remove(listLabel[i]);
    	}
    }
    
    /* (non-Javadoc)
     * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
     */
    @Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		this.setLayout(null);
		this.setOpaque(false);
    	this.setLocation(1480*2/3 + 15,75);
    	this.setSize(1480/3 - 30, 800-75);
    	
		BufferedImage image = null;
		try {
			image = ImageIO.read(new File("UI/circle-bg.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		g.drawImage(image, (getWidth()-image.getWidth())/2, (80-image.getHeight())/2, null);
		
		removeListFromPanel();
		
		/*JPanel panel = new JPanel() {
		    @Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				
				this.setLayout(null);
				this.setOpaque(false);
		    	this.setLocation(0, listHeight);
		    	this.setSize(1480/3 - 30, (50+5)*14);*/
		    	
				if (meal != null) {
					image = null;
					try {
						image = ImageIO.read(new File("UI/xmark.png"));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					LinkedList<FoodServing> foodServings = meal.getFoodServings();
					FoodServing foodServing;
					Macro foodServingMacros;
					JLabel label;
					Dimension size;
					int currHeight = listHeight;
					
					g.setColor(new Color(255,255,255,75));
					//for(int i = 0; i < 14; i++) {
					for(int i = 0; i < 10 && i < meal.getFoodServings().size(); i++) {
						foodServing = foodServings.get(i);
						foodServingMacros = foodServing.getMacros();
						g.fillRect(0, currHeight, getWidth() - 50, 50);
						listLabel[i].setText("<html><center>" + foodServing.getFood().getName() + "<br>"
								+ foodServing.getServingSize() + " " + foodServing.getServingUnit() + " | " + foodServingMacros.getCalories() + " cal</center></html>");
						/*listLabel[i].setText("<html><center>" + "name" + i + "<br>"
								+ "size" + i + " " + "unit" + i + " | " + "calories" + i + " cal</center></html>");*/
						//label.setFont(mainWindow.FONT_HELVETICA_NEUE_THIN.deriveFont(18.0f));
						size = listLabel[i].getPreferredSize();
						listLabel[i].setBounds((getWidth() - 50 - size.width)/2, currHeight, size.width, size.height);
						//label.setForeground(new Color(255,255,255,150));
						this.add(listLabel[i]);

						g.drawImage(image, getWidth() - 50 + (50-image.getWidth())/2, currHeight + (50-image.getHeight())/2, null);
						
						this.add(listButton[i]);
						
						currHeight += 50 + 5;
					}
				}
				/*}
		};
    	//JScrollPane scrollFrame = new JScrollPane(panel);
    	//panel.setAutoscrolls(true);
    	//scrollFrame.setPreferredSize(new Dimension(getWidth(),800-75-listHeight));
		this.add(panel);
    	//mainWindow.add(scrollFrame);*/
				
		g.setColor(new Color(255,255,255,75));
		g.fillRect(15, getHeight() - 60 - 30 - 40, getWidth() - 15, 40);
		
		try {
			image = ImageIO.read(new File("UI/btn-bg.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		g.drawImage(image, 0,  getHeight() - 60 - 15, null);
    }

	public JTextArea getNameInput() {
		return nameInput;
	}

	public void setNameInput(JTextArea nameInput) {
		this.nameInput = nameInput;
	}

}
