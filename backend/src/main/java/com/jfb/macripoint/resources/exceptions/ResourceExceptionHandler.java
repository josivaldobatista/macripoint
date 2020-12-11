package com.jfb.macripoint.resources.exceptions;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import com.jfb.macripoint.services.exceptions.DatabaseException;
import com.jfb.macripoint.services.exceptions.ResourceNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ResourceExceptionHandler {

  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<StandardError> entityNotFound(ResourceNotFoundException e, HttpServletRequest request) {
    HttpStatus status = HttpStatus.NOT_FOUND;
    StandardError err = new StandardError();
      err.setTimestamp(Instant.now());
      err.setStatus(status.value());
      err.setError("Resource not found!");
      err.setMessage(e.getMessage());
      err.setPath(request.getRequestURI());
    return ResponseEntity.status(status).body(err);
  }

  @ExceptionHandler(DatabaseException.class)
  public ResponseEntity<StandardError> databaseException(DatabaseException e, HttpServletRequest request) {
    HttpStatus status = HttpStatus.BAD_REQUEST;
    StandardError err = new StandardError();
      err.setTimestamp(Instant.now());
      err.setStatus(status.value());
      err.setError("Database integrity exception!");
      err.setMessage(e.getMessage());
      err.setPath(request.getRequestURI());
    return ResponseEntity.status(status).body(err);  
  }
}
