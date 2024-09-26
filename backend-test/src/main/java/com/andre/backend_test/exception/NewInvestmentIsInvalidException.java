package com.andre.backend_test.exception;

public class NewInvestmentIsInvalidException extends RuntimeException{

    public NewInvestmentIsInvalidException(String message){
        super(message);
    }
}
