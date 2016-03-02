package ca.uwo.csd.cs2212.team01;

import java.util.ListIterator;
import java.util.*;
import java.text.*;

public class Day {

	//Attributes
	private Date date;
	private Plan plan = new Plan();
	//FLAG - TIME-DEPENDENT
	private float dailyCalDiff;		//updated via processNewData()			
	//FLAG - REFRESH-DEPENDENT
	private int dayProgress = 0;	 // defautl value of 0 // out of 1440
	private Date lastUpdated;	 // defautl value of 0 // out of 1440
	
//DATA STORAGE
////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Raw Metric Data stored here:
	private double[] calArray = new double[1440];
	private double[] floorsArray = new double[1440];
	private double[] stepsArray = new double[1440];
	private double[] distArray = new double[1440];
	private boolean[] sedTimeArray = new boolean[1440];
	//Processed Metric Data stored here:
	private int[] totals = new int[6]; // index references below:
	//totals[0] = calorie totals
	//totals[1] = floor totals
	//totals[2] = step totals
	//totals[3] = distance totals
	//totals[4] = active time totals
	//totals[5] = sed time totals
////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	//Constructors:
	public Day() {}
	public Day(Date date) 
	{ this.date = date;}
	
	//Methods:
	
	//FLAG - REFRESH-DEPENDENT
	public void processNewData() { sumPerMin(); sumBoolean(); calcDailyCalDiff(); }
	private void sumPerMin()
	{
		double calArraySum = 0, floorsArraySum = 0, stepsArraySum = 0, distanceArraySum = 0;
		
		for (int i = 0; i < dayProgress; i++) calArraySum += calArray[i]; 			totals[0] = (int)calArraySum; 			//[0] = calorie totals
		
		for (int i = 0; i < dayProgress; i++) floorsArraySum += floorsArray[i]; 	totals[1] = (int)floorsArraySum;			//[1] = floor totals
		
		for (int i = 0; i < dayProgress; i++) stepsArraySum += stepsArray[i];  	totals[2] = (int)stepsArraySum;			//[2] = step totals
		  
		for (int i = 0; i < dayProgress; i++) distanceArraySum += distArray[i]; 	totals[3] = (int)distanceArraySum;	//[3] = distance totals
	}
	private void sumBoolean()
	{
		int actTimeSum = 0, sedTimeSum = 0;
		for(int i = 0; i < dayProgress; i++) { if(sedTimeArray[i]==true) sedTimeSum++; else actTimeSum++; }
		totals[4] = actTimeSum;	//[4] = active time totals
		totals[5] = sedTimeSum;	//[5] = sed time totals
	}
	public void calcDailyCalDiff()
	{
		int calBurned = totals[0]; 
		int calConsumed = (int)plan.getCaloriesConsumed();
		dailyCalDiff = (int)(calConsumed - calBurned);
	}
	
	//FLAG - CALLED BY UI
	//Return Today's Plan 
	public Feedback todaysMeals()
	{
		//FEEDBACK FORMAT
		//used: txtlist
		//[0] = String of "Meal #"
		//[1] = String of calorie content
		//example: [0] + [1] = "Meal 1 (+ 445 Cal)"
		Feedback feedback = new Feedback();
		feedback.setTXTCode(1);
		if(plan.getMeals().size() != 0)
		{
			ListIterator<Meal> meals = plan.getMeals().listIterator();
			while(meals.hasNext())
			{
				Meal meal = meals.next();
				feedback.addTXTone(  meal.getName()  );			//diff font colour
				feedback.addTXTtwo( meal.getCalorieString() );	//diff font colour
			}
		}
	
		return feedback;
	}
	public Feedback todaysWorkouts()
	{
		//FEEDBACK FORMAT
		//used: txtlist
		//[0] = String: "Workout"
		//[1] = String of calorie burn goal
		//example: [0] + [1] = "Workout (- 200 Cal)"
		Feedback feedback = new Feedback();
		feedback.setTXTCode(1);
		if(plan.getWorkouts().size() != 0)
		{
			if(plan.getWorkouts().size() == 1)
			{ 
				feedback.addTXTone( "Workout "); 																								//different font colour
				feedback.addTXTtwo( "(- " + plan.getWorkouts().getFirst().getGoalString() +" Cal)" );						//different font colour
			}
			else
			{
				ListIterator<Workout> workouts = plan.getWorkouts().listIterator();
				int counter = 1;
				while(workouts.hasNext())
				{
					feedback.addTXTone( "Workout " + counter ); 											//different font colour
					feedback.addTXTone( " (- " + workouts.next().getGoalString() +" Cal)" );	//different font colour
					counter++;
				}
			}
		}
		
		return feedback;
	}
	
	//FLAG - REFRESH-DEPENDENT // CALLED BY UI
	//Return Today's Progress
	public Feedback todaysProgess() // Cal eaten, Cal burned --> to be graphed by Kamal
	{
		//FEEDBACK FORMAT
		//used: FeedbackValues
		//[0] = calories eaten
		//[1] = actual calories burned
		Feedback feedback = new Feedback();
		feedback.addFeedbackValue(plan.getCaloriesConsumed());
		feedback.addFeedbackValue((float)totals[0]);
		return feedback;
	}
	
	public void generateFakeData(int scenario)
	{
		//Previous Day Scenarios
		if(scenario == 0) {
			for (int i = 480; i < 1440; i++)									//generates fake data for entire day (starts at 8am, ends at 11:59pm) [960 elements]
			{
				calArray[i] 		= (float)1.5;									//user is burning 1.5 cal each minute for whole day = 1440 cal
				floorsArray[i] 	= (float)1.0;									//user climbs 1 floor per minute whole day
				stepsArray[i] 	= (float)10.0;								//user takes 10 steps each minute for whole day
				distArray[i] 		= (float)2.0;									//user travels a distance of 2 meters each minute for whole day
				
				if (!(480 < i && i < 540)) sedTimeArray[i] = true; 	//user is active for 60 minutes
				else	sedTimeArray[i] = false; 								//user is sedentary for rest of day //sedentary minutes = complement of active minutes	
			}
		}
		if(scenario ==1) { 
			for (int i = 480; i < 1440; i++)									//generates fake data for entire day (starts at 8am, ends at 11:59pm)
			{
				calArray[i] 		= (float)2.1;									//user is burning 2 cal each minute for whole day = 2016 cal
				floorsArray[i] 	= (float)2.0;									//user climbs 2 floors per minute whole day
				stepsArray[i] 	= (float)20.0;								//user takes 20 steps each minute for whole day
				distArray[i] 		= (float)4.0;									//user travels a distance of 4 meters each minute for whole day
				
				if (!(480 < i && i < 570)) sedTimeArray[i] = true; //user is active for 90 minutes
				else	sedTimeArray[i] = false; 								//user is sedentary for rest of day //sedentary minutes = complement of active minutes	
			}
		}
		if(scenario ==2) { 
			for (int i = 480; i < 1440; i++)									//generates fake data for entire day (starts at 8am, ends at 11:59pm)
			{
				calArray[i] 		= (float)3;										//user is burning 3 cal each minute for whole day = 2880 cal
				floorsArray[i] 	= (float)4.0;									//user climbs 4 floors per minute whole day
				stepsArray[i] 	= (float)30.0;								//user takes 30 steps each minute for whole day
				distArray[i] 		= (float)5.0;									//user travels a distance of 5 meters each minute for whole day
				
				if (!(480 < i && i < 600)) sedTimeArray[i] = true; 	//user is active for 120 minutes
				else sedTimeArray[i] = false; 								//user is sedentary for rest of day //sedentary minutes = complement of active minutes	
			}
		}
		//Today Scenario:
		if(scenario ==3) { 
			for (int i = 480; i < 1020; i++)									//generates fake data for "Today": Day started at 8:00am and is currently 5:00pm
			{
				calArray[i] 		= (float)2;										//user has burned 2 cal each minute  = 1080 cal
				floorsArray[i] 	= (float)4.0;									//user climbs 4 floors per minute 
				stepsArray[i] 	= (float)30.0;								//user takes 30 steps each minute
				distArray[i] 		= (float)5.0;									//user travels a distance of 5 meters each minute
				
				if (!(480 < i && i < 570)) sedTimeArray[i] = true; 	//user is active for 120 minutes
				else sedTimeArray[i] = false; 								//user is sedentary for rest of day //sedentary minutes = complement of active minutes	
			}
		}
	}
	
	//Setters & Getters
	
//	private float[] calArray = new float[1440];
//	private float[] floorsArray = new float[1440];
//	private float[] stepsArray = new float[1440];
//	private float[] distArray = new float[1440];
//	private boolean[] actTimeArray = new boolean[1440];
	
	//Raw Data getters & setters
	public double[] getCalArray() { return calArray; }
	public double[] getFloorsArray() { return floorsArray; }
	public double[] getStepsArray() { return stepsArray; }
	public double[] getDistArray() { return distArray; }
	public boolean[] getSedTimeArray() { return sedTimeArray; }
	
	public void setCalArray(double array[]) { this.calArray = array; }
	public void setFloorsArray(double array[]) { this.floorsArray = array; }
	public void setStepsArray(double array[]) { this.stepsArray = array; }
	public void setDistArray(double array[]) { this.distArray = array; }
	public void setSedTimeArray(boolean array[]) { this.sedTimeArray = array; }
	
	//Processed Data getters
	public int[] getTotals() { return totals; }
	public int getTotalActiveMin() { return totals[4]; }
	public int getTotalSedMin()  { return totals[5]; }
	//get active and resting heart rate 
	public int getTotalDist()  { return totals[3]; }
	public int getTotalSteps()  { return totals[2]; }
	public int getTotalFloors()  { return totals[1]; }
	
	//other Getters & Setters
	public Date getDate() { return date; }
	public void setDate(Date date) { this.date = date; }
	public Date getLastUpdated() { return lastUpdated; }
	public void setLastUpdated(Date lastUpdated) { this.lastUpdated = lastUpdated; }
	public void setDayProgress(int dayProgress) { this.dayProgress = dayProgress; }
	public int getDayProgress() { return this.dayProgress; }
	public void setDailyCalDiff(float dailyCalDiff) {this.dailyCalDiff = dailyCalDiff;}
	public float getDailyCalDiff() {return dailyCalDiff; }
	public void setPlan(Plan plan) {this.plan = plan;}
	public Plan getPlan() {return this.plan;}
//	public void setDayNumber(int number) { this.dayNumber = number; }
//	public int getDayNumber() {return this.dayNumber; }
}
