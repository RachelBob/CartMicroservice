package com.lti.cartitemservice.exception;

import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class GlobalExceptionHandlerController {

	private static final Class<? extends Throwable>[] Object = null;



	   @ExceptionHandler(NoSuchElementException.class)
	    public ResponseEntity<String> noSuchElementException(NoSuchElementException noSuchElementEx){
	        return new ResponseEntity<String>("Id Dose not be Present.Please Enter valid Id..", HttpStatus.NOT_FOUND);
	    }
	    
	    @ExceptionHandler()
	    public ResponseEntity<String> methodNotSupportedException(
	            HttpRequestMethodNotSupportedException methodNotSupportedException, HttpStatus status, WebRequest request){
	        return new ResponseEntity<String>("Please change http method type.", HttpStatus.NOT_FOUND);
	    }
}
