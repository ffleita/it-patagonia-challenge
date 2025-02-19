package com.it_patagonia_challenge.it_patagonia_challenge.infrastructure.exception;

import com.it_patagonia_challenge.it_patagonia_challenge.domain.exception.DuplicatedEmpresaException;
import com.it_patagonia_challenge.it_patagonia_challenge.domain.exception.EmpresasNotFoundException;
import com.it_patagonia_challenge.it_patagonia_challenge.domain.exception.MissingRequiredAttributeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class GlobalExceptionHandler
{
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<String> HttpMessageNotReadableException(final HttpMessageNotReadableException ex)
	{
		return ResponseEntity.badRequest().body("Error en el formato del payload: " + ex.getMessage());
	}

	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<String> handleHttpRequestMethodNotSupportedException(final HttpRequestMethodNotSupportedException ex) {
		return new ResponseEntity<>("El recurso al que intenta acceder no es valido: " + ex.getMessage(), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(MissingRequiredAttributeException.class)
	public ResponseEntity<String> handleMissingRequiredAttributeException(final MissingRequiredAttributeException ex)
	{
		return ResponseEntity.badRequest().body(ex.getMessage());
	}

	@ExceptionHandler(EmpresasNotFoundException.class)
	public ResponseEntity<String> handleEmpresasNotFoundException(final EmpresasNotFoundException ex)
	{
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(DuplicatedEmpresaException.class)
	public ResponseEntity<String> handleDuplicatedEmpresaException(final DuplicatedEmpresaException ex)
	{
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
	}
}
