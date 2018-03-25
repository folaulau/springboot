package com.folaukaveinga.springbootexceptionhandling.controller;

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
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.folaukaveinga.springbootexceptionhandling.exception.ApiError;

//@ControllerAdvice
//public class MVCExceptionHandler extends ResponseEntityExceptionHandler {
//	private Logger log = LoggerFactory.getLogger(this.getClass());
//
//	@Override
//	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
//			HttpHeaders headers, HttpStatus status, WebRequest request) {
//		String error = "Malformed JSON request";
//		log.info("Hello handleHttpMessageNotReadable(...)");
//		return buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST, error, ex));
//	}
//
//	@ExceptionHandler(Exception.class)
//	protected ResponseEntity<Object> handleEntityNotFound(Exception ex) {
//		ApiError apiError = new ApiError(HttpStatus.BAD_GATEWAY);
//		apiError.setMessage(ex.getMessage());
//		return buildResponseEntity(apiError);
//	}
//
//	/**
//	 * Get - error code and string of message Delete - error code and string of
//	 * message Post, Put, Patch - error code and list of messages
//	 * 
//	 * @param apiError
//	 * @return ResponseEntity
//	 */
//	private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
//		return new ResponseEntity<>(apiError, apiError.getStatus());
//	}
//}
