package app.kubix.common.exception;

import app.kubix.common.exception.codes.ExceptionKeyAndMessage;
import org.springframework.http.HttpStatus;

public class BadRequestException extends GeneralException {

	private static final long serialVersionUID = 58432132123511L;

	public BadRequestException(Enum<? extends ExceptionKeyAndMessage> keyAndMessage) {
		super(keyAndMessage, HttpStatus.BAD_REQUEST);
	}

	public BadRequestException(String message, Enum<? extends ExceptionKeyAndMessage> key) {
		super(message, key, HttpStatus.BAD_REQUEST);
	}
}
