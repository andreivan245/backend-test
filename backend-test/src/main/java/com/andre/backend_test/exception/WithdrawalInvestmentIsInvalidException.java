package com.andre.backend_test.exception;

public class WithdrawalInvestmentIsInvalidException extends RuntimeException{

    public WithdrawalInvestmentIsInvalidException(String message){
        super(message);
    }
}
