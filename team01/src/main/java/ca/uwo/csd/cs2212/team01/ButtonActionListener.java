package ca.uwo.csd.cs2212.team01;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 * @author team01
 *
 */
public class ButtonActionListener implements ActionListener, Serializable {
	private static final long serialVersionUID = 1L;
	
	private int btnMode, value;
	private MainWindow mainWindow;
	
	/**
	 * Class Constructor
	 * @param btnMode
	 * @param value
	 * @param mainWindow
	 */
	public ButtonActionListener(int btnMode, int value, MainWindow mainWindow) {
		this.mainWindow = mainWindow;
		this.btnMode = btnMode;
		this.value = value;
	}
	
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent e) {
		String value;
		JLabel label;
		Dimension size;
		float currentWeight, targetWeight;
		
		// TODO Auto-generated method stub
		switch(this.btnMode) {		case 0: // Submit Current/Target Weights
			switch(this.value) {
			case 1: // Dashboard Screen
				/*label = this.mainWindow.getSplashScreen().getSetGoalPanel().getDesc();
				
				try {
					value = this.mainWindow.getSplashScreen().getSetGoalPanel().getCurrentWeight();
					currentWeight = Float.parseFloat(value);
					
					value = this.mainWindow.getSplashScreen().getSetGoalPanel().getTargetWeight();
					targetWeight = Float.parseFloat(value);
					
					if (currentWeight-targetWeight < 2) {
						label.setText("Try to lose at least 2 lbs!");
						size = label.getPreferredSize();
						label.setBounds((503-size.width)/2, label.getY(), size.width, size.height);
						break;
					}else if (currentWeight <= 0 || targetWeight <= 0) throw new NumberFormatException("Negative number!");

					this.mainWindow.setupVirtualTrainer(currentWeight, targetWeight);

					this.mainWindow.setVisible(false);
					this.mainWindow.getContentPane().removeAll();
					this.mainWindow.add(this.mainWindow.getDashboardScreen());
					this.mainWindow.setVisible(true);
				}catch (NumberFormatException ex) {
					label.setText("Invalid format! Enter a positive number.");
					size = label.getPreferredSize();
					label.setBounds((503-size.width)/2, label.getY(), size.width, size.height);
				}finally {
				}*/
				break;
				
			case 2: // Weigh Screen - Submit New Weight
				label = this.mainWindow.getWeighScreen().getSetWeighPanel().getDesc();
				
				try {
					value = this.mainWindow.getWeighScreen().getSetWeighPanel().getCurrentWeight();
					currentWeight = Float.parseFloat(value);
					
					if (currentWeight <= 0) throw new NumberFormatException("Negative number!");
					
					this.mainWindow.getVirtualTrainer().addNewWeightMeasurement(mainWindow.getUser(), currentWeight);
					
					this.mainWindow.updateDashboardScreen();
					
					this.mainWindow.setVisible(false);
					this.mainWindow.getContentPane().removeAll();
					this.mainWindow.add(this.mainWindow.getDashboardScreen());
					this.mainWindow.setVisible(true);
				}catch (NumberFormatException ex) {
					label.setText("Invalid format! Enter a positive number.");
					size = label.getPreferredSize();
					label.setBounds((503-size.width)/2, label.getY(), size.width, size.height);
				}
				break;
			}
			break;
			
		case 1: // Refresh Button
			this.mainWindow.updateLastRefreshed();
			//display "updating app"
			this.mainWindow.refreshEvent();
			//remove that textbox
			// notify user app is updating?
			this.mainWindow.updateDashboardScreen();
			
			//label = mainWindow.getDashboardScreen().getRefreshDesc();
			//label.setText("<html>Last Refreshed:<br>" + mainWindow.lastRefreshed().getTXTone().get(0) + "</html>");
			
			this.mainWindow.setVisible(false);
			this.mainWindow.getContentPane().removeAll();
			this.mainWindow.add(this.mainWindow.getDashboardScreen());
			this.mainWindow.setVisible(true);
			break;
			
		case 2: // Exit Button
			//write user dashboard preferences to file	
			try{
			   FileOutputStream fout = new FileOutputStream("window.dat");
			   ObjectOutputStream out = new ObjectOutputStream(fout);
			   out.writeObject(mainWindow.getPreferences());	//(over)writes dashboard preferences file
			   out.close();
			   fout.close();
			}catch(FileNotFoundException exception){
			   System.out.println(exception.getMessage());
			}catch(IOException exception){
			   System.out.println(exception.getMessage());
			}
			
			System.exit(0);
			break;
			
		case 3: // Weigh Screen
			this.mainWindow.setVisible(false);
			this.mainWindow.getContentPane().removeAll();
			this.mainWindow.add(this.mainWindow.getWeighScreen());
			this.mainWindow.setVisible(true);
			break;
			
		case 4: // Setup Screen
			switch(this.value) {
			case 0: // Next
				this.mainWindow.getSetupScreen().gotoNextPage();
				break;
			case 1: // Back
				this.mainWindow.getSetupScreen().gotoPrevPage();
				break;
			}
			break;
			
		case 5: // Continue Screen
			this.mainWindow.setVisible(false);
			this.mainWindow.getContentPane().removeAll();
			this.mainWindow.add(this.mainWindow.getSetupScreen());
			this.mainWindow.setVisible(true);
			break;
			
		case 6: // Add Meal and Dishes Screen
			switch(this.value) {
			case 0:
				NutritionPanel nutritionPanel = this.mainWindow.getAddMealDishScreen().getNutritionPanel();
				if (nutritionPanel.isMealScreen()) {
					if (nutritionPanel.getServingUnit().equals("Cup"))
						nutritionPanel.setServingUnit("g");
					else nutritionPanel.setServingUnit("Cup");
				}
				break;
			case 1:
				OCR ocr = new OCR();
				
				String[] result = new String[6];
				result[0] = "1";
				result[1] = "OCR method failed";
				
				try
				{
					result = ocr.analyzeImage();
				}
				catch (IOException e1)
				{
					e1.printStackTrace();
				}
				System.out.println(result[0]);
				System.out.println(result[1]);
				System.out.println(result[2]);
				System.out.println(result[3]);
				System.out.println(result[4]);
				System.out.println(result[5]);
				break;
			case 2:
				FoodServingSizePanel foodServingSizePanel = this.mainWindow.getAddMealDishScreen().getServingSizePanel();
				if (foodServingSizePanel.isMealScreen()) {
					if (foodServingSizePanel.getFoodServingUnit().equals("Cup"))
						foodServingSizePanel.setFoodServingUnit("g");
					else foodServingSizePanel.setFoodServingUnit("Cup");
				}
				break;
			case 3:
				AddMealDishScreen screen = this.mainWindow.getAddMealDishScreen();
				nutritionPanel = screen.getNutritionPanel();
				FoodServingSizePanel servingSizePanel = screen.getServingSizePanel();
				MealListPanel mealListPanel = screen.getMealListPanel();
				
				//Clear feedback panel
				servingSizePanel.clearFeedbackLabel();
				
				try {
					String foodName = nutritionPanel.getFoodName(); //taken from text entry field
					float servingSize = Float.parseFloat(nutritionPanel.getServingSize()); //taken from text entry field
					String servingUnit = nutritionPanel.getServingUnit().toLowerCase(); //taken from drop-down menu OR kamal's suggestion: button press to switch back and forth from "g" and "cup"
				
					float calories = Float.parseFloat(nutritionPanel.getCalories());
					float proteins = Float.parseFloat(nutritionPanel.getProteins());
					float carbs = Float.parseFloat(nutritionPanel.getCarbs());
					float fats = Float.parseFloat(nutritionPanel.getFats());
					
					Macro macro = new Macro(calories, proteins, carbs, fats);	//taken from multiple text entry fields
					
					Food food = new Food(foodName,1,servingSize,servingUnit,macro);
					float foodServingSize = Float.parseFloat(servingSizePanel.getFoodServingSize()); //taken from text entry field
					String foodServingUnit = servingSizePanel.getFoodServingUnit().toLowerCase(); //taken from drop-down menu OR kamal's suggestion: button press to switch back and forth from "g" and "cup"
					FoodServing foodServing  = new FoodServing(food,foodServingSize,foodServingUnit);
					
					mealListPanel.addFoodServing(foodServing);
					
					mealListPanel.repaint();
				}catch (NumberFormatException ex) {
					servingSizePanel.setFeedbackLabel("Please fill in all the fields.");
				}
				break;
			case 4:
				screen = this.mainWindow.getAddMealDishScreen();
				Meal meal = screen.getMealListPanel().getMeal();
				meal.setName(screen.getMealListPanel().getNameInput().getText());
				if (screen.isMealScreen()) this.mainWindow.addMeal(meal);
				else this.mainWindow.addDish(meal);
				screen.clearTextFields();
				screen.getMealListPanel().removeList();
				break;
			case 5:
			case 6:
				screen = this.mainWindow.getAddMealDishScreen();
				if (this.value == 5) screen.toggleMealDishScreen(true);
				else if (this.value == 6) screen.toggleMealDishScreen(false);
				break;
			}
			break;
			
		case 7: // Meal List Panel
			AddMealDishScreen addMealDishScreen = this.mainWindow.getAddMealDishScreen();
			MealListPanel mealListPanel = addMealDishScreen.getMealListPanel();
			mealListPanel.removeFoodServing(this.value);
			break;
			
		case 8: // My Meals Panel: remove meal
			MealDishScreen mealDishScreen = this.mainWindow.getMealDishScreen();
			int index = this.mainWindow.getMyMeals().size()-1-(mealDishScreen.getMyMealsPanel().getMyMealsIndex()+this.value);
			if (index == mealDishScreen.getDisplayPanel().getItemIndex() && mealDishScreen.getDisplayPanel().isMealItem()) {
				mealDishScreen.getDisplayPanel().setItemIndex(-1);
				mealDishScreen.getDisplayPanel().repaint();
			}
			mealDishScreen.getMyMealsPanel().removeMeal(index);
			break;
			
		case 9: // My Dishes Panel: remove dish
			mealDishScreen = this.mainWindow.getMealDishScreen();
			index = this.mainWindow.getMyDishes().size()-1-(mealDishScreen.getMyDishesPanel().getMyDishesIndex()+this.value);
			if (index == mealDishScreen.getDisplayPanel().getItemIndex() && !mealDishScreen.getDisplayPanel().isMealItem()) {
				mealDishScreen.getDisplayPanel().setItemIndex(-1);
				mealDishScreen.getDisplayPanel().repaint();
			}
			mealDishScreen.getMyDishesPanel().removeDish(index);
			break;
			
		case 10: // My Meals Panel: display meal
			mealDishScreen = this.mainWindow.getMealDishScreen();
			index = this.mainWindow.getMyMeals().size()-1-(mealDishScreen.getMyMealsPanel().getScrollIndex()+this.value);
			mealDishScreen.getDisplayPanel().displayItem(true, index);
			break;
			
		case 11: // My Dishes Panel: display dish
			mealDishScreen = this.mainWindow.getMealDishScreen();
			index = this.mainWindow.getMyDishes().size()-1-(mealDishScreen.getMyDishesPanel().getScrollIndex()+this.value);
			mealDishScreen.getDisplayPanel().displayItem(false, index);
			break;
			
		case 12: // My Meals & Dishes Panel
			switch(this.value) {
			case 0: // "Create New Meal" Button
				addMealDishScreen = this.mainWindow.getAddMealDishScreen();
				addMealDishScreen.toggleMealDishScreen(true);
				
				this.mainWindow.setVisible(false);
				this.mainWindow.getContentPane().removeAll();
				this.mainWindow.add(addMealDishScreen);
				this.mainWindow.setVisible(true);
				break;
			case 1: // "Create New Dish" Button
				addMealDishScreen = this.mainWindow.getAddMealDishScreen();
				addMealDishScreen.toggleMealDishScreen(false);
				
				this.mainWindow.setVisible(false);
				this.mainWindow.getContentPane().removeAll();
				this.mainWindow.add(addMealDishScreen);
				this.mainWindow.setVisible(true);
				break;
			}
			break;
			
		case 13: // Navigation Screen
			switch(this.value) {
			case 0: // Nagivates to Dashboard Screen
				this.mainWindow.setDashboardScreen(new DashboardScreen(mainWindow));
				this.mainWindow.setVisible(false);
				this.mainWindow.getContentPane().removeAll();
				this.mainWindow.add(this.mainWindow.getDashboardScreen());
				this.mainWindow.setVisible(true);
				break;
			case 1: // Nagivates to Profile Screen
				this.mainWindow.setProfileScreen(new ProfileScreen(mainWindow));
				this.mainWindow.setVisible(false);
				this.mainWindow.getContentPane().removeAll();
				this.mainWindow.add(this.mainWindow.getProfileScreen());
				this.mainWindow.setVisible(true);
				break;
			case 2: // Nagivates to Create Meals or Dishes Screen
				this.mainWindow.setVisible(false);
				this.mainWindow.getContentPane().removeAll();
				this.mainWindow.add(this.mainWindow.getAddMealDishScreen());
				this.mainWindow.setVisible(true);
				break;
			case 3: // Quits Program
				//write user dashboard preferences to file	
				try{
				   FileOutputStream fout = new FileOutputStream("window.dat");
				   ObjectOutputStream out = new ObjectOutputStream(fout);
				   out.writeObject(mainWindow.getPreferences());	//(over)writes dashboard preferences file
				   out.close();
				   fout.close();
				}catch(FileNotFoundException exception){
				   System.out.println(exception.getMessage());
				}catch(IOException exception){
				   System.out.println(exception.getMessage());
				}
				
				System.exit(0);
				break;
			case 4: // Nagivates to My Meals & Dishes Screen
				this.mainWindow.setVisible(false);
				this.mainWindow.getContentPane().removeAll();
				this.mainWindow.add(this.mainWindow.getMealDishScreen());
				this.mainWindow.setVisible(true);
				break;
			case 5: // Nagivates to Plans Screen
				this.mainWindow.setVisible(false);
				this.mainWindow.getContentPane().removeAll();
				this.mainWindow.add(this.mainWindow.getMyPlansScreen());
				this.mainWindow.setVisible(true);
				break;
			}
			break;
			
		case 14: // Go to Navigation Screen
			this.mainWindow.getPreferences().setTutorialMode(false);
			this.mainWindow.setVisible(false);
			this.mainWindow.getContentPane().removeAll();
			this.mainWindow.add(this.mainWindow.getNavigationScreen());
			this.mainWindow.setVisible(true);
			break;
			
		case 15: 
			switch(this.value) {
			case 0: // My Plans Screen: previous week
				this.mainWindow.getMyPlansScreen().prevWeek();
				break;
			case 1: // My Plans Screen: next week
				this.mainWindow.getMyPlansScreen().nextWeek();
				break;
			case 2: // Plan Manager Screen: meals
				PlanManagerScreen planManagerScreen = this.mainWindow.getPlanManagerScreen();
				planManagerScreen.toggleMealWorkoutScreen(0);
				break;
			case 3: // Plan Manager Screen: dishes
				planManagerScreen = this.mainWindow.getPlanManagerScreen();
				planManagerScreen.toggleMealWorkoutScreen(1);
				break;
			case 4: // Plan Manager Screen: add workout
				planManagerScreen = this.mainWindow.getPlanManagerScreen();
				planManagerScreen.addWorkout();
				break;
			case 5: // Plan Manager Screen: workouts
				planManagerScreen = this.mainWindow.getPlanManagerScreen();
				planManagerScreen.toggleMealWorkoutScreen(2);
				break;
			case 6: // Plan Manager Screen: remove dish from meal
				planManagerScreen = this.mainWindow.getPlanManagerScreen();
				planManagerScreen.addDishesMeal();
				break;
			}
			break;
			
		case 16: // My Plans Screen: Edit Plan
			MyPlansScreen myPlansScreen = this.mainWindow.getMyPlansScreen();
			PlanManagerScreen planManagerScreen = this.mainWindow.getPlanManagerScreen();
			Day day = null;
			
			if (myPlansScreen.getWeekIndex() == 0) {
				if (this.value == 0) day = this.mainWindow.getDays().getLast();
				else day = this.mainWindow.getFutureDays().get(this.value+7*myPlansScreen.getWeekIndex()-1);
			}else day = this.mainWindow.getFutureDays().get(this.value+7*myPlansScreen.getWeekIndex()-1);
			
			if (day.getPlan() != null) {
				planManagerScreen.setDay(day, 0);
				this.mainWindow.setVisible(false);
				this.mainWindow.getContentPane().removeAll();
				this.mainWindow.add(planManagerScreen);
				this.mainWindow.setVisible(true);
			}
			break;
			
		case 17: // My Plans Screen: Create New Plan/Edit Plan Meals
			myPlansScreen = this.mainWindow.getMyPlansScreen();
			planManagerScreen = this.mainWindow.getPlanManagerScreen();
			day = null;
			
			if (myPlansScreen.getWeekIndex() == 0) {
				if (this.value == 0) day = this.mainWindow.getDays().getLast();
				else day = this.mainWindow.getFutureDays().get(this.value+7*myPlansScreen.getWeekIndex()-1);
			}else day = this.mainWindow.getFutureDays().get(this.value+7*myPlansScreen.getWeekIndex()-1);
			
			if (day.getPlan() != null) {
				planManagerScreen.setDay(day, 0);
				this.mainWindow.setVisible(false);
				this.mainWindow.getContentPane().removeAll();
				this.mainWindow.add(planManagerScreen);
				this.mainWindow.setVisible(true);
			}else {
				day.setPlan(new Plan(mainWindow.getUser().bmr));
				myPlansScreen.repaint();
			}
			break;
			
		case 18: // My Plans Screen: Edit Plan Workouts
			myPlansScreen = this.mainWindow.getMyPlansScreen();
			planManagerScreen = this.mainWindow.getPlanManagerScreen();
			day = null;
			
			if (myPlansScreen.getWeekIndex() == 0) {
				if (this.value == 0) day = this.mainWindow.getDays().getLast();
				else day = this.mainWindow.getFutureDays().get(this.value+7*myPlansScreen.getWeekIndex()-1);
			}else day = this.mainWindow.getFutureDays().get(this.value+7*myPlansScreen.getWeekIndex()-1);
			
			if (day.getPlan() != null) {
				planManagerScreen.setDay(day, 2);
				this.mainWindow.setVisible(false);
				this.mainWindow.getContentPane().removeAll();
				this.mainWindow.add(planManagerScreen);
				this.mainWindow.setVisible(true);
			}else myPlansScreen.repaint();
			break;
			
		case 19: // My Plans Screen: Copy From Previous Plan
			myPlansScreen = this.mainWindow.getMyPlansScreen();
			Day prevDay = null;
			day = null;
			
			if (myPlansScreen.getWeekIndex() == 0) {
				if (this.value == 0) {
					day = this.mainWindow.getDays().getLast();
					prevDay = this.mainWindow.getDays().get(this.mainWindow.getDays().size()-2);
				}
				else {
					day = this.mainWindow.getFutureDays().get(this.value+7*myPlansScreen.getWeekIndex()-1);
					if (this.value == 1) prevDay = this.mainWindow.getDays().getLast();
					else prevDay = this.mainWindow.getFutureDays().get(this.value+7*myPlansScreen.getWeekIndex()-1-1);
				}
			}else {
				day = this.mainWindow.getFutureDays().get(this.value+7*myPlansScreen.getWeekIndex()-1);
				prevDay = this.mainWindow.getFutureDays().get(this.value+7*myPlansScreen.getWeekIndex()-1-1);
			}
			
			Plan newPlan = prevDay.getPlan().copyPlan(mainWindow.getUser().bmr);
			day.setPlan(newPlan);
			
			myPlansScreen.repaint();
			break;
			
		case 20: // Plan Manager Screen: remove meal
			planManagerScreen = this.mainWindow.getPlanManagerScreen();
			index = planManagerScreen.getMyDayMealsIndex()+this.value;
			planManagerScreen.removeMeal(index);
			break;
			
		case 21: // Plan Manager Screen: remove workout
			planManagerScreen = this.mainWindow.getPlanManagerScreen();
			index = planManagerScreen.getMyDayWorkoutsIndex()+this.value;
			planManagerScreen.removeWorkout(index);
			break;
			
		case 22: // Plan Manager Screen: add meal to day plan
			planManagerScreen = this.mainWindow.getPlanManagerScreen();
			index = planManagerScreen.getMyMealsIndex(0)+this.value;
			planManagerScreen.addMeal(this.mainWindow.getMyMeals().get(index));
			break;
			
		case 23: // Plan Manager Screen: add dish to meal
			planManagerScreen = this.mainWindow.getPlanManagerScreen();
			index = planManagerScreen.getMyMealsIndex(0)+this.value;
			planManagerScreen.addDish(this.mainWindow.getMyDishes().get(index));
			break;
			
		case 24: // Plan Manager Screen: remove dish from meal
			planManagerScreen = this.mainWindow.getPlanManagerScreen();
			index = planManagerScreen.getMyMealsIndex(1)+this.value;
			planManagerScreen.removeDish(index);
			break;
			
		case 25: // Profile Screen: toggle settings/profile screens
			switch(this.value) {
			case 0:
				ProfileScreen profileScreen = this.mainWindow.getProfileScreen();
				profileScreen.toggleScreen();
				break;
			}
			break;
			
		case 26: // Profile Screen: toggle dashboard panels
		case 27: // Profile Screen: activity tracking panels
		case 28: // Profile Screen: lifetime totals
			ProfileScreen profileScreen = this.mainWindow.getProfileScreen();
			profileScreen.toggleToggles(this.btnMode, this.value);
			break;
			
		case 29: // Dashboard: Navigate Activity Panel Days
			switch(this.value) {
			case 0:
				DashboardScreen dashboardScreen = this.mainWindow.getDashboardScreen();

				dashboardScreen.setActivityPanelPrevDay();
				index = dashboardScreen.getActivityPanelDayIndex();
				
				day = this.mainWindow.getDays().get(index);
				
				dashboardScreen.setActivityPanelDay(day);

				dashboardScreen.removeAll();
				dashboardScreen.initUI();
				dashboardScreen.repaint();
				break;
			case 1:
				dashboardScreen = this.mainWindow.getDashboardScreen();

				dashboardScreen.setActivityPanelNextDay();
				index = dashboardScreen.getActivityPanelDayIndex();
				
				day = this.mainWindow.getDays().get(index);
				
				dashboardScreen.setActivityPanelDay(day);
				
				dashboardScreen.removeAll();
				dashboardScreen.initUI();
				dashboardScreen.repaint();
				break;
			}
			break;
		}
	}
}
