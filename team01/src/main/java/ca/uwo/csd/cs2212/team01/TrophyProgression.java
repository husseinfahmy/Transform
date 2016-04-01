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
	private int lifeTimeStreak=0;
	
	//threshold for minimum calorie difference to be considered a successful day
	final int THRESHOLD = -500;
	
	/**
	 * TrophyProgression constructor
	 */
	public TrophyProgression()
	{
		this.currentStreak = 0;
		this.lifeTimeStreak = 0;
	}
	
	/**
	 * updates user's streak progression
	 * @param days a LinkedList of Day objects that store the data for each day
	 * @return int[]. int[0] = current streak number. int[1] = 
	 */
	public int[] updateStreaks(LinkedList<Day> days)
	{
		int[] result = new int[2];
		int count = 0;
		
		//start from the last complete day
		//continue backwards in the list until a day didn't reach goal deficit or until all of the days have been considered
		while((days.size() - 2 - count) >= 0 && days.get(days.size() - 2 - count).getDailyCalDiff() <= THRESHOLD)
		{
			count++;
		}
		this.currentStreak = count;
		result[0] = count;				//return current streak
		if(count>lifeTimeStreak)
		{	
			lifeTimeStreak=count; result[1] = 1; return result;		//user has broken personal record. 1 = true
		}
		else { result[1] = 0; } //user has NOT broken personal record. 0 = false
		return result;
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
	




	
}
