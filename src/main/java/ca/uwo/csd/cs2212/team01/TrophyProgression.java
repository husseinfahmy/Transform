package ca.uwo.csd.cs2212.team01;
import java.io.Serializable;
/*
 * This is a class representing TrophyProgression
*/
import java.util.*;



public class TrophyProgression implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//fields
	private int currentStreak;
	private int lifeTimeStreak;
	private int numberOfStreaks;
	
	//threshold for minimum calorie difference to be considered a successful day
	final int THRESHOLD = -500;
	
	/**
	 * TrophyProgression constructor
	 */
	public TrophyProgression()
	{
		this.currentStreak = 0;
		this.lifeTimeStreak = 0;
		this.numberOfStreaks = 0;
		
	}
	
	/**
	 * setter method to set the current streak of consecutive days of reaching the goal calorie deficit
	 * @param days a LinkedList of Day objects that store the data for each day
	 */
	public void setCurrentStreak(LinkedList<Day> days)
	{
		int count = 0;
		
		//start from the last complete day
		//continue backwards in the list until a day didn't reach goal deficit or until all of the days have been considered
		while((days.size() - 2 - count) >= 0 && days.get(days.size() - 2 - count).getDailyCalDiff() <= THRESHOLD)
		{
			count++;
		}
		
		this.currentStreak = count;
		if(currentStreak > 0)
			numberOfStreaks++;
	}
	
	/**
	 * setter method to set the best lifetime streak of consecutive days of reaching the goal calorie deficit
	 * @param days a LinkedList of Day objects that store the data for each day
	 * @return boolean true if a new lifetime best was reached, false otherwise
	 */
	public boolean setLifeTimeStreak(LinkedList<Day> days)
	{
		int largestStreak = 0;
		int currentStreak = 0;
		
		//go through all of the days, not including the current day
		for(int i = 0; i < days.size() - 1; i++)
		{
			//add to current streak if the current day meets the deficit threshold
			if(days.get(i).getDailyCalDiff() <= THRESHOLD)
			{
				currentStreak++;
			}
			//make the current streak zero if the current day doesn't meet the deficit threshold
			else
			{
				currentStreak = 0;
			}
			
			//set the largest streak equal to the current streak if the current streak surpasses the largest streak
			if(currentStreak > largestStreak)
			{
				largestStreak = currentStreak;
			}
			
		}
		
		if(largestStreak > this.lifeTimeStreak)
		{
			this.lifeTimeStreak = largestStreak;
			return true;
		}
		else
		{
			return false;
		}
	}
	
	/**
	 * accessor method to get the current streak
	 * @return int current streak of days
	 */
	public int getCurrentStreak()
	{
		return currentStreak;
	}

	/**
	 * accessor method to get the best lifetime streak
	 * @return int best lifetime streak of days
	 */
	public int getLifeTimeStreak()
	{
		return lifeTimeStreak;
	}
	
	public static void main(String args[])
	{
		TrophyProgression trophy = new TrophyProgression();
		LinkedList<Day> theDays = new LinkedList<Day>();
		for(int i = 1; i <= 10; i++){
			Day day = new Day();
			day.setDailyCalDiff(-610);
			theDays.add(day);
			
		}
		trophy.setCurrentStreak(theDays);
		trophy.setLifeTimeStreak(theDays);
		System.out.println("Current Streak: " + trophy.getCurrentStreak() + "\n Current Streak #: " + trophy.getNumberOfStreaks());
		System.out.println("Lifetime Streak: " + trophy.getLifeTimeStreak());
		for(int i = 1; i <= 10; i++){
			Day day = new Day();
			day.setDailyCalDiff(-5);
			theDays.add(day);
		}
		trophy.setCurrentStreak(theDays);
		if (trophy.setLifeTimeStreak(theDays))
		{
			System.out.println("True");
		}
		System.out.println("Current Streak: " + trophy.getCurrentStreak() + "\n Current Streak #: " + trophy.getNumberOfStreaks());
		System.out.println("Lifetime Streak: " + trophy.getLifeTimeStreak());
		for(int i = 1; i <= 25; i++){
			Day day = new Day();
			day.setDailyCalDiff(-510);
			theDays.add(day);
		}
			
		trophy.setCurrentStreak(theDays);
		if (trophy.setLifeTimeStreak(theDays))
		{
			System.out.println("True");
		}
		System.out.println("Current Streak: " + trophy.getCurrentStreak() + "\n Current Streak #: " + trophy.getNumberOfStreaks());
		System.out.println("Lifetime Streak: " + trophy.getLifeTimeStreak());
		for(int i = 1; i <= 8; i++){
			Day day = new Day();
			day.setDailyCalDiff(-5);
			theDays.add(day);
		}
			
		trophy.setCurrentStreak(theDays);
		if (trophy.setLifeTimeStreak(theDays))
		{
			System.out.println("True");
		}
		System.out.println("Current Streak: " + trophy.getCurrentStreak() + "\n Current Streak #: " + trophy.getNumberOfStreaks());
		System.out.println("Lifetime Streak: " + trophy.getLifeTimeStreak());
		
	}

	public int getNumberOfStreaks() {
		return numberOfStreaks;
	}

	public void setNumberOfStreaks(int numberOfStreaks) {
		this.numberOfStreaks = numberOfStreaks;
	}

	
}
