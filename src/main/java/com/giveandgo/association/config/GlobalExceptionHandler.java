package com.giveandgo.association.config;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgumentException(IllegalArgumentException ex) {
        ErrorResponse error = new ErrorResponse(ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorResponse> handleAccessDeniedException(AccessDeniedException ex) {
        ErrorResponse error = new ErrorResponse("Accès refusé : vous n'avez pas les autorisations nécessaires.");
        return new ResponseEntity<>(error, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(WebExchangeBindException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(WebExchangeBindException ex) {
        StringBuilder errorMessage = new StringBuilder();
        ex.getFieldErrors().forEach(error ->
                errorMessage.append(error.getField()).append(": ").append(error.getDefaultMessage()).append("; "));
        ErrorResponse error = new ErrorResponse(errorMessage.toString());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}

@Data
class ErrorResponse {
    private String message;

    public ErrorResponse(String message) {
        this.message = message;
    }
}