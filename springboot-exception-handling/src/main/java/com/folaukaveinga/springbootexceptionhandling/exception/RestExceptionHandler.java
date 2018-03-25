package com.folaukaveinga.springbootexceptionhandling.exception;

import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
	private Logger log = LoggerFactory.getLogger(this.getClass());

	/**
	 * 
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(PersistenceException.class)
	protected ResponseEntity<Object> handleDatabaseException(PersistenceException ex) {
		log.info("handleDatabaseException(..)");
		log.error(ex.getMessage());
		ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, ex.getMessage());
		return buildResponseEntity(apiError);
	}
	
	/**
	 * 
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(GetException.class)
	protected ResponseEntity<Object> handleGetException(GetException ex) {
		log.info("handleGetException(..)");
		log.error(ex.getMessage());
		return buildResponseEntity(ex.getError());
	}
	
	/**
	 * 
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(ProcessException.class)
	protected ResponseEntity<Object> handleProcessException(ProcessException ex) {
		log.info("handleProcessException(..)");
		log.error(ex.getMessage());
		return buildResponseEntity(ex.getError());
	}
	
	
	/**
	 * Fall back exception handler - if all fails, I WILL CATCH YOU!
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(Exception.class)
	protected ResponseEntity<Object> handleOther(Exception ex) {
		log.info("handleOther(..)");
		log.error(ex.getMessage());
		ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex.getMessage());
		return buildResponseEntity(apiError);
	}

	/**
	 * Get - error code and string of message Delete - error code and string of
	 * message Post, Put, Patch - error code and list of messages
	 * 
	 * @param apiError
	 * @return ResponseEntity
	 */
	private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
		
		return new ResponseEntity<>(apiError, apiError.getStatus());
	}
}
