package graphics;

import java.util.ArrayList;
import java.util.Stack;

import model.LindenmayerModel;
/**
 * This class calcuates how the L-system will appear on a digital canvas.
 * @author Benhur J. Tadiparti
 */
public class TurtleGraphics {
	
	private Stack<ArrayList<Double>> stack;
	private double x;
	private double y;
	private int angle;
	private double rotations;
	
	/**
	 * Initializes the field required to calculate the appearance of the L-system.
	 * @param model -- the class that stores all of the users' inputs
	 */
	public TurtleGraphics(LindenmayerModel model) {
		this.stack = new Stack<ArrayList<Double>>();
		this.x = model.getX();
		this.y = model.getY();
		this.rotations = 0;
		this.angle = model.getAngle();
	}
	
	/**
	 * Calculates where the end of the line stroke is on the canvas.
	 */
	public void move() {
		this.x += (15 * Math.sin(Math.toRadians(this.rotations)));
		this.y += (15 * Math.cos(Math.toRadians(this.rotations)));
	}
	
	/**
	 * Calculates how far left the line stroke should turn.
	 * 
	 */
	public void rotateLeft() {
		this.rotations = (this.rotations + this.angle) % 360;
	}
	
	/**
	 * Calculates how far right the line stroke should turn.
	 */
	public void rotateRight() {
		this.rotations = (this.rotations - this.angle) % 360;
	}
	
	/**
	 * Returns the current x coordinate of the L-system.
	 * @return the current x coordinate of the line
	 */
	public double getX() {
		return this.x;
	}
	
	/**
	 * Returns the current y coordinate of the L-system.
	 * @return the current y coordinate of the line
	 */
	public double getY() {
		return this.y;
	}
	
	/**
	 * Returns how far left/right should the L-system to turn.
	 * @return how far left/right should the line turn
	 */
	public double getRotations() {
		return this.rotations;
	}
	
	/**
	 * Removes the top element pushed onto the stack.
	 */
	public void pop() {
		this.stack.pop();
	}
	
	/**
	 * Stores an ArrayList in the stack.
	 * @param list -- the ArrayList that stores the x, y coordinates of the
	 *                current line in the L-system, and direction of the line
	 */
	public void push(ArrayList<Double> list) {
		this.stack.push(list);
	}
	
}