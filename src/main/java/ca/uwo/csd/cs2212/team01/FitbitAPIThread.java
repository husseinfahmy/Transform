package ca.uwo.csd.cs2212.team01;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;

import javax.swing.SwingWorker;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.github.scribejava.apis.FitbitApi20;
import com.github.scribejava.apis.service.FitbitOAuth20ServiceImpl;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.exceptions.OAuthConnectionException;
import com.github.scribejava.core.exceptions.OAuthException;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;

/**
 * @author Kamal
 *
 */
public class FitbitAPIThread extends SwingWorker<Void,Void> implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static final String CREDENTIALS_FILE_PATH = "src/main/resources/Team1Credentials.txt";
	public static final String TOKENS_FILE_PATH = "src/main/resources/Team1Tokens.txt";
	
	private MainWindow mainWindow;
	
	private String date, startTime, endTime;
	private Day day;
	
	//private String responseBody, name;
	
	//private boolean apiCall;
	
	public FitbitAPIThread(MainWindow mainWindow, String date, String startTime, String endTime, Day day) {
		this.mainWindow = mainWindow;
		
		this.date = date;
		this.startTime = startTime;
		this.endTime = endTime;
		this.day = day;
		
		//this.apiCall = true;
	}
	
	/*public FitbitAPIThread(MainWindow mainWindow, String responseBody, String name, Day day) {
		this.mainWindow = mainWindow;

		this.responseBody = responseBody;
		this.name = name;
		this.day = day;
		
		this.apiCall = false;
		
		this.execute();
	}*/
	
	/**
	 * @param responseBody
	 * @param name
	 * @param day
	 */
	/*public void setStoreDataFromAPIParameters(String responseBody, String name, Day day) {
		this.responseBody = responseBody;
		this.name = name;
		this.day = day;
		
		this.apiCall = false;
	}*/
	
	/**
	 * @param date
	 * @param startTime
	 * @param endTime
	 * @param day
	 */
	/*public void setAPICallParameters(String date, String startTime, String endTime, Day day) {
		this.date = date;
		this.startTime = startTime;
		this.endTime = endTime;
		this.day = day;
		
		this.apiCall = true;
	}*/

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
		{	day.setDayProgress(MainWindow.MAX_PROGRESS);
			day.setLastUpdated(mainWindow.getLastCall());
		}
		else 
		{    
			String endHoursStr = "" + fmEndTime.charAt(0) + fmEndTime.charAt(1);			//get the hours part of the string
			String endMinutesStr = "" + fmEndTime.charAt(3) + fmEndTime.charAt(4);		//get the minutes part of the string
			
			 int endHours = Integer.parseInt(endHoursStr); 			//convert to integer
			 int endMinutes = Integer.parseInt(endMinutesStr);		//convert to integer
			
			 //calculate total number of minutes of ENDTIME if # hours elapsed > 0
			 if ( endHours > 0 ) { endMinutes = endHours*60 + endMinutes; } 	
			   
			 day.setDayProgress(endMinutes);
			 day.setLastUpdated(mainWindow.getLastCall());
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
	//System.out.println("\n\n"+ name+ "\n\n\n" +responseBody);	
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
	     					calArray[i] = jsonDataObj.getDouble("value"); 
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
    	    		else if(name == "distance")
    	    		{
    	    			double[] distArray = day.getDistArray();
    	    			for(int i = 0; i < dataSet.length(); i++)
    	    			{
    	    				jsonDataObj = dataSet.getJSONObject(i); 
    	    				distArray[i] = jsonDataObj.getDouble("value");
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
		day.processNewData();
	}//StoreDataFromAPI()
	
	@Override
	protected Void doInBackground() throws Exception {
	    // Simulate doing something useful.
		/*for (int i = 0; i <= 10; i++) {
			Thread.sleep(1000);
		 
		 // The type we pass to publish() is determined
		 // by the second template parameter.
		 publish(i);
		}*/
		
		this.APICall(this.date, this.startTime, this.endTime, this.day);
		//if (this.apiCall) this.APICall(this.date, this.startTime, this.endTime, this.day);
		//else this.StoreDataFromAPI(this.responseBody, this.name, this.day);
		
		// Here we can return some object of whatever type
		// we specified for the first template parameter.
		// (in this case we're auto-boxing 'true').
	    //return true;
		return null;
	}
	
	// Can safely update the GUI from this method.
	protected void done() {
		/*boolean status;
		try {
		 // Retrieve the return value of doInBackground.
		 status = get();
		 statusLabel.setText("Completed with status: " + status);
		} catch (InterruptedException e) {
		 // This is thrown if the thread's interrupted.
		} catch (ExecutionException e) {
		 // This is thrown if we throw an exception
		 // from doInBackground.
		}*/
		
		this.mainWindow.apiThreadDoneHandler(this);
		
		/*if (this.apiCall && this.mainWindow.isFirstCall()) this.mainWindow.setFirstCall(false);
		
		this.mainWindow.setVisible(false);
		this.mainWindow.getContentPane().removeAll();
		this.mainWindow.add(this.mainWindow.getContinueScreen());
		this.mainWindow.setVisible(true);*/
	}

   /*@Override
   // Can safely update the GUI from this method.
	protected void process(List<Integer> chunks) {
	   // Here we receive the values that we publish().
	   // They may come grouped in chunks.
	   int mostRecentValue = chunks.get(chunks.size()-1);
    
	   countLabel1.setText(Integer.toString(mostRecentValue));
	}*/
}
