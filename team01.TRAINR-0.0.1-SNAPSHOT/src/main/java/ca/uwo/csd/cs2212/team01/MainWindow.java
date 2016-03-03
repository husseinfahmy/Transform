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

/**
 * @author Kamal
 *
 */
public class MainWindow extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Font FONT_HELVETICA_NEUE_THIN = null, FONT_HELVETICA_NEUE_ITALIC = null, FONT_HELVETICA_NEUE_BOLD = null;
	private int posX = 0, posY = 0;
	private boolean testMode;

	//Time & Date Stamps:
	private Date lastCall;
	private boolean firstCall = true;
	public static final int MAX_PROGRESS = 1440;

	//User & Virtual Trainer Storage:
	private User user;	
	private VirtualTrainer vt;

	//Data Storage:
	private LinkedList<Day> days = new LinkedList<Day>();
	private LinkedList<Day> futureDays = new LinkedList<Day>();

	//Time & Date Formats:
	private SimpleDateFormat fmDayofWeek = new SimpleDateFormat ("EEEE");		//date format: Wednesday
	private SimpleDateFormat fmLastRefresh = new SimpleDateFormat ("MMMM d, h:mm a");	//time format: "Feb 28, 1:34 PM"
	
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
    	
    	this.createFonts();
    	
    	if (testMode) {
    		this.lastCall = new Date();
    	}

		loadingScreen = new LoadingScreen(this);
		splashScreen = new SplashScreen(this);
		weighScreen = new WeighScreen(this);
		dashboardScreen = new DashboardScreen(this);
		
		this.add(loadingScreen);

		if (testMode) this.getLoadingScreen().initTestMode();
		else this.getLoadingScreen().initSetup();
	}
    
	public boolean isTestMode() { return this.testMode; }
	
	public void updateLastRefreshed() { this.setLastCall(new Date()); }
	
	public void updateDashboardScreen() { this.dashboardScreen = new DashboardScreen(this); }
	
	public Feedback lastRefreshed()
	{
		Feedback fb = new Feedback();
		fb.setTXTCode(1);
		fb.addTXTone(fmLastRefresh.format(this.lastCall.getTime()));
		return fb;
	}
	
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
	
    public User getUser() { return this.user; }
    public void setUser(User user) { this.user = user; }

    public Date getLastCall() { return this.lastCall; }
    public void setLastCall(Date lastCall) { this.lastCall = lastCall; }
    
    public boolean isFirstCall() { return firstCall; }
	public void setFirstCall(boolean firstCall) { this.firstCall = firstCall; }

	public LinkedList<Day> getDays() { return this.days; }
    public LinkedList<Day> getFutureDays() { return this.futureDays; }

	public Feedback updateWeeklyProgress() //to be called once everyday, every morning?
	{
		LinkedList<Day> past6Days = new LinkedList<Day>();
		for(int i = 6; i>0;i--) past6Days.add(days.get(days.size()-1-i));
			
		return this.vt.updateWeeklyProgress(past6Days);
	}
	
	public VirtualTrainer getVirtualTrainer() { return this.vt; }
	
	public String getDayOfWeek(Date day) { return fmDayofWeek.format(day); }
	
	public LoadingScreen getLoadingScreen() { return this.loadingScreen; }
	public SplashScreen getSplashScreen() { return this.splashScreen; }
	public WeighScreen getWeighScreen() { return this.weighScreen; }
	public DashboardScreen getDashboardScreen() { return this.dashboardScreen; }

    private void createFonts() {
    	GraphicsEnvironment genv = GraphicsEnvironment.getLocalGraphicsEnvironment();
    	
    	ClassLoader classLoader = getClass().getClassLoader();
    	File file;
    	
    	file = new File(classLoader.getResource("FONTS/HelveticaNeueThin.ttf").getFile());
    	//Font font = null;
		try {
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
		
		file = new File(classLoader.getResource("FONTS/HelveticaNeueThinItalic.ttf").getFile());
		try {
			FONT_HELVETICA_NEUE_ITALIC = Font.createFont(Font.TRUETYPE_FONT, file);
		} catch (FontFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		genv.registerFont(FONT_HELVETICA_NEUE_ITALIC);
		
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
    
	//API METHODS
    
	//[TEST: PASSED]
	public void APICall(String date, String startTime, String endTime, Day day) 
	{	
		// set Day Object's progress to current day progess and set Last Updated to "lastCall"
		if (endTime.equals("23:59")) {  day.setDayProgress(MAX_PROGRESS);  }
		else 
		{    
			 String hoursStr = "" + endTime.charAt(0) + endTime.charAt(1);		//get the hours part of the string
			 String minutesStr = "" + endTime.charAt(3) + endTime.charAt(4);	//get the minutes part of the string
			 
			 int hours = Integer.parseInt(hoursStr); 			//convert to int
			 int minutes = Integer.parseInt(minutesStr);		//convert to int
	  
			 if ( hours > 0 ) { minutes = hours*60 + minutes; }
			   
			 day.setDayProgress(minutes);
			 day.setLastUpdated(lastCall);
		 } 
		 
		// Make the API Calls:
		
    	String nameOfActivity = "";

    	for (int i = 0; i < 6; i++)
    	{
    		System.out.println(i);
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
        	
            FileReader fileReader = new FileReader(classLoader.getResource("Team1Credentials.txt").getFile());     
            bufferedReader = new BufferedReader(fileReader);
            clientID= bufferedReader.readLine();
            apiKey= bufferedReader.readLine();
            apiSecret = bufferedReader.readLine();
            bufferedReader.close();
            fileReader = new FileReader(classLoader.getResource("Team1Tokens.txt").getFile());
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
//        System.out.println("Now we're going to access a protected resource...");
//        System.out.println();
        //Example request:
        //    This is always the prefix (for my account)
        String requestUrlPrefix = "https://api.fitbit.com/1/user/3WGW2P/";
        String requestUrl;
        
        //    The URL from this point is how you ask for different information
        //"activities/steps/date/2016-02-18/1d/1min/time/07:15/19:30.json";
        requestUrl = requestUrlPrefix + "activities/" + nameOfActivity + "/date/" + date + "/1d/1min/time/"+ startTime + "/"+ endTime+ ".json";
        		//"activities/calories/date/2016-02-18/1d/1min/time/07:15/19:30.json";
        //"activities/floors/date/2016-02-18/1d/1min/time/07:15/19:30.json";
        
        // This actually generates an HTTP request from the URL
        //    -it has a header, body ect.
        OAuthRequest request = new OAuthRequest(Verb.GET, requestUrl, service);
         
        // This adds the information required by Fitbit to add the authorization information to the HTTP request
        // You must do this before the request will work
        // See: https://dev.fitbit.com/docs/oauth2/#making-requests
        service.signRequest(accessToken, request);
        //  If you are curious
//        System.out.println(request.toString());
//        System.out.println(request.getHeaders());
//        System.out.println(request.getBodyContents());
         
         
        //  This actually sends the request:
        Response response = request.send();
         
        //  The HTTP response from fitbit will be in HTTP format, meaning that it has a numeric code indicating
        //     whether is was successful (200) or not (400's or 500's), each code has a different meaning
//        System.out.println();
//        System.out.println("HTTP response code: "+response.getCode());
        int statusCode = response.getCode();
         
        switch(statusCode){
            case 200:
//                System.out.println("Success!");
//                System.out.println("HTTP response body:\n"+response.getBody());
                
                StoreDataFromAPI(response.getBody(), nameOfActivity,day);
                break;
            case 400:
                System.out.println("Bad Request - may have to talk to Beth");
                System.out.println("HTTP response body:\n"+response.getBody());
                break;
            case 401:
                System.out.println("Likely Expired Token");
                System.out.println("HTTP response body:\n"+response.getBody()); 
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
                System.out.println("HTTP response code: "+response.getCode());
                System.out.println("HTTP response body:\n"+response.getBody());
                break;
            case 429:
                System.out.println("Rate limit exceeded");
                System.out.println("HTTP response body:\n"+response.getBody());
                break;
            default:
                System.out.println("HTTP response code: "+response.getCode());
                System.out.println("HTTP response body:\n"+response.getBody());
        }
         
        BufferedWriter bufferedWriter=null;
        //  Save the current accessToken information for next time
         
        // IF YOU DO NOT SAVE THE CURRENTLY ACTIVE TOKEN INFO YOU WILL NOT BE ABLE TO REFRESH
        //   - contact Beth if this happens and she can reissue you a fresh set
         
        try {
            FileWriter fileWriter; 
            fileWriter = new FileWriter(classLoader.getResource("Team1Tokens.txt").getFile());
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
    }//end APIcall()
    
	//[TEST: PASSED]
	/**
	     *Storing the data into arrays from the API call 
     */
	private void StoreDataFromAPI(String responseBody, String name, Day day) 
	{
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