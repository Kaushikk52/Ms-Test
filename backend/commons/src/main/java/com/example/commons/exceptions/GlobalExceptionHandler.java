package com.example.commons.exceptions;

import com.example.commons.models.GenericResponse;
import com.example.commons.response.ResponseBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    /**
     * Handles exceptions when a requested resource is not found.
     * Maps the NotFoundException to an HTTP 404 NOT_FOUND response.
     *
     * @param e The NotFoundException instance.
     * @return A ResponseEntity with a failure response using the ResponseBuilder.
     */
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<GenericResponse<String>> handleNotFoundException(NotFoundException e){
        log.warn("❗❗ NotFoundException occurred : {}",e.getMessage());
        return new ResponseEntity<>(ResponseBuilder.failure(e.getMessage()), HttpStatus.NOT_FOUND);
    }

    /**
     * Handles exceptions thrown when method arguments with @Valid fail validation.
     * Maps the MethodArgumentNotValidException to an HTTP 400 BAD_REQUEST response.
     * This method collects all validation errors and returns them in a map.
     *
     * @param e The MethodArgumentNotValidException instance.
     * @return A ResponseEntity with a failure response containing validation errors.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<GenericResponse<String>> handleValidationException(MethodArgumentNotValidException e){
        log.warn("❗❗ Validation Exception occurred : {}",e.getMessage());
        return new ResponseEntity<>(ResponseBuilder.failure(e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    /**
     * A generic handler for all other unexpected exceptions.
     * This provides a fallback to catch any unhandled errors and prevent a crash.
     *
     * @param e The general Exception instance.
     * @return A ResponseEntity with a generic error message and HTTP 500 status.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<GenericResponse<String>> handleGenericException(Exception e){
        log.warn("❗❗ Unexpected Exception occurred : {}",e.getMessage());
        return new ResponseEntity<>(ResponseBuilder.failure(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
