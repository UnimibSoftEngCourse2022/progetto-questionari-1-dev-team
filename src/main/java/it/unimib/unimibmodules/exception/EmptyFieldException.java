package it.unimib.unimibmodules.exception;
/**
 * Thrown to indicate that required params are empty.
 * @author Luca milazzo
 * @version 0.0.1
 */
public class EmptyFieldException extends Exception {
	/**
	 * Constructs an EmptyFieldException with the specified message and root exception.
	 * @param	message	the exception message
	 * @param err the root exception object
	 */
	public EmptyFieldException(String message, Throwable err) {
		super(message, err);
	}
}