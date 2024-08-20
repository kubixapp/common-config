package app.kubix.common.handler;

import app.kubix.common.constant.Constants;
import app.kubix.common.constant.HttpResponseConstants;
import app.kubix.common.exception.GeneralException;
import app.kubix.common.exception.codes.GlobalExceptionCodes;
import app.kubix.common.exception.BadRequestException;
import app.kubix.common.exception.ForbiddenException;
import app.kubix.common.util.Utils;
import jakarta.validation.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	private final String preKey = "MAIN_PORTAL_";

	@ExceptionHandler(GeneralException.class)
	public ResponseEntity<Object> handle(GeneralException ex, WebRequest request) {
		return ofType(ex, request);
	}

	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<Object> handle(BadRequestException ex, WebRequest request) {
		return ofType(ex, request, GlobalExceptionCodes.BAD_REQUEST);
	}

	@ExceptionHandler(ForbiddenException.class)
	public ResponseEntity<Object> handle(ForbiddenException ex, WebRequest request) {
		return ofType(ex, request, GlobalExceptionCodes.FORBIDDEN);
	}

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<Object> handle(ConstraintViolationException ex, WebRequest request) {
		return ofType(ex, request, HttpStatus.BAD_REQUEST, GlobalExceptionCodes.HIBERNATE_CONSTRAINT_VIOLATION);
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<Object> handle(IllegalArgumentException ex, WebRequest request) {
		return ofType(ex, request, HttpStatus.BAD_REQUEST, GlobalExceptionCodes.ILLEGAL_ARGUMENT);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {

		List<String> validationErrors = ex.getBindingResult().getFieldErrors().stream()
				.map(e -> e.getField() + "_" + e.getDefaultMessage()).collect(Collectors.toList());
		return ofType(ex, request, HttpStatus.BAD_REQUEST, Constants.ARGUMENT_VALIDATION_FAILED,
				GlobalExceptionCodes.METHOD_ARGUMENT_NOT_VALID.getExceptionKey(), validationErrors);

	}

	private ResponseEntity<Object> ofType(Exception ex, WebRequest request, GlobalExceptionCodes globalExceptionCodes) {
		return ofType(ex, request, HttpStatus.BAD_REQUEST, globalExceptionCodes);
	}

	protected ResponseEntity<Object> ofType(Exception ex, WebRequest request, HttpStatus status,
			GlobalExceptionCodes globalExceptionCodes) {
		if (ex instanceof GeneralException) {
			return ofType(request, status, ((GeneralException) ex).getExceptionMessage(),
					((GeneralException) ex).getExceptionKey(), Collections.EMPTY_LIST);
		} else {
			log.error(Utils.getTraceError(ex));
			return ofType(request, status, ex.getMessage(), globalExceptionCodes.getExceptionKey(),
					Collections.EMPTY_LIST);
		}

	}

	protected ResponseEntity<Object> ofType(GeneralException ex, WebRequest request) {
		return ofType(request, ex.getHttpStatus(), ex.getExceptionMessage(), ex.getExceptionKey(),
				Collections.EMPTY_LIST);
	}

	private ResponseEntity<Object> ofType(WebRequest request, HttpStatus status, String message, String exceptionKey,
			List<?> validationErrors) {
		Map<String, Object> attributes = new HashMap<>();
		attributes.put(HttpResponseConstants.STATUS, status.value());
		attributes.put(HttpResponseConstants.CODE, exceptionKey);
		attributes.put(HttpResponseConstants.MESSAGE, message);
		attributes.put(HttpResponseConstants.RESULT, validationErrors);
		attributes.put(HttpResponseConstants.KEY, preKey + exceptionKey);
		attributes.put(HttpResponseConstants.TIMESTAMP,
				LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
		attributes.put(HttpResponseConstants.PATH, ((ServletWebRequest) request).getRequest().getRequestURI());
		return new ResponseEntity<>(attributes, status);
	}

	private ResponseEntity<Object> ofType(Exception ex, WebRequest request, HttpStatus status, String message,
			String exceptionKey, List<?> validationErrors) {
		Map<String, Object> attributes = new HashMap<>();
		attributes.put(HttpResponseConstants.STATUS, status.value());
		attributes.put(HttpResponseConstants.CODE, exceptionKey);
		attributes.put(HttpResponseConstants.MESSAGE, message);
		attributes.put(HttpResponseConstants.RESULT, validationErrors);
		attributes.put(HttpResponseConstants.KEY, preKey + exceptionKey);
		attributes.put(HttpResponseConstants.TIMESTAMP,
				LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
		attributes.put(HttpResponseConstants.PATH, ((ServletWebRequest) request).getRequest().getRequestURI());
		return new ResponseEntity<>(attributes, status);
	}

}
