package ca.uwo.csd.cs2212.team01;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.ListIterator;

/**
 * @author team01
 *
 */
public class Meal implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//Attributes
	private String name;
	private String calorieContent = "";
	private LinkedList<FoodServing> foodServings;
	private float calories;
	private Macro macros;
	private boolean isDish = false;
	private float dishWeight = 0;
	
	//Constructor
	/**
	 * Class Constructor
	 */
	public Meal() { foodServings = new LinkedList<FoodServing>(); macros = new Macro(); }
	
	//Getters & Setters
	/**
	 * Gets the name of the meal plan.
	 * @return
	 */
	public String getName() { return name; }
	/**
	 * Gets the Food Serving macros.
	 * @return
	 */
	public Macro getMacros() { return macros; }
	/**
	 * Gets the total calories of the Meal.
	 * @return
	 */
	public float getCalories() { return calories; }
	/**
	 * Gets the total calories of the Meal as a String.
	 * @return
	 */
	public String getCalorieString() { return calorieContent; }
	/**
	 * Gets the Food Servings of the meal.
	 * @return
	 */
	public LinkedList<FoodServing> getFoodServings() { return foodServings; }
	/**
	 * Sets the name of the Meal plan.
	 * @param name
	 */
	public void setName(String name) { this.name = name; }
	/**
	 * Sets the total calories of the Meal plan as a String.
	 * @param calorContent
	 */
	public void setCalorieString(String calorContent) { this.calorieContent = calorContent; }
	/**
	 * Sets the total calories of the Meal plan.
	 * @param calories
	 */
	public void setCalories(float calories) { this.calories = calories; }
	/**
	 * Sets the macros for the Food Serving.
	 * @param macros
	 */
	public void setMacros(Macro macros) { this.macros = macros; }
	/**
	 * Gets the Food Servings for the Meal plan.
	 * @param foodServings
	 */
	public void setFoodServings(LinkedList<FoodServing> foodServings) {this.foodServings = foodServings;}
	/**
	 * Specifies if Meal Object is a dish or not a dish (a standard meal)
	 * @param Boolean. True = is a dish. False = is a meal.
	 */
	public void setIsDish(boolean b) { this.isDish = b; } 
	/**
	 * Returns isDish. True = meal is a dish. False = meal is a standard meal
	 * @param Boolean
	 */
	public boolean getIsDish() { return this.isDish; }
	/**
	 * Setter for dishWeight
	 * @param weight, float
	 */
	public void setDishWeight(float weight) { this.dishWeight = weight; } 
	/**
	 * Getter for dishWeight()
	 * @return float, dishWeight
	 */
	public float getDishWeight() {return this.dishWeight; }
	
	//Methods
	/**
	 * Creates a new Food Serving option for the Meal plan.
	 * @param newFoodServing
	 */
	public void addFoodServing(FoodServing newFoodServing)
	{
		//Add Macros of foodserving to overal macros of entire meal
		calories += newFoodServing.getMacros().getCalories();
		macros.setProteins(macros.getProteins() + newFoodServing.getMacros().getProteins());
		macros.setCarbs(macros.getCarbs() + newFoodServing.getMacros().getCarbs());
		macros.setFats(macros.getFats() + newFoodServing.getMacros().getFats());
		
		foodServings.add(newFoodServing);
		calorieContent = " (+ "+(int)calories + " Cal)"; //ex:    Meal 1 (+ 445 Cal)
		
		if(isDish) this.calcDishWeight();
		//System.out.println("calorieContent: " + calorieContent);
	}
	
	/**
	 * Removes a Food Serving option for the Meal plan.
	 * @param index
	 */
	public void removeFoodServing(int index) //needs to handle exceptions
	{
		FoodServing foodServing = foodServings.get(index);
		
		//Remove Macros of foodserving to overal macros of entire meal
		calories -= foodServing.getMacros().getCalories();
		macros.setProteins(macros.getProteins() - foodServing.getMacros().getProteins());
		macros.setCarbs(macros.getCarbs() - foodServing.getMacros().getCarbs());
		macros.setFats(macros.getFats() - foodServing.getMacros().getFats());
		
		this.foodServings.remove(index); 
		calorieContent = " (+ "+(int)calories + " Cal)";
		
		if(isDish) this.calcDishWeight();
		//System.out.println("calorieContent: " + calorieContent);
	}
	
	/**
	 * Gets the total number of Food Servings.
	 * @return
	 */
	public int numberOfServings() { return foodServings.size(); }
	
	public boolean calcDishWeight()
	{
		if(isDish)
		{
			dishWeight = 0;
			ListIterator<FoodServing> foodservings = this.foodServings.listIterator();
			while(foodservings.hasNext())
			{
				FoodServing foodserving = foodservings.next();
				if(foodserving.getServingUnit().equals("g")) dishWeight += foodserving.getServingSize();
			}
			return true;
		}
		else return false;
	}
	
	
	/**
	 * Method to serve portions from meal (for serving from a Dish)
	 */
	
	
	
}//Class