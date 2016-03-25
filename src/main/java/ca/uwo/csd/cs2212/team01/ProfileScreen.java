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

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class ProfileScreen extends JPanel implements Serializable{
	private static final long serialVersionUID = 1L;
	private MainWindow mainWindow;
	
	private JButton settingsBtn;
	private JButton[] dashboardPanelsButtons, activityTrackingPanelsButtons, lifetimeTotalsButtons;
	private boolean settingsScreen;
	private JLabel[] label, labelSettings;
	private JTextArea[] input;
	
	private boolean[] dashboardPanelsToggler, activityTrackingPanelsToggler, lifetimeTotalsToggler;
	
	/**
	 * Class Constructor
	 * @param mainWindow
	 */
	public ProfileScreen(MainWindow mainWindow) {
		this.mainWindow = mainWindow;
		
		this.settingsScreen = false;
		
		dashboardPanelsToggler = new boolean[3];
		activityTrackingPanelsToggler = new boolean[3];
		lifetimeTotalsToggler = new boolean[4];

		dashboardPanelsButtons = new JButton[3];
		activityTrackingPanelsButtons = new JButton[3];
		lifetimeTotalsButtons = new JButton[4];

		label = new JLabel[8];
		labelSettings = new JLabel[5];
		input = new JTextArea[5];
		
		settingsBtn = new JButton();
		
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
    			
	    		if (!settingsScreen) {
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
    			}
    			
    			if (settingsScreen) settingsBtn.setText("Accept >");
    			else settingsBtn.setText("Settings >");
    			settingsBtn.setFont(mainWindow.FONT_HELVETICA_NEUE_THIN.deriveFont(30.0f));
    			settingsBtn.setBackground(null);
    			settingsBtn.setBorder(null);
    			settingsBtn.setFocusPainted(false);
    			settingsBtn.setMargin(new Insets(0, 0, 0, 0));
    			settingsBtn.setContentAreaFilled(false);
    			settingsBtn.setBorderPainted(false);
    			settingsBtn.setOpaque(false);
    			settingsBtn.setForeground(new Color(255,255,255,220));
    			settingsBtn.setFocusable(false);
    			Dimension size = settingsBtn.getPreferredSize();
    			settingsBtn.setBounds(getWidth() - 50 - size.width, (75 - size.height)/2, size.width, size.height);
    	        settingsBtn.addActionListener(new ButtonActionListener(25, 0, mainWindow));
    	        this.add(settingsBtn);
    	    	
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

    	//if (settingsScreen) {
			labelSettings[0] = new JLabel("Gender (M or F)", JLabel.CENTER);
			labelSettings[0].setOpaque(true);
			labelSettings[0].setFont(mainWindow.FONT_HELVETICA_NEUE_THIN.deriveFont(18.0f));
			labelSettings[0].setBounds(getWidth()/2 - 30 - (200*3 + 2), 75+60, 200, 40);
			labelSettings[0].setForeground(new Color(255,255,255,200));
			labelSettings[0].setBackground(new Color(0,0,0,120));
			
			labelSettings[1] = new JLabel("Age", JLabel.CENTER);
			labelSettings[1].setOpaque(true);
			labelSettings[1].setFont(mainWindow.FONT_HELVETICA_NEUE_THIN.deriveFont(18.0f));
			labelSettings[1].setBounds(getWidth()/2 - 30 - (200*3 + 2) + 200 + 1, 75+60, 200, 40);
			labelSettings[1].setForeground(new Color(255,255,255,200));
			labelSettings[1].setBackground(new Color(0,0,0,120));
			
			labelSettings[2] = new JLabel("Height (cm)", JLabel.CENTER);
			labelSettings[2].setOpaque(true);
			labelSettings[2].setFont(mainWindow.FONT_HELVETICA_NEUE_THIN.deriveFont(18.0f));
			labelSettings[2].setBounds(getWidth()/2 - 30 - (200*3 + 2) + 200*2 + 2, 75+60, 200, 40);
			labelSettings[2].setForeground(new Color(255,255,255,200));
			labelSettings[2].setBackground(new Color(0,0,0,120));
			
			labelSettings[3] = new JLabel("Current Weight (lbs)", JLabel.CENTER);
			labelSettings[3].setOpaque(true);
			labelSettings[3].setFont(mainWindow.FONT_HELVETICA_NEUE_THIN.deriveFont(18.0f));
			labelSettings[3].setBounds(getWidth()/2 + 30, 75+60, 200, 40);
			labelSettings[3].setForeground(new Color(255,255,255,200));
			labelSettings[3].setBackground(new Color(0,0,0,120));
			
			labelSettings[4] = new JLabel("Target Weight (lbs)", JLabel.CENTER);
			labelSettings[4].setOpaque(true);
			labelSettings[4].setFont(mainWindow.FONT_HELVETICA_NEUE_THIN.deriveFont(18.0f));
			labelSettings[4].setBounds(getWidth()/2 + 30 + 350, 75+60, 200, 40);
			labelSettings[4].setForeground(new Color(255,255,255,200));
			labelSettings[4].setBackground(new Color(0,0,0,120));

    		input[0] = new JTextArea(this.mainWindow.getPreferences().getUser().getGender() + "");
    		input[0].setOpaque(false);
    		input[0].setFont(mainWindow.FONT_HELVETICA_NEUE_THIN.deriveFont(22.0f));
    		Dimension size = input[0].getPreferredSize();
    		input[0].setBounds(getWidth()/2 - 30 - (200*3 + 2) + 75, 75+60 + 40 + 1 + (40-size.height)/2, 200, size.height);
    		input[0].setCaretColor(new Color(255,255,255,200));
    		input[0].setForeground(new Color(255,255,255,200));

    		input[1] = new JTextArea(this.mainWindow.getPreferences().getUser().getAge() + "");
    		input[1].setOpaque(false);
    		input[1].setFont(mainWindow.FONT_HELVETICA_NEUE_THIN.deriveFont(22.0f));
    		size = input[1].getPreferredSize();
    		input[1].setBounds(getWidth()/2 - 30 - (200*3 + 2) + 200 + 1 + 75, 75+60 + 40 + 1 + (40-size.height)/2, 200, size.height);
    		input[1].setCaretColor(new Color(255,255,255,200));
    		input[1].setForeground(new Color(255,255,255,200));

    		input[2] = new JTextArea(this.mainWindow.getPreferences().getUser().getHeight() + "");
    		input[2].setOpaque(false);
    		input[2].setFont(mainWindow.FONT_HELVETICA_NEUE_THIN.deriveFont(22.0f));
    		size = input[2].getPreferredSize();
    		input[2].setBounds(getWidth()/2 - 30 - (200*3 + 2) + 200*2 + 2 + 75, 75+60 + 40 + 1 + (40-size.height)/2, 200, size.height);
    		input[2].setCaretColor(new Color(255,255,255,200));
    		input[2].setForeground(new Color(255,255,255,200));
    		
    		input[3] = new JTextArea();
    		input[3].setOpaque(false);
    		input[3].setFont(mainWindow.FONT_HELVETICA_NEUE_THIN.deriveFont(22.0f));
    		size = input[3].getPreferredSize();
    		input[3].setBounds(getWidth()/2 + 30 + 75, 75+60 + 40 + 1 + (40-size.height)/2, 200, size.height);
    		input[3].setCaretColor(new Color(255,255,255,200));
    		input[3].setForeground(new Color(255,255,255,200));

    		input[4] = new JTextArea();
    		input[4].setOpaque(false);
    		input[4].setFont(mainWindow.FONT_HELVETICA_NEUE_THIN.deriveFont(22.0f));
    		size = input[4].getPreferredSize();
    		input[4].setBounds(getWidth()/2 + 30 + 350 + 75, 75+60 + 40 + 1 + (40-size.height)/2, 200, size.height);
    		input[4].setCaretColor(new Color(255,255,255,200));
    		input[4].setForeground(new Color(255,255,255,200));
    	//}else {
			label[0] = new JLabel("Gender", JLabel.CENTER);
			label[0].setOpaque(true);
			label[0].setFont(mainWindow.FONT_HELVETICA_NEUE_THIN.deriveFont(18.0f));
			label[0].setBounds((getWidth() - 200*4 - 3)/2, 75+60, 200, 40);
			label[0].setForeground(new Color(255,255,255,200));
			label[0].setBackground(new Color(0,0,0,120));
			
			label[1] = new JLabel("Age", JLabel.CENTER);
			label[1].setOpaque(true);
			label[1].setFont(mainWindow.FONT_HELVETICA_NEUE_THIN.deriveFont(18.0f));
			label[1].setBounds((getWidth() - 200*4 - 3)/2 + 200 + 1, 75+60, 200, 40);
			label[1].setForeground(new Color(255,255,255,200));
			label[1].setBackground(new Color(0,0,0,120));
			
			label[2] = new JLabel("Height (cm)", JLabel.CENTER);
			label[2].setOpaque(true);
			label[2].setFont(mainWindow.FONT_HELVETICA_NEUE_THIN.deriveFont(18.0f));
			label[2].setBounds((getWidth() - 200*4 - 3)/2 + 200*2 + 2, 75+60, 200, 40);
			label[2].setForeground(new Color(255,255,255,200));
			label[2].setBackground(new Color(0,0,0,120));
			
			label[3] = new JLabel("BMR (cal)", JLabel.CENTER);
			label[3].setOpaque(true);
			label[3].setFont(mainWindow.FONT_HELVETICA_NEUE_THIN.deriveFont(18.0f));
			label[3].setBounds((getWidth() - 200*4 - 3)/2 + 200*3 + 3, 75+60, 200, 40);
			label[3].setForeground(new Color(255,255,255,200));
			label[3].setBackground(new Color(0,0,0,120));
	
			label[4] = new JLabel(this.mainWindow.getPreferences().getUser().getGender() + "", JLabel.CENTER);
			label[4].setOpaque(true);
			label[4].setFont(mainWindow.FONT_HELVETICA_NEUE_THIN.deriveFont(18.0f));
			label[4].setBounds((getWidth() - 200*4 - 3)/2, 75+60 + 40 + 1, 200, 40);
			label[4].setForeground(new Color(255,255,255,200));
			label[4].setBackground(new Color(0,0,0,120));
			
			label[5] = new JLabel(this.mainWindow.getPreferences().getUser().getAge() + "", JLabel.CENTER);
			label[5].setOpaque(true);
			label[5].setFont(mainWindow.FONT_HELVETICA_NEUE_THIN.deriveFont(18.0f));
			label[5].setBounds((getWidth() - 200*4 - 3)/2 + 200 + 1, 75+60 + 40 + 1, 200, 40);
			label[5].setForeground(new Color(255,255,255,200));
			label[5].setBackground(new Color(0,0,0,120));
			
			label[6] = new JLabel(this.mainWindow.getPreferences().getUser().getHeight() + "", JLabel.CENTER);
			label[6].setOpaque(true);
			label[6].setFont(mainWindow.FONT_HELVETICA_NEUE_THIN.deriveFont(18.0f));
			label[6].setBounds((getWidth() - 200*4 - 3)/2 + 200*2 + 2, 75+60 + 40 + 1, 200, 40);
			label[6].setForeground(new Color(255,255,255,200));
			label[6].setBackground(new Color(0,0,0,120));
			
			label[7] = new JLabel("1600", JLabel.CENTER);
			label[7].setOpaque(true);
			label[7].setFont(mainWindow.FONT_HELVETICA_NEUE_THIN.deriveFont(18.0f));
			label[7].setBounds((getWidth() - 200*4 - 3)/2 + 200*3 + 3, 75+60 + 40 + 1, 200, 40);
			label[7].setForeground(new Color(255,255,255,200));
			label[7].setBackground(new Color(0,0,0,120));
    	//}

		for(int i = 0; i < 3; i++) {
			dashboardPanelsToggler[i] = this.mainWindow.getPreferences().getDashboardPanelsToggler(i);

			dashboardPanelsButtons[i] = new JButton();
			//dashboardPanelsButtons[i].setFont(mainWindow.FONT_HELVETICA_NEUE_THIN.deriveFont(25.0f));
			dashboardPanelsButtons[i].setBackground(null);
			dashboardPanelsButtons[i].setBorder(null);
			dashboardPanelsButtons[i].setFocusPainted(false);
			dashboardPanelsButtons[i].setMargin(new Insets(0, 0, 0, 0));
			dashboardPanelsButtons[i].setContentAreaFilled(false);
			dashboardPanelsButtons[i].setBorderPainted(false);
			dashboardPanelsButtons[i].setOpaque(false);
			dashboardPanelsButtons[i].setForeground(new Color(255,255,255,220));
			dashboardPanelsButtons[i].setFocusable(false);
			//Dimension size = dashboardPanelsButtons[i].getPreferredSize();
			dashboardPanelsButtons[i].setBounds(130+(290-20)/2+465*i, getHeight()-60+(60-20)/2, 20, 20);
	        dashboardPanelsButtons[i].addActionListener(new ButtonActionListener(26, i, mainWindow));
		}
		
		for(int i = 0; i < 3; i++) {
			activityTrackingPanelsToggler[i] = this.mainWindow.getPreferences().getActivityTrackingPanelsToggler(i);

			activityTrackingPanelsButtons[i] = new JButton();
			//activityTrackingPanelsButtons[i].setFont(mainWindow.FONT_HELVETICA_NEUE_THIN.deriveFont(25.0f));
			activityTrackingPanelsButtons[i].setBackground(null);
			activityTrackingPanelsButtons[i].setBorder(null);
			activityTrackingPanelsButtons[i].setFocusPainted(false);
			activityTrackingPanelsButtons[i].setMargin(new Insets(0, 0, 0, 0));
			activityTrackingPanelsButtons[i].setContentAreaFilled(false);
			activityTrackingPanelsButtons[i].setBorderPainted(false);
			activityTrackingPanelsButtons[i].setOpaque(false);
			activityTrackingPanelsButtons[i].setForeground(new Color(255,255,255,220));
			activityTrackingPanelsButtons[i].setFocusable(false);
			//Dimension size = activityTrackingPanelsButtons[i].getPreferredSize();
			activityTrackingPanelsButtons[i].setBounds(1350+(60-20)/2, 415+(75-20)/2 + 100*i, 20, 20);
	        activityTrackingPanelsButtons[i].addActionListener(new ButtonActionListener(27, i, mainWindow));
		}
		
		for(int i = 0; i < 4; i++) {
			lifetimeTotalsToggler[i] = this.mainWindow.getPreferences().getLifetimeTotalsToggler(i);

			lifetimeTotalsButtons[i] = new JButton();
			//lifetimeTotalsButtons[i].setFont(mainWindow.FONT_HELVETICA_NEUE_THIN.deriveFont(25.0f));
			lifetimeTotalsButtons[i].setBackground(null);
			lifetimeTotalsButtons[i].setBorder(null);
			lifetimeTotalsButtons[i].setFocusPainted(false);
			lifetimeTotalsButtons[i].setMargin(new Insets(0, 0, 0, 0));
			lifetimeTotalsButtons[i].setContentAreaFilled(false);
			lifetimeTotalsButtons[i].setBorderPainted(false);
			lifetimeTotalsButtons[i].setOpaque(false);
			lifetimeTotalsButtons[i].setForeground(new Color(255,255,255,220));
			lifetimeTotalsButtons[i].setFocusable(false);
			//Dimension size = lifetimeTotalsButtons[i].getPreferredSize();
			lifetimeTotalsButtons[i].setBounds(1060+12+(60-20)/2 + 69*i, 685+(60-20)/2, 20, 20);
	        lifetimeTotalsButtons[i].addActionListener(new ButtonActionListener(28, i, mainWindow));
		}
	}
	
	public void toggleToggles(int btnMode, int value) {
		switch(btnMode) {
		case 26:
			dashboardPanelsToggler[value] = !dashboardPanelsToggler[value];
			break;
		case 27:
			activityTrackingPanelsToggler[value] = !activityTrackingPanelsToggler[value];
			break;
		case 28:
			lifetimeTotalsToggler[value] = !lifetimeTotalsToggler[value];
			break;
		}
		
		this.repaint();
	}
	
	private void removeObjects() {
		for(int i = 0; i < 8; i++) this.remove(label[i]);
		for(int i = 0; i < 5; i++) {
			//this.remove(input[i]);
			this.remove(labelSettings[i]);
		}
		
		for(int i = 0; i < 3; i++) this.remove(dashboardPanelsButtons[i]);
		for(int i = 0; i < 3; i++) this.remove(activityTrackingPanelsButtons[i]);
		for(int i = 0; i < 4; i++) this.remove(lifetimeTotalsButtons[i]);
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
    	
    	removeObjects();
    	
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		BufferedImage image = null;
		try {
			if (this.settingsScreen) image = ImageIO.read(new File("UI/profile-bg2.png"));
			else image = ImageIO.read(new File("UI/profile-bg1.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		g2.drawImage(image, 0, 0, getWidth(), getHeight(), 0, 0, image.getWidth(), image.getHeight(), null);
		
		BufferedImage checked = null;
		try {
			checked = ImageIO.read(new File("UI/checktoggle.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		
		BufferedImage unchecked = null;
		try {
			unchecked = ImageIO.read(new File("UI/checktoggle-un.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (this.settingsScreen) {
			for(int i = 0; i < 3; i++) {
				if (dashboardPanelsToggler[i]) g2.drawImage(checked, 130+(290-20)/2+465*i, getHeight()-60+(60-20)/2, null);
				else g2.drawImage(unchecked, 130+(290-20)/2+465*i, getHeight()-60+(60-20)/2, null);
				this.add(dashboardPanelsButtons[i]);
			}
			
			for(int i = 0; i < 3; i++) {
				if (activityTrackingPanelsToggler[i]) g2.drawImage(checked, 1350+(60-20)/2, 415+(75-20)/2 + 100*i, null);
				else g2.drawImage(unchecked, 1350+(60-20)/2, 415+(75-20)/2 + 100*i, null);
				this.add(activityTrackingPanelsButtons[i]);
			}
			
			for(int i = 0; i < 4; i++) {
				if (lifetimeTotalsToggler[i]) g2.drawImage(checked, 1060+12+(60-20)/2 + 69*i, 685+(60-20)/2, null);
				else g2.drawImage(unchecked, 1060+12+(60-20)/2 + 69*i, 685+(60-20)/2, null);
				this.add(lifetimeTotalsButtons[i]);
			}
			for(int i = 0; i < 5; i++) {
				this.add(labelSettings[i]);
			}
			g2.setColor(new Color(0,0,0,120));
			for(int i = 0; i < 3; i++) g2.fillRect(getWidth()/2 - 30 - (200*3 + 2) + (200 + 1)*i, 75+60 + 40 + 1, 200, 40);
			g2.fillRect(getWidth()/2 + 30, 75+60 + 40 + 1, 200, 40);
			g2.fillRect(getWidth()/2 + 30 + 350, 75+60 + 40 + 1, 200, 40);
		}else {
			for(int i = 0; i < 8; i++) this.add(label[i]);
		}
    }

	public void toggleScreen() {
		Preferences screen = this.mainWindow.getPreferences();
		if (this.settingsScreen) {
			screen.setDashboardPanelsToggler(dashboardPanelsToggler);
			screen.setActivityTrackingPanelsToggler(activityTrackingPanelsToggler);
			screen.setLifetimeTotalsToggler(lifetimeTotalsToggler);
			
			User user = screen.getUser();
			
			user.setGender(input[0].getText().toUpperCase().charAt(0));
			user.setAge(Integer.parseInt(input[1].getText()));
			user.setHeight(Float.parseFloat(input[2].getText()));
			
			if (input[3].getText().length() > 0 && input[4].getText().length() > 0) {
				screen.getVt().setWeightLossGoal(user, Float.parseFloat(input[3].getText()), Float.parseFloat(input[4].getText()));
				screen.getVt().setMileStones();
			}
		}
		
		this.settingsScreen = !this.settingsScreen;
		
		if (this.settingsScreen) this.settingsBtn.setText("Accept >");
		else this.settingsBtn.setText("Settings >");
		
		for(int i = 0; i < 5; i++) {
			if (this.settingsScreen) this.add(input[i]);
			else this.remove(input[i]);
		}
		
		this.repaint();
	}
}
