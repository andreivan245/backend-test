package com.andre.backend_test.exception.handler;

import com.andre.backend_test.exception.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(NewInvestmentIsInvalidException.class)
    ResponseEntity<ErrorMessage> newInvestmentIsInvalid(NewInvestmentIsInvalidException exception, HttpServletRequest request){
        ErrorMessage error = new ErrorMessage();

        error.setTimestamp(Instant.now());
        error.setStatus(HttpStatus.NOT_ACCEPTABLE.value());
        error.setError("Investment creation date or invalid value");
        error.setMessage(exception.getMessage());
        error.setPath(request.getRequestURI());

        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(error);
    }

    @ExceptionHandler(InvestmentNotFoundException.class)
    ResponseEntity<ErrorMessage> investmentNotFound(InvestmentNotFoundException exception, HttpServletRequest request){
        ErrorMessage error = new ErrorMessage();

        error.setTimestamp(Instant.now());
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setError("Investment not found");
        error.setMessage(exception.getMessage());
        error.setPath(request.getRequestURI());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(WithdrawalInvestmentIsInvalidException.class)
    ResponseEntity<ErrorMessage> withdrawalInvestmentIsInvalid(WithdrawalInvestmentIsInvalidException exception, HttpServletRequest request){
        ErrorMessage error = new ErrorMessage();

        error.setTimestamp(Instant.now());
        error.setStatus(HttpStatus.NOT_ACCEPTABLE.value());
        error.setError("Invalid withdrawal date");
        error.setMessage(exception.getMessage());
        error.setPath(request.getRequestURI());

        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(error);
    }

    @ExceptionHandler(InvestmentAlreadyWithdrawnException.class)
    ResponseEntity<ErrorMessage> investmentAlreadyWithdrawn(InvestmentAlreadyWithdrawnException exception, HttpServletRequest request){
        ErrorMessage error = new ErrorMessage();

        error.setTimestamp(Instant.now());
        error.setStatus(HttpStatus.NOT_ACCEPTABLE.value());
        error.setError("Investment already withdrawn");
        error.setMessage(exception.getMessage());
        error.setPath(request.getRequestURI());

        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(error);
    }

    @ExceptionHandler(InvestmentListNotFoundException.class)
    ResponseEntity<ErrorMessage> investmentListNotFound(InvestmentListNotFoundException exception, HttpServletRequest request){
        ErrorMessage error = new ErrorMessage();

        error.setTimestamp(Instant.now());
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setError("Investment list not found");
        error.setMessage(exception.getMessage());
        error.setPath(request.getRequestURI());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
}
