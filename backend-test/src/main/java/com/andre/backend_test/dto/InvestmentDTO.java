package com.andre.backend_test.dto;

import com.andre.backend_test.entity.Investment;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Date;

@Component
public class InvestmentDTO {

    private String owner;

    private LocalDate creationDate;

    private Double value;

    private Boolean alreadyWithdrawn;

    private LocalDate withdrawnDate;

    private Double withdrawnValue;

    public InvestmentDTO(){
    }

    public InvestmentDTO(String owner, LocalDate creationDate, Double value, Boolean alreadyWithdrawn, LocalDate withdrawnDate, Double withdrawnValue) {
        this.owner = owner;
        this.creationDate = creationDate;
        this.value = value;
        this.alreadyWithdrawn = alreadyWithdrawn;
        this.withdrawnDate = withdrawnDate;
        this.withdrawnValue = withdrawnValue;
    }

    public InvestmentDTO(Investment investment) {
        owner = investment.getOwner();
        creationDate = investment.getCreationDate();
        value = investment.getValue();
        alreadyWithdrawn = investment.getAlreadyWithdrawn();
        withdrawnDate = investment.getWithdrawnDate();
        withdrawnValue = investment.getWithdrawnValue();
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Boolean getAlreadyWithdrawn() {
        return alreadyWithdrawn;
    }

    public void setAlreadyWithdrawn(Boolean alreadyWithdrawn) {
        this.alreadyWithdrawn = alreadyWithdrawn;
    }

    public LocalDate getWithdrawnDate() {
        return withdrawnDate;
    }

    public void setWithdrawnDate(LocalDate withdrawnDate) {
        this.withdrawnDate = withdrawnDate;
    }

    public Double getWithdrawnValue() {
        return withdrawnValue;
    }

    public void setWithdrawnValue(Double withdrawnValue) {
        this.withdrawnValue = withdrawnValue;
    }
}
