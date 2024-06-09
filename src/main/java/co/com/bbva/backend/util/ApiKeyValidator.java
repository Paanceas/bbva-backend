package co.com.bbva.backend.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import co.com.bbva.backend.exception.CurrencyNotFoundException;

@Component
public class ApiKeyValidator {

	private static String validApiKey;

	@Value("${api.key}")
	public void setValidApiKey(String apiKey) {
		validApiKey = apiKey;
	}

	public static void validate(String apiKey) {
		if (validApiKey == null || !validApiKey.equals(apiKey)) {
			throw new CurrencyNotFoundException("Invalid API Key");
		}
	}
}
