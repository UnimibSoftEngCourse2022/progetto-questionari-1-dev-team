package it.unimib.unimibmodules.exception;

/**
 * Thrown to indicate that an answer was created with an empty text.
 * @author Davide Costantini
 * @version 0.0.1
 */
public class EmptyAnswerException extends Exception {

	/**
	 * Constructs an EmptyAnswerException with the specified <code>message</code>.
	 * @param	message	the exception message
	 */
	public EmptyAnswerException(String message) {

		super(message);
	}
}
