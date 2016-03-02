package ca.uwo.csd.cs2212.team01;

import java.util.LinkedList;

public class Meal {
	
	//Attributes
	private String name;
	private String calorieContent = "";
	private LinkedList<FoodServing> foodServings;
	private float calories;
	private Macro macros;
	
	//Constructor
	public Meal() { foodServings = new LinkedList<FoodServing>(); macros = new Macro(); }
	
	//Getters & Setters
	public String getName() { return name; }
	public Macro getMacros() { return macros; }
	public float getCalories() { return calories; }
	public String getCalorieString() { return calorieContent; }
	public LinkedList<FoodServing> getFoodServings() { return foodServings; }
	public void setName(String name) { this.name = name; }
	public void setCalorieString(String calorContent) { this.calorieContent = calorContent; }
	public void setCalories(float calories) { this.calories = calories; }
	public void setMacros(Macro macros) { this.macros = macros; }
	public void setFoodServings(LinkedList<FoodServing> foodServings) {this.foodServings = foodServings;}
	
	//Methods
	public void addFoodServing(FoodServing newFoodServing)
	{
		//Add Macros of foodserving to overal macros of entire meal
		calories += newFoodServing.getMacros().getCalories();
		macros.setProteins(macros.getProteins() + newFoodServing.getMacros().getProteins());
		macros.setCarbs(macros.getCarbs() + newFoodServing.getMacros().getCarbs());
		macros.setFats(macros.getFats() + newFoodServing.getMacros().getFats());
		
		foodServings.add(newFoodServing);
		calorieContent = " (+ "+(int)calories + " Cal)"; //ex:    Meal 1 (+ 445 Cal)
		//System.out.println("calorieContent: " + calorieContent);
	}
	
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
		//System.out.println("calorieContent: " + calorieContent);
	}
	
	public int numberOfServings() 
	{ return foodServings.size(); }
	
}//Class
