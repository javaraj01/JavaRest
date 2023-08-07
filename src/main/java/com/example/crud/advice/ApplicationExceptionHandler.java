package com.example.crud.advice;


import com.example.crud.exception.ErrorDetails;
import com.example.crud.exception.ErrorResponse;
import com.example.crud.exception.InvalidRequestException;
import com.example.crud.exception.NoSuchProductExistException;
import org.apache.catalina.connector.Response;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ApplicationExceptionHandler {
    @ExceptionHandler(NoSuchProductExistException.class)
    public ResponseEntity<?> nosuchProduct(NoSuchProductExistException ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(InvalidRequestException.class)
    public ResponseEntity<?> inValidRequest(InvalidRequestException ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<?> handleInvalidArgument(MethodArgumentNotValidException ex) {
//        ErrorDetails errorDetails = new ErrorDetails(new Date(), "validation Error",
//                ex.getBindingResult().getFieldError().getDefaultMessage());
//        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
//    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> resourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }


}
