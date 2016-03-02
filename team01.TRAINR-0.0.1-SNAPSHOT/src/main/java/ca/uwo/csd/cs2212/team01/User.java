package ca.uwo.csd.cs2212.team01;

import java.util.LinkedList;

public class User {

	//Attributes
	public String name;
	public int age;
	public char gender;
	public float[] height;
	public LinkedList<Journey> myJourneys = new LinkedList<Journey>();
	
	//Constructor
	public User(String name) {this.name = name;}
	
	//Methods
	public void addJourney(Journey journey)
	{
		myJourneys.add(journey);
	}
	public int numberOfJourneys()
	{
		return myJourneys.size();
	}
}
