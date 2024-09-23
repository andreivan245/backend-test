package com.andre.backend_test.service;

import com.andre.backend_test.dto.InvestmentDTO;
import com.andre.backend_test.entity.Investment;
import com.andre.backend_test.repository.InvestmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class InvestmentService {

   @Autowired
   private InvestmentRepository investmentRepository;

    public Investment createInvestment(InvestmentDTO investmentDTO){
        if (!newInvestmentIsValid(investmentDTO.getCreationDate(), investmentDTO.getValue())){
            throw new RuntimeException("Invalid investment");
        }

        Investment entityInvestment = new Investment(investmentDTO);
        investmentRepository.save(entityInvestment);

        return entityInvestment;
    }

    public void getInvestment(){

    }

    public void withdrawalInvestment(){

    }

    public void getListInvestments(){

    }

    public boolean newInvestmentIsValid(Date date, Double value){
        Date today = new Date();

        if(today.before(date) || value < 0) return false;

        return true;
    }
}
