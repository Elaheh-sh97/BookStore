package com.store.bookstore.controller;

import com.store.bookstore.dto.AddToCartResponsedto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<Map<String, Object>> handleResponseStatusException(ResponseStatusException exception) {
        Map<String,Object> error=new HashMap<>();
        error.put("success", false);
        error.put("message",exception.getReason());
        error.put("status",exception.getStatusCode().value());
        error.put("timestamp", LocalDateTime.now().toString());
        return new ResponseEntity<>(error,exception.getStatusCode());
    }
}
