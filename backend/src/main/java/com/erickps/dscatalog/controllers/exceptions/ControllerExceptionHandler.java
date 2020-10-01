package com.erickps.dscatalog.controllers.exceptions;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.erickps.dscatalog.services.exceptions.EntityNotFoundException;

@ControllerAdvice
public class ControllerExceptionHandler {

	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<StandardError> notFoundException(EntityNotFoundException error, HttpServletRequest request) {
		StandardError newError = new StandardError();
		newError.setTimestamp(Instant.now());
		newError.setStatus(HttpStatus.NOT_FOUND.value());
		newError.setError("Resource not found");
		newError.setMessage(error.getMessage());
		newError.setPath(request.getRequestURI());
		return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(newError);
	}

}
