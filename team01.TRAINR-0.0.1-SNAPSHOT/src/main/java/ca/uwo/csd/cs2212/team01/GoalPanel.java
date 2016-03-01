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

public class GoalPanel extends JPanel {
	private Font font;
	private Rectangle rectList[];
	private Rectangle panelRect;
	private MainWindow mainWindow;
	
	private JLabel title, tableTitle1, tableTitle2, desc, btnDesc;
	private JTextArea currentWeight, targetWeight;
	private JButton submitForm;
	
	public GoalPanel(MainWindow mainWindow, Font font) {
		this.mainWindow = mainWindow;
		
		this.font = font;
		this.rectList = new Rectangle[4];

		this.setLayout(null);
		this.setOpaque(false);
		
		int height = 0;
		
		title = new JLabel("Set Weight Loss Goal", JLabel.LEFT);
		title.setFont(this.font.deriveFont(45.0f));
		Dimension size = title.getPreferredSize();
		title.setBounds((503-size.width)/2, height, size.width, size.height);
		title.setForeground(new Color(255,255,255,150));
		height += size.height+20;

		rectList[0] = new Rectangle(0, height, 250, 50);
		rectList[1] = new Rectangle(253, height, 250, 50);
		/*g2.fillRect(0, height, 250, 50);
		g2.fillRect(253, height, 250, 50);*/

		tableTitle1 = new JLabel("Current Weight (lbs)", JLabel.CENTER);
		tableTitle1.setFont(this.font.deriveFont(22.0f));
		tableTitle1.setBounds(0, height, 250, 50);
		tableTitle1.setForeground(new Color(255,255,255,150));

		tableTitle2 = new JLabel("Target Weight (lbs)", JLabel.CENTER);
		tableTitle2.setFont(this.font.deriveFont(22.0f));
		tableTitle2.setBounds(253, height, 250, 50);
		tableTitle2.setForeground(new Color(255,255,255,150));
		
		height += 53;

		rectList[2] = new Rectangle(0, height, 250, 50);
		rectList[3] = new Rectangle(253, height, 250, 50);
		/*g2.fillRect(0, height, 250, 50);
		g2.fillRect(253, height, 250, 50);*/
		//currWeightInputPos = new Dimension(0, height);
		
		currentWeight = new JTextArea();
		//currentWeight.setContentType("text/html");
		currentWeight.setLayout(null);
		//currentWeight.setWrapStyleWord(true);
		//currentWeight.setLineWrap(true);
		//currentWeight.setLocation(5, height+8);
		currentWeight.setSize(250, 50);
		currentWeight.setBounds(5+100, height+8, 250-100, 50-8);
		currentWeight.setOpaque(false);
		currentWeight.setFont(this.font.deriveFont(22.0f));
		currentWeight.setForeground(new Color(255,255,255,200));
		currentWeight.setCaretColor(new Color(255,255,255,200));
		//currentWeight.setEditorKit(kit);
		
		/*Document doc;
		SimpleAttributeSet center = new SimpleAttributeSet();
		StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);*/
		
		//doc = (StyledDocument)currentWeight.getDocument();
		//doc.setParagraphAttributes(0, doc.getLength(), center, false);

		targetWeight = new JTextArea();
		//targetWeight.setContentType("text/html");
		targetWeight.setLayout(null);
		//targetWeight.setLocation(253+5, height+8);
		//targetWeight.setSize(250, 50);
		targetWeight.setBounds(253+5+100, height+8, 250-100, 50-8);
		targetWeight.setOpaque(false);
		targetWeight.setFont(this.font.deriveFont(22.0f));
		targetWeight.setForeground(new Color(255,255,255,200));
		targetWeight.setCaretColor(new Color(255,255,255,200));
		//targetWeight.setEditorKit(kit);

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
		/*submitForm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				mainFrame.setVisible(false);
				this.mainFrame.getContentPane().removeAll();
				this.mainFrame.add(new DashboardScreen(mainFrame));
				this.mainFrame.setVisible(true);
				System.out.println("Button Clicked");
			}
		});*/
		
		btnDesc = new JLabel("Click to continue >", JLabel.LEFT);
		btnDesc.setFont(this.font.deriveFont(45.0f));
		size = btnDesc.getPreferredSize();
		submitForm.setSize(size.width, size.height);
		//btnDesc.setBounds((503-size.width)/2, height, size.width, size.height);
		//btnDesc.setForeground(new Color(255,255,255,150));
		
		submitForm.setLocation((503-size.width)/2, height);
		
		height += size.height;
		
		panelRect = new Rectangle(0, 0, 503, height);
		
    	this.add(title);
    	this.add(tableTitle1);
    	this.add(tableTitle2);
		this.add(currentWeight);
    	this.add(targetWeight);
    	this.add(desc);
    	this.add(submitForm);
    	//this.add(btnDesc);
	}

	public String getCurrentWeight() { return this.currentWeight.getText(); }
	public String getTargetWeight() { return this.targetWeight.getText(); }
	public JLabel getDesc() { return this.desc; }
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		this.setSize(panelRect.width, panelRect.height);
    	this.setLocation(1480/2 + (1480/2-panelRect.width)/2,(800-panelRect.height)/2);
    	
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		g2.setColor(new Color(0,0,0,(int)(255*0.5f)));
		
		for(Rectangle rect : this.rectList) g2.fillRect(rect.x, rect.y, rect.width, rect.height);
	}
}
