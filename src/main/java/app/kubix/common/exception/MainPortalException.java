package app.kubix.common.exception;

import app.kubix.common.exception.codes.ExceptionKeyAndMessage;
import org.springframework.http.HttpStatus;

public class MainPortalException extends GeneralException {

	private static final long serialVersionUID = 58432132465811L;

	public MainPortalException(Enum<? extends ExceptionKeyAndMessage> keyAndMessage) {
		super(keyAndMessage, HttpStatus.BAD_REQUEST);
	}

	public MainPortalException(Throwable throwable, Enum<? extends ExceptionKeyAndMessage> key) {
		super(throwable, key, HttpStatus.BAD_REQUEST);
	}

	public MainPortalException(String message, Enum<? extends ExceptionKeyAndMessage> key) {
		super(message, key, HttpStatus.BAD_REQUEST);
	}

}
