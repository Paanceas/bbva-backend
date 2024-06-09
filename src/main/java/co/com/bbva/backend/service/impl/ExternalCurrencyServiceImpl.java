package co.com.bbva.backend.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import co.com.bbva.backend.exception.CurrencyNotFoundException;
import co.com.bbva.backend.service.ExternalCurrencyService;

@Service
public class ExternalCurrencyServiceImpl implements ExternalCurrencyService {

	@Value("${external.api.url}")
	private String apiUrl;

	@Value("${external.api.key}")
	private String apiKey;

	@Override
	public Map<String, Double> getExchangeRates() {
		RestTemplate restTemplate = new RestTemplate();
		String url = apiUrl + "?access_key=" + apiKey;
		try {
			Map<String, Object> response = restTemplate.getForObject(url, Map.class);
			if (response == null || !response.containsKey("rates")) {
				throw new CurrencyNotFoundException("No se pudieron obtener las tasas de cambio.");
			}
			Map<String, Object> rateObjects = (Map<String, Object>) response.get("rates");
			Map<String, Double> rates = new HashMap<>();
			for (Map.Entry<String, Object> entry : rateObjects.entrySet()) {
				if (entry.getValue() instanceof Integer) {
					rates.put(entry.getKey(), ((Integer) entry.getValue()).doubleValue());
				} else if (entry.getValue() instanceof Double) {
					rates.put(entry.getKey(), (Double) entry.getValue());
				} else {
					throw new CurrencyNotFoundException("Tipo de dato inesperado para la tasa de cambio.");
				}
			}
			return rates;
		} catch (RestClientException e) {
			throw new CurrencyNotFoundException(
					"Error al comunicarse con el servicio de tasas de cambio: " + e.getMessage());
		}
	}

	@Override
	public Map<String, String> getSymbols() {
		RestTemplate restTemplate = new RestTemplate();
		String url = apiUrl.replace("latest", "symbols") + "?access_key=" + apiKey;
		try {
			Map<String, Object> response = restTemplate.getForObject(url, Map.class);
			if (response == null || !response.containsKey("symbols")) {
				throw new CurrencyNotFoundException("No se pudieron obtener los símbolos de divisas.");
			}
			return (Map<String, String>) response.get("symbols");
		} catch (RestClientException e) {
			throw new CurrencyNotFoundException(
					"Error al comunicarse con el servicio de símbolos de divisas: " + e.getMessage());
		}
	}
}
