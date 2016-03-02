package ca.uwo.csd.cs2212.team01;

/**
 * This is a class representing a Food
*/
public class Food {
	
	//fields
	private String name; //Name of food
	//Nutritional Info of food stored here:
	private Macro macroInfo;
	private float servingInfo;
	private String ServingUnit; //"cup", "grams", "can", "ml", etc
	///////////////////////////////////////////////////////////
	//item number in data base:
	private int itemNumber;
	///////////////////////////////////////////////////////////
	
	/**
	 * Food constructor
	 */
	public Food ()
	{
		this.macroInfo = null;
		this.itemNumber = 0;
		this.servingInfo = 0;
		this.name = null;
	}
	
	/**
	 * Food constructor
	 * @param macro Macro holding the Macro of the Food
	 * @param itemNumber int holding the item number of the Food
	 * @param serving float holding the number of servings of the Food
	 * @param name String holding the name of the Food
	 */

	public Food(String name, int itemNumber, float servingSize, String servingUnit, Macro macro)
	{
		this.macroInfo = macro;
		this.itemNumber = itemNumber;
		this.servingInfo = servingSize;
		this.name = name;
		this.ServingUnit = servingUnit;
	}

	/**
	 * accessor method to get the Macro in the Food
	 * @return Macro in the Food
	 */
	public Macro getMacroInfo() {
		return macroInfo;
	}

	/**
	 * accessor method to get the item number of the Food
	 * @return int holding the item number of the Food
	 */
	public int getItemNumber() {
		return itemNumber;
	}

	/**
	 * accessor method to get the number of servings of the Food
	 * @return float holding the number of servings
	 */
	public float getServingInfo() {
		return servingInfo;
	}

	/**
	 * accessor method to get the name of the Food
	 * @return String holding the name of the Food
	 */
	public String getName() {
		return name;
	}
	
	public String getServingUnit() {
		return this.ServingUnit;
	}

	/**
	 * setter method to set the Macro in the Food
	 * @param macro Macro that holds the Macro information for the Food
	 */
	public void setMacros(Macro macro) {
		this.macroInfo = macro;
	}

	/**
	 * setter method to set the item number of the Food
	 * @param itemNumber int that holds the item number of the Food
	 */
	public void setItemNumber(int itemNumber) {
		this.itemNumber = itemNumber;
	}

	/**
	 * setter method to set number of servings of the Food
	 * @param serving float that holds the number of servings of the Food
	 */
	public void setServing(float serving) {
		this.servingInfo = serving;
	}

	/**
	 * setter method to set the name of the Food
	 * @param name String that holds the name of the Food
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * setter method to set serving type:
	 * 'g' = in grams
	 * 'c' = cups
	 * @param type
	 */
	public void setServingUnit(String unit)
	{ this.ServingUnit = unit;}
	
	

}
