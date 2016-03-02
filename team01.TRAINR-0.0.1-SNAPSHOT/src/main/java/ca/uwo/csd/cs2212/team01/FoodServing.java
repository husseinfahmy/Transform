package ca.uwo.csd.cs2212.team01;

public class FoodServing {

	private Food food; //the food that this foodServing's nutritional info is derived from
	private Macro macros = new Macro(); //derived nutritional info is stored here
	private float servingSize;
	private String servingUnit;

	public FoodServing(Food food, float servingSize, String servingUnit) {
		this.food = food;
		this.servingSize = servingSize;
		this.servingUnit = servingUnit;
		this.calcMacros(food, servingSize);
	}

	//public float unitConverter() {}
	
	//recieves Food object, servingSize and servingUnit
	//use formula to calculate macros for this serving of food
	public void calcMacros(Food food, float servingSize) 
	{
		macros.setCalories( ( servingSize / food.getServingInfo() ) * food.getMacroInfo().getCalories() ); // (input serving size / ref serving size) * ref calories
		macros.setProteins( ( servingSize / food.getServingInfo() ) * food.getMacroInfo().getProteins() );
		macros.setCarbs( ( servingSize / food.getServingInfo() ) * food.getMacroInfo().getCarbs() );
		macros.setFats( ( servingSize / food.getServingInfo() ) * food.getMacroInfo().getFats() );
	}
	
	public Macro getMacros() { return macros; }
	
	public Food getFood() { return food; }
	public void setFood(Food food) { this.food = food; }
	public float getServingSize() { return servingSize; }
	public void setServingSize(float servingSize) { this.servingSize = servingSize; }
	public String getServingUnit() { return servingUnit; }
	public void setServingType(String ServingUnit) { this.servingUnit = ServingUnit; }

}
