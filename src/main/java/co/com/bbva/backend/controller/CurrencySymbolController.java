package co.com.bbva.backend.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.bbva.backend.annotation.ApiKeyRequired;
import co.com.bbva.backend.service.ExternalCurrencyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/currency-symbols")
@Tag(name = "Currency Symbols API", description = "API for fetching currency symbols")
public class CurrencySymbolController {

	@Autowired
	private ExternalCurrencyService externalCurrencyService;

	@GetMapping
	@ApiKeyRequired
	@Operation(summary = "Get currency symbols", description = "Fetches currency symbols from the external API")
	public Map<String, String> getCurrencySymbols() {
		return externalCurrencyService.getSymbols();
	}
}
