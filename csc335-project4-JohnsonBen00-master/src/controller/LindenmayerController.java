package controller;

import model.LindenmayerModel;
/**
 * This class interacts with the user's guess and compute an L-system string.
 * @author Benhur J. Tadiparti
 *
 */
public class LindenmayerController {
	
	private LindenmayerModel model;
	
	/**
	 * Initializes the fields required.
	 * @param model -- the class that stores the users' inputs
	 */
	public LindenmayerController(LindenmayerModel model) {
		this.model = model;
	}
	
	/**
	 * Computes the final L-system string recursively, and stores said string in the model.
	 * @param ls -- the string being modified
	 * @param iterator -- the iterations the function is currently on
	 */
	public void getFinalString(String ls, int iterator) {
		String result = "";
		
		if (iterator == this.model.getIterations()) { // If reached desired # of modification to the String, store in model and exit
			this.model.setResult(ls);
			return;
		} else { // If not, replace all chars that appear in the string with its mapping values
			for (int i = 0; i < ls.length(); i++) {
				if (ls.charAt(i) == '-' || ls.charAt(i) == '+' || ls.charAt(i) == '[' || ls.charAt(i) == ']' || this.model.getVal(ls.charAt(i)) == null) {
					result += ls.charAt(i);
				} else {
					result += this.model.getVal(ls.charAt(i));
				}
			}
			iterator++;
			getFinalString(result, iterator);
		}
	}
    
}
