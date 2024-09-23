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

    private String owner;

    private Date creationDate;

    private Double value;

    public Investment(){
    }

    public Investment(InvestmentDTO investmentDTO){
        owner = investmentDTO.getOwner();
        creationDate = investmentDTO.getCreationDate();
        value = investmentDTO.getValue();
    }

    public Investment(String owner, Date creationDate, Double value) {
        this.owner= owner;
        this.creationDate = creationDate;
        this.value = value;
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
