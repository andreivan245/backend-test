package com.andre.backend_test.entity;

import com.andre.backend_test.dto.InvestmentDTO;
import jakarta.persistence.*;


import java.time.LocalDate;



@Entity
public class Investment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String owner;

    private LocalDate creationDate;

    private double value;

    private boolean alreadyWithdrawn;

    private LocalDate withdrawnDate;

    private double withdrawnValue;

    public Investment(){
    }

    public Investment(InvestmentDTO investmentDTO){
        owner = investmentDTO.getOwner();
        creationDate = investmentDTO.getCreationDate();
        value = investmentDTO.getValue();
        alreadyWithdrawn = false;
        withdrawnDate = null;
        withdrawnValue = 0;
    }

    public Investment(String owner, LocalDate creationDate, double value, boolean alreadyWithdrawn, LocalDate withdrawnDate, double withdrawnValue) {
        this.owner= owner;
        this.creationDate = creationDate;
        this.value = value;
        this.alreadyWithdrawn = alreadyWithdrawn;
        this.withdrawnDate = withdrawnDate;
        this.withdrawnValue = withdrawnValue;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
