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
}
