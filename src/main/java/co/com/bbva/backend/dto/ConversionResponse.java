package co.com.bbva.backend.dto;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ConversionResponse {
	private LocalDate conversionDate;
	private double conversionRate;
	private double originalAmount;
	private double convertedAmount;
}
