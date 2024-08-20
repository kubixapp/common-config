package app.kubix.common.data;

public class Response {

	private String responseCode;

	private String responseType;

	private Object data;

	public Response(String responseCode, String responseType) {
		this.responseCode = responseCode;
		this.responseType = responseType;
	}

	public Response(String responseCode, String responseType, Object data) {
		this.responseCode = responseCode;
		this.responseType = responseType;
		this.data = data;
	}

	public String getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	public String getResponseType() {
		return responseType;
	}

	public void setResponseType(String responseType) {
		this.responseType = responseType;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
}
