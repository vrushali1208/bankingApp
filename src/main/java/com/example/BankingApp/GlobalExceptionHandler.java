package com.example.BankingApp;

import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.BankingApp.Response.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse<Object>> handleResourceNotFound(ResourceNotFoundException ex) {
        ApiResponse<Object> response = new ApiResponse<>(ex.getMessage(), null, false);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Object>> handleGeneralException(Exception ex) {
        ApiResponse<Object> response = new ApiResponse<>("Internal Server Error", null, false);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Object>> handleValidationException(MethodArgumentNotValidException ex) {
        String errors = ex.getBindingResult()
                          .getFieldErrors()
                          .stream()
                          .map(error -> error.getField() + ": " + error.getDefaultMessage())
                          .collect(Collectors.joining(", "));

        ApiResponse<Object> response = new ApiResponse<>("Validation failed: " + errors, null, false);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}
