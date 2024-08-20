package app.kubix.common.exception;

import app.kubix.common.exception.codes.ExceptionKeyAndMessage;
import org.springframework.http.HttpStatus;

public class ForbiddenException extends GeneralException {

	private static final long serialVersionUID = 58432132123512L;

	public ForbiddenException(String message, Enum<? extends ExceptionKeyAndMessage> keyAndMessage) {
		super(message, keyAndMessage, HttpStatus.FORBIDDEN);
	}
}
