package com.madhavan.basicrestapi.exceptions;


import com.madhavan.basicrestapi.DTO.ApiError;
import com.madhavan.basicrestapi.DTO.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse<?>> handleResourceNotFoundException(ResourceNotFoundException ex) {
        ApiError apiError = new ApiError(ex.getMessage(), HttpStatus.NOT_FOUND.value(),null);
        return buildErrorResponse(apiError, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<?>> handleValidationException(MethodArgumentNotValidException ex){
        List<String> errorMessages = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .toList();
        //String combinedErrorMessage = String.join("; ", errorMessages);
        ApiError apiError = new ApiError("Invalid inputs", HttpStatus.BAD_REQUEST.value(), errorMessages);
        return buildErrorResponse(apiError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<?>> handleGenericException(Exception ex) {
        ApiError apiError = new ApiError(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(),null);
        return buildErrorResponse(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<ApiResponse<?>> buildErrorResponse(ApiError apiError, HttpStatus httpStatus) {
        ApiResponse<?> apiResponse = new ApiResponse<>(apiError);
        return new ResponseEntity<>(apiResponse, httpStatus);
    }

}
