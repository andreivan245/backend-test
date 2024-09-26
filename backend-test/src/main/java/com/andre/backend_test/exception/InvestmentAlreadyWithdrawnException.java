package com.andre.backend_test.exception;

public class InvestmentAlreadyWithdrawnException extends RuntimeException{
    public InvestmentAlreadyWithdrawnException(String message){
        super(message);
    }
}
