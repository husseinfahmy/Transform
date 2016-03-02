package ca.uwo.csd.cs2212.team01;

import java.util.LinkedList;
import java.util.ListIterator;

public class Plan 
{	
	//Attributes
	public LinkedList<Meal> meals;
	private LinkedList<Workout> workouts;
	//private float bmr = 1600;							//need to create a formula/method for stage 3 to compute this
	private int mealCount = 0;
	//private int workoutCount = 0;
	private float caloriesConsumed = 0;
	private float caloriesBurned = 1600;		//need to create a formula/method for stage 3 to compute this
	private float predictedCalorieDiff = caloriesConsumed - caloriesBurned;  // total meal calories - (BMR + Calories burned from Workouts)
	
	//Contructor
	public Plan() 
	{ meals = new LinkedList<Meal>(); workouts = new LinkedList<Workout>(); }
	
	//Methods
	
	public boolean workoutPlanned() //is there a workout scheduled?
	{ if(workouts.size() != 0) return true;  else return false; }
	
	//Meals & Workouts:
	public void addWorkout(Workout workout) 
	{  workouts.add(workout);  caloriesBurned += workout.getCalorieBurnGoal();  updatePredictedCalorieBurn(); }
	
	public boolean removeWorkout(int index)
	{ 
		if(index >=  workouts.size()){ return false;} //index is out of bounds
		else{
		caloriesBurned -= workouts.get(index).getCalorieBurnGoal(); 
		workouts.remove(index); updatePredictedCalorieBurn();
		return true;
		}
	}
	
	public void addMeal(Meal meal) 
	{ 
		Meal newMeal = new Meal();
		newMeal.setCalories(meal.getCalories());
		newMeal.setCalorieString(meal.getCalorieString());
		newMeal.setMacros(meal.getMacros());
		newMeal.setFoodServings(meal.getFoodServings());
		mealCount++;
		newMeal.setName("Meal "+mealCount);
		meals.add(newMeal);
		caloriesConsumed += newMeal.getCalories();
		updatePredictedCalorieBurn();
		//System.out.println("mealCount: " + mealCount);
	}
	
	
	public boolean removeMeal(int index) 
	{ 
		if(index >=  meals.size()){ return false;} //index is out of bounds
		else{
		mealCount--;
		caloriesConsumed -= meals.get(index).getCalories(); 
		meals.remove(index); 
		//Rename all meals with correct meal number
		ListIterator<Meal> mealsIter = meals.listIterator();
		int counter = 1;
		while(mealsIter.hasNext())
		{
			mealsIter.next().setName("Meal "+counter); counter++;
		}
		//System.out.println("mealCount: " + mealCount);
		updatePredictedCalorieBurn();
		return true; //successfuly removed meal
		}
	}
	
	public LinkedList<Meal> getMeals() { return meals; }
	
	public LinkedList<Workout> getWorkouts() { return workouts; }
	
	//Calories Consumed, Burned & Predicted Difference:
	public void setCaloriesConsumed(float caloriesConsumed) 
	{ this.caloriesConsumed = caloriesConsumed; }
	
	public float getCaloriesConsumed()
	{ return this.caloriesConsumed; }
	
	public void setCaloriesBurned(float caloriesBurned)
	{ this.caloriesBurned = caloriesBurned; }
	
	public float getCaloriesBurned()
	{ return this.caloriesBurned; }
	
	public void updatePredictedCalorieBurn()
	{ predictedCalorieDiff = caloriesConsumed - caloriesBurned; }
	
	public float getPredictedCalorieBurn() 
	{return this.predictedCalorieDiff; }
	
	public void setPredictedCalorieBurn(float cal) 
	{ this.predictedCalorieDiff = cal; }
	
}//Class