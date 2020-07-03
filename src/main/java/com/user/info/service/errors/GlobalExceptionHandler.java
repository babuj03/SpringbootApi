package com.user.info.service.errors;



import java.time.LocalDateTime;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

 
  @ExceptionHandler(UserNotFoundException.class)
  public ResponseEntity<ExceptionResponse> handleFeignUserNotFoundException(UserNotFoundException ex, WebRequest request, HttpServletResponse response) {
	  ExceptionResponse exceptionResponse = new ExceptionResponse(
			  ex.getMessage(),
			  request.getDescription(false),
			  LocalDateTime.now(),
			  HttpStatus.NOT_FOUND.value());
	  
	  return new ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.NOT_FOUND);
  }
  
  
  
  @ExceptionHandler(Exception.class)
  public ResponseEntity<Object> handleAllException(Exception ex, WebRequest request) {
	  ExceptionResponse exceptionResponse = new ExceptionResponse(ex.getMessage(), request.getDescription(false), LocalDateTime.now(), HttpStatus.INTERNAL_SERVER_ERROR.value());
	  return new ResponseEntity<Object>(exceptionResponse,  HttpStatus.INTERNAL_SERVER_ERROR);
  
   }
 
}





