package ca.uwo.csd.cs2212.team01;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;

public class Feedback {

	//Attributes
	public int textCode;
	public int fontSize = 24;
	public int buttonCode = 0;
	public LinkedList<String> feedbackText = new  LinkedList<String>();
	public LinkedList<String> feedbackTextTwo = new  LinkedList<String>();
	public LinkedList<Float> feedbackValue = new LinkedList<Float>();
	//date & time attribute
	
	public Feedback() {}
	
	public void setTXTCode(int code) { this.textCode = code; }
	public void setFontSize(int size) { this.fontSize = size; }
	public void setButtonCode(int code) { this.buttonCode = code; }
	
	public int getTextCode() { return this.textCode; }
	public int getFontSize() {return this.fontSize; }
	public int getButtonCode() { return this.buttonCode; }
	
	public void addTXTone(String text) { this.feedbackText.add(text); }
	public void addTXTtwo(String text) { this.feedbackTextTwo.add(text); }
	public void addFeedbackValue(Float value) { this.feedbackValue.add(value); }
	
	public LinkedList<String> getTXTone() { return feedbackText; }
	public LinkedList<String> getTXTtwo() { return feedbackTextTwo; }
	public LinkedList<Float> getFirstValues() { return feedbackValue; }
}
