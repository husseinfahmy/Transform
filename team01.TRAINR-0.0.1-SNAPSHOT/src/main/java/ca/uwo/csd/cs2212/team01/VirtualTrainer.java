package ca.uwo.csd.cs2212.team01;

import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Stack;

public class VirtualTrainer {

	//Attributes
	private LinkedList<Float> userWeight;
	private float targetWeight;
	private boolean achievementStatus;
	
	private Stack<Goal> remainingMileStones;
	private Goal currentMileStone;
	private Stack<Goal> completedMileStones;
	
	private Feedback milestoneFeedback;
	
	//Constructor
	public VirtualTrainer()
	{
			this.userWeight = new LinkedList<Float>();
			this.remainingMileStones = new Stack<Goal>(); 
			this.completedMileStones = new Stack<Goal>(); 
			achievementStatus = false; 
	}
	
	//Getters & Setters
	public boolean addNewWeightMeasurement(User user, float weight)
	{
		if(weight > 0 && weight<999) { userWeight.add(weight); milestoneFeedback = updateMileStoneProgress(user) ; return true; }
		else { return false; }
	}
	public boolean setTargetWeight(float weight)
	{
		if(weight > 0 && weight<999) { targetWeight = weight; return true; }
		else { return false; }
	}
	
	public float getTargetWeight()
	{ return targetWeight; }
	public float getStartingWeight()
	{ return userWeight.getFirst();}
	public float getCurrentWeight()
	{ return userWeight.getLast(); }
	
	//Methods
	//Milestone Setup
	public boolean setWeightLossGoal(User user ,float startingWeight, float targetWeight)
	{
		if(startingWeight-targetWeight<2){ return false; }//User must specify a weight loss goal larger than 2 lbs }
		
		//user is specifiying a NEW weight loss goal - transfer old Milestones to User's trophy case
		else if(achievementStatus)
		{
			//user.addJourney(new Journey("Journey #"+user.numberOfJourneys()+1,completedMileStones,userWeight));
			completedMileStones = new Stack<Goal>(); remainingMileStones = new Stack<Goal>(); userWeight = new LinkedList<Float>();
			achievementStatus = false;
			addNewWeightMeasurement(user, startingWeight); setTargetWeight(targetWeight); return true;
			//System.out.println("user is specifiying a NEW weight loss goal. Assume old Milestones have been transfered to User's trophy case by updateMileStoneProgress()");
		}
		//user has not completed current target weight/journey but user wants to start a new weight loss journey / delete current progress. Must warn user this action will delete current progress.
		else if(userWeight.size() != 0)
		{
			completedMileStones = new Stack<Goal>(); remainingMileStones = new Stack<Goal>(); userWeight = new LinkedList<Float>();
			addNewWeightMeasurement(user, startingWeight); setTargetWeight(targetWeight); return true;
//			System.out.println("user has not completed current target weight/journey but user wants to start a new weight loss journey");
//			System.out.println("Warning. This will delete all progress towards current weight target and start a new weight loss journey. No current progress will be saved.");
		}
		//user is starting a weight loss journey for the first time
		else
		{ addNewWeightMeasurement(user, startingWeight); setTargetWeight(targetWeight); return true; } //System.out.println("user is starting a weight loss journey for the first time"); 
	}

	public void setMileStones() 
	{
		//calculate total weight loss
		float totalWeightLoss = this.getStartingWeight() - this.getTargetWeight();
		
		//total weight loss is even
		if(totalWeightLoss%2 == 0) 
		{
			int totalMileStones = (int)totalWeightLoss / 2;							
			remainingMileStones.push(new Goal("Last Milestone (#"+totalMileStones+")",false,7000));
			for(int i = totalMileStones-1; i > 0; i--)
			{ remainingMileStones.push(new Goal("Milestone #"+i,false,7000)); }
			this.currentMileStone = remainingMileStones.pop();						
		}
		//total weight loss is odd
		else 
		{
			float lastWeight = totalWeightLoss%2;
			float allWeightminusLast = totalWeightLoss-lastWeight;
			int allMSminusLast = (int)allWeightminusLast / 2;
			remainingMileStones.push(new Goal("Last Milestone (#"+(allMSminusLast+1)+")",false,lastWeight*3500));
			for(int i = allMSminusLast; i > 0; i--) { remainingMileStones.push(new Goal("Milestone #"+i,false,7000)); }
			this.currentMileStone = remainingMileStones.pop();		
		}
	}
	
	//DASHBOARD - TRAINR FEEDBACK PANEL:
	
	// still have to write up "feedback format" for each of the feedback algorithms
	
	//FLAG - TIME-DEPENDENT
	public Feedback updateMileStoneProgress(User user) //to be called once a week (every sunday) by Main? // return feedback?
	{
		
		//TEXTCODE 2 = DISPLAY TEXT & PROGRESS
		//TEXTCODE 1 = DISPLAY TEXT ONLY
		//TEXTCODE 0 = DO NOT DISPLAY TEXT
		//BUTTONCODE 1  = DISPLAY "CUSTOMIZE MY PLAN" BUTTON
		//BUTTONCODE 0  = DO NOT DISPLAY BUTTONS
		
		//feedbackValues:
		//[0] = current MileStone number
		//[1] = current MileStone progress, out of 7000
		//[2] = X pounds to go"
		
		Feedback msUpdateFeedback = new Feedback();
		
		if(userWeight.size() == 1) 
		{ 
			return null;   //User has not weighed themselves yet after entering in their Starting Weight. No progress to measure. In theory, this won't ever happen.
		}
		
		else if(currentMileStone == null) 
		{ 
			msUpdateFeedback.setTXTCode(1); //DISPLAY 1 SENTENCE 
			msUpdateFeedback.addTXTone
			(
					"Warning: User has completed all Milestones and is continuing to use the TRAINR without a new Target Weight goal.\n"
					+ "No new Milestones can be achieved until a new Target Weight Goal has been set."
			);
			return msUpdateFeedback; 
		} 
		
		else //Calculate change in weight
		{
			float weightDiff =  userWeight.getLast() - userWeight.get((userWeight.size()-2));
			//System.out.println("weightDiff: "+weightDiff);
			if(weightDiff > 0) //User has gained weight
			{ 
				
				//<Write code to decrease Milestone Progress>//
				
				msUpdateFeedback.setTXTCode(1);  //DISPLAY 1 SENTENCE
				msUpdateFeedback.setButtonCode(1); //DISPLAY "CUSTOMIZE MY PLAN" BUTTON
				msUpdateFeedback.addTXTone
				(
						"You have gained " +  weightDiff + " lbs!<br>"
						+ "You must take action by either reducing your calorie intake<br>"
						+ "or setting a higher calorie burn goal during your workouts:"
				); 
				return msUpdateFeedback; 
			} 
			
			else if(weightDiff == 0) //User's weight has not changed
			{
				msUpdateFeedback.setTXTCode(1);  //DISPLAY 1 SENTENCE
				msUpdateFeedback.setButtonCode(1); //DISPLAY "CUSTOMIZE MY PLAN" BUTTON
				msUpdateFeedback.addTXTone
				(
						"You have not lost any weight :(<br>"
						+ "You must take action by either reducing your calorie intake<br>"
						+ "or setting a higher calorie burn goal during your workouts:"
				); 
				return msUpdateFeedback; 
			}
			
			else //User has lost weight
			{
				float usersProgress = 3500*(Math.abs(weightDiff));
				//System.out.println("usersProgress: "+usersProgress);
				int counter = 0; //counts the number of Milestones achieved within this update
				while(usersProgress > 0 )
				{
					if(remainingMileStones.size()==0 && currentMileStone == null) //user is done all MS but has also lost further weight. Warn user further weight loss isn't being used towards any Milestone progression.
					{ 
						msUpdateFeedback.setTXTCode(1) ;
						msUpdateFeedback.setFontSize(20);
						msUpdateFeedback.addTXTone
						(
								"Warning: You have completed all Milestones and have lost more weight than specified target weight.<br>"
								+ "TRAINR is not tracking any new Milestone progress with weight that has been lost beyond the Target Weight of: " + this.targetWeight +  " lbs.<br>"
								+ "Please specify a new Target Weight goal if you wish to achieve more Milestone Rewards!"
						);
						return msUpdateFeedback;
					} 
					
					usersProgress = currentMileStone.updateProgress(usersProgress);
					//System.out.println("usersProgress: "+usersProgress);System.out.println(currentMileStone.getStatus());System.out.println("current ms progress: "+currentMileStone.progress);
					if(currentMileStone.getStatus()) 
					 { 
						 completedMileStones.push(currentMileStone); currentMileStone=null; counter++;
						 if (remainingMileStones.size() != 0) currentMileStone = remainingMileStones.pop(); 
						 
						 else //User has completed all Milestones
						 { 
							 achievementStatus = true; 
							 user.addJourney(new Journey("Journey #"+user.numberOfJourneys()+1,completedMileStones,userWeight));  
							 msUpdateFeedback.setTXTCode(1);
							 msUpdateFeedback.addTXTone
							 (
									 "Congratulations!<br>You have completed all your Milestones!<br>You have successfully achieved your weight loss goal!"
							);
							 return msUpdateFeedback; 
						 } 
					}
				}
				
				if(counter == 1) //You’ve lost X lbs and achieved a MileStone! You have achieved X Milestones so far! Progress towards your next MileStone:
				{
					msUpdateFeedback.setTXTCode(2);
					msUpdateFeedback.addTXTone
					(
							"You've lost " + Math.abs(weightDiff) + " lbs and achieved a Milestone!<br>"
							+ "You have achieved " + completedMileStones.size() + feedbackHelper() + "so far!<br>"
							+ "Progress towards your next Milestone:"
					);
					msUpdateFeedback.addFeedbackValue((float)completedMileStones.size()+1); 			//current MileStone number
					msUpdateFeedback.addFeedbackValue((currentMileStone.progress)/7000);				//current MileStone progress, out of 7000
					msUpdateFeedback.addFeedbackValue(2-(currentMileStone.progress / 3500)); 			//"X pounds to go"
				}
				
				else if(counter > 1) //You’ve lost X lbs and achieved X MileStones! You have achieved X Milestones so far! Progress towards your next MileStone:
				{
					msUpdateFeedback.setTXTCode(2);
					msUpdateFeedback.addTXTone
					(
							"You've lost " + Math.abs(weightDiff) + " lbs and achieved " + counter + " Milestones!<br>"
							+ "You have achieved " + completedMileStones.size() + feedbackHelper() + "so far!<br>"
							+ "Progress towards your next Milestone:"
					);
					msUpdateFeedback.addFeedbackValue((float)completedMileStones.size()+1); 			//current MileStone number
					msUpdateFeedback.addFeedbackValue((currentMileStone.progress)/7000);				//current MileStone progress, out of 7000
					msUpdateFeedback.addFeedbackValue(2-(currentMileStone.progress / 3500)); 			//"X pounds to go"
				}
				else //You’ve lost X lbs! You have achieved X Milestones so far! Progress towards your next MileStone:
				{
					msUpdateFeedback.setTXTCode(2);
					if(completedMileStones.size() > 0)
					{
						msUpdateFeedback.addTXTone
						(
								"You've lost " + Math.abs(weightDiff) + " lbs!<br>"
								+ "You have achieved " + completedMileStones.size() + " Milestones so far!<br>"
								+ "Progress towards your next Milestone:"
						);
					}
					else // User is working towards achieving their first Milestone
					{
						msUpdateFeedback.addTXTone
						(
								"You've lost " + Math.abs(weightDiff) + " lbs!<br>"
								+ "Progress towards your first Milestone:"
						);
					}
					msUpdateFeedback.addFeedbackValue((float)completedMileStones.size()+1); 			//current MileStone number
					msUpdateFeedback.addFeedbackValue((currentMileStone.progress)/7000);				//current MileStone progress, out of 7000
					msUpdateFeedback.addFeedbackValue(2-(currentMileStone.progress / 3500)); 			//"X pounds to go"
				}
			
				return msUpdateFeedback;
			}//second else - User has lost weight
		}//first else - calcualte weight change and update MS progress accordingly
	}//public void msUpdateProgress()
	//FLAG - CALLED BY UI
	public Feedback getmsFeedback() 
	{ return milestoneFeedback; }
	//FLAG - REFRESH-DEPENDENT - CALLED BY UI
	public Feedback updateTodaysProgress(Day today)
	{
		Float calDiff = today.getDailyCalDiff();
		Feedback todayFeedback = new Feedback();
		//System.out.println(calDiff);
	
		if(calDiff <= -500) 
		{
			todayFeedback.setTXTCode(1);
			todayFeedback.addTXTone
			(
					"You're currently in a " + calDiff + " calorie deficit :)<br>"
					+ "Stick to your plan and you'll hit today's calorie goal!"
			);
			todayFeedback.addFeedbackValue(calDiff);
			return todayFeedback;
		}
		
		else if(calDiff < -1 && calDiff > -500) 
		{
			todayFeedback.setTXTCode(1);
			todayFeedback.addTXTone
			(
					"You're currently in a " + calDiff + " calorie deficit.<br>"
					+ "Stick to your planned meals & burn enough calories<br>"
					+ "to make sure you hit today's calorie deficit goal."
			);
			todayFeedback.addFeedbackValue(calDiff);
			return todayFeedback;
		}
		
		else if(calDiff >= 0) 
		{
			todayFeedback.setTXTCode(1);
			todayFeedback.addTXTone
			(
					"You’re curently in a " + calDiff + " calorie surplus.<br>"
					+ "Stick to your planned meals and burn enough<br>"
					+ "calories to make sure you hit today's<br>"
					+ "calorie deficit goal."
			);
			todayFeedback.addFeedbackValue(calDiff);
			return todayFeedback;
		}
		else { todayFeedback.addTXTone("error"); return todayFeedback; }
	}
	//FLAG - TIME-DEPENDENT - CALLED BY UI
	public Feedback updateWeeklyProgress(LinkedList<Day> pastWeek) //to be called once everyday, every morning?
	{
		ListIterator<Day> pastWeekIter = pastWeek.listIterator();
		int successDays = 0, missedDay = 0;
		while(pastWeekIter.hasNext()) 
		{
			if(pastWeekIter.next().getDailyCalDiff()<=-490) { successDays++;}
			else missedDay = successDays + 1;
		}
		//System.out.println("successDays: "+successDays);
		Feedback weekFeedback = new Feedback();
		
		if(successDays == 6) 
		{ 
			weekFeedback.setTXTCode(1); 
			weekFeedback.addTXTone ( "You’ve had 6 successful days in a row!" );
		}
		
		else if(successDays == 5 && missedDay == 1)
		{
			weekFeedback.setTXTCode(1);
			 weekFeedback.addTXTone
			 (
					 "You missed your calorie goal yesterday but you’ve had 5 successful days before that! Good job!"
			 );
			 return weekFeedback;
		}
		
		else if(successDays == 5 && missedDay > 1)
		{
			weekFeedback.setTXTCode(1);
			 weekFeedback.addTXTone
			 (
					 "You missed your calorie goal " + missedDay + " days ago but you’ve had 5 successful days! Great job!"
			 );
			 return weekFeedback;
		}
		
		else if(successDays == 4)
		{
			weekFeedback.setTXTCode(1);
			 weekFeedback.addTXTone
			 (
					 "You missed your calorie goal twice in the past 6 days but you’ve had " + successDays +" successful days.<br>"
			 		+ "Make sure you stick to your daily plans!"
			 );
			 return weekFeedback;
		}
		
		else if(successDays == 3)
		{
			weekFeedback.setTXTCode(1);
			 weekFeedback.addTXTone
			 (
					 "You missed your calorie goal 3 times in the past 6 days but you’ve had " + successDays +" successful days.<br>"
			 		+ "You're missing your daily calorie goal too many times. "
			 		+ "Take action by reducing your calorie intake or setting a higher calorie burn goal during your workouts: "
			 );
			 weekFeedback.setButtonCode(1); // display customize meal plan button.
		}
		
		else if(successDays == 2)
		{
			weekFeedback.setTXTCode(1);
			weekFeedback.addTXTone
			(
					 "You missed your calorie goal 4 times in the past 6 days but you’ve had " + successDays +" successful days.<br>"
					 + "You're missing your daily calorie goal too many times. "
			 		 + "You must take action by reducing your calorie intake or setting a higher calorie burn goal during your workouts: "					 
			);
			weekFeedback.setButtonCode(1); // display customize meal plan button.
			return weekFeedback;
		}
		
		else if(successDays == 1)
		{
			weekFeedback.setTXTCode(1);
			 weekFeedback.addTXTone
			 (
					 "You missed your calorie goal 5 times in the past 6 days but you’ve had " + successDays +" successful days.<br>"
					 + "If you keep this up, you will not achieve your weight loss goal. "
			 		 + "You must take action by reducing your calorie intake or setting a higher calorie burn goal during your workouts: "					 
			 );
			 weekFeedback.setButtonCode(1); // display customize meal plan button.
			 return weekFeedback;
		}
		
		else if(successDays == 0)
		{
			weekFeedback.setTXTCode(1);
			 weekFeedback.addTXTone
			 (
					 "You missed your calorie goal every single day in the past 6 days."
					 + "If you keep this up, you will not achieve your weight loss goal. "
					 + "You must take action by reducing your calorie intake or setting a higher calorie burn goal during your workouts: "	
			 );
			 weekFeedback.setButtonCode(1); // display customize meal plan button.
			 return weekFeedback;
		}
		  weekFeedback.addTXTone("Error"); return weekFeedback; 
		
	}
	
	//DAILY PLANS FEEDBACK //MEAL ADDING FEEDBACK //WORKOUT ADDING FEEDBACK
	public Feedback dailyPlanFeedback(Plan plan) //called each time a meal or a workout is added or edited by user
	{
		float caloriesConsumed = plan.getCaloriesConsumed();
		float caloriesBurned = plan.getCaloriesBurned();
		float calDiff = caloriesConsumed - caloriesBurned;
		Feedback planFeedback = new Feedback();
		System.out.println("calDiff from vt: "+calDiff);
		if(calDiff < -650)
		{
			planFeedback.setTXTCode(1);
			planFeedback.addTXTone
			(
					"Be careful not to deprive yourself of too many calories.<br>"
					+ "Losing weight too fast can be unhealthy.<br>"
					+ "Roughly a -500 calorie deficit is recommended."
			);
		}
		else if(-485 > calDiff && calDiff >= -650 )
		{
			planFeedback.setTXTCode(1);
			planFeedback.addTXTone
			(
					"Great! Your plan should result in a calorie deficit of roughly 500 calories!<br>"
					+ "Trainr will help you stick to this plan when this day starts."
			);
		}
		else if(0 >= calDiff && calDiff >- 485)
		{
			planFeedback.setTXTCode(1);
			if(plan.workoutPlanned()) //if a workout has already been planned for that day
			{
				planFeedback.addTXTone
				(
						"Action Required! Your planned calorie difference is not near -500 calories!<br>"
						+ "Either reduce your calorie intake by editing your meals or<br>"
						+ "increase the calories you’ll burn by setting a higher calorie burn goal during your scheduled workout."
				);
				return planFeedback;
			}
			else //if no workout is planned for that day yet
			{
				planFeedback.addTXTone
				(
						"Action Required! Your planned calorie difference is not near -500 calories!<br>"
						+ "Either reduce your calorie intake by editing your meals or<br>"
						+ "schedule a workout to burn more calories."
				);
				return planFeedback;
			}
		}
		else if(calDiff > 0)
		{
			planFeedback.setTXTCode(1);
			if(plan.workoutPlanned()) //if a workout has already been planned for that day
			{
				planFeedback.addTXTone
				(
						"Action Required! Your plan is resulting in a calorie surplus which will cuase you to gain weight!<br>"
						+ "You need to reduce your calorie intake by editing your meals or<br>"
						+ "increase the calories you’ll burn by setting a higher calorie burn goal during your scheduled workout."
				);
				return planFeedback;
			}
			else //if no workout is planned for that day yet
			{
				planFeedback.addTXTone
				(
						"Action Required! Your plan is resulting in a calorie surplus which will cuase you to gain weight!<br>"
						+ "You need to reduce your calorie intake by editing your meals or<br>"
						+ "schedule a workout to burn more calories."
				);
				return planFeedback;
			}
		}
		return planFeedback;
	}
	
	public String feedbackHelper()
	{ if(completedMileStones.size()==1) return " Milestone "; else return " Milestones "; }
	
}//Class