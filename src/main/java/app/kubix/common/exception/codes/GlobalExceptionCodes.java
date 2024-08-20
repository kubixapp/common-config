package app.kubix.common.exception.codes;

public enum GlobalExceptionCodes implements ExceptionKeyAndMessage {

	EXPIRED_JWT("20031", "JWT expired"), NO_SUCH_ELEMENT("20001", "No such element"), EMAIL_NOT_FOUND("20002",
			"Email is not found in System"), METHOD_ARGUMENT_TYPE_MISMATCH("20002",
					"Method argument type mismatch"), REQUEST_METHOD_NOT_SUPPORTED("20003",
							"Request method not supported"), MEDIA_TYPE_NOT_SUPPORTED("20004",
									"Media type not supported"), MEDIA_TYPE_NOT_ACCEPTABLE("2005",
											"Media type not acceptable"), MISSING_PATH_VARIABLE("20006",
													"Missing path variable"), MISSING_SERVLET_REQUEST_PARAMETER("20007",
															"Missing servlet request parameter"), SERVLET_REQUEST_BINDING(
																	"20008",
																	"Servlet request binding"), CONVERSION_NOT_SUPPORTED(
																			"20009",
																			"Converion not supported"), TYPE_MISMATCH(
																					"20010",
																					"Type mismatch"), MESSAGE_NOT_READABLE(
																							"20011",
																							"Message not readable"), MESSAGE_NOT_WRITABLE(
																									"20012",
																									"Message not writable"), MISSING_SERVLET_REQUEST_PART(
																											"20013",
																											"Missing servlet request part"), BIND_EXCEPTION(
																													"20014",
																													"Bind Exception"), NO_HANDLER_FOUND(
																															"20015",
																															"No handler found"), ASYNC_REQUEST_TIMEOUT(
																																	"20016",
																																	"Async request timeout"), INTERNAL(
																																			"20017",
																																			"Internal"), CONSTRAINT_VIOLATION(
																																					"20018",
																																					"Constraint Violation"), HIBERNATE_CONSTRAINT_VIOLATION(
																																							"20018",
																																							"Hibernate constraint violation"), ARITHMETIC(
																																									"20019",
																																									"Arithmetic"), UNEXPECTED_TYPE(
																																											"20020",
																																											"Unexpected type"), PERSISTENT_OBJECT(
																																													"20021",
																																													"Persistent object"), PROPERTY_REFERENCE(
																																															"20022",
																																															"Property Refernce"), TRANSIENT_PROPERTY_VALUE(
																																																	"20023",
																																																	"Transient property value"), ACCESS_DENIED(
																																																			"20024",
																																																			"Access denied"), BAD_REQUEST(
																																																					"20025",
																																																					"Bad Request"), ENTITY_NOT_FOUND(
																																																							"20026",
																																																							"Entity not found"), FORBIDDEN(
																																																									"20027",
																																																									"Forbidden"), NULL_POINTER(
																																																											"20028",
																																																											"NullPointer"), SQL_INTEGRITY_CONSTRAINT_VIOLATION_EXCEPTION(
																																																													"20029",
																																																													"SQL Integrity Constraint Violation Exception"), NON_UNIQUE_ELEMENT(
																																																															"20030",
																																																															"Non unique element"),

	MAX_UPLOAD_SIZE_EXCEEDED("10001", "Max upload size exceeded"), METHOD_NOT_SUPPORTED("10002"), CONNECT_EXCEPTION(
			"10003"), FILE_EXCEPTION("10004", "There is a problem in file."), JPA_DATA_INTEGRITY_VIOLATION(
					"10005"), SQL_VIOLATION_EXCEPTION("10006"), METHOD_ARGUMENT_NOT_VALID("10007",
							"Argument validation failed"), FEIGN_GENERAL("10008"), DATE_TIME_PARSE("10012",
									"Date time is not parse correctly."), INVALID_FORMAT("10013",
											"Invalid format exception"), ILLEGAL_ARGUMENT("10014",
													"Illegal argumant"), SQL_EXCEPTION("3804",
															"An error occurred while retrieving data from the database"),;
	private String exceptionKey;
	private String message;

	GlobalExceptionCodes(String exceptionKey, String message) {
		this.exceptionKey = exceptionKey;
		this.message = message;
	}

	GlobalExceptionCodes(String exceptionKey) {
		this.exceptionKey = exceptionKey;
		this.message = "NO Message";
	}

	@Override
	public String getExceptionKey() {
		return exceptionKey;
	}

	@Override
	public String getExceptionMessage() {
		return message;
	}

	public GlobalExceptionCodes exceptionKey(String exceptionKey) {
		this.exceptionKey = exceptionKey;
		return this;
	}
}
