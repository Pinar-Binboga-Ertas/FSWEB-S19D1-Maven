package com.workintech.s18d2.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@Slf4j
public class PlantExceptionHandler {

    @ExceptionHandler(PlantException.class)
    public ResponseEntity<?> handlePlantException(PlantException ex) {

        log.error("PlantException: {}", ex.getMessage());

        Map<String, Object> error = new HashMap<>();
        error.put("success", false);
        error.put("message", ex.getMessage());

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGenericException(Exception ex) {

        log.error("Unexpected error: {}", ex.getMessage());

        Map<String, Object> error = new HashMap<>();
        error.put("success", false);
        error.put("message", "Unexpected error occurred");

        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}