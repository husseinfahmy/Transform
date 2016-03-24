package ca.uwo.csd.cs2212.team01;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.ListIterator;

public class testing implements Serializable {
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		// MEAL BUILDER
		/*
		Boolean filled = true;
		LinkedList<Meal> myMeals = new LinkedList<Meal>();
		Meal meal = new Meal(); 
		
		//"Add" Button: Create FoodServing and add to Meal
		if(filled)																	//if all fields are filled, else display message telling User to fill fields
		{
			//Clear feedback panel
			
			String foodName = "food name"; 															//taken from text entry field
			float servingSize = (float)0.5;																	//taken from text entry field
			String servingUnits = "g";																		//taken from drop-down menu OR kamal's suggestion: button press to switch back and forth from "g" and "cup"
			Macro macro = new Macro((float)100, (float)20, (float)50, (float)5);	//taken from multiple text entry fields
			
			Food food = new Food(foodName,1,servingSize,servingUnits,macro);
			float foodServingSize = (float)0.5;
			String foodServingUnits = "cup";	
			FoodServing foodServing  = new FoodServing(food,foodServingSize,foodServingUnits);
			
			
			meal.addFoodServing(foodServing);

			
			//Refresh Panel to display added FoodServing with an "X" button to remove it
				
			//CLEAR ALL TEXT ENTRY FIELDS
			
			//"X" Button: Remove FoodServings from current Meal being built
			//meal.removeFoodServing(index);
			////Refresh Panel
		}
		else
			System.out.println("Please fill in all the fields");
		
		//"Add Meal to My Meals" Button:
		myMeals.add(meal);
		
		
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		// TEST: Added: method to calculate total weight if Meal object is Dish
		
		// FoodServing(Food food, float servingSize, String servingUnit)
		
		Macro macro1 = new Macro((float)100, (float)20, (float)50, (float)5);
		Food food1 = new Food("food item",1,(float)1,"g",macro1);
		FoodServing foodServing1 = new FoodServing(food1,(float)12.4,"g");		// 100 calories
		
		Meal meal1 = new Meal();
		meal1.addFoodServing(foodServing1);	
		meal1.addFoodServing(foodServing1);	
		meal1.addFoodServing(foodServing1);	
		meal1.addFoodServing(foodServing1);	
		meal1.addFoodServing(foodServing1);	
		meal1.addFoodServing(foodServing1);	
		meal1.addFoodServing(foodServing1);	
		meal1.addFoodServing(foodServing1);	
		meal1.addFoodServing(foodServing1);	
		meal1.addFoodServing(foodServing1);	// 1000 calories total // 124 grams total
		
		
		meal1.setIsDish(false);
//		System.out.println("Is meal 1 a dish?: "+meal1.calcDishWeight());
//		System.out.println("Meal 1's total weight is: "+meal1.getDishWeight());
		
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		//PlAN MANAGER SCREEN - 2 components:
		// (1) Week Navigator: 	< [    ] ---- [    ] >
		// (2) 7x Daily Plan panels 
		// (3) Plus Button: Add a new plan to a day
		// (4) Repeat Button: Repeat previous day's plan if it exists
		
		LinkedList<Day> days = new LinkedList<Day>();
		LinkedList<Day> futureDays = new LinkedList<Day>();
		LinkedList<Meal> myDishes = new LinkedList<Meal>();
		
		//dummy data:
		Day day = new Day(new Date());
		days.add(day);
		
		//Create 28  empty day objects
		Date[] dateArray = new Date[21];
		for (int i = 0; i<21; i++)
		{ dateArray[i] = new Date(System.currentTimeMillis() + (i+1)*24*60*60*1000); }
		
		//Add to "futureDays"
		for(int i = 0; i<21; i++)
		{ day = new Day(dateArray[i]); day.setDayProgress(0); futureDays.add(day); }
		//end of dummy data
		
		SimpleDateFormat fmDate = new SimpleDateFormat("yyyy-MM-dd"); 				//date format: 2016-02-18
		SimpleDateFormat fmDayofWeek = new SimpleDateFormat ("EEEE");				//date format: Wednesday
		SimpleDateFormat fmLastRefresh = new SimpleDateFormat ("MMMM d, h:mm a");	//time format: "Feb 28, 1:34 PM"
		SimpleDateFormat fmTime = new SimpleDateFormat ("H:mm");					//time format 07:15 (or 13:00 for 1pm)
		
		SimpleDateFormat planMgrDate = new SimpleDateFormat ("MMM d");				//date format: Mar 17
		
		
		//[TEST: PASSED]
		//verifying dummy data
		//for (int i = 0; i < futureDays.size(); i++) System.out.println("Day Number: "+(i+1)+" > "+planMgrDate.format(futureDays.get(i).getDate()));
		//end of verifying dummy data
		
		//Week 1 - first day = current day. next 6 days = first 6 days in futureDays		
		//Week 2 - next 7 days after first 6 days											
		//Week 3 - next 7 days after first 13 days												
		
		// Week 1 = Index = 1
		// Week 2 = Index = 2
		// Week 3 = Index = 3	

		
		
		
		
		
		
		
		// (1) Week Navigator: 	< [    ] ---- [    ] >
		
		// " < " BUTTON
		// " > " BUTTON
		// " [   ] " TEXTBOX (left)
		// " [   ] " TEXTBOX (right)
		
		int index = 1;	// always start the Plan Manager at Week 1
		
		//	" > " BUTTON: displays next week
		if(index+1<=4) index++; else {} //else, do nothing (or do not display " > " button?)
		//Refresh the left and right text boxes :  < [  left  ] ---- [  right  ] >  and refresh the 7 Daily Plan panels
		
		//	" < " BUTTON: displays the previous week
		if(index-1>=0) index--; else {} //else, do nothing (or do not display " < " button?)
		//Refresh the left and right text boxes :  < [  left  ] ---- [  right  ] >  and refresh the 7 Daily Plan panels
		
		// "left [    ]" TEXTBOX: displays the STARTING date of the currently displayed week
		if(index == 1)  System.out.println(planMgrDate.format(days.getLast().getDate()));				//TEXTBOX will display this String
		if(index == 2)  System.out.println(planMgrDate.format(futureDays.get(6).getDate()));			//TEXTBOX will display this String
		if(index == 3)  System.out.println(planMgrDate.format(futureDays.get(13).getDate()));			//TEXTBOX will display this String
		
		// "right [    ]" TEXTBOX: displays the ENDING date of the currently displayed week
		if(index == 1)  System.out.println(planMgrDate.format(futureDays.get(5).getDate()));			//TEXTBOX will display this String
		if(index == 2)  System.out.println(planMgrDate.format(futureDays.get(12).getDate()));			//TEXTBOX will display this String
		if(index == 3)  System.out.println(planMgrDate.format(futureDays.get(19).getDate()));			//TEXTBOX will display this String

		
		
		
		
		
		// (2) 7x Daily Plan panels 
		
		Day dayOne; 	String dayOfWeek1;	int mealNumber1;	int workoutNumber1;	int predCalDiff1;
		Day dayTwo; 	String dayOfWeek2;	int mealNumber2;	int workoutNumber2;	int predCalDiff2;
		Day dayThree;	String dayOfWeek3;	int mealNumber3;	int workoutNumber3;	int predCalDiff3;
		Day dayFour;	String dayOfWeek4;	int mealNumber4;	int workoutNumber4;	int predCalDiff4;
		Day dayFive;	String dayOfWeek5;	int mealNumber5;	int workoutNumber5;	int predCalDiff5;
		Day daySix;		String dayOfWeek6;	int mealNumber6;	int workoutNumber6;	int predCalDiff6;
		Day daySeven;String dayOfWeek7;	int mealNumber7;	int workoutNumber7;	int predCalDiff7;
		
		if(index == 1)
		{
			dayOne = 	days.getLast();		
			dayTwo = 	futureDays.get(0);			
			dayThree = 	futureDays.get(1);	
			dayFour = 	futureDays.get(2);	
			dayFive = 	futureDays.get(3);	
			daySix = 	futureDays.get(4);		
			daySeven = 	futureDays.get(5);	
		}
		else if(index == 2) 
		{
			dayOne = 	futureDays.get(6);		
			dayTwo = 	futureDays.get(7);			
			dayThree = 	futureDays.get(8);	
			dayFour = 	futureDays.get(9);	
			dayFive =	futureDays.get(10);	
			daySix = 	futureDays.get(11);		
			daySeven = 	futureDays.get(12);
		}
		else //index == 3
		{
			dayOne = 	futureDays.get(13);		
			dayTwo = 	futureDays.get(14);			
			dayThree = 	futureDays.get(15);	
			dayFour = 	futureDays.get(16);	
			dayFive = 	futureDays.get(17);	
			daySix = 	futureDays.get(18);		
			daySeven = 	futureDays.get(19);
		}
		
		//Panel 1////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		//(a): day TEXTBOX
		 dayOfWeek1 = fmDayofWeek.format(dayOne.getDate());								//day TEXTBOX will display this String
		
		//(b): display either "No Plan" TEXTBOX & "+", "repeat" BUTTON    OR    "Edit" BUTTON
		if(dayOne.getPlan() == null) 
		{
			System.out.println("No Plan");
			//Display 2 buttons:
			// " + " BUTTON & " repeat " BUTTON
			//if " +  button is clicked:
			dayOne.setPlan(new Plan());
				//refresh panel (ie. it will jump to the 'else' statement below)
			//if " repeat " button is clicked:
				if(index == 1 && days.get(days.size()-2).getPlan() != null) { dayOne.setPlan(days.get(days.size()-2).getPlan()); } 	//refresh panel (ie. it will jump to the 'else' statement below)
				else if(index == 2 && futureDays.get(5).getPlan() != null) { dayOne.setPlan(futureDays.get(5).getPlan()); }			//refresh panel (ie. it will jump to the 'else' statement below)
				else if(index == 3 && futureDays.get(12).getPlan() != null) { dayOne.setPlan(futureDays.get(5).getPlan()); }			//refresh panel (ie. it will jump to the 'else' statement below)
				//else, button does nothing (or does not display if there's time to do that)
		} 
		else  
		{	
			System.out.println("BUTTON: Edit"); //display "Edit" BUTTON
			//(c): display 1) Meals, "+" button 2) Work outs, "+" button 3) predicted Cal diff
			mealNumber1 = dayOne.getPlan().getMeals().size();				//(1			//Display mealNumber + " Meal". Display "+" button besides String.
			workoutNumber1 = dayOne.getPlan().getWorkouts().size();			//(2			//Display workoutNumber + "Workout ". Display "+" button besides String.
			predCalDiff1 = (int)dayOne.getPlan().getPredictedCalorieBurn();	//(3			//Display predCalDiff + " cal". Display "!" besides String if not at least -500 cal or Display "checkmark" if it is.
		}
		
		//Panel 2////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		//(a): day TEXTBOX
		dayOfWeek2 = fmDayofWeek.format(dayTwo.getDate());								//day TEXTBOX will display this String
		
		//(b): display either "No Plan" TEXTBOX    OR    "Edit" BUTTON
		if(dayTwo.getPlan() == null)
		{
			System.out.println("No Plan");
			//Display 2 buttons:
			// " + " BUTTON & " repeat " BUTTON
			//if " +  button is clicked:
				dayTwo.setPlan(new Plan());
				//refresh panel (ie. it will jump to the 'else' statement below)
			//if " repeat " button is clicked:
				if(dayOne.getPlan() != null) { dayTwo.setPlan(dayOne.getPlan()); } else {} //do nothing
		}
		else  
		{
			System.out.println("BUTTON: Edit"); //display "Edit" BUTTOn 
			//(c): display 1) Meals, "+" button 2) Work outs, "+" button 3) predicted Cal diff
			mealNumber2 = dayTwo.getPlan().getMeals().size();				//(1			//Display mealNumber + " Meal". Display "+" button besides String.
			workoutNumber2 = dayTwo.getPlan().getWorkouts().size();			//(2			//Display workoutNumber + "Workout ". Display "+" button besides String.
			predCalDiff2 = (int)dayTwo.getPlan().getPredictedCalorieBurn();	//(3			//Display predCalDiff + " cal". Display "!" besides String if not at least -500 cal or Display "checkmark" if it is.
		}
		
		//Panel 3////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		//(a): day TEXTBOX
		dayOfWeek3 = fmDayofWeek.format(dayThree.getDate());								//day TEXTBOX will display this String
		
		//(b): display either "No Plan" TEXTBOX    OR    "Edit" BUTTON
		if(dayThree.getPlan() == null) 
		{
			System.out.println("No Plan");
			//Display 2 buttons:
			// " + " BUTTON & " repeat " BUTTON
			//if " +  button is clicked:
				dayThree.setPlan(new Plan());
				//refresh panel (ie. it will jump to the 'else' statement below)
			//if " repeat " button is clicked:
				if(dayTwo.getPlan() != null) { dayThree.setPlan(dayTwo.getPlan()); } else {} //do nothing
		}
		else 
		{
			System.out.println("BUTTON: Edit"); //display "Edit" BUTTON 
			//(c): display 1) Meals, "+" button 2) Work outs, "+" button 3) predicted Cal diff
			mealNumber3 = dayThree.getPlan().getMeals().size();					//(1			//Display mealNumber + " Meal". Display "+" button besides String.
			workoutNumber3 = dayThree.getPlan().getWorkouts().size();			//(2			//Display workoutNumber + "Workout ". Display "+" button besides String.
			predCalDiff3 = (int)dayThree.getPlan().getPredictedCalorieBurn();	//(3			//Display predCalDiff + " cal". Display "!" besides String if not at least -500 cal or Display "checkmark" if it is.
		}
		
		//Panel 4////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		//(a): day TEXTBOX
		dayOfWeek4 = fmDayofWeek.format(dayFour.getDate());								//day TEXTBOX will display this String
		
		//(b): display either "No Plan" TEXTBOX    OR    "Edit" BUTTON
		if(dayFour.getPlan() == null) 
		{
			System.out.println("No Plan");
			//Display 2 buttons:
			// " + " BUTTON & " repeat " BUTTON
			//if " +  button is clicked:
				dayFour.setPlan(new Plan());
				//refresh panel (ie. it will jump to the 'else' statement below)
			//if " repeat " button is clicked:
				if(dayThree.getPlan() != null) { dayFour.setPlan(dayThree.getPlan()); } else {} //do nothing
		}
		else  
		{ 
			System.out.println("BUTTON: Edit"); //display "Edit" BUTTON 
			//(c): display 1) Meals, "+" button 2) Work outs, "+" button 3) predicted Cal diff
			mealNumber4 = dayFour.getPlan().getMeals().size();						//(1			//Display mealNumber + " Meal". Display "+" button besides String.
			workoutNumber4 = dayFour.getPlan().getWorkouts().size();			//(2			//Display workoutNumber + "Workout ". Display "+" button besides String.
			predCalDiff4 = (int)dayFour.getPlan().getPredictedCalorieBurn();	//(3			//Display predCalDiff + " cal". Display "!" besides String if not at least -500 cal or Display "checkmark" if it is.
		
		}
		
		//Panel 5////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		//(a): day TEXTBOX
		dayOfWeek5 = fmDayofWeek.format(dayFive.getDate());								//day TEXTBOX will display this String
		
		//(b): display either "No Plan" TEXTBOX    OR    "Edit" BUTTON
		if(dayFive.getPlan() == null)
		{
			System.out.println("No Plan");
			//Display 2 buttons:
			// " + " BUTTON & " repeat " BUTTON
			//if " +  button is clicked:
				dayFive.setPlan(new Plan());
				//refresh panel (ie. it will jump to the 'else' statement below)
			//if " repeat " button is clicked:
				if(dayFour.getPlan() != null) { dayFive.setPlan(dayFour.getPlan()); } else {} //do nothing
		}
		else  
		{
			System.out.println("BUTTON: Edit"); //display "Edit" BUTTOn 
			//(c): display 1) Meals, + button 2) Work outs, + button 3) predicted Cal diff
			mealNumber5 = dayFive.getPlan().getMeals().size();					//(1			//Display mealNumber + " Meal". Display "+" button besides String.
			workoutNumber5 = dayFive.getPlan().getWorkouts().size();			//(2			//Display workoutNumber + "Workout ". Display "+" button besides String.
			predCalDiff5 = (int)dayFive.getPlan().getPredictedCalorieBurn();	//(3			//Display predCalDiff + " cal". Display "!" besides String if not at least -500 cal or Display "checkmark" if it is.
		}
		
		//Panel 6////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		//(a): day TEXTBOX
		dayOfWeek6 = fmDayofWeek.format(daySix.getDate());								//day TEXTBOX will display this String
		
		//(b): display either "No Plan" TEXTBOX    OR    "Edit" BUTTON
		if(daySix.getPlan() == null)
		{
			System.out.println("No Plan");
			//Display 2 buttons:
			// " + " BUTTON & " repeat " BUTTON
			//if " +  button is clicked:
				daySix.setPlan(new Plan());
				//refresh panel (ie. it will jump to the 'else' statement below)
			//if " repeat " button is clicked:
				if(dayFive.getPlan() != null) { daySix.setPlan(dayFive.getPlan()); } else {} //do nothing
		}
		else  
		{	
			System.out.println("BUTTON: Edit"); //display "Edit" BUTTOn 
			//(c): display 1) Meals, + button 2) Work outs, + button 3) predicted Cal diff
			mealNumber6 = daySix.getPlan().getMeals().size();				//(1			//Display mealNumber + " Meal". Display "+" button besides String.
			workoutNumber6 = daySix.getPlan().getWorkouts().size();			//(2			//Display workoutNumber + "Workout ". Display "+" button besides String.
			predCalDiff6 = (int)daySix.getPlan().getPredictedCalorieBurn();	//(3			//Display predCalDiff + " cal". Display "!" besides String if not at least -500 cal or Display "checkmark" if it is.
		}		
		
		//Panel 7////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			
		//(a): day TEXTBOX
		dayOfWeek7 = fmDayofWeek.format(daySeven.getDate());								//day TEXTBOX will display this String
		
		//(b): display either "No Plan" TEXTBOX    OR    "Edit" BUTTON
		if(daySeven.getPlan() == null)
		{
			System.out.println("No Plan");
			//Display 2 buttons:
			// " + " BUTTON & " repeat " BUTTON
			//if " +  button is clicked:
				daySix.setPlan(new Plan());
				//refresh panel (ie. it will jump to the 'else' statement below)
			//if " repeat " button is clicked:
				if(daySix.getPlan() != null) { dayFive.setPlan(daySix.getPlan()); } else {} //do nothing
		}
		else  
		{
			System.out.println("BUTTON: Edit"); //display "Edit" BUTTOn 
			//(c): display 1) Meals, + button 2) Work outs, + button 3) predicted Cal diff
			mealNumber7 = daySeven.getPlan().getMeals().size();					//(1			//Display mealNumber + " Meal". Display "+" button besides String.
			workoutNumber7 = daySeven.getPlan().getWorkouts().size();			//(2			//Display workoutNumber + "Workout ". Display "+" button besides String.
			predCalDiff7 = (int)daySeven.getPlan().getPredictedCalorieBurn();	//(3			//Display predCalDiff + " cal". Display "!" besides String if not at least -500 cal or Display "checkmark" if it is.
		}	
		
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
*/		
/////////////////////////////////
		// DISH SERVER when adding meals:
/////////////////////////////////
		
		//START of dummy data:
		Plan plan = new Plan();
		LinkedList<Meal> myDishes = new LinkedList<Meal>();
		LinkedList<Meal> myMeals = new LinkedList<Meal>();
		
		Macro dummyMacro = new Macro(100,10,10,10);
		Food dummyFood = new Food("dish name",1,100,"g",dummyMacro);
		FoodServing dummyFoodServing = new FoodServing(dummyFood, 100, "g");
		
		Meal dish1 = new Meal(); dish1.setIsDish(true);		dish1.setName("Dish 1");		//extra Dish, not displayed by default unless "v" button is pressed
		Meal dish2 = new Meal(); dish2.setIsDish(true);		dish2.setName("Dish 2");		//Dish displayed at position 8
		Meal dish3 = new Meal(); dish3.setIsDish(true);		dish3.setName("Dish 3");		//Dish displayed at position 7
		Meal dish4 = new Meal(); dish4.setIsDish(true);		dish4.setName("Dish 4");		//Dish displayed at position 6
		Meal dish5 = new Meal(); dish5.setIsDish(true);		dish5.setName("Dish 5");		//Dish displayed at position 5
		Meal dish6 = new Meal(); dish6.setIsDish(true);		dish6.setName("Dish 6");		//Dish displayed at position 4
		Meal dish7 = new Meal(); dish7.setIsDish(true);		dish7.setName("Dish 7");		//Dish displayed at position 3
		Meal dish8 = new Meal(); dish8.setIsDish(true);		dish8.setName("Dish 8");		//Dish displayed at position 2
		Meal dish9 = new Meal(); dish9.setIsDish(true);		dish9.setName("Dish 9");		//Dish displayed at position 1 (most recently made dish)
	
		dish1.addFoodServing(dummyFoodServing);
		dish2.addFoodServing(dummyFoodServing);
		dish3.addFoodServing(dummyFoodServing);
		dish4.addFoodServing(dummyFoodServing);
		dish5.addFoodServing(dummyFoodServing);
		dish6.addFoodServing(dummyFoodServing);
		dish7.addFoodServing(dummyFoodServing);
		dish8.addFoodServing(dummyFoodServing);
		dish9.addFoodServing(dummyFoodServing);
	
		myDishes.add(dish1);
		myDishes.add(dish2);
		myDishes.add(dish3);
		myDishes.add(dish4);
		myDishes.add(dish5);
		myDishes.add(dish6);
		myDishes.add(dish7);
		myDishes.add(dish8);
		myDishes.add(dish9);
		System.out.println("dummy data");
		System.out.println(myDishes.get(myDishes.size()-1).getName());		//position 1
		System.out.println(myDishes.get(myDishes.size()-2).getName());		//position 2
		System.out.println(myDishes.get(myDishes.size()-3).getName());		//position 3
		System.out.println(myDishes.get(myDishes.size()-4).getName());		//position 4
		System.out.println(myDishes.get(myDishes.size()-5).getName());		//position 5
		System.out.println(myDishes.get(myDishes.size()-6).getName());		//position 6
		System.out.println(myDishes.get(myDishes.size()-7).getName());		//position 7
		System.out.println(myDishes.get(myDishes.size()-8).getName());		//position 8
		System.out.println();
		//END of dummy data
		
/////////////////////////////////
		// (1) Displaying the list of Dishes. Displaying the navigational arrows and how their functionality works.
/////////////////////////////////
		
		if( myDishes.isEmpty() )
		{
			//display String / text box: "No Dishes\n Create Dishes from the T Menu"
		}
		else if ( myDishes.size() <= 8 )
		{
			//display the dishes WITHOUT any navigational arrows
		}
		else //myDishes.size() > 5
		{
			// display navigational arrows " ^ " and " v"
			
			// initially display the 8 MOST RECENTLY made dishes ie. the last 8 dishes in the LinkedList myDishes
			int index = 0;

			// " ^ " BUTTON: perform scroll up:
			 
			if( (myDishes.size()-(index-1)-1) <= myDishes.size()-1 )
			{
				index--;
				//refresh the following:
				System.out.println(myDishes.get(myDishes.size()-index-1).getName());		//position 1
				System.out.println(myDishes.get(myDishes.size()-index-2).getName());		//position 2
				System.out.println(myDishes.get(myDishes.size()-index-3).getName());		//position 3
				System.out.println(myDishes.get(myDishes.size()-index-4).getName());		//position 4
				System.out.println(myDishes.get(myDishes.size()-index-5).getName());		//position 5
				System.out.println(myDishes.get(myDishes.size()-index-6).getName());		//position 6
				System.out.println(myDishes.get(myDishes.size()-index-7).getName());		//position 7
				System.out.println(myDishes.get(myDishes.size()-index-8).getName());		//position 8
			}
			else 
			{
				System.out.println(" ^: Do Nothing");
				// button does nothing
			}
			 
			// " v " BUTTON: perform scroll down:
			 
			if( myDishes.size()-(index+1) - 8 >= 0 )
			{
				index++;
				//refresh the following:
				System.out.println(myDishes.get(myDishes.size()-index-1).getName());		//position 1
				System.out.println(myDishes.get(myDishes.size()-index-2).getName());		//position 2
				System.out.println(myDishes.get(myDishes.size()-index-3).getName());		//position 3
				System.out.println(myDishes.get(myDishes.size()-index-4).getName());		//position 4
				System.out.println(myDishes.get(myDishes.size()-index-5).getName());		//position 5
				System.out.println(myDishes.get(myDishes.size()-index-6).getName());		//position 6
				System.out.println(myDishes.get(myDishes.size()-index-7).getName());		//position 7
				System.out.println(myDishes.get(myDishes.size()-index-8).getName());		//position 8
			}
			else
			{
				System.out.println(" v: Do Nothing");
				// button does nothing (.size()-index-5) 
			}
			
		}

///////////////////////////////
		// (2) Serving from myDishes to create a Meal to be added to Plan
///////////////////////////////
		
		Meal newMeal = new Meal();
		
		// dummy user scenario:
		// user enters in a serving size for the first dish at the top of the list --> goes into "Float servingSize"
		// user clicks "serve" button
		
		// the above 2 actions should execute the code below:
		
		String foodName = dish1.getName(); 															
		///////////////////////////////////////
		float servingSize = (float) 50;			// taken from text entry field ie. from the "  1/2  |  g  " (Its always in grams when it comes to Dishes, our code only handles grams)
		///////////////////////////////////////
		
		Macro macro = new Macro();
		macro.setCalories( ( dish1.getCalories() / dish1.getDishWeight() ) * servingSize ); 
		macro.setProteins( ( dish1.getMacros().getProteins() / dish1.getDishWeight() ) * servingSize );
		macro.setCarbs( ( dish1.getMacros().getCarbs() / dish1.getDishWeight() ) * servingSize );
		macro.setFats( ( dish1.getMacros().getFats() / dish1.getDishWeight() ) * servingSize );
		
		Food food = new Food(foodName,1,servingSize,"g",macro);
		FoodServing foodServing  = new FoodServing(food,servingSize,"g");
		
		newMeal.addFoodServing(foodServing);
		
		//refresh "# Servings  |  # Calories" --> should now say "1 Serving  |  X Calories"
		// X calories  = newMeal.getCalories();
		// # servings = newMeal.getFoodServings.size();
		
/////////////////////////////////
		// ADDING MEALS FROM myMeals to Plan
/////////////////////////////////
		
		//display ~10 meals and a plus button besides each. Navigational arrows that can scroll up or down in same fashion.
		
		// " + " button: gets that Meal from "myMeals" and adds it to the plan:
		int index=0; 									//the right value needs to be stored in index
		newMeal = myMeals.get(index);
		plan.addMeal(newMeal);
		// refreshes UI so added meal shows up on the plan
		
/////////////////////////////////
		// ADDING WOKOUTS to Plan
/////////////////////////////////
		
		int calBurnGoal = 200; 		//get this value from the text entry field
		// "ADD WORKOUT" button:
		Workout newWorkout = new Workout(calBurnGoal);
		plan.addWorkout(newWorkout);
		//refresh UI so added work out shows up on the plan
		

/////////////////////////////////
		//Trainr Feedback Panel --> has 7 panels. Each panel is placed beside each of the 7 Daily Panels
/////////////////////////////////
		// In each of the panels
		//Dummy data:
		Day day = new Day(); 		//get the same Day object that's used in the adjacent Daily Panel
		day.setPlan(plan);			//dummy plan, but in the real code: get the day's plan
		//end of dummy data
		
		if(day.getPlan() == null)
		{
			//display String / text box: "This day needs a plan" with an arrow
		}
		else
		{
			if(day.getPlan().getMeals().size() == 0 && day.getPlan().getWorkouts().size() == 0 )
			{
				//display String / text box: "Add either Meals or Workouts that will result in at least a -500 calorie difference" with an arrow
			}
			else if (day.getPlan().getPredictedCalorieBurn() > -500 )
			{
				//display String / text box: "This day’s calorie difference isn’t at least -500! Reduce your calorie intake (Meals) or  Add a Workout with a high enough Calorie Burn Goal" with an arrow
			}
			else
			{
				//display absolutely nothing - this happens if the calorie difference IS at least -500 which means its an accepted plan
			}
		}
	
		
		
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		
		
	} // Main
} // Class
