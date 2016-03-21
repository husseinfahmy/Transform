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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.github.scribejava.apis.FitbitApi20;
import com.github.scribejava.apis.service.FitbitOAuth20ServiceImpl;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.exceptions.OAuthConnectionException;
import com.github.scribejava.core.exceptions.OAuthException;
/**
 * @author team01
 *
 */
public class MainWindow extends JFrame {
	static final String CREDENTIALS_FILE_PATH = "src/main/resources/Team1Credentials.txt";
	static final String TOKENS_FILE_PATH = "src/main/resources/Team1Tokens.txt";
	
	private static final long serialVersionUID = 1L;
	public Font FONT_HELVETICA_NEUE_THIN = null, FONT_HELVETICA_NEUE_ITALIC = null, FONT_HELVETICA_NEUE_BOLD = null;
	private int posX = 0, posY = 0;
	private boolean testMode;

	//Time & Date Stamps:

	private Date lastCall;
	private Boolean firstCall = true;//srlze
	public static final int MAX_PROGRESS = 1440;

	//User & Virtual Trainer Storage:
	private User user;	//srlze
	private VirtualTrainer vt;


	//Data Storage:
	private LinkedList<Day> days = new LinkedList<Day>();
	private LinkedList<Day> futureDays = new LinkedList<Day>();
	
	private LinkedList<Meal> myMeals = new LinkedList<Meal>();
	private LinkedList<Meal> myDishes = new LinkedList<Meal>();

	//Time & Date Formats:
	private SimpleDateFormat fmDate = new SimpleDateFormat("yyyy-MM-dd"); 				//date format: 2016-02-18
	private SimpleDateFormat fmDayofWeek = new SimpleDateFormat ("EEEE");				//date format: Wednesday
	private SimpleDateFormat fmLastRefresh = new SimpleDateFormat ("MMMM d, h:mm a");	//time format: "Feb 28, 1:34 PM"
	private SimpleDateFormat fmTime = new SimpleDateFormat ("H:mm");					//time format 07:15 (or 13:00 for 1pm)
	
	private LoadingScreen loadingScreen;
	private WeighScreen weighScreen;
	private SplashScreen splashScreen;
	private DashboardScreen dashboardScreen;
	
	//User Preferences
	private DashboardPreferences userDashboardPreferences = new DashboardPreferences();
	
	/**
	 * Class Constructor
	 * @param testMode
	 */
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
    	
    	this.createFonts();
    	
    	if (testMode) {
    		this.lastCall = new Date();
    	}
    	
    	
    	
    	try {
    		//read user dashboard preferences from file
    		ObjectInputStream in = new ObjectInputStream(new FileInputStream("dashboardpreferences.dat"));
    		userDashboardPreferences = (DashboardPreferences) in.readObject();
    		
    		
    		//read future days and plans data
    		in = new ObjectInputStream(new FileInputStream("futuredays.dat"));
    		futureDays = (LinkedList<Day>) in.readObject();
    		
    		
    		//read meal history
    		in = new ObjectInputStream(new FileInputStream("mymeals.dat"));
    		myMeals = (LinkedList<Meal>) in.readObject();
    		
    		
    		//read dish history
    		in = new ObjectInputStream(new FileInputStream("mydishes.dat"));
    		myDishes = (LinkedList<Meal>) in.readObject();
    		
    		
    		//read user past days from file
    		in = new ObjectInputStream(new FileInputStream("pastdays.dat"));
    		days = (LinkedList<Day>) in.readObject();
    	    
    	    
    		//read firstcall from file
    		in = new ObjectInputStream(new FileInputStream("firstcall.dat"));
    		firstCall = (Boolean) in.readObject();
    		
    		
    		//read user from file
    		in = new ObjectInputStream(new FileInputStream("user.dat"));
    		user = (User) in.readObject();
    		
    		
    		//read virtual trainer from file
    		in = new ObjectInputStream(new FileInputStream("virtualtrainer.dat"));
    		vt = (VirtualTrainer) in.readObject();
    		in.close();
    	} catch (FileNotFoundException e) {
    		System.out.println("Couldn't find previous preferences file. Resetting preferences");
    	} catch (IOException e) {
    		System.out.println(e.getMessage());
    	} catch (ClassNotFoundException e) {
    		System.out.println(e.getMessage());
    	}

    	
    	loadingScreen = new LoadingScreen(this);
		splashScreen = new SplashScreen(this);
		weighScreen = new WeighScreen(this);
		dashboardScreen = new DashboardScreen(this);
		
		this.add(loadingScreen);

		if (testMode) this.getLoadingScreen().initTestMode();
		else this.getLoadingScreen().initSetup();
	}
	
	/**
	 * Checks if the program is in "test" mode.
	 * @return
	 */
	public boolean isTestMode() { return this.testMode; }
	
	/**
	 * Gets the last timestamp the user data was updated.
	 */
	public void updateLastRefreshed() { this.setLastCall(new Date()); }
	
	/**
	 * Refreshes the Dashboard Screen.
	 */
	public void updateDashboardScreen() { this.dashboardScreen = new DashboardScreen(this); }
	
	/**
	 * Returns the last timestamp the user data was updated in MMMM d, h:mm a format.
	 * @return
	 */
	public Feedback lastRefreshed()
	{
		Feedback fb = new Feedback();
		fb.setTXTCode(1);
		fb.addTXTone(fmLastRefresh.format(this.lastCall.getTime()));
		return fb;
	}
	
	/**
	 * Sets up the Virtual Trainer.
	 * @param currentWeight
	 * @param targetWeight
	 */
	public void setupVirtualTrainer(float currentWeight, float targetWeight) {
		// Virtual Trainer needs to deliver feedback to the user based on the processed data.
		vt = new VirtualTrainer();
    	// Test Mode assumes user has already specified a weight loss goal:
		System.out.println();
		if(vt.setWeightLossGoal(user, currentWeight, targetWeight)) System.out.println("Setting current weight and target weight successful.") ;
		vt.setMileStones();
		System.out.println();
		
		//if (testMode) vt.addNewWeightMeasurement(user, currentWeight);
	}
	
    /**
     * Gets the user object.
     * @return
     */
    public User getUser() { return this.user; }
    /**
     * Sets the user object.
     * @param user
     */
    public void setUser(User user) { this.user = user; }

    /**
     * Gets the time a last API call was made.
     * @return
     */
    public Date getLastCall() { return this.lastCall; }
    /**
     * Sets the time the last API call was made.
     * @param lastCall
     */
    public void setLastCall(Date lastCall) { this.lastCall = lastCall; }
    
    /**
     * Checks if the API call is the first time in real-time.
     * @return
     */
    public boolean isFirstCall() { return firstCall; }
	/**
	 * Sets the first API call time in real-time.
	 * @param firstCall
	 */
	public void setFirstCall(boolean firstCall) { this.firstCall = firstCall; }

	/**
	 * Returns this week's data.
	 * @return
	 */
	public LinkedList<Day> getDays() { return this.days; }
    /**
     * Returns next week's data.
     * @return
     */
    public LinkedList<Day> getFutureDays() { return this.futureDays; }
    
    
    /**
     * Returns user preferences for dashboard
     * @return
     */
	public DashboardPreferences getUserDashboardPreferences() {
		return userDashboardPreferences;
	}

	public void setUserDashboardPreferences(DashboardPreferences userDashboardPreferences) {
		this.userDashboardPreferences = userDashboardPreferences;
	}

	public LinkedList<Meal> getMyMeals() {
		return myMeals;
	}

	public void setMyMeals(LinkedList<Meal> myMeals) {
		this.myMeals = myMeals;
	}

	public LinkedList<Meal> getMyDishes() {
		return myDishes;
	}

	public void setMyDishes(LinkedList<Meal> myDishes) {
		this.myDishes = myDishes;
	}

	/**
	 * Copies this week's data onto another LinkedList data structure.
	 * @return
	 */
	public Feedback updateWeeklyProgress() //to be called once everyday, every morning?
	{
		LinkedList<Day> past6Days = new LinkedList<Day>();
		for(int i = 6; i>0;i--) past6Days.add(days.get(days.size()-1-i));
			
		return this.vt.updateWeeklyProgress(past6Days);
	}
	
	/**
	 * Gets the Virtual Trainer object.
	 * @return VirtualTrainer
	 */
	public VirtualTrainer getVirtualTrainer() { return this.vt; }
	/**
	 * Sets the Virtual Trainer object
	 * @param vt
	 */
	public void setVirtualTrainer(VirtualTrainer vt) { this.vt = vt; } 
	
	public Boolean getFirstCall() {
		return firstCall;
	}

	public void setFirstCall(Boolean firstCall) {
		this.firstCall = firstCall;
	}

	/**
	 * Gets the day of the week as a string.
	 * @param day
	 * @return
	 */
	public String getDayOfWeek(Date day) { return fmDayofWeek.format(day); }
	
	/**
	 * Gets the Loading Screen object.
	 * @return
	 */
	public LoadingScreen getLoadingScreen() { return this.loadingScreen; }
	/**
	 * Gets the Splash Screen object.
	 * @return
	 */
	public SplashScreen getSplashScreen() { return this.splashScreen; }
	/**
	 * Gets the Weigh Screen object.
	 * @return
	 */
	public WeighScreen getWeighScreen() { return this.weighScreen; }
	/**
	 * Gets the Dashboard Screen object.
	 * @return
	 */
	public DashboardScreen getDashboardScreen() { return this.dashboardScreen; }

    /**
     * Load custom fonts & generate system environmental variables for the fonts.
     */
    private void createFonts() {
    	GraphicsEnvironment genv = GraphicsEnvironment.getLocalGraphicsEnvironment();
    	
    	ClassLoader classLoader = getClass().getClassLoader();
    	File file;
    	
    	//file = new File(classLoader.getResource("FONTS/HelveticaNeueThin.ttf").getFile());
    	//Font font = null;
		/*try {
			FONT_HELVETICA_NEUE_THIN = Font.createFont(Font.TRUETYPE_FONT, file);
			//FONT_HELVETICA_NEUE_BOLD = Font.createFont(Font.TRUETYPE_FONT, new File("FONTS/HelveticaNeueBold.ttf"));
		} catch (FontFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		genv.registerFont(FONT_HELVETICA_NEUE_THIN);
		
		FONT_HELVETICA_NEUE_THIN = UIManager.getDefaults().getFont("TabbedPane.font");
		
		//file = new File(classLoader.getResource("FONTS/HelveticaNeueThinItalic.ttf").getFile());
		try {
			FONT_HELVETICA_NEUE_ITALIC = Font.createFont(Font.TRUETYPE_FONT, file);
		} catch (FontFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		genv.registerFont(FONT_HELVETICA_NEUE_ITALIC);*/

		FONT_HELVETICA_NEUE_THIN = UIManager.getDefaults().getFont("TabbedPane.font");
		FONT_HELVETICA_NEUE_ITALIC = UIManager.getDefaults().getFont("TabbedPane.font");
		
		/*try {
			FONT_HELVETICA_NEUE_BOLD = Font.createFont(Font.TRUETYPE_FONT, new File("FONTS/HelveticaNeue.ttf"));
		} catch (FontFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
    	//GraphicsEnvironment genv = GraphicsEnvironment.getLocalGraphicsEnvironment();
    	//genv.registerFont(font);
    	//font = font.deriveFont(12f);
    }
    
    
    //REFRESH EVENT METHOD
    
    /**
	 * Called to refresh the App's data with up-to-date data
	 * Called when:
	 * 1) App launches (and firstCall == false) (during LoadingScreen)
	 * 2) User clicks Manual Refresh button
	 */
	public void refreshEvent()
	{
			//[TEST:			]
			// Initial Setup is completed, time has elapsed, and the user has triggered a refresh event where their data and the UI needs updating.
			// API calls need to be made to update the user's data and the downloaded data needs to be processed and displayed on UI.
			//Pseudo-Algorithm:
			// Get time difference between when the last API call was made and OS's current time. Convert to minutes.
			// Compute the number of days that need to be updated from this time difference.
			// Make the appropriate number of API calls to update the total number of days that need updating.
		
		
			//Get OS's current time
			//Compute time difference
			//////////////////////////////
			Date currentTime = new Date(); 																
			long timeRange = (currentTime.getTime() - lastCall.getTime())/(long)1000/(long)60; 		//convert time difference to minutes
			//////////////////////////////
			
			if(timeRange > 0) 																				//else: not enough time has elapsed to warrant a refresh
			{
				int todayRemainder = 1440 - days.getLast().getDayProgress();
				
				if (timeRange < todayRemainder) 															//Update current day up till currentTime
				{
					
					setLastCall(currentTime); 
					APICall(fmDate.format(days.getLast().getDate()),fmTime.format(days.getLast().getLastUpdated()),fmTime.format(currentTime),days.getLast());
				}
				
				else if (timeRange == todayRemainder) 														//Update entire remainder of current day
				{ 
					setLastCall(currentTime);
					APICall(fmDate.format(days.getLast().getDate()),fmTime.format(days.getLast().getLastUpdated()),fmTime.format(currentTime),days.getLast());
//					//Must add a new day to "days". This is the new "current day":
//					Date date = new Date(); //Date of new day starting at 12:00am
//					Day day = new Day(date);
//					mainWindow.getDays().add(day);
					
					//Transfer first day in "futureDays" to end of "days"
					//////////////////////////////////////////////////////////
					days.add(futureDays.pop());
					//must replenish day lost by "futureDays"
					Day day = new Day(new Date(futureDays.getLast().getDate().getTime() + 24*60*60*1000));	//Has a date that is one day ahead of the last day in "futureDays"
					day.setDayProgress(0);
					//add day to end of "futureDays"
					futureDays.add(day);
					//////////////////////////////////////////////////////////
					
				}
				
				else if (timeRange > todayRemainder) 
				{ 
					setLastCall(currentTime);								//First, update the previous "current day" first
					APICall(fmDate.format(days.getLast().getDate()),fmTime.format(days.getLast().getLastUpdated()),"23:59",days.getLast()); 												
					
					double daysRemainder = timeRange - todayRemainder; 					//the previous "current day" has been updated so we must remove this from the days that need updating.
					int nDays = (int)daysRemainder / 1440; 								//number of WHOLE days that need to be added to "days" and then updated
					
					if (nDays == 0) 													//only new current day needs to be updated
					{
						//Transfer first day in "futureDays" to end of "days"
						//////////////////////////////////////////////////////////
						days.add(futureDays.pop());
						//must replenish day lost by "futureDays"
						Day day = new Day(new Date(futureDays.getLast().getDate().getTime() + 24*60*60*1000));	//Has a date that is one day ahead of the last day in "futureDays"
						day.setDayProgress(0);
						//add day to end of "futureDays"
						futureDays.add(day);
						//////////////////////////////////////////////////////////
						
						//now update the new current day in "days" with the appropriate amount of data:
						APICall(fmDate.format(days.getLast().getDate()),"00:00",fmTime.format(currentTime),days.getLast()); 
					}
					else if(nDays > 0)
					{
						//Need to transfer "nDays" number of days from "futureDays" to "days"
						//Assign the correct dates to them. Then add to "days" LinkedList. Then call APICall on new days.
						for (int i = 0; i<nDays; i++)
						{ 
							//Transfer first day in "futureDays" to end of "days"
							//////////////////////////////////////////////////////////
							days.add(futureDays.pop());
							//must replenish day lost by "futureDays"
							Day day = new Day(new Date(futureDays.getLast().getDate().getTime() + 24*60*60*1000));	//Has a date that is one day ahead of the last day in "futureDays"
							day.setDayProgress(0);
							//add day to end of "futureDays"
							futureDays.add(day);
							//////////////////////////////////////////////////////////
							
							//now update the newly added day in "days" with a full day's worth of data:
							APICall(fmDate.format(days.getLast().getDate()),"00:00","23:59",days.getLast()); 
						}
						if (((int)daysRemainder % 1440) > 0) 							// then the new "current day" needs updated data as well
						{ 
							//Transfer first day in "futureDays" to end of "days"
							//////////////////////////////////////////////////////////
							days.add(futureDays.pop());
							//must replenish day lost by "futureDays"
							Day day = new Day(new Date(futureDays.getLast().getDate().getTime() + 24*60*60*1000));	//Has a date that is one day ahead of the last day in "futureDays"
							day.setDayProgress(0);
							//add day to end of "futureDays"
							futureDays.add(day);
							//////////////////////////////////////////////////////////
							
							//now update the new current day in "days" with the appropriate amount of data:
							APICall(fmDate.format(days.getLast().getDate()),"00:00",fmTime.format(currentTime),days.getLast()); 
						}
					}
				}
			}
			
	}
    
    
    
    
    
	//API METHODS
    
	//[TEST: PASSED]
	/**
	 * Retrieves data from the FitBit API on a day-to-day basis.
	 * @param date
	 * @param startTime
	 * @param endTime
	 * @param day
	 */
    public void APICall(String date, String startTime, String endTime, Day day) 
	{	
String fmStartTime = startTime;		//fmStartTime.length() == 4 if "0:00" or == 5 if "00:00"
		String fmEndTime = endTime;			//fmEndTime.length() == 4 if "0:00" or == 5 if "00:00"
		
		// format startTime and endTime if times are between 1:00am and 9:59am ( ie. if .length() == 4 ) by adding an initial "0" 
		if(fmStartTime.length() == 4) 	
		{
			fmStartTime = "0" + fmStartTime.charAt(0) + ":" + fmStartTime.charAt(2) + fmStartTime.charAt(3);
		}
		if(fmEndTime.length() == 4) 
		{
			fmEndTime = "0" + fmEndTime.charAt(0) + ":" + fmEndTime.charAt(2) + fmEndTime.charAt(3);
		}
		
		// set Day Object's progress to current day progress
		// set Last Updated to "lastCall"	
		if (fmEndTime.equals("23:59"))
		{  day.setDayProgress(MAX_PROGRESS);  }
		else 
		{    
			String endHoursStr = "" + fmEndTime.charAt(0) + fmEndTime.charAt(1);			//get the hours part of the string
			String endMinutesStr = "" + fmEndTime.charAt(3) + fmEndTime.charAt(4);		//get the minutes part of the string
			
			 int endHours = Integer.parseInt(endHoursStr); 			//convert to integer
			 int endMinutes = Integer.parseInt(endMinutesStr);		//convert to integer
			
			 //calculate total number of minutes of ENDTIME if # hours elapsed > 0
			 if ( endHours > 0 ) { endMinutes = endHours*60 + endMinutes; } 	
			   
			 day.setDayProgress(endMinutes);
			 day.setLastUpdated(lastCall);
		 }
		
		// Make the API Calls:
		
    	String nameOfActivity = "";
	try{
    	for (int i = 0; i < 6; i++)
    	{
    		if(i == 0)
    		{
    			nameOfActivity ="floors";
    		}
    		else if( i == 1)
    		{
    			nameOfActivity = "steps";
    		}
    		else if( i == 2)
    		{
    			nameOfActivity = "calories";
    		}
    		else if( i == 3)
    		{
    			nameOfActivity = "distance";
    		}
    		else if( i == 4)
    		{
    			nameOfActivity = "minutesSedentary";
    		}
    		else 
    		{
    			nameOfActivity = "heart";
    		}

        //read credentials from a file
        BufferedReader bufferedReader=null;
        // This will reference one line at a time
        String line = null;
         
        //Need to save service credentials for Fitbit
        String apiKey = null;      
        String apiSecret = null;
        String clientID = null;
         
        //holder for all the elements we will need to make an access token ( information about an authenticated session )
        String accessTokenItself =  null;
        String tokenType = null;
        String refreshToken = null;
        Long expiresIn = null;
        String rawResponse = null;
        
    	ClassLoader classLoader = getClass().getClassLoader();
    	
        //This is the only scope you have access to currently
        String scope = "activity%20heartrate";
        try {
            // File with service credentials.
        	//classLoader.getResource("Team1Credentials.txt").getFile()
            FileReader fileReader = new FileReader(CREDENTIALS_FILE_PATH);     
            bufferedReader = new BufferedReader(fileReader);
            clientID= bufferedReader.readLine();
            apiKey= bufferedReader.readLine();
            apiSecret = bufferedReader.readLine();
            bufferedReader.close();
            //classLoader.getResource("Team1Tokens.txt").getFile()
            fileReader = new FileReader(TOKENS_FILE_PATH);
            bufferedReader = new BufferedReader(fileReader);
                     
            accessTokenItself = bufferedReader.readLine();
            tokenType = bufferedReader.readLine();
            refreshToken = bufferedReader.readLine();
            expiresIn = Long.parseLong(bufferedReader.readLine());
            rawResponse = bufferedReader.readLine();
        }
        catch(FileNotFoundException ex) {
            System.out.println( "Unable to open file\n"+ex.getMessage());
            System.exit(1);
        }
        catch(IOException ex) {
            System.out.println( "Error reading/write file\n"+ex.getMessage());  
            System.exit(1);
        }
        finally{
            try{
                if (bufferedReader!=null)
                    // Always close files.
                    bufferedReader.close(); 
            }
            catch(Exception e){
                System.out.println( "Error closing file\n"+e.getMessage()); 
            }
        }
        //  Create the Fitbit service - you will ask this to ask for access/refresh pairs
        //     and to add authorization information to the requests to the API
        FitbitOAuth20ServiceImpl service = (FitbitOAuth20ServiceImpl) new ServiceBuilder()
                .apiKey(clientID)       //fitbit uses the clientID here
                .apiSecret(apiSecret)
                .callback("http://localhost:8080")
                .scope(scope)
                .grantType("authorization_code")
                .build(FitbitApi20.instance());
         
        //  The access token contains everything you will need to authenticate your requests
        //  It can expire - at which point you will use the refresh token to refresh it
        //  See: https://dev.fitbit.com/docs/oauth2/#refreshing-tokens
        //    I have authenticated and given you the contents of the response to use
        OAuth2AccessToken accessToken = new OAuth2AccessToken(
                accessTokenItself,
                tokenType,
                refreshToken,
                expiresIn,
                rawResponse);
        // Now let's go and ask for a protected resource!
        //Example request:
        //    This is always the prefix (for my account)
        String requestUrlPrefix = "https://api.fitbit.com/1/user/3WGW2P/";
        String requestUrl;
        
        //    The URL from this point is how you ask for different information
        		requestUrl = requestUrlPrefix + "activities/" + nameOfActivity + "/date/" + date + "/1d/1min/time/"+ fmStartTime + "/"+ fmEndTime+ ".json";	
        
        // This actually generates an HTTP request from the URL
        //    -it has a header, body ect.
        OAuthRequest request = new OAuthRequest(Verb.GET, requestUrl, service);
         
        // This adds the information required by Fitbit to add the authorization information to the HTTP request
        // You must do this before the request will work
        // See: https://dev.fitbit.com/docs/oauth2/#making-requests
        service.signRequest(accessToken, request);
         
         
        //  This actually sends the request:
        Response response = request.send();
         
        //  The HTTP response from fitbit will be in HTTP format, meaning that it has a numeric code indicating
        //     whether is was successful (200) or not (400's or 500's), each code has a different meaning
//        System.out.println();
        int statusCode = response.getCode();
         
        switch(statusCode){
            case 200:
//                System.out.println("Success!");
                
                StoreDataFromAPI(response.getBody(), nameOfActivity,day);
                break;
            case 400:
                System.out.println("Bad Request - may have to talk to Beth");
                break;
            case 401:
                System.out.println("Likely Expired Token");
                System.out.println("Try to refresh");
                 
                // This uses the refresh token to get a completely new accessToken object
                //   See:  https://dev.fitbit.com/docs/oauth2/#refreshing-tokens           
                // This accessToken is now the current one, and the old ones will not work
                //   again.  You should save the contents of accessToken.
                accessToken = service.refreshOAuth2AccessToken(accessToken);
                 
                // Now we can try to access the service again
                // Make sure you create a new OAuthRequest object each time!
                request = new OAuthRequest(Verb.GET, requestUrl, service);
                service.signRequest(accessToken, request);
                response = request.send();
                 
                // Hopefully got a response this time:
                break;
            case 429:
                System.out.println("Rate limit exceeded");
                break;
            default:
        }
         
        BufferedWriter bufferedWriter=null;
        //  Save the current accessToken information for next time
         
        // IF YOU DO NOT SAVE THE CURRENTLY ACTIVE TOKEN INFO YOU WILL NOT BE ABLE TO REFRESH
        //   - contact Beth if this happens and she can reissue you a fresh set
         
        try {
            FileWriter fileWriter; 
            fileWriter = new FileWriter(TOKENS_FILE_PATH);
            bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(accessToken.getToken());
            bufferedWriter.newLine();
            bufferedWriter.write(accessToken.getTokenType());
            bufferedWriter.newLine();
            bufferedWriter.write(accessToken.getRefreshToken());
            bufferedWriter.newLine();
            bufferedWriter.write(accessToken.getExpiresIn().toString() );
            bufferedWriter.newLine();
            bufferedWriter.write(accessToken.getRawResponse());
            bufferedWriter.newLine();
            bufferedWriter.close();
        }
        catch(FileNotFoundException ex) {
            System.out.println( "Unable to open file\n"+ex.getMessage());               
        }
        catch(IOException ex) {
            System.out.println( "Error reading/write file\n"+ex.getMessage());                 
        }
        finally{
            try{
                if (bufferedWriter!=null)
                    bufferedWriter.close(); 
            }
            catch(Exception e){
                System.out.println( "Error closing file\n"+e.getMessage()); 
            }
        }//end try
    }//end for loop 
    
    	}//from here on and the try at the top
    	catch(OAuthConnectionException e){
    		System.out.println("not connected to the internet");
    	}
        catch(OAuthException e){
        	System.out.println("unable to aunthenticate, check the OAuth tokens");
        }
    	catch(Exception e){
    		System.out.println("an unforscene error has occured");
    	}
}//end APIcall()
    
	//[TEST: PASSED]
	/**
	 * Storing the data into data structures (arrays) from the API call.
     */
	/**
	 * @param responseBody
	 * @param name
	 * @param day
	 */
	private void StoreDataFromAPI(String responseBody, String name, Day day) 
	{
	System.out.println("\n\n"+ name+ "\n\n\n" +responseBody);	
			try {
	    			JSONObject jsonObj =  new JSONObject(responseBody);
	    			JSONArray dataType = (JSONArray) jsonObj.get("activities-" + name);
	    			JSONObject dataTypeOverTime = (JSONObject) jsonObj.get("activities-" + name + "-intraday");
	    			JSONArray dataSet = (JSONArray) dataTypeOverTime.get("dataset");
	    			
	    			JSONObject jsonDataObj;
	    			
	     			if(name == "calories")
	     			{
	     				double[] calArray = day.getCalArray();
	     				for(int i = 0; i < dataSet.length() -1; i++)
	     				{
		    	        	 
	     					jsonDataObj = dataSet.getJSONObject(i); 
	     					calArray[i] = (Double) jsonDataObj.get("value"); 
	     				}
	     				day.setCalArray(calArray);
    	    		}
	     			
    	    		else if(name == "floors")
    	    		{
    	    			int[] floorArray = day.getFloorsArray();
	     				for(int i = 0; i < dataSet.length(); i++)
	     				{
		    	        	 
	     					jsonDataObj = dataSet.getJSONObject(i); 
	     					floorArray[i] = jsonDataObj.getInt("value"); 
	     				}
	     				day.setFloorsArray(floorArray);
    	    		}
    	    		else if(name == "distace")
    	    		{
    	    			double[] distArray = day.getDistArray();
    	    			for(int i = 0; i < dataSet.length(); i++)
    	    			{
    	    				jsonDataObj = dataSet.getJSONObject(i); 
    	    				distArray[i] = (Double) jsonDataObj.get("value");
    	    			}
    	    			day.setDistArray(distArray);
    	    		}
    	    		else if(name == "steps")
    	    		{
    	    			int[] stepsArray = day.getStepsArray();
    	    			for(int i = 0; i < dataSet.length(); i++)
    	    			{
    	    				jsonDataObj = dataSet.getJSONObject(i); 
    	    				stepsArray[i] =  jsonDataObj.getInt("value");
    	    			}
    	    			day.setStepsArray(stepsArray);
    	    				
    	    		}
    	    		else if(name == "minutesSedentary")
    	    		{
    	    			int[] sedArray = day.getSedTimeArray();
    	    			for(int i = 0; i < dataSet.length(); i++)
    	    			{
    	    				jsonDataObj = dataSet.getJSONObject(i); 
    	    				sedArray[i]= jsonDataObj.getInt("value");
    	    			}
    	    			day.setSedTimeArray(sedArray);
    		        	
    	    		}
    	    		else if ( name == "heart")
    	    		{
    	    			int[] hrArray = day.getHrArray();
    	    			for(int i = 0; i < dataSet.length(); i++)
    	    			{
    	    				jsonDataObj = dataSet.getJSONObject(i); 
    	    				hrArray[i]= jsonDataObj.getInt("value");
    	    			}
    	    			day.setHrArray(hrArray);
    	    		}      
	    		} catch (JSONException e) {
	    			// TODO Auto-generated catch block
	    			e.printStackTrace();
	    		}
		
	}//StoreDataFromAPI()
	
	/**
	 * Storing the data into data structures (arrays) from the and old API call to test 
	 * the program when not connected to the Internet.
     */
    private void StoredAPIData(Day day){
    	
    }
	/**
	 * Creates a mouse listener to move the program from any position on the window.
	 */
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
