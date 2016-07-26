package ca.uwo.csd.cs2212.team01;

import java.util.ListIterator;
import java.io.Serializable;
import java.util.*;

/**
 * @author team01
 *
 */
public class Day implements Serializable {
	private static final long serialVersionUID = 1L;

	//ATTRIBUTES:
	private Date date;
	private Date lastUpdated;	
	private float dailyCalDiff = 0;					
	private int dayProgress = 0;	 	//MAX_VALUE: 1440 (1440 minutes in an entire day)
	private Plan plan;
	 
	//DATA STORAGE
////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Raw Metric Data stored here:
	private double[] calArray 		= new double[1440];
	private int[] floorsArray		 	= new int[1440];
	private int[] stepsArray 			= new int[1440];
	private double[] distArray 		= new double[1440];
	private int[] sedTimeArray 	= new int[1440];
	private int[] hrArray 				= new int[1440];
	//Processed Metric Data stored here:
	private int[] totals = new int[8]; // index references below:
	//totals[0] = calorie totals
	//totals[1] = floor totals
	//totals[2] = step totals
	//totals[3] = distance totals
	//totals[4] = active time totals
	//totals[5] = sed time totals
	//totals[6] = active HR
	//totals[7] = resting HR
	//END OF DATA STORAGE
////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	//CONSTRUCTORS:
	/**
	 * 
	 */
	// this constructor is only used in test mode
	public Day() {
		//plan = new Plan(1600);
	}
	/**
	 * @param date
	 */
	public Day(Date date)  {
		//plan = new Plan(1600);
		this.date = date;
	}
	
	//METHODS:
	
	//Data Processing methods
	/**
	 * 
	 */
	public void processNewData() { sumPerMin(); sumBoolean(); processHR(); calcDailyCalDiff(); }
	/**
	 * 
	 */
	private void sumPerMin()
	{
		double calArraySum = 0, floorsArraySum = 0, stepsArraySum = 0, distanceArraySum = 0;
		
		for (int i = 0; i < dayProgress; i++) calArraySum += calArray[i]; 			totals[0] = (int)calArraySum; 			//[0] = calorie totals
		
		for (int i = 0; i < dayProgress; i++) floorsArraySum += floorsArray[i]; 		totals[1] = (int)floorsArraySum;		//[1] = floor totals
		
		for (int i = 0; i < dayProgress; i++) stepsArraySum += stepsArray[i];  	totals[2] = (int)stepsArraySum;		//[2] = step totals
		  
		for (int i = 0; i < dayProgress; i++) distanceArraySum += distArray[i]; 	totals[3] = (int)distanceArraySum;	//[3] = distance totals
	}
	/**
	 * 
	 */
	private void sumBoolean()
	{
		int actTimeSum = 0, sedTimeSum = 0;
		for(int i = 0; i < dayProgress; i++) 
		{ if(sedTimeArray[i]==1)  { sedTimeSum++; }  else if (sedTimeArray[i]==0)  { actTimeSum++; }  }
		totals[4] = actTimeSum;		//[4] = active time totals
		totals[5] = sedTimeSum;		//[5] = sed time totals
	}
	/**
	 * 
	 */
	public void processHR()
	{
		int activeHR=0, restingHR=200;
		for(int i = 0; i < dayProgress; i++)  {  if ( hrArray[i] > activeHR ) activeHR = hrArray[i]; if ( hrArray[i] > 0 &&  hrArray[i] < restingHR ) restingHR = hrArray[i]; }
		totals[6] = activeHR; 		//[6] = active HR
		totals[7] = restingHR;  	//[7] = resting HR
	}
	
	/**
	 * 
	 */
	public int[] HeartRateZone(){
		int i = 0, heartrate = 0, zone1 = 0, zone2 = 0, zone3 = 0, zone4 = 0, zone5 = 0;
		int[] zone = new int[5];
		
		for(i = 0; i < 1440; i++){
			heartrate = hrArray[i];
			
			if(heartrate >= 104 && heartrate < 114){
				//Zone 1
				zone1++;
			
			}else if(heartrate >= 114 && heartrate < 133){
				//Zone 2
				zone2++;
			
			}else if(heartrate >= 133 && heartrate < 152){
				//Zone 3
				zone3++;
			
			}else if(heartrate >= 152 && heartrate < 171){
				//Zone 4
				zone4++;
	
			}else if(heartrate >= 171 && heartrate < 190){
				//Zone 5
				zone5++;
			
			}
		}
			zone[0] = zone1;
			zone[1] = zone2;
			zone[2] = zone3;
			zone[3] = zone4;
			zone[4] = zone5;
			/*
			System.out.println("Zone 1: " + zone1);
			System.out.println("Zone 2: " + zone2);
			System.out.println("Zone 3: " + zone3);
			System.out.println("Zone 4: " + zone4);
			System.out.println("Zone 5: " + zone5);
			*/
			return zone;
	}
	
	/*
	 * 
	 */
	
	public void calcDailyCalDiff()
	{
		int calBurned = totals[0]; 
		int calConsumed = 0;
		if (plan != null) calConsumed = (int)plan.getCaloriesConsumed();
		dailyCalDiff = (int)(calConsumed - calBurned);
	}
	
	//Return Today's Plan 
	/**
	 * @return
	 */
	public Feedback todaysMeals()
	{
		//FEEDBACK FORMAT
		//used: txtlist
		//[0] = String of "Meal #"
		//[1] = String of calorie content
		//example: [0] + [1] = "Meal 1 (+ 445 Cal)"
		Feedback feedback = new Feedback();
		feedback.setTXTCode(1);
		if(plan != null && plan.getMeals().size() != 0)
		{
			ListIterator<Meal> meals = plan.getMeals().listIterator();
			while(meals.hasNext())
			{
				Meal meal = meals.next();
				feedback.addTXTone(  meal.getName()  );			//diff font colour
				feedback.addTXTtwo( meal.getCalorieString() );	//diff font colour
			}
		}
		else
		{
			feedback=null;
		}
		
		return feedback;
	}
	/**
	 * @return
	 */
	public Feedback todaysWorkouts()
	{
		//FEEDBACK FORMAT
		//used: txtlist
		//[0] = String: "Workout"
		//[1] = String of calorie burn goal
		//example: [0] + [1] = "Workout (- 200 Cal)"
		Feedback feedback = new Feedback();
		feedback.setTXTCode(1);
		if(plan != null && plan.getWorkouts().size() != 0)
		{
			if(plan.getWorkouts().size() == 1)
			{ 
				feedback.addTXTone( "Workout "); 															//different font colour
				feedback.addTXTtwo( "(- " + plan.getWorkouts().getFirst().getGoalString() +" Cal)" );		//different font colour
			}
			else if(plan.getWorkouts().size() > 1)
			{
				ListIterator<Workout> workouts = plan.getWorkouts().listIterator();
				int counter = 1;
				while(workouts.hasNext())
				{
					feedback.addTXTone( "Workout " + counter ); 											//different font colour
					feedback.addTXTone( " (- " + workouts.next().getGoalString() +" Cal)" );				//different font colour
					counter++;
				}
			}
			else
			{
				feedback=null;
			}
		}
		
		return feedback;
	}
	
	//Return Today's Progress
	/**
	 * @return
	 */
	public Feedback todaysProgess()
	{
		//FEEDBACK FORMAT
		//used: FeedbackValues
		//[0] = calories eaten
		//[1] = actual calories burned
		Feedback feedback = new Feedback();
		if (plan != null) feedback.addFeedbackValue(plan.getCaloriesConsumed());
		else feedback.addFeedbackValue(0.0f);
		feedback.addFeedbackValue((float)totals[0]);
		return feedback;
	}
	
	//Generate fake data for this day
	/**
	 * @param scenario
	 */
	public void generateFakeData(int scenario)
	{
		//Previous Day Scenarios
		if(scenario == 0) {
			for (int i = 479; i < 1440; i++)										//generates fake data for entire day (starts at 8am, ends at 11:59pm) [960 elements]
			{
				calArray[i] 		= (float)1.5;										//user is burning 1.5 cal each minute for whole day = 1440 cal
				if (479 < i && i < 482) floorsArray[i] 	= (int)1;										//user climbs 1 floor per minute whole day
				stepsArray[i] 	= (int)1.0;											//user takes 1 steps each minute for whole day
				if (479 < i && i < 485) distArray[i] 	= (float)1.0;									//user travels a distance of 2 meters each minute for whole day
				
				if (479 < i && i < 540)
				{ 
					sedTimeArray[i] = 0;											//user is active for 60 minutes 
					hrArray[i] = 130;													//user has an active heart rate of 130bpm
				}		
				else	
				{
					sedTimeArray[i] = 1; 											//user is sedentary for rest of day //sedentary minutes = complement of active minutes	
					hrArray[i] = 75;													//user has an resting heart rate of 75bpm
				}
			}
			for (int i = 0; i < 479; i++)		{ sedTimeArray[i] = 1; }
		}
		if(scenario ==1) { 
			for (int i = 479; i < 1440; i++)										//generates fake data for entire day (starts at 8am, ends at 11:59pm)
			{
				calArray[i] 		= (float)2.1;										//user is burning 2 cal each minute for whole day = 2016 cal
				if (479 < i && i < 482) floorsArray[i] 	= (int)1;											//user climbs 2 floors per minute whole day
				stepsArray[i] 	= (int)2.0;											//user takes 2 steps each minute for whole day
				if (479 < i && i < 485) distArray[i] 	= (float)1.0;									//user travels a distance of 4 meters each minute for whole day
				
				if (479 < i && i < 570)
				{
					sedTimeArray[i] = 0; 											//user is active for 90 minutes
					hrArray[i] = 135;													//user has an active heart rate of 135bpm
				}
				else	
				{
					sedTimeArray[i] = 1; 											//user is sedentary for rest of day //sedentary minutes = complement of active minutes
					hrArray[i] = 75;													//user has an resting heart rate of 75bpm	
				}
			}
			for (int i = 0; i < 479; i++)		{ sedTimeArray[i] = 1; }
		}
		if(scenario ==2) { 
			for (int i = 479; i < 1440; i++)										//generates fake data for entire day (starts at 8am, ends at 11:59pm)
			{
				calArray[i] 		= (float)3;											//user is burning 3 cal each minute for whole day = 2880 cal
				if (479 < i && i < 482) floorsArray[i] 	= (int)1;											//user climbs 4 floors per minute whole day
				stepsArray[i] 	= (int)3.0;											//user takes 3 steps each minute for whole day
				if (479 < i && i < 485) distArray[i] 	= (float)1.0;										//user travels a distance of 5 meters each minute for whole day
				
				if (479 < i && i < 600)
				{
					sedTimeArray[i] = 0; 											//user is active for 120 minutes
					hrArray[i] = 140;													//user has an active heart rate of 140bpm
				}
				else 
				{
					sedTimeArray[i] = 1; 											//user is sedentary for rest of day //sedentary minutes = complement of active minutes	
					hrArray[i] = 75;													//user has an resting heart rate of 75bpm
				}
			}
			for (int i = 0; i < 479; i++)		{ sedTimeArray[i] = 1; }
		}
		//Today Scenario:
		if(scenario ==3) { 
			for (int i = 479; i < 1020; i++)										//generates fake data for "Today": Day started at 8:00am and is currently 5:00pm
			{
				calArray[i] 		= (float)2;											//user has burned 2 cal each minute  = 1080 cal
				if (479 < i && i < 482) floorsArray[i] 	= (int)1;											//user climbs 4 floors per minute 
				stepsArray[i] 	= (int)3.0;											//user takes 3 steps each minute
				if (479 < i && i < 485) distArray[i] 	= (float)1.0;										//user travels a distance of 5 meters each minute
				
				if (479 < i && i < 600) 
				{
					sedTimeArray[i] = 0; 											//user is active for 120 minutes
					hrArray[i] = 145;													//user has an active heart rate of 145bpm
				}
				else 
				{
					sedTimeArray[i] = 1; 											//user is sedentary for rest of day //sedentary minutes = complement of active minutes
					hrArray[i] = 75;													//user has an resting heart rate of 75bpm	
				}
			}
			for (int i = 0; i < 479; i++)		{ sedTimeArray[i] = 1; }
		}
	}
	
	//SETTERS & GETTERS:
	
	//Raw Data getters
	/**
	 * @return
	 */
	public double[] getCalArray() 	{ return calArray; }
	/**
	 * @return
	 */
	public int[] getFloorsArray() 		{ return floorsArray; }
	/**
	 * @return
	 */
	public int[] getStepsArray() 		{ return stepsArray; }
	/**
	 * @return
	 */
	public double[] getDistArray() 	{ return distArray; }
	/**
	 * @return
	 */
	public int[] getSedTimeArray() 	{ return sedTimeArray; }
	/**
	 * @return
	 */
	public int[] getHrArray() 			{ return hrArray; }
	//Raw Data setters
	/**
	 * @param array
	 */
	public void setCalArray(double array[]) 	{ this.calArray = array; }
	/**
	 * @param array
	 */
	public void setFloorsArray(int array[]) 	{ this.floorsArray = array; }
	/**
	 * @param array
	 */
	public void setStepsArray(int array[]) 	{ this.stepsArray = array; }
	/**
	 * @param array
	 */
	public void setDistArray(double array[]) { this.distArray = array; }
	/**
	 * @param array
	 */
	public void setSedTimeArray(int array[])	{ this.sedTimeArray = array; }
	/**
	 * @param hrArray
	 */
	public void setHrArray(int[] hrArray) 		{ this.hrArray = hrArray; }
	
	//Processed Data getters
	/**
	 * @return
	 */
	public int[] getTotals() 			{ return totals; }
	/**
	 * @return
	 */
	public int getTotalCals() 		{ return totals[0]; }
	/**
	 * @return
	 */
	public int getTotalFloors()  	{ return totals[1]; }
	/**
	 * @return
	 */
	public int getTotalSteps()  		{ return totals[2]; }
	/**
	 * @return
	 */
	public int getTotalDist()  		{ return totals[3]; }
	/**
	 * @return
	 */
	public int getTotalActiveMin()	{ return totals[4]; }
	/**
	 * @return
	 */
	public int getTotalSedMin()  	{ return totals[5]; }
	/**
	 * @return
	 */
	public int getActiveHR() 		{ return totals[6]; }
	/**
	 * @return
	 */
	public int getRestingHR() 		{ return totals[7]; }
	
	//other setters
	/**
	 * @param date
	 */
	public void setDate(Date date) { this.date = date; }
	/**
	 * @param lastUpdated
	 */
	public void setLastUpdated(Date lastUpdated) { this.lastUpdated = lastUpdated; }
	/**
	 * @param dayProgress
	 */
	public void setDayProgress(int dayProgress) { this.dayProgress = dayProgress; }
	/**
	 * @param dailyCalDiff
	 */
	public void setDailyCalDiff(float dailyCalDiff) {this.dailyCalDiff = dailyCalDiff;}
	/**
	 * @param plan
	 */
	public void setPlan(Plan plan) {this.plan = plan;}
	//other getters
	/**
	 * @return
	 */
	public Date getDate() { return date; }
	/**
	 * @return
	 */
	public int getDayProgress() { return this.dayProgress; }
	/**
	 * @return
	 */
	public Date getLastUpdated() { return lastUpdated; }
	/**
	 * @return
	 */
	public float getDailyCalDiff() {return dailyCalDiff; }
	/**
	 * @return
	 */
	public Plan getPlan() {return this.plan;}
}