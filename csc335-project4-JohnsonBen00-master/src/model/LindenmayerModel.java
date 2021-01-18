package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
/**
 * This class has methods that stores and interacts with the user's inputs.
 * @author Benhur J. Tadiparti
 */
public class LindenmayerModel extends java.util.Observable{
	
	private int x;
	private int y;
	private int iterations;
	private int angle;
	private char axiom;
	private HashMap<Character, String> mapping;
	private ArrayList<Character> result;
	
	/**
	 * Initials important fields that store the user's inputs.
	 */
    public LindenmayerModel() {
    	this.x = -1;
    	this.y = -1;
    	this.iterations = 6;
    	this.angle = -1;
    	this.axiom = '0';
    	this.mapping = new HashMap<>();
    	this.result = new ArrayList<>();
    }
    
    /**
     * Stores the x coordinate the user chose.
     * @param x -- the "root" x coordinate of the L-system
     */
    public void setX(int x) {
    	this.x = x;
    	if (checkfields()) {
    		setChanged();
    		notifyObservers();
    	}
    }
    
    /**
     * Returns the x coordinate the user entered.
     * @return -- the "root" x coordinate of the L-system
     */
    public int getX() {
    	return this.x;
    }
    
    /**
     * Stores the y coordinate the user chose.
     * @param y -- the "root" y coordinate of the L-system
     */
    public void setY(int y) {
    	this.y = y;
    	if (checkfields()) {
    		setChanged();
    		notifyObservers();
    	}
    }
    
    /**
     * Returns the y coordinate the user entered.
     * @return -- the "root" y coordinate of the L-system
     */
    public int getY() {
    	return this.y;
    }
    
    /**
     * Stores iteration value the user chose for the L-system.
     * @param i -- the iteration value the user would like to 
     *             go through when creating the L-system
     */
    public void setIterations(int i) {
    	this.iterations = i;
    	if (checkfields()) {
    		setChanged();
    		notifyObservers();
    	}
    }
    
    /**
     * Returns the iteration value the user entered for the L-system.
     * @return -- the iteration value of the L-system
     */
    public int getIterations() {
    	return this.iterations;
    }
    
    /**
     * Stores the angle the user chose for the L-system.
     * @param a -- the angle the user would like to would 
     *             to use when creating the L-system
     */
    public void setAngle(int a) {
    	this.angle = a;
    	if (checkfields()) {
    		setChanged();
    		notifyObservers();
    	}
    }
    
    /**
     * Returns the angle the user entered for the L-system.
     * @return -- the angle used for the L-system
     */
    public int getAngle() {
    	return this.angle;
    }
    
    /**
     * Stores the axiom the user chose to start the L-system off with.
     * @param ax -- the axiom the user would like to start the L-system with
     */
    public void setAxiom(char ax) {
    	this.axiom = ax;
    	if (checkfields()) {
    		setChanged();
    		notifyObservers();
    	}
    }
    
    /**
     * Returns the axiom the user enter for the L-system.
     * @return the axiom used to created the L-system
     */
    public char getAxiom() {
    	return this.axiom;
    }
    
    /**
     * Stores the key,value of the L-system "mapping" the user chose.
     * @param m -- the key that is going to be replaced in the L-system
     * @param string -- the value used to modify the L-system
     */
    public void setMap(char m, String string) {
    	if (this.mapping.containsKey(m)) {
    		this.mapping.replace(m, string);
    	} else {
    		this.mapping.put(m, string);
    	}
    	if (checkfields()) {
    		setChanged();
    		notifyObservers();
    	}
    }
    
    /**
     * Returns the mapping keys the user entered for the L-system.
     * @return the mapping keys of the L-system
     */
    public Set<Character> getKey() {
    	return this.mapping.keySet();
    }
    
    /**
     * Returns the value of the key the user entered for the L-system.
     * @param m -- the key that is going to be replaced with its value
     * @return the value that is linked to one of the keys in the L-system "mapping"
     */
    public String getVal(char m) {
    	return this.mapping.get(m);
    }
    
    /**
     * Returns the size of the "mapping"
     * @return the size of the "mapping" HashMap 
     */
    public int getSize() {
    	return this.mapping.size();
    }
    
    /**
     * Stores the modified L-system string in an ArrayList that the user wants.
     * @param replace -- the modified string of the L-system
     */
    public void setResult(String replace) {
    	this.result.clear();
    	for (int i = 0; i < replace.length(); i ++) {
    		this.result.add(replace.charAt(i));
    	}
    }
    
    /**
     * Returns an ArrayList containing the modified L-system string.
     * @return the ArrayList that stores the modified string of the L-system
     */
    public ArrayList<Character> getResult() {
    	return this.result;
    }
    
    /**
     * Checks if all required inputs for the L-system are entered.
     * @return -- true if all fields are entered, else false
     */
    public boolean checkfields() {
    	if (this.x == -1) {
    		return false;
    	} else if (this.y == -1) {
    		return false;
    	} else if (this.angle == -1) {
    		return false;
    	} else if (this.axiom == '0') {
    		return false;
    	} else if (this.mapping.size() != 2) {
    		return false;
    	}
		return true;
    }
   
}