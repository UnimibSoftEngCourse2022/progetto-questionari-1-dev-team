package it.unimib.unimibmodules.exception;

/**
 * Exception for collections with an incorrect amount of items.
 * @author Davide Costantini
 * @version 1.0.0
 */
public class IncorrectSizeException extends Exception {

	/**
	 * Constructs an IncorrectSizeException with the specified message.
	 * @param	message	the exception message
	 */
	public IncorrectSizeException(String message) {

		super(message);
	}
}
