package ca.uwo.csd.cs2212.team01;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class WeighPanel extends JPanel {
	private Font font;
	private Rectangle rectList[];
	private Rectangle panelRect;
	private MainWindow mainWindow;
	
	private JLabel tableTitle1, desc, btnDesc;
	private JTextArea currentWeight;
	private JButton submitForm;
	
	public WeighPanel(MainWindow mainWindow, Font font) {
		this.mainWindow = mainWindow;
		
		this.font = font;
		this.rectList = new Rectangle[2];

		this.setLayout(null);
		this.setOpaque(false);
		
		int height = 0;

		rectList[0] = new Rectangle((500-250)/2, height, 250, 50);

		tableTitle1 = new JLabel("Current Weight (lbs)", JLabel.CENTER);
		Dimension size = tableTitle1.getPreferredSize();
		tableTitle1.setFont(this.font.deriveFont(22.0f));
		tableTitle1.setBounds((500-size.width)/2, height, 250, 50);
		tableTitle1.setForeground(new Color(255,255,255,150));
		
		height += 53;

		rectList[1] = new Rectangle((500-250)/2, height, 250, 50);
		
		currentWeight = new JTextArea();
		currentWeight.setLayout(null);
		currentWeight.setSize(250, 50);
		currentWeight.setBounds((500-250)/2 + 5+100, height+8, 250-100, 50-8);
		currentWeight.setOpaque(false);
		currentWeight.setFont(this.font.deriveFont(22.0f));
		currentWeight.setForeground(new Color(255,255,255,200));
		currentWeight.setCaretColor(new Color(255,255,255,200));

		height += 53+20;

		desc = new JLabel("Please enter in a weight for each.", JLabel.LEFT);
		desc.setFont(this.font.deriveFont(30.0f));
		size = desc.getPreferredSize();
		desc.setBounds((503-size.width)/2, height, size.width, size.height);
		desc.setForeground(new Color(255,255,255,150));
		height += size.height+50;
		
		submitForm = new JButton("Click to continue >");
		submitForm.setFont(this.font.deriveFont(45.0f));
		submitForm.setBackground(null);
		submitForm.setBorder(null);
		submitForm.setFocusPainted(false);
		submitForm.setMargin(new Insets(0, 0, 0, 0));
		submitForm.setContentAreaFilled(false);
		submitForm.setBorderPainted(false);
		submitForm.setOpaque(false);
		submitForm.setForeground(new Color(255,255,255,150));
		submitForm.setFocusable(false);
        //submitForm.addActionListener(new ButtonActionListener(2, 1, this.mainWindow));
        
		btnDesc = new JLabel("Click to continue >", JLabel.LEFT);
		btnDesc.setFont(this.font.deriveFont(45.0f));
		size = btnDesc.getPreferredSize();
		submitForm.setSize(size.width, size.height);
		btnDesc.setBounds(0, 0, size.width, size.height);
		btnDesc.setForeground(new Color(255,255,255,150));
		
		submitForm.setLocation((503-size.width)/2, height);
		
		height += size.height;
		
		panelRect = new Rectangle(0, 0, 503, height);
		
    	this.add(tableTitle1);
		this.add(currentWeight);
    	this.add(desc);
    	this.add(submitForm);
    	//this.add(btnDesc);
	}

	public String getCurrentWeight() { return this.currentWeight.getText(); }
	public JLabel getDesc() { return this.desc; }
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		this.setSize(panelRect.width, panelRect.height);
    	this.setLocation((1480/2-panelRect.width)/2,(800-panelRect.height)/2);
    	
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		g2.setColor(new Color(0,0,0,(int)(255*0.5f)));
		
		for(Rectangle rect : this.rectList) g2.fillRect(rect.x, rect.y, rect.width, rect.height);
	}
}
