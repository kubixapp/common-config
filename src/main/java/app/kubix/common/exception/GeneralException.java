package app.kubix.common.exception;

import app.kubix.common.exception.codes.ExceptionKeyAndMessage;
import org.springframework.http.HttpStatus;

public class GeneralException extends RuntimeException {

	private static final long serialVersionUID = 853354321283511L;

	private final String exceptionKey;
	private final String exceptionMessage;
	private final HttpStatus httpStatus;

	public GeneralException(Enum<? extends ExceptionKeyAndMessage> keyAndMessage) {
		super(((ExceptionKeyAndMessage) keyAndMessage).getExceptionMessage());
		ExceptionKeyAndMessage key = (ExceptionKeyAndMessage) keyAndMessage;
		this.exceptionKey = key.getExceptionKey();
		this.exceptionMessage = key.getExceptionMessage();
		this.httpStatus = HttpStatus.BAD_REQUEST;
	}

	public GeneralException(Enum<? extends ExceptionKeyAndMessage> keyAndMessage, HttpStatus httpStatus) {
		super(((ExceptionKeyAndMessage) keyAndMessage).getExceptionMessage());
		ExceptionKeyAndMessage key = (ExceptionKeyAndMessage) keyAndMessage;
		this.exceptionKey = key.getExceptionKey();
		this.exceptionMessage = key.getExceptionMessage();
		this.httpStatus = httpStatus;
	}

	public GeneralException(String message, Enum<? extends ExceptionKeyAndMessage> keyAndMessage,
			HttpStatus httpStatus) {
		super(message);
		ExceptionKeyAndMessage key = (ExceptionKeyAndMessage) keyAndMessage;
		this.exceptionKey = key.getExceptionKey();
		this.exceptionMessage = key.getExceptionMessage();
		this.httpStatus = httpStatus;
	}

	public GeneralException(Throwable cause, Enum<? extends ExceptionKeyAndMessage> keyAndMessage,
			HttpStatus httpStatus) {
		super(cause);
		ExceptionKeyAndMessage key = (ExceptionKeyAndMessage) keyAndMessage;
		this.exceptionKey = key.getExceptionKey();
		this.exceptionMessage = key.getExceptionMessage();
		this.httpStatus = httpStatus;
	}

	public String getExceptionMessage() {
		return exceptionMessage;
	}

	public String getExceptionKey() {
		return exceptionKey;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}
}
