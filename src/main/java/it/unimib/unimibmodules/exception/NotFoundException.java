package it.unimib.unimibmodules.exception;
/**
 * Exception class for not found entities.
 * @author Luca Milazzo
 * @version 0.0.1
 */
public class NotFoundException extends Throwable{
	/**
	 * Constructs an FormatException with the specified message and exception data.
	 * @param	message	the exception message
	 * @param err the root exception object
	 */
	public NotFoundException(String message, Throwable err) {
		super(message, err);
	}
}
