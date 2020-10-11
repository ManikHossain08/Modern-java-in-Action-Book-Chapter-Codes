package soen6441.exception.samples;

/**
 * A user defined exception class that is used to throw
 * negative numbers exception. 
 *  
 * @author Sleiman Rabah - Concordia University Fall 2012.
 */
public class NegativeNumber extends Exception {
	/**
	 * Default constructor.
	 */
	public NegativeNumber() {
		super("Division by Zero!");
	}

	/**
	 * A Constructor with custom error message.
	 * 
	 * @param message
	 *            the user's custom error message.
	 */
	public NegativeNumber(String message) {
		super(message);
	}

}
