package co.com.bbva.backend.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ConversionRequest {

	@DecimalMin(value = "0.0", inclusive = false, message = "The amount must be greater than 0")
	private double amount;

	@NotBlank(message = "From currency must not be blank")
	@Size(min = 3, max = 3, message = "From currency must be a 3-letter code")
	private String fromCurrency;

	@NotBlank(message = "To currency must not be blank")
	@Size(min = 3, max = 3, message = "To currency must be a 3-letter code")
	private String toCurrency;
}
