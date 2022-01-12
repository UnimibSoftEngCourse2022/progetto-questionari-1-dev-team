package it.unimib.unimibmodules.exception;
/**
 * Thrown to indicate format incompatibility.
 * @author Luca milazzo
 * @version 0.0.1
 */
public class FormatException extends Throwable{
	/**
	 * Constructs an FormatException with the specified message and root exception.
	 * @param	message	the exception message
	 * @param err the root exception object
	 */
	public FormatException(String message, Throwable err) {
		super(message, err);
	}
}
