package ca.uwo.csd.cs2212.team01;

import java.io.Serializable;
import java.util.LinkedList;

/**
 * @author team01
 *
 */
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	//Attributes
	private String name;
	private int age;
	private char gender;
	private float height;
	private LinkedList<Journey> myJourneys = new LinkedList<Journey>();
	private double bestDistance;
	private int bestFloors;
	private int bestSteps;
	
	//Constructor
	/**
	 * @param name
	 * @param age
	 * @param gender
	 * @param height
	 */
	public User(String name, int age, char gender, float height) {
		this.name = name;
		this.setAge(age);
		this.setGender(gender);
		this.setHeight(height);
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

	public char getGender() {
		return gender;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}
}