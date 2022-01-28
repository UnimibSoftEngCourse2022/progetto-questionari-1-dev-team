package it.unimib.unimibmodules.exception;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Exception handler class. It handles the HTTP response for an exception.
 * @author Luca Milazzo
 * @version 0.3.0
 */
@ControllerAdvice
public class ExceptionController {
	private static final Logger LOGGER =  LogManager.getLogger(ExceptionController.class); 
	
	/**
     * Returns a ResponseEntity to the rest client with the error message and HTTP code 400
     * @param  ex the object that contains the exception's data
     * @return   an HTTP response with status 400 and the error message
     * @see FormatException
     */
    @ExceptionHandler(value = { FormatException.class })
    public ResponseEntity<Object> handleFormatException(FormatException ex) {
        LOGGER.error("Invalid Format: {}" , ex.getMessage());
        return new ResponseEntity<>(ex.getMessage(),HttpStatus.BAD_REQUEST);
    }
    
    /**
     * Returns a ResponseEntity to the rest client with the error message and HTTP code 404
     * @param  ex the object that contains the exception's data
     * @return   an HTTP response with status 404 and the error message
     * @see NotFoundException
     */
    @ExceptionHandler(value = { NotFoundException.class })
    public ResponseEntity<Object> handleNotFoundException(NotFoundException ex) {

        LOGGER.error("Not found: {}", ex.getMessage());
        return new ResponseEntity<>(ex.getMessage(),HttpStatus.NOT_FOUND);
    }

	/**
	 * Returns a ResponseEntity to the rest client with the error message and HTTP code 400.
	 * @param	e	the object that contains the exception's data
	 * @return		an HTTP response with status 400 and the error message
	 * @see EmptyFieldException
	 */
	@ExceptionHandler(value = EmptyFieldException.class)
	public ResponseEntity<Object> handleNotFoundException(EmptyFieldException e) {

		LOGGER.error("EmptyFieldException: {}", e.getMessage());
		return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
	}

	/**
	 * Returns a ResponseEntity to the rest client with the error message and HTTP code 400.
	 * @param	e	the object that contains the exception's data
	 * @return		an HTTP response with status 400 and the error message
	 * @see IncorrectSizeException
	 */
	@ExceptionHandler(value = IncorrectSizeException.class)
	public ResponseEntity<Object> handleIncorrectSizeException(IncorrectSizeException e) {

		LOGGER.error("IncorrectSizeException: {}", e.getMessage());
		return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
	}
}
