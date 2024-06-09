package co.com.bbva.backend.service.impl;

import java.time.LocalDate;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.bbva.backend.dto.ConversionRequest;
import co.com.bbva.backend.dto.ConversionResponse;
import co.com.bbva.backend.exception.CurrencyNotFoundException;
import co.com.bbva.backend.service.CurrencyConversionService;
import co.com.bbva.backend.service.ExternalCurrencyService;

@Service
public class CurrencyConversionServiceImpl implements CurrencyConversionService {

	@Autowired
	private ExternalCurrencyService externalCurrencyService;

	public ConversionResponse convertCurrency(ConversionRequest request) {
		Map<String, Double> rates = externalCurrencyService.getExchangeRates();

		Double fromRate = rates.get(request.getFromCurrency());
		Double toRate = rates.get(request.getToCurrency());

		if (fromRate == null || toRate == null) {
			throw new CurrencyNotFoundException("Moneda no encontrada: "
					+ (fromRate == null ? request.getFromCurrency() : request.getToCurrency()));
		}

		double conversionRate = toRate / fromRate;
		double convertedAmount = request.getAmount() * conversionRate;

		return ConversionResponse.builder().conversionDate(LocalDate.now()).conversionRate(conversionRate)
				.originalAmount(request.getAmount()).convertedAmount(convertedAmount).build();
	}
}
