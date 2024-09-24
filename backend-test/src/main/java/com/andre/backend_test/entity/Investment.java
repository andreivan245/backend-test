package com.andre.backend_test.entity;

import com.andre.backend_test.dto.InvestmentDTO;
import jakarta.persistence.*;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.util.Date;


@Entity
@Table(name = "tb_investments")
public class Investment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String owner;

    private LocalDate creationDate;

    private Double value;

    private Boolean alreadyWithdrawn;

    private LocalDate withdrawnDate;

    private Double withdrawnValue;

    public Investment(){
    }

    public Investment(InvestmentDTO investmentDTO){
        owner = investmentDTO.getOwner();
        creationDate = investmentDTO.getCreationDate();
        value = investmentDTO.getValue();
        alreadyWithdrawn = false;
        withdrawnDate = null;
        withdrawnValue = null;
    }

    public Investment(String owner, LocalDate creationDate, Double value, Boolean alreadyWithdrawn, LocalDate withdrawnDate, Double withdrawnValue) {
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
