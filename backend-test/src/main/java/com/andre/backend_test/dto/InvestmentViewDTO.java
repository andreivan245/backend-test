package com.andre.backend_test.dto;

import com.andre.backend_test.entity.Investment;
import org.springframework.stereotype.Component;



@Component
public class InvestmentViewDTO {

    private Double initialValue;

    private Double expectedBalance;

    public InvestmentViewDTO(){
    }

    public InvestmentViewDTO(Double initialValue, Double expectedBalance){
        this.initialValue = initialValue;
        this.expectedBalance = expectedBalance;
    }
    
}
