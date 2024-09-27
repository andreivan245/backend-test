package com.andre.backend_test.exception;

public class InvestmentListNotFoundException extends RuntimeException{

    public InvestmentListNotFoundException(String message){
        super(message);
    }
}
