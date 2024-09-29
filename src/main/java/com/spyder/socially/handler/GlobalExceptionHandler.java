package com.spyder.socially.handler;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler
	public ResponseEntity<String> handleUserNotFoundException(UserNotFoundException ex, WebRequest request){
		return new ResponseEntity<>(ex.getMessage(),HttpStatus.NOT_FOUND);
	}

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.CONFLICT)  // HTTP 409 Conflict
    public String handleConflict(DataIntegrityViolationException e) {
        return "Duplicate entry. Please ensure the username or email is unique.";
    }

}
