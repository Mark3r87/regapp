package lt.mark3r.registrationapp.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@ResponseStatus(HttpStatus.NOT_FOUND)  // 404
	@ExceptionHandler(NullPointerException.class)
	public void handleNotFound(NullPointerException exception) {
		LOGGER.error("Caught NullPointerException", exception);
	}

	// other exception handlers below

}

