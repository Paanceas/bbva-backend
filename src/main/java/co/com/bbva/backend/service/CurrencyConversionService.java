package co.com.bbva.backend.service;

import co.com.bbva.backend.dto.ConversionRequest;
import co.com.bbva.backend.dto.ConversionResponse;

public interface CurrencyConversionService {
	public ConversionResponse convertCurrency(ConversionRequest request);

}
