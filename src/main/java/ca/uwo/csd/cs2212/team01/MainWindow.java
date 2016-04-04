/**
 * 
 */
package ca.uwo.csd.cs2212.team01;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;

import javax.swing.*;

/**
 * @author team01
 *
 */
public class MainWindow extends JFrame implements Serializable {
	private static final long serialVersionUID = 1L;

	public Font FONT_HELVETICA_NEUE_THIN = null, FONT_HELVETICA_NEUE_ITALIC = null, FONT_HELVETICA_NEUE_BOLD = null;
	private int posX = 0, posY = 0;
	private boolean testMode, loadingAPIData, apiThreadsRunning;

	// Time & Date Stamps:
	public static final int MAX_PROGRESS = 1440;

	// Time & Date Formats:
	public static SimpleDateFormat fmDate = new SimpleDateFormat("yyyy-MM-dd"); // date
																				// format:
																				// 2016-02-18
	public static SimpleDateFormat fmDayofWeek = new SimpleDateFormat("EEEE"); // date
																				// format:
																				// Wednesday
	public static SimpleDateFormat fmLastRefresh = new SimpleDateFormat("MMMM d, h:mm a"); // date
																							// format:
																							// "Feb
																							// 28,
																							// 1:34
																							// PM"
	public static SimpleDateFormat fmTime = new SimpleDateFormat("H:mm"); // date
																			// format:
																			// 07:15
																			// (or
																			// 13:00
																			// for
																			// 1pm)
	public static SimpleDateFormat fmDay = new SimpleDateFormat("MMM d"); // date
																			// format:
																			// "Feb
																			// 28"
	public static SimpleDateFormat fmActivityDay = new SimpleDateFormat("EEEE, MMM d"); // date
																						// format:
																						// "Wednesday,
																						// Feb
																						// 28"

	private LoadingScreen loadingScreen;
	private ContinueScreen continueScreen;
	private SetupScreen setupScreen;
	private AddMealDishScreen addMealDishScreen;
	private MealDishScreen mealDishScreen;
	private WeighScreen weighScreen;
	private DashboardScreen dashboardScreen;
	private NavigationScreen navScreen;
	private MyPlansScreen myPlansScreen;
	private PlanManagerScreen planManagerScreen;
	private ProfileScreen profileScreen;

	private LinkedList<FitbitAPIThread> apiThreads;

	// User Preferences
	private Preferences userPreferences = null;

	/**
	 * Class Constructor
	 * 
	 * @param testMode
	 */
	public MainWindow(boolean testMode) {
		this.testMode = testMode;

		apiThreads = new LinkedList<FitbitAPIThread>();
		loadingAPIData = apiThreadsRunning = false;

		try {
			FileInputStream fin;
			if (this.isTestMode())
				fin = new FileInputStream("window-test.dat");
			else
				fin = new FileInputStream("window.dat");
			ObjectInputStream in = new ObjectInputStream(fin);
			userPreferences = (Preferences) in.readObject();
			in.close();
			fin.close();
		} catch (FileNotFoundException e) {
			System.out.println("No previous data found. Welcome, new user!");
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
		if (userPreferences == null)
			userPreferences = new Preferences();

		// this.setLayout(null);
		// this.setLocation(0, 0);
		this.setTitle("TRAINR");
		this.setSize(1480, 800);
		this.setUndecorated(true);
		this.setLocationRelativeTo(null);
		this.createMouseListener();
		// changed EXIT_ON_CLOSE to DISPOSE_ON_CLOSE, prevents errors when using
		// JFrame
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		this.createFonts();

		if (testMode) {
			this.userPreferences.setLastCall(new Date());
		}

		// apiThread = new FitbitAPIThread(this);

		navScreen = new NavigationScreen(this);
		continueScreen = new ContinueScreen(this);
		setupScreen = new SetupScreen(this);
		addMealDishScreen = new AddMealDishScreen(this);
		mealDishScreen = new MealDishScreen(this);
		loadingScreen = new LoadingScreen(this);
		weighScreen = new WeighScreen(this);
		myPlansScreen = new MyPlansScreen(this);
		planManagerScreen = new PlanManagerScreen(this);

		this.add(this.getSetupScreen());
		/*this.add(this.getLoadingScreen());

		if (testMode)
			this.loadingScreen.initTestMode();
		else
			this.loadingScreen.initSetup();*/
	}

	public Preferences getPreferences() {
		return this.userPreferences;
	}

	/**
	 * Checks if the program is in "test" mode.
	 * 
	 * @return
	 */
	public boolean isTestMode() {
		return this.testMode;
	}

	/**
	 * Gets the last timestamp the user data was updated.
	 */
	public void updateLastRefreshed() {
		this.setLastCall(new Date());
	}

	/**
	 * Refreshes the Dashboard Screen.
	 */
	public void updateDashboardScreen() {
		this.setDashboardScreen(new DashboardScreen(this));
	}

	/**
	 * Returns the last timestamp the user data was updated in MMMM d, h:mm a
	 * format.
	 * 
	 * @return
	 */
	public Feedback lastRefreshed() {
		Feedback fb = new Feedback();
		fb.setTXTCode(1);
		fb.addTXTone(fmLastRefresh.format(this.userPreferences.getLastCall().getTime()));
		return fb;
	}

	/**
	 * Sets up the Virtual Trainer.
	 * 
	 * @param currentWeight
	 * @param targetWeight
	 */
	public void setupVirtualTrainer(float currentWeight, float targetWeight) {
		// Virtual Trainer needs to deliver feedback to the user based on the
		// processed data.
		this.userPreferences.setVt(new VirtualTrainer());

		// Test Mode assumes user has already specified a weight loss goal:
		this.userPreferences.getVt().setWeightLossGoal(this.userPreferences.getUser(), currentWeight, targetWeight);
		this.userPreferences.getVt().setMileStones();
	}

	/**
	 * Gets the user object.
	 * 
	 * @return
	 */
	public User getUser() {
		return this.userPreferences.getUser();
	}

	/**
	 * Sets the user object.
	 * 
	 * @param user
	 */
	public void setUser(User user) {
		this.userPreferences.setUser(user);
	}

	/**
	 * Gets the time a last API call was made.
	 * 
	 * @return
	 */
	public Date getLastCall() {
		return this.userPreferences.getLastCall();
	}

	/**
	 * Sets the time the last API call was made.
	 * 
	 * @param lastCall
	 */
	public void setLastCall(Date lastCall) {
		this.userPreferences.setLastCall(lastCall);
	}

	/**
	 * Checks if the API call is the first time in real-time.
	 * 
	 * @return
	 */
	public boolean isFirstCall() {
		return this.userPreferences.getFirstCall();
	}

	/**
	 * Sets the first API call time in real-time.
	 * 
	 * @param firstCall
	 */
	public void setFirstCall(boolean firstCall) {
		this.userPreferences.setFirstCall(firstCall);
	}

	/**
	 * Returns this week's data.
	 * 
	 * @return
	 */
	public LinkedList<Day> getDays() {
		return this.userPreferences.getDays();
	}

	/**
	 * Returns next week's data.
	 * 
	 * @return
	 */
	public LinkedList<Day> getFutureDays() {
		return this.userPreferences.getFutureDays();
	}

	/**
	 * Adds a Meal to the list of Meals.
	 */
	public void addMeal(Meal newMeal) {
		if (newMeal != null && this.userPreferences.getMyMeals().size() < 7)
			this.userPreferences.getMyMeals().add(newMeal);
	}

	/**
	 * Adds a Dish to the list of Dishes.
	 */
	public void addDish(Meal newDish) {
		if (newDish != null && this.userPreferences.getMyDishes().size() < 7)
			this.userPreferences.getMyDishes().add(newDish);
	}

	/**
	 * Returns user preferences for dashboard
	 * 
	 * @return
	 */
	public Preferences getUserPreferences() {
		return this.userPreferences;
	}

	public void setUserPreferences(Preferences userPreferences) {
		this.userPreferences = userPreferences;
	}

	public LinkedList<Meal> getMyMeals() {
		return this.userPreferences.getMyMeals();
	}

	public void setMyMeals(LinkedList<Meal> myMeals) {
		this.userPreferences.setMyMeals(myMeals);
	}

	public LinkedList<Meal> getMyDishes() {
		return this.userPreferences.getMyDishes();
	}

	public void setMyDishes(LinkedList<Meal> myDishes) {
		this.userPreferences.setMyDishes(myDishes);
	}

	/**
	 * Copies this week's data onto another LinkedList data structure.
	 * 
	 * @return
	 */
	public Feedback updateWeeklyProgress() // to be called once everyday, every
											// morning?
	{
		LinkedList<Day> past6Days = new LinkedList<Day>();
		for (int i = 6; i > 0; i--)
			past6Days.add(this.userPreferences.getDays().get(this.userPreferences.getDays().size() - 1 - i));

		return this.userPreferences.getVt().updateWeeklyProgress(past6Days);
	}

	/**
	 * Gets the Virtual Trainer object.
	 * 
	 * @return VirtualTrainer
	 */
	public VirtualTrainer getVirtualTrainer() {
		return this.userPreferences.getVt();
	}

	/**
	 * Sets the Virtual Trainer object
	 * 
	 * @param vt
	 */
	public void setVirtualTrainer(VirtualTrainer vt) {
		this.userPreferences.setVt(vt);
	}

	public Boolean getFirstCall() {
		return this.userPreferences.getFirstCall();
	}

	public void setFirstCall(Boolean firstCall) {
		this.userPreferences.setFirstCall(firstCall);
	}

	/**
	 * Gets the day of the week as a string.
	 * 
	 * @param day
	 * @return
	 */
	public String getDayOfWeek(Date day) {
		return fmDayofWeek.format(day);
	}

	/**
	 * Gets the Navigation Screen object.
	 * 
	 * @return
	 */
	public NavigationScreen getNavigationScreen() {
		return this.navScreen;
	}

	/**
	 * Gets the Meal Dish Screen object.
	 * 
	 * @return
	 */
	public MealDishScreen getMealDishScreen() {
		return this.mealDishScreen;
	}

	/**
	 * Gets the My Plans Screen object.
	 * 
	 * @return
	 */
	public MyPlansScreen getMyPlansScreen() {
		return this.myPlansScreen;
	}

	/**
	 * Gets the Plan Manager Screen object.
	 * 
	 * @return
	 */
	public PlanManagerScreen getPlanManagerScreen() {
		return this.planManagerScreen;
	}

	/**
	 * Gets the Loading Screen object.
	 * 
	 * @return
	 */
	public LoadingScreen getLoadingScreen() {
		return this.loadingScreen;
	}

	/**
	 * Gets the Continue Screen object.
	 * 
	 * @return
	 */
	public ContinueScreen getContinueScreen() {
		return this.continueScreen;
	}

	/**
	 * Gets the Setup Screen object.
	 * 
	 * @return
	 */
	public SetupScreen getSetupScreen() {
		return this.setupScreen;
	}

	/**
	 * Gets the Add Meal and Dishes Screen object.
	 * 
	 * @return
	 */
	public AddMealDishScreen getAddMealDishScreen() {
		return this.addMealDishScreen;
	}

	/**
	 * Gets the Weigh Screen object.
	 * 
	 * @return
	 */
	public WeighScreen getWeighScreen() {
		return this.weighScreen;
	}

	/**
	 * Gets the Dashboard Screen object.
	 * 
	 * @return
	 */
	public DashboardScreen getDashboardScreen() {
		return this.dashboardScreen;
	}

	/**
	 * Load custom fonts & generate system environmental variables for the
	 * fonts.
	 */
	private void createFonts() {
		GraphicsEnvironment genv = GraphicsEnvironment.getLocalGraphicsEnvironment();

		// ClassLoader classLoader = getClass().getClassLoader();
		// File file = null;

		// Font font = null;
		try {
			FONT_HELVETICA_NEUE_THIN = Font.createFont(Font.TRUETYPE_FONT,
					new File("src/main/resources/FONTS/HelveticaNeueThin.ttf"));
			// FONT_HELVETICA_NEUE_BOLD = Font.createFont(Font.TRUETYPE_FONT,
			// new File("FONTS/HelveticaNeueBold.ttf"));
		} catch (FontFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		genv.registerFont(FONT_HELVETICA_NEUE_THIN);

		try {
			FONT_HELVETICA_NEUE_ITALIC = Font.createFont(Font.TRUETYPE_FONT,
					new File("src/main/resources/FONTS/HelveticaNeueThinItalic.ttf"));
		} catch (FontFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		genv.registerFont(FONT_HELVETICA_NEUE_ITALIC);

		// FONT_HELVETICA_NEUE_THIN =
		// UIManager.getDefaults().getFont("TabbedPane.font");
		// FONT_HELVETICA_NEUE_ITALIC =
		// UIManager.getDefaults().getFont("TabbedPane.font");

		/*
		 * try { FONT_HELVETICA_NEUE_BOLD = Font.createFont(Font.TRUETYPE_FONT,
		 * new File("FONTS/HelveticaNeue.ttf")); } catch (FontFormatException e)
		 * { // TODO Auto-generated catch block e.printStackTrace(); } catch
		 * (IOException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); }
		 */

		// GraphicsEnvironment genv =
		// GraphicsEnvironment.getLocalGraphicsEnvironment();
		// genv.registerFont(font);
		// font = font.deriveFont(12f);
	}

	/**
	 * Setup and call the API method in a thread which retrieves data from the
	 * FitBit API on a day-to-day basis.
	 * 
	 * @param date
	 * @param startTime
	 * @param endTime
	 * @param day
	 */
	public void APICall(String date, String startTime, String endTime, Day day) {
		apiThreads.add(new FitbitAPIThread(this, date, startTime, endTime, day));
		// this.apiThread.setAPICallParameters(date, startTime, endTime, day);
		// this.apiThread.execute();
	}

	/**
	 * Setup and call the API method in a thread which stores the data into data
	 * structures (arrays) from the API call.
	 * 
	 * @param responseBody
	 * @param name
	 * @param day
	 */
	/*
	 * public void StoreDataFromAPI(String responseBody, String name, Day day) {
	 * apiThreads.add(new FitbitAPIThread(this, responseBody, name, day));
	 * //this.apiThread.setStoreDataFromAPIParameters(responseBody, name, day);
	 * //this.apiThread.execute(); }
	 */

	// REFRESH EVENT METHOD

	/**
	 * Called to refresh the App's data with up-to-date data Called when: 1) App
	 * launches (and firstCall == false) (during LoadingScreen) 2) User clicks
	 * Manual Refresh button
	 */
	public void refreshEvent() {

		if (this.testMode) {
			this.userPreferences.getTp().updateStreaks(this.userPreferences.getDays());

			this.userPreferences.getUser().setLifeTimeValues(this.userPreferences.getDays());
			this.userPreferences.getUser().setBestValues(this.userPreferences.getDays());

			this.setVisible(false);
			this.getContentPane().removeAll();
			this.updateDashboardScreen();
			this.add(this.getDashboardScreen());
			this.setVisible(true);
			return;
		}
		
		this.setLoadingAPIData(true);

		// [TEST: ]
		// Initial Setup is completed, time has elapsed, and the user has
		// triggered a refresh event where their data and the UI needs updating.
		// API calls need to be made to update the user's data and the
		// downloaded data needs to be processed and displayed on UI.
		// Pseudo-Algorithm:
		// Get time difference between when the last API call was made and OS's
		// current time. Convert to minutes.
		// Compute the number of days that need to be updated from this time
		// difference.
		// Make the appropriate number of API calls to update the total number
		// of days that need updating.

		// Get OS's current time
		// Compute time difference
		//////////////////////////////
		Date currentTime = new Date();
		long timeRange = (currentTime.getTime() - this.userPreferences.getLastCall().getTime()) / (long) 1000
				/ (long) 60; // convert time difference to minutes
		//////////////////////////////

		if (timeRange > 0) // else: not enough time has elapsed to warrant a
							// refresh
		{
			int todayRemainder = 1440 - this.userPreferences.getDays().getLast().getDayProgress();

			if (timeRange < todayRemainder) // Update current day up till
											// currentTime
			{

				setLastCall(currentTime);
				APICall(fmDate.format(this.userPreferences.getDays().getLast().getDate()),
						fmTime.format(this.userPreferences.getDays().getLast().getLastUpdated()),
						fmTime.format(currentTime), this.userPreferences.getDays().getLast());
			}

			else if (timeRange == todayRemainder) // Update entire remainder of
													// current day
			{
				setLastCall(currentTime);
				APICall(fmDate.format(this.userPreferences.getDays().getLast().getDate()),
						fmTime.format(this.userPreferences.getDays().getLast().getLastUpdated()),
						fmTime.format(currentTime), this.userPreferences.getDays().getLast());
				// //Must add a new day to "days". This is the new "current
				// day":
				// Date date = new Date(); //Date of new day starting at 12:00am
				// Day day = new Day(date);
				// mainWindow.getDays().add(day);

				// Transfer first day in "futureDays" to end of "days"
				//////////////////////////////////////////////////////////
				this.userPreferences.getDays().add(this.userPreferences.getFutureDays().pop());
				// must replenish day lost by "futureDays"
				Day day = new Day(new Date(
						this.userPreferences.getFutureDays().getLast().getDate().getTime() + 24 * 60 * 60 * 1000)); // Has
																													// a
																													// date
																													// that
																													// is
																													// one
																													// day
																													// ahead
																													// of
																													// the
																													// last
																													// day
																													// in
																													// "futureDays"
				day.setDayProgress(0);
				// add day to end of "futureDays"
				this.userPreferences.getFutureDays().add(day);
				//////////////////////////////////////////////////////////

			}

			else if (timeRange > todayRemainder) {
				setLastCall(currentTime); // First, update the previous "current
											// day" first
				APICall(fmDate.format(this.userPreferences.getDays().getLast().getDate()),
						fmTime.format(this.userPreferences.getDays().getLast().getLastUpdated()), "23:59",
						this.userPreferences.getDays().getLast());

				double daysRemainder = timeRange - todayRemainder; // the
																	// previous
																	// "current
																	// day" has
																	// been
																	// updated
																	// so we
																	// must
																	// remove
																	// this from
																	// the days
																	// that need
																	// updating.
				int nDays = (int) daysRemainder / 1440; // number of WHOLE days
														// that need to be added
														// to "days" and then
														// updated

				if (nDays == 0) // only new current day needs to be updated
				{
					// Transfer first day in "futureDays" to end of "days"
					//////////////////////////////////////////////////////////
					this.userPreferences.getDays().add(this.userPreferences.getFutureDays().pop());
					// must replenish day lost by "futureDays"
					Day day = new Day(new Date(
							this.userPreferences.getFutureDays().getLast().getDate().getTime() + 24 * 60 * 60 * 1000)); // Has
																														// a
																														// date
																														// that
																														// is
																														// one
																														// day
																														// ahead
																														// of
																														// the
																														// last
																														// day
																														// in
																														// "futureDays"
					day.setDayProgress(0);
					// add day to end of "futureDays"
					this.userPreferences.getFutureDays().add(day);
					//////////////////////////////////////////////////////////

					// now update the new current day in "days" with the
					// appropriate amount of data:
					APICall(fmDate.format(this.userPreferences.getDays().getLast().getDate()), "00:00",
							fmTime.format(currentTime), this.userPreferences.getDays().getLast());
				} else if (nDays > 0) {
					// Need to transfer "nDays" number of days from "futureDays"
					// to "days"
					// Assign the correct dates to them. Then add to "days"
					// LinkedList. Then call APICall on new days.
					for (int i = 0; i < nDays; i++) {
						// Transfer first day in "futureDays" to end of "days"
						//////////////////////////////////////////////////////////
						this.userPreferences.getDays().add(this.userPreferences.getFutureDays().pop());
						// must replenish day lost by "futureDays"
						Day day = new Day(new Date(this.userPreferences.getFutureDays().getLast().getDate().getTime()
								+ 24 * 60 * 60 * 1000)); // Has a date that is
															// one day ahead of
															// the last day in
															// "futureDays"
						day.setDayProgress(0);
						// add day to end of "futureDays"
						this.userPreferences.getFutureDays().add(day);
						//////////////////////////////////////////////////////////

						// now update the newly added day in "days" with a full
						// day's worth of data:
						APICall(fmDate.format(this.userPreferences.getDays().getLast().getDate()), "00:00", "23:59",
								this.userPreferences.getDays().getLast());
					}
					if (((int) daysRemainder % 1440) > 0) // then the new
															// "current day"
															// needs updated
															// data as well
					{
						// Transfer first day in "futureDays" to end of "days"
						//////////////////////////////////////////////////////////
						this.userPreferences.getDays().add(this.userPreferences.getFutureDays().pop());
						// must replenish day lost by "futureDays"
						Day day = new Day(new Date(this.userPreferences.getFutureDays().getLast().getDate().getTime()
								+ 24 * 60 * 60 * 1000)); // Has a date that is
															// one day ahead of
															// the last day in
															// "futureDays"
						day.setDayProgress(0);
						// add day to end of "futureDays"
						this.userPreferences.getFutureDays().add(day);
						//////////////////////////////////////////////////////////

						// now update the new current day in "days" with the
						// appropriate amount of data:
						APICall(fmDate.format(this.userPreferences.getDays().getLast().getDate()), "00:00",
								fmTime.format(currentTime), this.userPreferences.getDays().getLast());
					}
				}
			}
		}

		this.setLoadingAPIData(false);
	}

	/**
	 * Storing the data into data structures (arrays) from the and old API call
	 * to test the program when not connected to the Internet.
	 */
	/*
	 * private void StoredAPIData(Day day){
	 * 
	 * }
	 */
	/**
	 * Creates a mouse listener to move the program from any position on the
	 * window.
	 */
	private void createMouseListener() {
		this.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				posX = e.getX();
				posY = e.getY();
			}
		});

		this.addMouseMotionListener(new MouseAdapter() {
			public void mouseDragged(MouseEvent evt) {
				// sets frame position when mouse dragged
				setLocation(evt.getXOnScreen() - posX, evt.getYOnScreen() - posY);
			}
		});
	}

	public ProfileScreen getProfileScreen() {
		return profileScreen;
	}

	public void setProfileScreen(ProfileScreen profileScreen) {
		this.profileScreen = profileScreen;
	}

	public void setDashboardScreen(DashboardScreen dashboardScreen) {
		this.dashboardScreen = dashboardScreen;
	}

	public void apiThreadDoneHandler(FitbitAPIThread apiThread) {
		apiThreads.remove(apiThread);
		if (apiThreads.size() > 0) {
			apiThreads.getFirst().execute();
			return;
		}
		if (!this.isLoadingAPIData()) {
			if (apiThreads.size() <= 0) {
				apiThreadsRunning = false;
				
				if (this.isFirstCall()) {
					this.setFirstCall(false);

					this.setVisible(false);
					this.getContentPane().removeAll();
					this.add(this.getSetupScreen());
					this.setVisible(true);
				} else {
					this.userPreferences.getTp().updateStreaks(this.userPreferences.getDays());

					this.userPreferences.getUser().setLifeTimeValues(this.userPreferences.getDays());
					this.userPreferences.getUser().setBestValues(this.userPreferences.getDays());

					this.setVisible(false);
					this.getContentPane().removeAll();
					this.updateDashboardScreen();
					this.add(this.getDashboardScreen());
					this.setVisible(true);
				}
			}
		}
	}
	
	public boolean isLoadingAPIData() {
		return loadingAPIData;
	}

	public void setLoadingAPIData(boolean loadingAPIData) {
		this.loadingAPIData = loadingAPIData;
		if (!apiThreadsRunning && !loadingAPIData && apiThreads.size() > 0) {
			apiThreadsRunning = true;
			apiThreads.getFirst().execute();
		}
	}
}