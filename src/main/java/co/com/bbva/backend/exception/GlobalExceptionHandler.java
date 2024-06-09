package co.com.bbva.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.RestClientException;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(CurrencyNotFoundException.class)
	public ResponseEntity<String> handleCurrencyNotFoundException(CurrencyNotFoundException ex) {
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(MissingRequestHeaderException.class)
	public ResponseEntity<String> handleMissingRequestHeaderException(MissingRequestHeaderException ex) {
		return new ResponseEntity<>("Falta el encabezado 'API-KEY' o no es válido.", HttpStatus.UNAUTHORIZED);
	}

	@ExceptionHandler(RestClientException.class)
	public ResponseEntity<String> handleRestClientException(RestClientException ex) {
		return new ResponseEntity<>("Error al comunicarse con el servicio de tasas de cambio: " + ex.getMessage(),
				HttpStatus.SERVICE_UNAVAILABLE);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleGeneralException(Exception ex) {
		return new ResponseEntity<>("Error interno del servidor.", HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
