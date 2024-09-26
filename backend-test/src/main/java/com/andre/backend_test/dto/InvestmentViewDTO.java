package com.andre.backend_test.dto;

import org.springframework.stereotype.Component;



@Component
public class InvestmentViewDTO {

    private double initialValue;

    private double expectedBalance;

    public InvestmentViewDTO(){
    }

    public InvestmentViewDTO(double initialValue, double expectedBalance){
        this.initialValue = initialValue;
        this.expectedBalance = expectedBalance;
    }

    public double getExpectedBalance() {
        return expectedBalance;
    }

    public void setExpectedBalance(double expectedBalance) {
        this.expectedBalance = expectedBalance;
    }

    public double getInitialValue() {
        return initialValue;
    }

    public void setInitialValue(double initialValue) {
        this.initialValue = initialValue;
    }
}
