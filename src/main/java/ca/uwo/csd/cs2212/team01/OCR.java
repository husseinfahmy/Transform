package ca.uwo.csd.cs2212.team01;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.IIOException;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;


public class OCR {
		
		public OCR() {}
	
		public String[] analyzeImage() throws IOException{
			
			//RETURN THIS
			String[] analyzed = new String[6];
			//
			
			String text = null;//holds the data from the picture file 
			File img = null; 
			BufferedImage nutritionLabel;
			
			JFileChooser chooser = new JFileChooser();
			FileNameExtensionFilter filter = new FileNameExtensionFilter(
			    "JPG & GIF Images", "jpg", "gif");
			chooser.setFileFilter(filter);
			 int returnVal = chooser.showOpenDialog(null);
			    if(returnVal == JFileChooser.APPROVE_OPTION) {
			           img = chooser.getSelectedFile();
			    }

			    try {
			    
			        nutritionLabel = ImageIO.read(img); 
			
						if (nutritionLabel != null){
						//Declares a Tesseract object to run OCR 
						Tesseract tesseract = new Tesseract();

							try {
								//runs OCR on the image and saves it to a sting
								String result = tesseract.doOCR(nutritionLabel);//on image
								String calories = "";
								String servingSize = "";
								String protein = "";
								String fat = "";
								String carbs = "";
								Boolean totalFat = false;
								Boolean serving = false;

								for (int i = 0; i < result.length(); i++){
								    if(result.charAt(i) == 'P' && result.charAt(i+1) == 'e' && result.charAt(i+2) == 'r' && !serving){
								    	
								    	for(int x = 3; x < 25; x++ ){
								    		if(result.charAt(i+x) == '('){
								    			x++;
								    			while (result.charAt(i+x) != ')'){
								    				servingSize = servingSize + result.charAt(i+x);
								    				x++;
								    			}
								    		}
								    		}
								    		if(servingSize == ""){
								    			int count = 0;
								    			int x =3;
								    			while(result.charAt(i+x) != 'g' && (result.charAt(i+x) != 'm' && result.charAt(i+x + 1) !='L') && count < 9){
								    				servingSize = servingSize + result.charAt(i+x);
								    				x++;
								    				count++;
								    			}
								    			if (count > 9){
								    				servingSize = "";
								    			}
								    			else if(result.charAt(i+x) == 'g'){
								    				servingSize = servingSize + result.charAt(i+x);
								    			}
								    			else if(result.charAt(i+x) == 'm' && result.charAt(i+x+1) == 'L'){
								    				servingSize = servingSize + "mL";
								    			}

								    		}

								    	serving = true;
								    	i = i + 10;
								    	
								    }
								    else if(result.charAt(i) == 'S' && result.charAt(i+1) == 'e' && result.charAt(i+2) == 'r' && result.charAt(i + 3) == 'v' && result.charAt(i+4) == 'i' && result.charAt(i+5) == 'n'&& result.charAt(i+6) == 'g' && result.charAt(i+7) == ' ' && result.charAt(i+8) == 'S' && result.charAt(i + 9) == 'i' && result.charAt(i+10) == 'z' && result.charAt(i+11) == 'e'){
	
									    	for(int x = 11; x < 25; x++ ){
									    		if(result.charAt(i+x) == '('){
									    			x++;
									    			while (result.charAt(i+x) != ')'){
									    				servingSize = servingSize + result.charAt(i+x);
									    				x++;
									    			}
									    		}
									    		}
									    		if(servingSize == ""){
									    			int count = 0;
									    			int x =3;
									    			while(result.charAt(i+x) != 'g' && (result.charAt(i+x) != 'm' && result.charAt(i+x + 1) !='L') && count < 9){
									    				servingSize = servingSize + result.charAt(i+x);
									    				x++;
									    				count++;
									    			}
									    			if (count > 9){
									    				servingSize = "";
									    			}
									    			else if(result.charAt(i+x) == 'g'){
									    				servingSize = servingSize + result.charAt(i+x);
									    			}
									    			else if(result.charAt(i+x) == 'm' && result.charAt(i+x+1) == 'L'){
									    				servingSize = servingSize + "mL";
									    			}

									    		}

									    		serving = true;
									    	i = i + 15;
									    	
								    }
								    else if((result.charAt(i) == 'C' || result.charAt(i) == 'c') && result.charAt(i+1) == 'a' && result.charAt(i+2) == 'l' && result.charAt(i+3) == 'o' && result.charAt(i+4) == 'r' && result.charAt(i+5) == 'i'&& result.charAt(i+6) == 'e'&& result.charAt(i+7) == 's'){
								    	int x = 8;
								    	while(result.charAt(i+x) != ' ')
								    	{
								    		x++;
								    	}
								    	while(result.charAt(i+x) >= '0' && result.charAt(i+x) <= '9' || result.charAt(i+x) == ' '){
								    		calories = calories + result.charAt(i+x);
								    		x++;
								    	}
								    	i = i +x;
								    	
								    
								}
								    else if(result.charAt(i) == 'P' && result.charAt(i+1) == 'r' && result.charAt(i+2) == 'o' && result.charAt(i+3) == 't' && result.charAt(i+4) == 'e' && result.charAt(i+5) == 'i'&& result.charAt(i+6) == 'n'){
								    	int x = 7;
								    	while(result.charAt(i+x) != ' ' || (result.charAt(i+x) == ' ' && result.charAt(i+x+1) == '/' && result.charAt(i+x+2) == ' ')){ 
								    	
								    		if((result.charAt(i+x) == ' ' && result.charAt(i+x+1) == '/' && result.charAt(i+x+2) == ' ')){
								    			x = x +3;
								    		}
								    		x++;
								    	}
								    	Boolean spaceAfterNumber = false;
								    	while(result.charAt(i+x) >= '0' && result.charAt(i+x) <= '9' || (result.charAt(i+x) == ' ' && !spaceAfterNumber)){
								    		if(result.charAt(i+x) >= '0' && result.charAt(i+x) <= '9'){
								    			spaceAfterNumber = true;
								    		}
								    		protein = protein + result.charAt(i+x);
								    		x++;
								    	}
								    	protein = protein + " g";
								    	i = i +x;
								    	
								    
								}
								    else if(result.charAt(i) == 'C' && result.charAt(i+1) == 'a' && result.charAt(i+2) == 'r' && result.charAt(i+3) == 'b' && result.charAt(i+4) == 'o' && result.charAt(i+5) == 'h' && result.charAt(i+6) == 'y'&& result.charAt(i+7) == 'd' && result.charAt(i+8) == 'r' && result.charAt(i+9) == 'a' && result.charAt(i+10) == 't'&& result.charAt(i+11) == 'e'){
								    	int x = 12;
								    	while(result.charAt(i+x) != ' ' || (result.charAt(i+x) == ' ' && result.charAt(i+x+1) == '/' && result.charAt(i+x+2) == ' ')){ 
								    	
								    		if((result.charAt(i+x) == ' ' && result.charAt(i+x+1) == '/' && result.charAt(i+x+2) == ' ')){
								    			x = x +3;
								    		}
								    		x++;
								    	}
								    	Boolean spaceAfterNumber = false;
								    	while(result.charAt(i+x) >= '0' && result.charAt(i+x) <= '9' || (result.charAt(i+x) == ' ' && !spaceAfterNumber)){
								    		if(result.charAt(i+x) >= '0' && result.charAt(i+x) <= '9'){
								    			spaceAfterNumber = true;
								    		}
								    		carbs = carbs + result.charAt(i+x);
								    		x++;
								    	}
								    	carbs = carbs + " g";
								    	i = i +x;
								    	
								    
								}
								    else if(result.charAt(i) == 'F' && result.charAt(i+1) == 'a' && result.charAt(i+2) == 't' && !totalFat){
								    	int x = 3;
								    	while(result.charAt(i+x) != ' ')
								    	{
								    		x++;
								    	}
								    	while(result.charAt(i+x) >= '0' && result.charAt(i+x) <= '9' || result.charAt(i+x) == '.' || result.charAt(i+x) == ' '){
								    		if(result.charAt(i+x-1) >= '0' && result.charAt(i+x-1) <= '9' && result.charAt(i+x+1) >= '0' && result.charAt(i+x+1) <= '9' && result.charAt(i+x) >= ' '){
								    			fat = fat + ".";
									    		x++;
								    		}
								    		fat = fat + result.charAt(i+x);
								    		x++;
								    	}
								    	if(fat.charAt(0) >= '0' && fat.charAt(0) <= '9'&& fat.charAt(1) >= ' ' && fat.charAt(2) >= '0' && fat.charAt(0) <= '9'){
								    		String finalFat = "";
								    		finalFat = finalFat + fat.charAt(0);
								    		finalFat = finalFat + '.';
								    		for(int y = 2; y < fat.length(); y++){
								    			finalFat = finalFat + fat.charAt(y);
								    		}
								    		fat = finalFat;
								    	}
								    	fat = fat + " g";
								    	i = i +x;
								    
								    
								}
								    else if(result.charAt(i) == 'T' && result.charAt(i+1) == 'o' && result.charAt(i+2) == 't' && result.charAt(i + 3) == 'a' && result.charAt(i+4) == 'l' && result.charAt(i+5) == ' '&& result.charAt(i+6) == 'F' && result.charAt(i+7) == 'a' && result.charAt(i+8) == 't'){
								    	totalFat = true;
								    	fat = "";
								    	int x = 9;
								    	while(result.charAt(i+x) != ' ')
								    	{
								    		x++;
								    	}
								    	while(result.charAt(i+x) >= '0' && result.charAt(i+x) <= '9' || result.charAt(i+x) == '.' || result.charAt(i+x) == ' '){
								    		if(result.charAt(i+x-1) >= '0' && result.charAt(i+x-1) <= '9' && result.charAt(i+x+1) >= '0' && result.charAt(i+x+1) <= '9' && result.charAt(i+x) >= ' '){
								    			fat = fat + ".";
									    		x++;
								    		}
								    		fat = fat + result.charAt(i+x);
								    		x++;
								    	}
								    	if(fat.charAt(0) >= '0' && fat.charAt(0) <= '9'&& fat.charAt(1) >= ' ' && fat.charAt(2) >= '0' && fat.charAt(0) <= '9'){
								    		String finalFat = "";
								    		finalFat = finalFat + fat.charAt(0);
								    		finalFat = finalFat + '.';
								    		for(int y = 2; y < fat.length(); y++){
								    			finalFat = finalFat + fat.charAt(y);
								    		}
								    		fat = finalFat;
								    	}
								    	fat = fat + " g";
								    	i = i +x;
								    	
								    
								}
								
								    }
								if(calories == "" || servingSize == "" ||protein == "" || fat == "" || carbs == "")
								{
									analyzed[0]="1";		// "1" means an error occured
									analyzed[1] = "Error occured while reading nutrition facts, please enter them in manualy";
									return analyzed;
								}
								else
								{
									
									analyzed[0]="0";		// "0" means success 
									analyzed[1]=servingSize;
									analyzed[2]=calories;
									analyzed[3]=carbs;
									analyzed[4]=protein;
									analyzed[5]=fat;
								
									return analyzed;
								}

							} catch (TesseractException e) 	//catches any exception in the OCR
							{
								analyzed[0]="1";			// "1" means an error occured
								analyzed[1] = "Please choose a differen image.";
								return analyzed;
							}
						}
						else
						{
							analyzed[0]="1";				// "1" means an error occured
							analyzed[1] = "Unsupported file chosen!";
							return analyzed;
						}
						
		 } 
			    catch(IllegalArgumentException e)
			    {
			    	analyzed[0]="1";						// "1" means an error occured
					analyzed[1] = "No file was chosen!";
					return analyzed;
			    }
			 catch(IIOException e)
			    {
				 	analyzed[0]="1";						// "1" means an error occured
					analyzed[1] = "Incompatible file format!";
					return analyzed;
			    }
			    catch (IOException e) 
			    { 
			    	e.printStackTrace(); 
			    	analyzed[0]="1";						// "1" means an error occured
			    	analyzed[1]="An error occured. Please try again.";
			    	return analyzed;
			    }
		}
		//close file
			

	}



