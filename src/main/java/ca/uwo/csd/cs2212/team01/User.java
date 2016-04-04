
package ca.uwo.csd.cs2212.team01;

import java.io.Serializable;
import java.util.LinkedList;

/**
 * @author team01
 *
 */
public class User implements Serializable{
	private static final long serialVersionUID = 1L;
	
	//Attributes
	public String name;
	public int age;				
	public char gender;			// f or m
	public float height;		//cm
	public LinkedList<Journey> myJourneys = new LinkedList<Journey>();
	public float bmr;
	
	private double lifeTimeDistance;
	private int lifeTimeFloors;
	private int lifeTimeSteps;
	
	private double bestDistance;
	private int bestFloors;
	private int bestSteps;
	
	
	//Constructor
	/**
	 * @param name
	 */
	public User(String name) {this.name = name;}
	
	//Constructor
	/**
	 * @param name
	 * @param age
	 * @param gender
	 * @param height
	 */
	public User(String name, int age, char gender, float height) {
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.height = height;
	}
	
	//Methods
	/**
	 * @param journey
	 */
	public void addJourney(Journey journey)
	{
		myJourneys.add(journey);
	}
	/**
	 * @return
	 */
	public int numberOfJourneys()
	{
		return myJourneys.size();
	}
	
	
	
	
	/**
	 * setter method to set the values of lifetime totals for distance, floors, and steps
	 * @param days a LinkedList of Day objects that store the data for each day
	 */
	public void setLifeTimeValues(LinkedList<Day> days)
	{
		double totalDistance = 0;
		int totalFloors = 0;
		int totalSteps = 0;
		
		//go through all of the days to get the sum for each attribute
		for(int i = 0; i < days.size(); i++)
		{
			totalDistance += days.get(i).getTotalDist();
			totalFloors += days.get(i).getTotalFloors();
			totalSteps += days.get(i).getTotalSteps();
		}
		
		this.lifeTimeDistance = totalDistance;
		this.lifeTimeFloors = totalFloors;
		this.lifeTimeSteps = totalSteps;
	}
	
	
	/**
	 * setter method to set the values of the best days for distance, floors, and steps
	 * @param days a LinkedList of Day objects that store the data for each day
	 */
	public void setBestValues(LinkedList<Day> days)
	{
		int maxDistance = 0;
		int maxFloors = 0;
		int maxSteps = 0;
		
		//go through all of the days to find the maximum value for each attribute
		for(int i = 0; i < days.size(); i++)
		{
			if(days.get(i).getTotalDist() > maxDistance)
			{
				maxDistance = days.get(i).getTotalDist();
			}
			
			if(days.get(i).getTotalFloors() > maxFloors)
			{
				maxFloors = days.get(i).getTotalFloors();
			}
			
			if(days.get(i).getTotalSteps() > maxSteps)
			{
				maxSteps = days.get(i).getTotalSteps();
			}
		}
		
		this.bestDistance = maxDistance;
		this.bestFloors = maxFloors;
		this.bestSteps = maxSteps;
	}
	
	
	
	public void calcBMR(VirtualTrainer vt)
	{
		if(gender=='f' || gender=='F')
		{ setBmr((float)(447.6 + (9.2*(vt.getCurrentWeight()/2.2)) + (3.1*(height/0.39)) - (4.3*age))-1500); }
		
		else if(gender=='m' || gender == 'M')
		{ setBmr((float)(88.3 + (13.4*(vt.getCurrentWeight()/2.2)) + (4.8*(height/0.39)) - (5.7*age))-1800); }
		
		else
		{ setBmr(0);}
	}

	
	
	
	
	/**
	 * accessor method to get the largest distance in one day
	 * @return double largest distance in one day
	 */
	public double getBestDistance()
	{
		return bestDistance;
	}

	/**
	 * accessor method to get the largest number of floors in one day
	 * @return int largest number of floors in one day
	 */
	public int getBestFloors()
	{
		return bestFloors;
	}

	/**
	 * accessor method to get the largest number of steps in one day
	 * @return int largest number of steps in one day
	 */
	public int getBestSteps()
	{
		return bestSteps;
	}
	

	/**
	 * accessor method to get lifetime distance
	 * @return double lifetime total distance
	 */
	public double getLifeTimeDistance()
	{
		return lifeTimeDistance;
	}
	
	/**
	 * accessor method to get the lifetime floors
	 * @return int lifetime total floors
	 */
	public int getLifeTimeFloors()
	{
		return lifeTimeFloors;
	}
	
	/**
	 * accessor method to get the lifetime steps
	 * @return int lifetime total steps
	 */
	public int getLifeTimeSteps()
	{
		return lifeTimeSteps;
	}

	public float getBmr() {
		return bmr;
	}

	public void setBmr(float bmr) {
		this.bmr = bmr;
	}

}

