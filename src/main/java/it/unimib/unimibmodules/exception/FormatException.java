package it.unimib.unimibmodules.exception;
/**
 * Exception for format incompatibilities.
 * @author Luca milazzo
 * @version 0.4.1
 */
public class FormatException extends Exception{
	/**
	 * Constructs an FormatException with the specified message and root exception.
	 * @param	message	the exception message
	 * @param err the root exception object
	 */
	public FormatException(String message, Throwable err) {
		super(message, err);
	}
}
