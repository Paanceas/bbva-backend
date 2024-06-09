package co.com.bbva.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.bbva.backend.annotation.ApiKeyRequired;
import co.com.bbva.backend.dto.ConversionRequest;
import co.com.bbva.backend.dto.ConversionResponse;
import co.com.bbva.backend.service.CurrencyConversionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/currency-conversion")
@Tag(name = "Currency Conversion API", description = "API for converting currencies")
public class CurrencyConversionController {

	@Autowired
	private CurrencyConversionService currencyConversionService;

	@PostMapping
	@ApiKeyRequired
	@Operation(summary = "Convert currency", description = "Converts an amount from one currency to another")
	public ConversionResponse convertCurrency(@RequestBody @Valid ConversionRequest request) {
		return currencyConversionService.convertCurrency(request);
	}
}
