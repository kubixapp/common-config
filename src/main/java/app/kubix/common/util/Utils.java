package app.kubix.common.util;

import app.kubix.common.data.Response;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Base64;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class Utils {

	public String encode(String data) {
		return Base64.getEncoder().encodeToString(data.getBytes());
	}

	public boolean checkHashData(String data, String hashData) {
		byte[] decodedBytes = Base64.getDecoder().decode(hashData);
		String decodedString = new String(decodedBytes);
		return data.equals(decodedString);
	}

	public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
			Pattern.CASE_INSENSITIVE);

	public boolean validateEmail(String emailStr) {
		Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
		return matcher.matches();
	}

	public String generateOTP() {
		int randomNo = (int) (Math.random() * 900000) + 100000;
		String otp = String.valueOf(randomNo);
		return otp;
	}

	public HttpStatusCode exception(Response response) {
		if (response != null && response.getResponseCode().equals("200"))
			return HttpStatusCode.valueOf(200);
		return HttpStatusCode.valueOf(500);
	}

	public static String getTraceError(Exception e) {
		StringWriter sw = new StringWriter();
		sw.append("\n");
		e.printStackTrace(new PrintWriter(sw));
		return sw.toString();
	}

	public static <T extends Enum<T>> Set<T> stringToEnumSet(String enumSetAsString, Class<T> clazz) {
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			return objectMapper.readValue(enumSetAsString,
					objectMapper.getTypeFactory().constructCollectionType(Set.class, clazz));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
