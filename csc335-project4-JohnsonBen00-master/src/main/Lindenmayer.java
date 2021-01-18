package main;

import gui.LindenmayerView;
import javafx.application.Application;
/**
 * This class simulates the Lindenmayer Systems.
 * With the user's inputs, the program formulates a repeating pattern,
 * resulting a javaFX stage with lines on its canvas representing said patterns.
 * @author Benhur J. Tadiparti
 */
public class Lindenmayer {
	
	/**
	 * Calls/Constructs the appropriate classes to simulate the L-system.
	 * @param args -- Not used
	 */
	public static void main(String[] args) {
		Application.launch(LindenmayerView.class, args);
	}
	
}