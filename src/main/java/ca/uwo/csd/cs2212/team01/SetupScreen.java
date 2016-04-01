
package ca.uwo.csd.cs2212.team01;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class SetupScreen extends JPanel implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private MainWindow mainWindow;
	
	private int page;
	
	private JTextArea inputGender, inputAge, inputHeight, inputCurrentWeight, inputTargetWeight;
	
	private String gender;
	private int age;
	private float height, currentWeight, targetWeight;
	
	/**
	 * Class Constructor
	 * @param mainWindow
	 */
	public SetupScreen(MainWindow mainWindow) {
		this.mainWindow = mainWindow;
		
		this.page = 1;
		
		this.setLayout(null);
		this.setOpaque(false);
    	this.setLocation(0, 0);
    	this.setSize(1480, 800);
    	
		this.initUI();
	}
	
    /**
     * Renders the Setup Screen
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

    			BufferedImage checkmark = null;
				try {
					checkmark = ImageIO.read(new File("UI/checkmark.png"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
    			JLabel label = new JLabel("Setup TRANSFORM");
    			label.setFont(mainWindow.FONT_HELVETICA_NEUE_THIN.deriveFont(30.0f));
    			Dimension size = label.getPreferredSize();
    			label.setBounds((getWidth() - size.width)/2, (getHeight()-size.height)/2, size.width, size.height);
    			label.setForeground(Color.WHITE);
    			this.add(label);
				
    			/*JLabel label = new JLabel("Setup");
    			label.setFont(mainWindow.FONT_HELVETICA_NEUE_THIN.deriveFont(30.0f));
    			Dimension size = label.getPreferredSize();
    			label.setBounds(getWidth()/7 - size.width, (getHeight()-size.height)/2, size.width, size.height);
    			label.setForeground(Color.WHITE);
    			this.add(label);

    			g2.setColor(Color.WHITE);
    	        g2.setStroke(new BasicStroke(1.0f));
    	        
    	        int length;
    	        
    	        if (page >= 1) {
    	        	length = (getWidth()/2 - getWidth()/7 - 10 - 30)/2;
    	        	if (page == 1) length /= 2;
    	        	g2.drawLine(getWidth()/7 + 10, 75/2, getWidth()/7 + 10 + length, 75/2);
    	        }
    	        
    	        if (page >= 3) {
    	        	g2.drawLine(getWidth()/7 + 10 + (getWidth()/2 - getWidth()/7 - 10 - 30)/2 + 30, 75/2, (getWidth() - 30)/2, 75/2);
    	        	g.drawImage(checkmark, getWidth()/7 + 10 + (getWidth()/2 - getWidth()/7 - 10 - 30)/2, (75-checkmark.getHeight())/2, checkmark.getWidth(), checkmark.getHeight(), null);
    	        }else g2.draw(new Ellipse2D.Double(getWidth()/7 + 10 + (getWidth()/2 - getWidth()/7 - 10 - 30)/2,(getHeight()-30)/2, 30, 30));

    			label = new JLabel("Done");
    			label.setFont(mainWindow.FONT_HELVETICA_NEUE_THIN.deriveFont(30.0f));
    			size = label.getPreferredSize();
    			label.setBounds(getWidth() - getWidth()/7, (getHeight()-size.height)/2, size.width, size.height);
    			label.setForeground(Color.WHITE);
    			this.add(label);

    	        if (page >= 4) {
    	        	length = (getWidth()/2 - getWidth()/7 - 10 - 15 - 30)/2;
    	        	g2.drawLine((getWidth() - 30)/2 + 30, 75/2, (getWidth() - 30)/2 + 30 + length, 75/2);
    	        	g.drawImage(checkmark, (getWidth() - 30)/2, (75-checkmark.getHeight())/2, checkmark.getWidth(), checkmark.getHeight(), null);
    	        }else g2.draw(new Ellipse2D.Double((getWidth() - 30)/2,(getHeight()-30)/2, 30, 30));
    	        
    	        if (page >= 5) {
    	        	length = (getWidth() - getWidth()/7 - 10) - ((getWidth() - 30)/2 + 30 + (getWidth()/2 - getWidth()/7 - 10 - 15 - 30)/2 + 30);
    	        	if (page == 5) length /= 2;
    	        	g2.drawLine((getWidth() - 30)/2 + 30 + (getWidth()/2 - getWidth()/7 - 10 - 15 - 30)/2 + 30, 75/2, (getWidth() - 30)/2 + 30 + (getWidth()/2 - getWidth()/7 - 10 - 15 - 30)/2 + 30 + length, 75/2);
    	        	g.drawImage(checkmark, (getWidth() - 30)/2 + 30 + (getWidth()/2 - getWidth()/7 - 10 - 15 - 30)/2, (75-checkmark.getHeight())/2, checkmark.getWidth(), checkmark.getHeight(), null);
    	        }else g2.draw(new Ellipse2D.Double((getWidth() - 30)/2 + 30 + (getWidth()/2 - getWidth()/7 - 10 - 15 - 30)/2,(getHeight()-30)/2, 30, 30));
*/
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
    	        //exitBtn.addMouseMotionListener(new ButtonMotionListener(4, 0, mainWindow));
    	        this.add(exitBtn);
    	        
    			//g2.drawImage(image, getWidth()-image.getWidth()-13, 13, null);
    		}
    	};
    	bannerPanel.setBounds(0, 0, getWidth(), 75);
    	this.add(bannerPanel);
    	
    	if (this.page <= 1) {
			JButton button = new JButton();
			//button.setFont(mainWindow.FONT_HELVETICA_NEUE_THIN.deriveFont(35.0f));
			button.setBackground(null);
			button.setBorder(null);
			button.setFocusPainted(false);
			button.setMargin(new Insets(0, 0, 0, 0));
			button.setContentAreaFilled(false);
			button.setBorderPainted(false);
			button.setOpaque(false);
			button.setForeground(new Color(255,255,255,220));
			button.setFocusable(false);
			//Dimension size = button.getPreferredSize();
			button.setBounds((getWidth()-415)/2, getHeight()-50-18, 415, 50);
	        button.addActionListener(new ButtonActionListener(4, 0, mainWindow));
	        this.add(button);
    	}else {
    		JButton button;
    		if (this.page >= 11) button = new JButton("Done >");
    		else button = new JButton("Next >");
	        button.addActionListener(new ButtonActionListener(4, 0, mainWindow));
			button.setFont(mainWindow.FONT_HELVETICA_NEUE_THIN.deriveFont(35.0f));
			button.setBackground(null);
			button.setBorder(null);
			button.setFocusPainted(false);
			button.setMargin(new Insets(0, 0, 0, 0));
			button.setContentAreaFilled(false);
			button.setBorderPainted(false);
			button.setOpaque(false);
			button.setForeground(new Color(255,255,255,220));
			button.setFocusable(false);
			Dimension size = button.getPreferredSize();
			button.setBounds(getWidth()/2 + 30, getHeight()-size.height-30, size.width, size.height);
	        this.add(button);

			button = new JButton("< Back");
			button.setFont(mainWindow.FONT_HELVETICA_NEUE_THIN.deriveFont(35.0f));
			button.setBackground(null);
			button.setBorder(null);
			button.setFocusPainted(false);
			button.setMargin(new Insets(0, 0, 0, 0));
			button.setContentAreaFilled(false);
			button.setBorderPainted(false);
			button.setOpaque(false);
			button.setForeground(new Color(255,255,255,220));
			button.setFocusable(false);
			size = button.getPreferredSize();
			button.setBounds(getWidth()/2 - size.width - 30, getHeight()-size.height-30, size.width, size.height);
	        button.addActionListener(new ButtonActionListener(4, 1, mainWindow));
	        this.add(button);
    	}
    	
    	switch(this.page) {
    	/*case 1:
    		JLabel title = new JLabel("How does TRAINR work?");
			title.setFont(mainWindow.FONT_HELVETICA_NEUE_THIN.deriveFont(60.0f));
			Dimension titlesize = title.getPreferredSize();
			title.setBounds((getWidth() - titlesize.width)/2, bannerPanel.getHeight() + titlesize.height/2, titlesize.width, titlesize.height);
			title.setForeground(new Color(255,255,255,200));
    		this.add(title);
    		
    		JLabel desc = new JLabel("<html><center>TRAINR helps you achieve your weight loss goal by forcing<br>"
    				+ "your body to be in a <font color='white'>fat burning state</font> as often as possible<br><br>"
    				+ "TRAINR helps you achieve this state by making sure you<br>"
    				+ "<font color='white'>burn more calories than you consume</font><br>"
    				+ "This is often referred to as<br>"
    				+ "\"Calories in less than Calories out\"<br><br>"
    				+ "TRAINR's approach is simple: achieve a <font color='white'><i>daily</i> -500 calorie difference</font><br>"
    				+ "between your <font color='white'>Calories In</font> and <font color='white'>Calories Out</font></center></html>");
    		desc.setFont(mainWindow.FONT_HELVETICA_NEUE_THIN.deriveFont(32.0f));
    		Dimension size = desc.getPreferredSize();
    		desc.setBounds((getWidth()-size.width)/2, bannerPanel.getHeight() + titlesize.height*2, size.width, size.height);
    		desc.setForeground(new Color(255,255,255,128));
    		this.add(desc);
    		break;

    	case 2:
    		title = new JLabel("How does TRAINR work?");
			title.setFont(mainWindow.FONT_HELVETICA_NEUE_THIN.deriveFont(60.0f));
			titlesize = title.getPreferredSize();
			title.setBounds((getWidth() - titlesize.width)/2, bannerPanel.getHeight() + titlesize.height/2, titlesize.width, titlesize.height);
			title.setForeground(new Color(255,255,255,200));
    		this.add(title);
    		
    		desc = new JLabel("<html><center>Each day, TRAINR will help you make a Plan that involves:<br><br>"
    				+ "Planning your <font color='white'>Calories In</font> (the food you will eat)<br>"
    				+ "Planning your <font color='white'>Calories Out</font> (the calories you will burn)<br>"
    				+ "so that your predicted calorie difference wil be planned to be <font color='white'>-500 calories</font><br><br>"
    				+ "Your <font color='white'>Fitbit Device</font> will monitor the actual calories you burn and<br>"
    				+ "TRAINR will use this information to:<br><br>"
    				+ "> Tell you whether you're on track with your plan. You will earn rewards if you are!<br>"
    				+ "> tell you which adjustments to make if you're not on track with your plan<br>"
    				+ "> Will show you a prediction of when you should achieve your weight loss goal!</center></html>");
    		desc.setFont(mainWindow.FONT_HELVETICA_NEUE_THIN.deriveFont(32.0f));
    		size = desc.getPreferredSize();
    		desc.setBounds((getWidth()-size.width)/2, bannerPanel.getHeight() + titlesize.height*2, size.width, size.height);
    		desc.setForeground(new Color(255,255,255,128));
    		this.add(desc);
    		break;*/

    	case 6:
    		/*title = new JLabel("Enter in your body stats");
			title.setFont(mainWindow.FONT_HELVETICA_NEUE_THIN.deriveFont(60.0f));
			titlesize = title.getPreferredSize();
			title.setBounds((getWidth() - titlesize.width)/2, bannerPanel.getHeight() + titlesize.height/2, titlesize.width, titlesize.height);
			title.setForeground(new Color(255,255,255,200));
    		this.add(title);
    		
    		desc = new JLabel("so TRAINR can get itself setup for you");
    		desc.setFont(mainWindow.FONT_HELVETICA_NEUE_THIN.deriveFont(35.0f));
    		size = desc.getPreferredSize();
    		desc.setBounds((getWidth()-size.width)/2, bannerPanel.getHeight() + titlesize.height*2, size.width, size.height);
    		desc.setForeground(new Color(255,255,255,150));
    		this.add(desc);*/
    		
    		JLabel tableTitle = new JLabel("Gender ( M or F )", JLabel.CENTER);
    		tableTitle.setOpaque(true);
    		tableTitle.setFont(mainWindow.FONT_HELVETICA_NEUE_THIN.deriveFont(22.0f));
    		tableTitle.setBounds((getWidth() - 250*3 - 2)/2, (getHeight() - 50*2 - 1)/2 + 100, 250, 50);
    		tableTitle.setForeground(new Color(255,255,255,200));
    		tableTitle.setBackground(new Color(0,0,0,120));
    		this.add(tableTitle);
    		
    		tableTitle = new JLabel("Age", JLabel.CENTER);
    		tableTitle.setOpaque(true);
    		tableTitle.setFont(mainWindow.FONT_HELVETICA_NEUE_THIN.deriveFont(22.0f));
    		tableTitle.setBounds((getWidth() - 250*3 - 2)/2 + 250 + 1, (getHeight() - 50*2 - 1)/2 + 100, 250, 50);
    		tableTitle.setForeground(new Color(255,255,255,200));
    		tableTitle.setBackground(new Color(0,0,0,120));
    		this.add(tableTitle);
    		
    		tableTitle = new JLabel("Height ( cm )", JLabel.CENTER);
    		tableTitle.setOpaque(true);
    		tableTitle.setFont(mainWindow.FONT_HELVETICA_NEUE_THIN.deriveFont(22.0f));
    		tableTitle.setBounds((getWidth() - 250*3 - 2)/2 + 250*2 + 2, (getHeight() - 50*2 - 1)/2 + 100, 250, 50);
    		tableTitle.setForeground(new Color(255,255,255,200));
    		tableTitle.setBackground(new Color(0,0,0,120));
    		this.add(tableTitle);
    		
    		JComponent inputboxes = new JComponent() {
    			@Override
    			protected void paintComponent(Graphics g) {
    				super.paintComponent(g);
    				
    				this.setLayout(null);
    				this.setSize(250*3 + 2,50);
    				//this.setLocation(0, 0);

    				Graphics2D g2 = (Graphics2D) g;
    				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    				
    				g2.setColor(new Color(0,0,0,120));
    				
    				g2.fillRect(0, 0, 250, 50);
    				g2.fillRect(250 + 1, 0, 250, 50);
    				g2.fillRect(250*2 + 2, 0, 250, 50);
    			}
    		};
    		
    		inputGender = new JTextArea(gender);
    		inputGender.setOpaque(false);
    		inputGender.setFont(mainWindow.FONT_HELVETICA_NEUE_THIN.deriveFont(22.0f));
    		Dimension size = inputGender.getPreferredSize();
    		inputGender.setBounds(0 + 100, (50 - size.height)/2, 250-100, 50);
    		inputGender.setCaretColor(new Color(255,255,255,200));
    		inputGender.setForeground(new Color(255,255,255,200));
    		inputboxes.add(inputGender);
    		
    		inputAge = new JTextArea((age != 0 ? age : "") + "");
    		inputAge.setLayout(null);
    		inputAge.setOpaque(false);
    		inputAge.setFont(mainWindow.FONT_HELVETICA_NEUE_THIN.deriveFont(22.0f));
    		inputAge.setBounds(250 + 1 + 100, (50 - size.height)/2, 250-100, 50);
    		inputAge.setCaretColor(new Color(255,255,255,200));
    		inputAge.setForeground(new Color(255,255,255,200));
    		inputboxes.add(inputAge);
    		
    		inputHeight = new JTextArea((height != 0 ? height : "") + "");
    		inputHeight.setLayout(null);
    		inputHeight.setOpaque(false);
    		inputHeight.setFont(mainWindow.FONT_HELVETICA_NEUE_THIN.deriveFont(22.0f));
    		inputHeight.setBounds(250*2 + 2 + 100, (50 - size.height)/2, 250-100, 50);
    		inputHeight.setCaretColor(new Color(255,255,255,200));
    		inputHeight.setForeground(new Color(255,255,255,200));
    		inputboxes.add(inputHeight);
    		
    		inputboxes.setBounds((getWidth() - 250*3 - 2)/2, (getHeight() - 50*2 - 1)/2 + 50 + 1 + 100, 250*3 + 2, 50);
    		this.add(inputboxes);
    		break;
    		
    	case 7:
    		/*title = new JLabel("Set a Weight Loss Goal");
			title.setFont(mainWindow.FONT_HELVETICA_NEUE_THIN.deriveFont(60.0f));
			titlesize = title.getPreferredSize();
			title.setBounds((getWidth() - titlesize.width)/2, bannerPanel.getHeight() + titlesize.height/2, titlesize.width, titlesize.height);
			title.setForeground(new Color(255,255,255,200));
    		this.add(title);
    		
    		desc = new JLabel("Enter in your current and target weight goal");
    		desc.setFont(mainWindow.FONT_HELVETICA_NEUE_THIN.deriveFont(35.0f));
    		size = desc.getPreferredSize();
    		desc.setBounds((getWidth()-size.width)/2, bannerPanel.getHeight() + titlesize.height*2, size.width, size.height);
    		desc.setForeground(new Color(255,255,255,150));
    		this.add(desc);*/
    		
    		tableTitle = new JLabel("Current Weight ( lbs )", JLabel.CENTER);
    		tableTitle.setOpaque(true);
    		tableTitle.setFont(mainWindow.FONT_HELVETICA_NEUE_THIN.deriveFont(22.0f));
    		tableTitle.setBounds((getWidth() - 250*2 - 1)/2 - 60, (getHeight() - 50*2 - 1)/2 + 30, 250, 50);
    		tableTitle.setForeground(new Color(255,255,255,200));
    		tableTitle.setBackground(new Color(0,0,0,120));
    		this.add(tableTitle);
    		
    		tableTitle = new JLabel("Target Weight ( lbs )", JLabel.CENTER);
    		tableTitle.setOpaque(true);
    		tableTitle.setFont(mainWindow.FONT_HELVETICA_NEUE_THIN.deriveFont(22.0f));
    		tableTitle.setBounds((getWidth() - 250*2 - 1)/2 + 250 + 1 + 60, (getHeight() - 50*2 - 1)/2 + 30, 250, 50);
    		tableTitle.setForeground(new Color(255,255,255,200));
    		tableTitle.setBackground(new Color(0,0,0,120));
    		this.add(tableTitle);
    		
    		inputboxes = new JComponent() {
    			@Override
    			protected void paintComponent(Graphics g) {
    				super.paintComponent(g);
    				
    				this.setLayout(null);
    				this.setSize(250*2 + 1 + 120,50);
    				//this.setLocation(0, 0);

    				g.setColor(new Color(0,0,0,120));
    				
    				g.fillRect(0, 0, 250, 50);
    				g.fillRect(250 + 1 + 120, 0, 250, 50);
    			}
    		};
    		
    		inputCurrentWeight = new JTextArea((currentWeight != 0 ? currentWeight : "") + "");
    		inputCurrentWeight.setOpaque(false);
    		inputCurrentWeight.setFont(mainWindow.FONT_HELVETICA_NEUE_THIN.deriveFont(22.0f));
    		size = inputCurrentWeight.getPreferredSize();
    		inputCurrentWeight.setBounds(0 + 100, (50 - size.height)/2, 250-100, 50);
    		inputCurrentWeight.setCaretColor(new Color(255,255,255,200));
    		inputCurrentWeight.setForeground(new Color(255,255,255,200));
    		inputboxes.add(inputCurrentWeight);
    		
    		inputTargetWeight = new JTextArea((targetWeight != 0 ? targetWeight : "") + "");
    		inputTargetWeight.setLayout(null);
    		inputTargetWeight.setOpaque(false);
    		inputTargetWeight.setFont(mainWindow.FONT_HELVETICA_NEUE_THIN.deriveFont(22.0f));
    		inputTargetWeight.setBounds(250 + 1 + 100 + 120, (50 - size.height)/2, 250-100, 50);
    		inputTargetWeight.setCaretColor(new Color(255,255,255,200));
    		inputTargetWeight.setForeground(new Color(255,255,255,200));
    		inputboxes.add(inputTargetWeight);
    		
    		inputboxes.setBounds((getWidth() - 250*2 - 1)/2 - 60, (getHeight() - 50*2 - 1)/2 + 50 + 1 + 30, 250*2 + 1 + 120, 50);
    		this.add(inputboxes);
    		break;

    	/*case 5:
    		title = new JLabel("Focus On Milestones");
			title.setFont(mainWindow.FONT_HELVETICA_NEUE_THIN.deriveFont(60.0f));
			titlesize = title.getPreferredSize();
			title.setBounds((getWidth() - titlesize.width)/2, bannerPanel.getHeight() + titlesize.height/2, titlesize.width, titlesize.height);
			title.setForeground(new Color(255,255,255,200));
    		this.add(title);
    		
    		desc = new JLabel("<html><center>In order to get you motivated, TRAINR will get you to focus on losing only<br>"
    				+ "<font color='white'>2 lbs at a time</font>. Each time you lose 2 lbs, you will receive a <font color='white'>Milestone</font> reward!</center></html>");
    		desc.setFont(mainWindow.FONT_HELVETICA_NEUE_THIN.deriveFont(32.0f));
    		size = desc.getPreferredSize();
    		desc.setBounds((getWidth()-size.width)/2, bannerPanel.getHeight() + titlesize.height*2, size.width, size.height);
    		desc.setForeground(new Color(255,255,255,128));
    		this.add(desc);
    		
    		JPanel panel = new JPanel() {
    			@Override
    			protected void paintComponent(Graphics g) {
    				//super.paintComponent(g);
    				
    				this.setLayout(null);
    				this.setOpaque(false);
    				//this.setLocation(0, 0);
    				
    				Graphics2D g2 = (Graphics2D) g;
    				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

    				this.setSize(1480/3,50*4 + 1);
    	    		//this.setBounds((1480-getWidth())/2, 0, getWidth(), getHeight());
    				
    				JLabel label = new JLabel("2.0 lbs to go!", JLabel.LEFT);
        			label.setFont(mainWindow.FONT_HELVETICA_NEUE_THIN.deriveFont(22.0f));
        			Dimension size = label.getPreferredSize();
        			label.setBounds(0, (50-size.height)/2, size.width, size.height);
        			label.setForeground(new Color(106, 185, 255, 255));
        			this.add(label);
        			g2.setColor(new Color(106, 185, 255, 255));
        			g2.drawLine(size.width + 10, 50/2, (getWidth()-50) - (int)((getWidth()-(size.width + 10)-50)*2/2), 50/2);

        			g2.setColor(Color.WHITE);
        			g2.draw(new Ellipse2D.Double(getWidth()-50-1, 0, 50, 50));
        			label = new JLabel("MS", JLabel.LEFT);
        			label.setFont(mainWindow.FONT_HELVETICA_NEUE_THIN.deriveFont(22.0f));
        			size = label.getPreferredSize();
        			label.setBounds(getWidth()-50 + (50-size.width)/2, (50-size.height)/2, size.width, size.height);
        			label.setForeground(Color.WHITE);
        			this.add(label);
    				
    				label = new JLabel("1.3 lbs to go!", JLabel.LEFT);
        			label.setFont(mainWindow.FONT_HELVETICA_NEUE_THIN.deriveFont(22.0f));
        			size = label.getPreferredSize();
        			label.setBounds(0, 50*1 + 50/2 + (50-size.height)/2, size.width, size.height);
        			label.setForeground(new Color(106, 185, 255, 255));
        			this.add(label);
        			g2.setColor(new Color(106, 185, 255, 255));
        			g2.drawLine(size.width + 10, 50*2, (getWidth()-50) - (int)((getWidth()-(size.width + 10)-50)*1.3f/2), 50*2);

        			g2.setColor(Color.WHITE);
        			g2.draw(new Ellipse2D.Double(getWidth()-50-1, 50*1 + 50/2, 50, 50));
        			label = new JLabel("MS", JLabel.LEFT);
        			label.setFont(mainWindow.FONT_HELVETICA_NEUE_THIN.deriveFont(22.0f));
        			size = label.getPreferredSize();
        			label.setBounds(getWidth()-50 + (50-size.width)/2, 50*1 + 50/2 + (50-size.height)/2, size.width, size.height);
        			label.setForeground(Color.WHITE);
        			this.add(label);
    				
    				label = new JLabel("0.7 lbs to go!", JLabel.LEFT);
        			label.setFont(mainWindow.FONT_HELVETICA_NEUE_THIN.deriveFont(22.0f));
        			size = label.getPreferredSize();
        			label.setBounds(0, 50*3 + (50-size.height)/2, size.width, size.height);
        			label.setForeground(new Color(106, 185, 255, 255));
        			this.add(label);
        			g2.setColor(new Color(106, 185, 255, 255));
        			g2.drawLine(size.width + 10, 50*3 + 50/2, (getWidth()-50) - (int)((getWidth()-(size.width + 10)-50)*0.7f/2), 50*3 + 50/2);

        			g2.setColor(Color.WHITE);
        			g2.draw(new Ellipse2D.Double(getWidth()-50-1, 50*3, 50, 50));
        			label = new JLabel("MS", JLabel.LEFT);
        			label.setFont(mainWindow.FONT_HELVETICA_NEUE_THIN.deriveFont(22.0f));
        			size = label.getPreferredSize();
        			label.setBounds(getWidth()-50 + (50-size.width)/2, 50*3 + (50-size.height)/2, size.width, size.height);
        			label.setForeground(Color.WHITE);
        			this.add(label);
    			}
    		};
    		panel.setBounds((1480-1480/3)/2, bannerPanel.getHeight() + titlesize.height*2 + size.height + 50/2, 1480/3, 50*4);
    		this.add(panel);


    		JLabel label = new JLabel("You lost 2 lbs and achieved a Milestone!");
    		label.setFont(mainWindow.FONT_HELVETICA_NEUE_THIN.deriveFont(25.0f));
    		size = label.getPreferredSize();
    		label.setBounds((getWidth()-size.width)/2, panel.getY() + panel.getHeight() + 50/2, size.width, size.height);
    		label.setForeground(new Color(255,255,255,200));
    		this.add(label);
    		
    		panel = new JPanel() {
    			@Override
    			protected void paintComponent(Graphics g) {
    				//super.paintComponent(g);
    				
    				this.setLayout(null);
    				this.setOpaque(false);
    				//this.setLocation(0, 0);
    				this.setSize(50 + 1,50 + 1);
    				
    				Graphics2D g2 = (Graphics2D) g;
    				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        			g2.setColor(Color.WHITE);
        			g2.draw(new Ellipse2D.Double(0, 0, 50, 50));
        			JLabel label = new JLabel("MS", JLabel.LEFT);
        			label.setFont(mainWindow.FONT_HELVETICA_NEUE_THIN.deriveFont(22.0f));
        			Dimension size = label.getPreferredSize();
        			label.setBounds((51-size.width)/2, (51-size.height)/2, size.width, size.height);
        			label.setForeground(Color.WHITE);
        			this.add(label);
    			}
    		};
    		panel.setBounds((1480-51)/2, label.getY() + label.getHeight() + 50/2, 51, 51);
    		this.add(panel);
    		break;

    	case 6:
    		title = new JLabel("One last thing...");
			title.setFont(mainWindow.FONT_HELVETICA_NEUE_THIN.deriveFont(60.0f));
			titlesize = title.getPreferredSize();
			title.setBounds((getWidth() - titlesize.width)/2, bannerPanel.getHeight() + titlesize.height/2, titlesize.width, titlesize.height);
			title.setForeground(new Color(255,255,255,200));
    		this.add(title);
    		
    		desc = new JLabel("<html><center>Remember that TRAINR will act a your guild to ensure you<br>"
    				+ "achieve your weight loss goal.<br>"
    				+ "There will always be a <font color='white'>\"Trainr Feedback Panel\"</font> preset<br>"
    				+ "giving you guidance based on your Ftbit information and<br>"
    				+ "progres. And remember,<br><br>"
    				+ "\"<i>Never give up on your goal just because of the time it will take to accomplish it.<br>"
    				+ "That time will pass away\"</i> &ndash; <small style='font-size:22pt;'>Earl Nightingale</small><br><br>"
    				+ "Good luck!<br>TeamOne, TRAINR development team.</center></html>");
    		desc.setFont(mainWindow.FONT_HELVETICA_NEUE_THIN.deriveFont(32.0f));
    		size = desc.getPreferredSize();
    		desc.setBounds((getWidth()-size.width)/2, bannerPanel.getHeight() + titlesize.height*2, size.width, size.height);
    		desc.setForeground(new Color(255,255,255,128));
    		this.add(desc);
    		break;*/
    	}
    }
    
    public void gotoNextPage() {
    	switch(this.page) {
    	case 6: // Save gender, age, height input values
    		try {
	    		gender = inputGender.getText();
	    		if (!gender.toUpperCase().equals("M") && !gender.toUpperCase().equals("F")) throw new NumberFormatException();
	    		age = Integer.parseInt(inputAge.getText());
	    		height = Float.parseFloat(inputHeight.getText());
    		}catch(NumberFormatException e) {
				return;
    		}
    		break;
    	case 7: // Save current/target weight input values
    		try {
	    		currentWeight = Float.parseFloat(inputCurrentWeight.getText());
	    		targetWeight = Float.parseFloat(inputTargetWeight.getText());
			}catch(NumberFormatException e) {
				return;
			}
    		break;
    	case 11: // Setup User & Virtual Trainer Objects
    		User user = new User("Beth Locke", age, gender.toUpperCase().charAt(0), height);
			this.mainWindow.setUser(user);
			
			this.mainWindow.setupVirtualTrainer(currentWeight, targetWeight);
    		user.calcBMR(this.mainWindow.getVirtualTrainer());

			this.mainWindow.setFirstCall(false);

			this.mainWindow.updateDashboardScreen();
			this.mainWindow.setVisible(false);
			this.mainWindow.getContentPane().removeAll();
			this.mainWindow.add(this.mainWindow.getDashboardScreen());
			this.mainWindow.setVisible(true);
    		break;
    	}
    	
    	this.page++;
    	if (this.page > 11) this.page = 11;
    	this.redraw();
    }
    
    public void gotoPrevPage() {
    	switch(this.page) {
    	case 6: // Save gender, age, height input values
    		try {
	    		gender = inputGender.getText();
	    		age = Integer.parseInt(inputAge.getText());
	    		height = Float.parseFloat(inputHeight.getText());
    		}catch(NumberFormatException e) {
    		}
    		break;
    	case 7: // Save current/target weight input values
    		try {
	    		currentWeight = Float.parseFloat(inputCurrentWeight.getText());
	    		targetWeight = Float.parseFloat(inputTargetWeight.getText());
			}catch(NumberFormatException e) {
			}
    		break;
    	}
    	
    	this.page--;
    	if (this.page < 1) this.page = 1;
    	this.redraw();
    }
    
    public void redraw() {
    	this.removeAll();
    	this.initUI();
    	this.repaint();
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
			image = ImageIO.read(new File("UI/Bkg-Setup-" + this.page + ".png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		g2.drawImage(image, 0, 0, getWidth(), getHeight(), 0, 0, image.getWidth(), image.getHeight(), null);
	}
}

