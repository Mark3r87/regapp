/**
 * File: GlobalExceptionHandler.java
 * Author: Gediminas Kaminskas
 * Date: 2024-05-08
 * This file contains the GlobalExceptionHandler class, which is responsible for handling exceptions globally across
 * the application.
 */

package lt.mark3r.registrationapp.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * The GlobalExceptionHandler class is responsible for handling exceptions globally across the application.
 * It uses the @ControllerAdvice annotation to provide centralized exception handling across all
 *
 * @RequestMapping methods.
 * <p>
 * Annotations:
 * - @ControllerAdvice: Indicates that the class assists a "Controller".
 */
@ControllerAdvice
public class GlobalExceptionHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	/**
	 * Handles NullPointerExceptions that occur in the application.
	 * It logs the exception and returns a 404 NOT FOUND status.
	 *
	 * @param exception The caught NullPointerException.
	 */
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(NullPointerException.class)
	public void handleNotFound(NullPointerException exception) {
		LOGGER.error("Caught NullPointerException", exception);
	}


}

