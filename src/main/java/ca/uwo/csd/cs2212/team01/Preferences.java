/**
 * 
 */
package ca.uwo.csd.cs2212.team01;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;

public class Preferences implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/*private boolean showCaloriesBurned;
	private boolean showTotalDistance;
	private boolean showFloorsClimbed;
	private boolean showStepsTaken;
	private boolean showActiveMinutes;
	private boolean showSedentaryMinutes;
	private boolean showTrainR;*/
	
	//Time & Date Stamps:
	private Date lastCall;
	private Boolean firstCall = true;

	//User & Virtual Trainer Storage:
	private User user;	//srlze
	private VirtualTrainer vt;

	//Data Storage:
	private LinkedList<Day> days = new LinkedList<Day>();
	private LinkedList<Day> futureDays = new LinkedList<Day>();
	
	private LinkedList<Meal> myMeals = new LinkedList<Meal>();
	private LinkedList<Meal> myDishes = new LinkedList<Meal>();

	public Preferences(){
		/*boolean showCaloriesBurned = true;
		boolean showTotalDistance = true;
		boolean showFloorsClimbed = true;
		boolean showStepsTaken = true;
		boolean showActiveMinutes = true;
		boolean showSedentaryMinutes = true;
		boolean showTrainR = true;*/
	}

	/*public boolean isShowCaloriesBurned() {
		return showCaloriesBurned;
	}


	public void setShowCaloriesBurned(boolean showCaloriesBurned) {
		this.showCaloriesBurned = showCaloriesBurned;
	}


	public boolean isShowTotalDistance() {
		return showTotalDistance;
	}


	public void setShowTotalDistance(boolean showTotalDistance) {
		this.showTotalDistance = showTotalDistance;
	}


	public boolean isShowFloorsClimbed() {
		return showFloorsClimbed;
	}


	public void setShowFloorsClimbed(boolean showFloorsClimbed) {
		this.showFloorsClimbed = showFloorsClimbed;
	}


	public boolean isShowStepsTaken() {
		return showStepsTaken;
	}


	public void setShowStepsTaken(boolean showStepsTaken) {
		this.showStepsTaken = showStepsTaken;
	}


	public boolean isShowActiveMinutes() {
		return showActiveMinutes;
	}


	public void setShowActiveMinutes(boolean showActiveMinutes) {
		this.showActiveMinutes = showActiveMinutes;
	}


	public boolean isShowSedentaryMinutes() {
		return showSedentaryMinutes;
	}


	public void setShowSedentaryMinutes(boolean showSedentaryMinutes) {
		this.showSedentaryMinutes = showSedentaryMinutes;
	}


	public boolean isShowTrainR() {
		return showTrainR;
	}


	public void setShowTrainR(boolean showTrainR) {
		this.showTrainR = showTrainR;
	}*/


	public Date getLastCall() {
		return lastCall;
	}


	public void setLastCall(Date lastCall) {
		this.lastCall = lastCall;
	}


	public VirtualTrainer getVt() {
		return vt;
	}


	public void setVt(VirtualTrainer vt) {
		this.vt = vt;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public Boolean getFirstCall() {
		return firstCall;
	}


	public void setFirstCall(Boolean firstCall) {
		this.firstCall = firstCall;
	}


	public LinkedList<Day> getDays() {
		return days;
	}


	public void setDays(LinkedList<Day> days) {
		this.days = days;
	}


	public LinkedList<Day> getFutureDays() {
		return futureDays;
	}


	public void setFutureDays(LinkedList<Day> futureDays) {
		this.futureDays = futureDays;
	}


	public LinkedList<Meal> getMyMeals() {
		return myMeals;
	}


	public void setMyMeals(LinkedList<Meal> myMeals) {
		this.myMeals = myMeals;
	}


	public LinkedList<Meal> getMyDishes() {
		return myDishes;
	}


	public void setMyDishes(LinkedList<Meal> myDishes) {
		this.myDishes = myDishes;
	}
}