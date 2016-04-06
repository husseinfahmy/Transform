
package ca.uwo.csd.cs2212.team01;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.ListIterator;

/**
 * @author team01
 *
 */
public class Plan implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//Attributes
	public LinkedList<Meal> meals;
	private LinkedList<Workout> workouts;
	//private float bmr = 1600;							//need to create a formula/method for stage 3 to compute this
	private int mealCount = 0;
	//private int workoutCount = 0;
	private float caloriesConsumed = 0;
	private float caloriesBurned = 0;		//need to create a formula/method for stage 3 to compute this
	private float predictedCalorieDiff = caloriesConsumed - caloriesBurned;  // total meal calories - (BMR + Calories burned from Workouts)
	
	//Contructor
	/**
	 * 
	 */
	public Plan(float bmr) 
	{
		meals = new LinkedList<Meal>(); workouts = new LinkedList<Workout>();
		caloriesBurned = bmr;
		predictedCalorieDiff = caloriesConsumed - caloriesBurned;
	}
	
	//Methods
	
	/**
	 * @return
	 */
	public boolean workoutPlanned() //is there a workout scheduled?
	{ if(workouts.size() != 0) return true;  else return false; }
	
	//Meals & Workouts:
	/**
	 * @param workout
	 */
	public void addWorkout(Workout workout) 
	{  workouts.add(workout);  caloriesBurned += workout.getCalorieBurnGoal();  updatePredictedCalorieBurn(); }
	
	/**
	 * @param index
	 * @return
	 */
	public boolean removeWorkout(int index)
	{ 
		if(index >=  workouts.size()){ return false;} //index is out of bounds
		else{
		caloriesBurned -= workouts.get(index).getCalorieBurnGoal(); 
		workouts.remove(index); updatePredictedCalorieBurn();
		return true;
		}
	}
	
	/**
	 * @param meal
	 */
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
	
	
	/**
	 * @param index
	 * @return
	 */
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
	
	/**
	 * @return
	 */
	public LinkedList<Meal> getMeals() { return meals; }
	
	/**
	 * @return
	 */
	public LinkedList<Workout> getWorkouts() { return workouts; }
	
	//Calories Consumed, Burned & Predicted Difference:
	/**
	 * @param caloriesConsumed
	 */
	public void setCaloriesConsumed(float caloriesConsumed) 
	{ this.caloriesConsumed = caloriesConsumed; }
	
	/**
	 * @return
	 */
	public float getCaloriesConsumed()
	{ return this.caloriesConsumed; }
	
	/**
	 * @param caloriesBurned
	 */
	public void setCaloriesBurned(float caloriesBurned)
	{ this.caloriesBurned = caloriesBurned; }
	
	/**
	 * @return
	 */
	public float getCaloriesBurned()
	{ return this.caloriesBurned; }
	
	/**
	 * 
	 */
	public void updatePredictedCalorieBurn()
	{ predictedCalorieDiff = caloriesConsumed - caloriesBurned; }
	
	/**
	 * @return
	 */
	public float getPredictedCalorieBurn() 
	{return this.predictedCalorieDiff; }
	
	/**
	 * @param cal
	 */
	public void setPredictedCalorieBurn(float cal) 
	{ this.predictedCalorieDiff = cal; }
	
	/**
	 * Returns a copy of the plan this method is called on
	 * @return Plan
	 */
	public Plan copyPlan(float bmr)
	{
		Plan newPlan = new Plan(bmr);

		//Meals: construct same number of new meals
		
		for(int i = 0; i < this.getMeals().size(); i++)
		{
			Meal newMeal = new Meal();
			newMeal.setName(this.getMeals().get(i).getName());
			newMeal.setIsDish(this.getMeals().get(i).getIsDish());
			if(this.getMeals().get(i).getIsDish()){newMeal.setDishWeight(this.getMeals().get(i).getDishWeight());}
			//FoodServings: construct same number of new foodservings and add to new meal
			
			for(int j = 0; j < this.getMeals().get(i).getFoodServings().size(); j++)	//construct the next meal
			{
				//public Food(String name, int itemNumber, float servingSize, String servingUnit, Macro macro)
				Macro newMacro = new Macro();	//for the Food Object
				
				newMacro.setCalories( this.getMeals().get(i).getFoodServings().get(j).getFood().getMacroInfo().getCalories());
				newMacro.setCarbs(this.getMeals().get(i).getFoodServings().get(j).getFood().getMacroInfo().getCarbs());
				newMacro.setFats(this.getMeals().get(i).getFoodServings().get(j).getFood().getMacroInfo().getFats());
				newMacro.setProteins(this.getMeals().get(i).getFoodServings().get(j).getFood().getMacroInfo().getProteins());
				
				Food newFood = new Food	(
						this.getMeals().get(i).getFoodServings().get(j).getFood().getName(),
						1, this.getMeals().get(i).getFoodServings().get(j).getFood().getServingInfo(),
						this.getMeals().get(i).getFoodServings().get(j).getFood().getServingUnit(), newMacro
										);
				//public FoodServing(Food food, float servingSize, String servingUnit)
				FoodServing newFS = new FoodServing
						(
								newFood,
								this.getMeals().get(i).getFoodServings().get(j).getServingSize(),
								this.getMeals().get(i).getFoodServings().get(j).getServingUnit()
						);
				
				newMeal.addFoodServing(newFS);
			}
			
			newPlan.addMeal(newMeal);
		}
		
		//Workouts: construct same number of new workouts
		for(int i = 0; i < this.getWorkouts().size(); i++)
		{
			Workout newWRK = new Workout(this.getWorkouts().get(i).getCalorieBurnGoal());
			newPlan.addWorkout(newWRK);
		}
		
		return newPlan;	
	}
	/**
	 * Setter for mealCount
	 * @param mealCount
	 */
	public void setMealCount(int mealCount){this.mealCount=mealCount;}
	/**
	 * Getter for mealCount
	 * @return
	 */
	public int getMealCount(){return mealCount;}

	

}//Class