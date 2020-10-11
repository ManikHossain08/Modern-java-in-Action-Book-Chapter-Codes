package soen6441.exception.samples;

/**
 * A class that represents a divide by zero exception.
 * 
 * @author Sleiman Rabah - Concordia University Fall 2012.
 */
public class DivideByZero extends Exception {

	/**
	 * Default constructor.
	 */
	public DivideByZero() {
		super("Division by Zero!");
	}

	/**
	 * A Constructor with custom error message.
	 * 
	 * @param message
	 *            the user's custom error message.
	 */
	public DivideByZero(String message) {
		super(message);
	}
}
