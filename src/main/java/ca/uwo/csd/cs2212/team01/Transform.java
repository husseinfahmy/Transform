/**
 * 
 */
package ca.uwo.csd.cs2212.team01;

// Log4J logging - Josh
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.LinkedList;

import javax.swing.SwingUtilities;

/**
 * @author team01
 *
 */
public class Transform {
	static Logger logger = LogManager.getLogger (Transform.class.getName());
	private static boolean testMode;
	private static MainWindow window;
	
    /**
     * Runs the Transform program
     * @param args
     */
    public static void main(String[] args) {
		logger.trace("Entering main");
		logger.warn("Hello Maven/log4j World");
		logger.info("My username is team01");
		
		if (args.length > 0 && args[0].equals("test")) testMode = true;
		else testMode = false;
		
		SwingUtilities.invokeLater(new Runnable() {
        	//@Override
        	public void run() {
        		window = new MainWindow(testMode);
        		
        		/*try {
	    			ObjectInputStream in = new ObjectInputStream(new FileInputStream("window.dat"));
	    			window = (MainWindow) in.readObject();
	    			in.close();
    			}catch(FileNotFoundException e){
    				System.out.println("No previous data found. Welcome, new user!");
    				window = new MainWindow(testMode);
    			}catch(IOException e){
    				System.out.println(e.getMessage());
    			}catch(ClassNotFoundException e){
    				System.out.println(e.getMessage());
    			}*/
    			window.setVisible(true);
        	}
		});
		
		logger.trace("Exiting main");
	}
}
