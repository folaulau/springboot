package com.folaukaveinga.exception;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler {
	private static final String UNEXPECTED_ERROR = "Exception.unexpected";

	@Autowired
	private MessageSource messageSource;

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Object> handleArgumentNotValidException(MethodArgumentNotValidException ex, Locale locale) {
		BindingResult result = ex.getBindingResult();
		
		List<String> errorMessages = result.getAllErrors().stream()
				.map(objectError -> messageSource.getMessage(objectError, locale)).collect(Collectors.toList());
		return new ResponseEntity<>(errorMessages, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleExceptions(Exception ex, Locale locale) {
		String errorMessage = messageSource.getMessage(UNEXPECTED_ERROR, null, locale);
		ex.printStackTrace();
		return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
