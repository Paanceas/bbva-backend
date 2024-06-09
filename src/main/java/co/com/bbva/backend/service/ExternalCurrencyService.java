package co.com.bbva.backend.service;

import java.util.Map;

public interface ExternalCurrencyService {
	Map<String, Double> getExchangeRates();

	Map<String, String> getSymbols();
}
