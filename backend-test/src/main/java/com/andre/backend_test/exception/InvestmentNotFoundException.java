package com.andre.backend_test.exception;

public class InvestmentNotFoundException extends RuntimeException{

    public InvestmentNotFoundException(String message){
        super(message);
    }
}
