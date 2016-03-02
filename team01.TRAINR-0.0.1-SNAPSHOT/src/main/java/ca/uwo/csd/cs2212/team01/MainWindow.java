/**
 * 
 */
package ca.uwo.csd.cs2212.team01;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Arc2D;
import java.awt.image.BufferedImage;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.StyleSheet;

/**
 * @author Kamal
 *
 */
public class MainWindow extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int posX = 0, posY = 0;
	private boolean testMode;
	
	static final int MAX_PROGRESS = 1440;

	//Time & Date Stamps:
	private Date lastCall;

	//User & Virtual Trainer Storage:
	private User user;	
	private VirtualTrainer vt;

	//Data Storage:
	private LinkedList<Day> days = new LinkedList<Day>();
	private LinkedList<Day> futureDays = new LinkedList<Day>();
	private LinkedList<Day> past6Days = new LinkedList<Day>();
	
	private LoadingScreen loadingScreen;
	private WeighScreen weighScreen;
	private SplashScreen splashScreen;
	private DashboardScreen dashboardScreen;
	
	public MainWindow(boolean testMode) {
		this.testMode = testMode;
		
    	//this.setLayout(null);
    	//this.setLocation(0, 0);
    	this.setTitle("TRAINR");
    	this.setSize(1480, 800);
    	this.setUndecorated(true);
    	this.setLocationRelativeTo(null);
    	this.createMouseListener();
    	// changed EXIT_ON_CLOSE to DISPOSE_ON_CLOSE, prevents errors when using JFrame
    	this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    	
    	if (testMode) {
    		this.lastCall = new Date();
    	}

		loadingScreen = new LoadingScreen(this);
		splashScreen = new SplashScreen(this);
		//weighScreen = new WeighScreen(this);
		dashboardScreen = new DashboardScreen(this);
		
		this.add(splashScreen);
	}
    
	public boolean isTestMode() { return this.testMode; }
	public void setLastCall(Date newDate) { this.lastCall = newDate; }
	
	public void setupVirtualTrainer(float currentWeight, float targetWeight) {
		// Virtual Trainer needs to deliver feedback to the user based on the processed data.
		vt = new VirtualTrainer();
    	// Test Mode assumes user has already specified a weight loss goal:
		System.out.println();
		if(vt.setWeightLossGoal(user, currentWeight, targetWeight)) System.out.println("Setting current weight and target weight successful.") ;
		vt.setMileStones();
		System.out.println();
		
		if (testMode) vt.addNewWeightMeasurement(user, currentWeight);
	}
	
    public LinkedList<Day> getDays() { return this.days; }
    public LinkedList<Day> getPast6Days() { return this.past6Days; }

	public Feedback updateWeeklyProgress() //to be called once everyday, every morning?
	{
		return this.vt.updateWeeklyProgress(past6Days);
	}
	
	public VirtualTrainer getVirtualTrainer() {
		return this.vt;
	}
	
	public LoadingScreen getLoadingScreen() { return this.loadingScreen; }
	public SplashScreen getSplashScreen() { return this.splashScreen; }
	public WeighScreen getWeighScreen() { return this.weighScreen; }
	public DashboardScreen getDashboardScreen() { return this.dashboardScreen; }
    
	private void createMouseListener() {
    	this.addMouseListener(new MouseAdapter()
    	{
    	   public void mousePressed(MouseEvent e)
    	   {
    	      posX = e.getX();
    	      posY = e.getY();
    	   }
    	});
    	
    	this.addMouseMotionListener(new MouseAdapter()
    	{
    	     public void mouseDragged(MouseEvent evt)
    	     {
    			//sets frame position when mouse dragged			
    			setLocation(evt.getXOnScreen()-posX,evt.getYOnScreen()-posY);
    	     }
    	});
    }
}