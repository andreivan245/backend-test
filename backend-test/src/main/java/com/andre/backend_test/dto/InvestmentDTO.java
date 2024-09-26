package com.andre.backend_test.dto;

import com.andre.backend_test.entity.Investment;
import org.springframework.stereotype.Component;

import java.time.LocalDate;


@Component
public class InvestmentDTO {

    private String owner;

    private LocalDate creationDate;

    private double value;

    private boolean alreadyWithdrawn;

    private LocalDate withdrawnDate;

    private double withdrawnValue;

    public InvestmentDTO(){
    }

    public InvestmentDTO(String owner, LocalDate creationDate, double value, boolean alreadyWithdrawn, LocalDate withdrawnDate, double withdrawnValue) {
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

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public boolean getAlreadyWithdrawn() {
        return alreadyWithdrawn;
    }

    public void setAlreadyWithdrawn(boolean alreadyWithdrawn) {
        this.alreadyWithdrawn = alreadyWithdrawn;
    }

    public LocalDate getWithdrawnDate() {
        return withdrawnDate;
    }

    public void setWithdrawnDate(LocalDate withdrawnDate) {
        this.withdrawnDate = withdrawnDate;
    }

    public double getWithdrawnValue() {
        return withdrawnValue;
    }

    public void setWithdrawnValue(double withdrawnValue) {
        this.withdrawnValue = withdrawnValue;
    }
}
