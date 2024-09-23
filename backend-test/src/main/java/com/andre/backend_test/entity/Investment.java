package com.andre.backend_test.entity;

import com.andre.backend_test.dto.InvestmentDTO;
import jakarta.persistence.*;

import java.util.Date;


@Entity
@Table(name = "tb_investments")
public class Investment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date creationDate;

    private Double value;

    public Investment(){
    }

    public Investment(InvestmentDTO investmentDTO){
        creationDate = investmentDTO.getCreationDate();
        value = investmentDTO.getValue();
    }

    public Investment(Date creationDate, Double value) {
        this.creationDate = creationDate;
        this.value = value;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
