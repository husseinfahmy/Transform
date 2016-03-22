package ca.uwo.csd.cs2212.team01;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 * @author team01
 *
 */
public class ButtonActionListener implements ActionListener {
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
			   out.writeObject(mainWindow.getPreferences());
			   //mainWindow.getPreferences().writeObject(out); //(over)writes preferences file
			   out.close();
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
				//this.mainWindow.getAddMealDishScreen().toggleServingUnit();
				break;
			case 1:
				break;
			case 2:
				//this.mainWindow.getAddMealDishScreen().toggleFoodServingUnit();
				break;
			case 3:
				AddMealDishScreen screen = this.mainWindow.getAddMealDishScreen();
				NutritionPanel nutritionPanel = screen.getNutritionPanel();
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
			mealDishScreen.getMyMealsPanel().removeMeal(this.value);
			break;
			
		case 9: // My Dishes Panel: remove dish
			mealDishScreen = this.mainWindow.getMealDishScreen();
			mealDishScreen.getMyDishesPanel().removeDish(this.value);
			break;
			
		case 10: // My Meals Panel: display meal
			mealDishScreen = this.mainWindow.getMealDishScreen();
			mealDishScreen.getDisplayPanel().displayItem(true, mealDishScreen.getMyMealsPanel().getScrollIndex()+this.value);
			break;
			
		case 11: // My Dishes Panel: display dish
			mealDishScreen = this.mainWindow.getMealDishScreen();
			mealDishScreen.getDisplayPanel().displayItem(false, mealDishScreen.getMyDishesPanel().getScrollIndex()+this.value);
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
			System.out.println("nav button");
			switch(this.value) {
			case 0: // Nagivates to Dashboard Screen
				this.mainWindow.setVisible(false);
				this.mainWindow.getContentPane().removeAll();
				this.mainWindow.add(this.mainWindow.getDashboardScreen());
				this.mainWindow.setVisible(true);
				break;
			case 1: // Nagivates to Profile Screen
				this.mainWindow.setVisible(false);
				this.mainWindow.getContentPane().removeAll();
				this.mainWindow.add(this.mainWindow.getDashboardScreen());
				this.mainWindow.setVisible(true);
				break;
			case 2: // Nagivates to Create Meals or Dishes Screen
				this.mainWindow.setVisible(false);
				this.mainWindow.getContentPane().removeAll();
				this.mainWindow.add(this.mainWindow.getAddMealDishScreen());
				this.mainWindow.setVisible(true);
				break;
			case 3: // Quits Program
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
				this.mainWindow.setVisible(true);
				break;
			}
			break;
			
		case 14: // Go to Navigation Screen
			this.mainWindow.setVisible(false);
			this.mainWindow.getContentPane().removeAll();
			this.mainWindow.add(this.mainWindow.getNavigationScreen());
			this.mainWindow.setVisible(true);
			break;
			
		/*case 0: // Submit Current/Target Weights
			switch(this.value) {
			case 1: // Dashboard Screen
				label = this.mainWindow.getSplashScreen().getSetGoalPanel().getDesc();
				
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
				}
				break;*/
				
			/*case 2: // Weigh Screen - Submit New Weight
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
				}finally {
				}
				break;
			}
			break;*/
			
		/*case 1: // Refresh Button
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
			break;*/
			
		/*case 2: // Exit Button
		
			//write user dashboard preferences to file	
			try{
			   FileOutputStream fout = new FileOutputStream("window.dat");
			   ObjectOutputStream out = new ObjectOutputStream(fout);
			   out.writeObject(mainWindow);	//(over)writes dashboard preferences file
			   out.close();
			}catch(FileNotFoundException exception){
			   System.out.println(exception.getMessage());
			}catch(IOException exception){
			   System.out.println(exception.getMessage());
			}
			
			System.exit(0);
			break;*/
			
		/*case 3: // Weigh Screen
			this.mainWindow.setVisible(false);
			this.mainWindow.getContentPane().removeAll();
			this.mainWindow.add(this.mainWindow.getWeighScreen());
			this.mainWindow.setVisible(true);
			break;*/
		}
	}
}
