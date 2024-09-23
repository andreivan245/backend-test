package com.andre.backend_test.dto;

import com.andre.backend_test.entity.Investment;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class InvestmentDTO {

    private Date creationDate;

    private Double value;

    public InvestmentDTO(){
    }

    public InvestmentDTO(Date creationDate, Double value) {
        this.creationDate = creationDate;
        this.value = value;
    }

    public InvestmentDTO(Investment investment) {
        creationDate = investment.getCreationDate();
        value = investment.getValue();
    }


    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }
}
