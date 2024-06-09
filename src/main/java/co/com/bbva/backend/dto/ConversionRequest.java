package co.com.bbva.backend.dto;

import lombok.Data;

@Data
public class ConversionRequest {
	private double amount;
	private String fromCurrency;
	private String toCurrency;
}
