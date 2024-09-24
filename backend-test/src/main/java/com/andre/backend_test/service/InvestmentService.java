package com.andre.backend_test.service;

import com.andre.backend_test.dto.InvestmentDTO;
import com.andre.backend_test.dto.InvestmentViewDTO;
import com.andre.backend_test.entity.Investment;
import com.andre.backend_test.repository.InvestmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Optional;

@Service
public class InvestmentService {

    private static final Double WINRATE = 0.52;

    @Autowired
    private InvestmentRepository investmentRepository;

    public Investment createInvestment(InvestmentDTO investmentDTO){
        if (!newInvestmentIsValid(investmentDTO.getCreationDate(), investmentDTO.getValue())){
            throw new RuntimeException("Invalid investment");
        }

        Investment entityInvestment = new Investment(investmentDTO);
        return investmentRepository.save(entityInvestment);
    }

    public InvestmentViewDTO getInvestmentView(Long id){
        Optional<Investment> optionalEntityInvestment = investmentRepository.findById(id);

        Investment entityInvestment = optionalEntityInvestment.orElseThrow(
                () -> new RuntimeException("Id not found: " + id));

        if(entityInvestment.getAlreadyWithdrawn()){
            return new InvestmentViewDTO(entityInvestment.getValue(), entityInvestment.getWithdrawnValue());
        }

        return expectedBalanceCalculationView(entityInvestment);

    }

    public Investment withdrawalInvestment(Long id, LocalDate withdrawalDay){
        Optional<Investment> optionalEntityInvestment = investmentRepository.findById(id);

        Investment entityInvestment = optionalEntityInvestment.orElseThrow(
                () -> new RuntimeException("Id not found: " + id));

        if(!withdrawalIsValid(entityInvestment,withdrawalDay)){
            throw new RuntimeException("Withdrawal day is invalid");
        }

        return investmentRepository.save(withdrawalBalanceCalculation(entityInvestment,withdrawalDay));

    }

    public void getListInvestments(){

    }

    public boolean newInvestmentIsValid(LocalDate date, Double value){
        LocalDate today = LocalDate.now();

        if(today.isBefore(date) || value < 0) return false;

        return true;
    }

    public InvestmentViewDTO expectedBalanceCalculationView(Investment investment){
        Double initialAmount = investment.getValue();
        Double expectedBalance = investment.getValue();
        LocalDate today = LocalDate.now();

        long monthsBetween = ChronoUnit.MONTHS.between(today,investment.getCreationDate());

        int monthsBetweenInt = (int) monthsBetween;

        for(int i = 0; i < monthsBetweenInt; i++){
            expectedBalance = expectedBalance*WINRATE;
        }

        return new InvestmentViewDTO(initialAmount,expectedBalance);
    }

    public boolean withdrawalIsValid(Investment investment, LocalDate withdrawalDay){
        if(withdrawalDay.isBefore(investment.getCreationDate())) return false;

        return true;
    }

    public Investment withdrawalBalanceCalculation(Investment investment, LocalDate withdrawalDay){
        Double expectedBalance = investment.getValue();


        long monthsBetween = ChronoUnit.MONTHS.between(withdrawalDay,investment.getCreationDate());

        int monthsBetweenInt = (int) monthsBetween;

        for(int i = 0; i < monthsBetweenInt; i++){
            expectedBalance = expectedBalance*WINRATE;
        }

        investment.setAlreadyWithdrawn(true);
        investment.setWithdrawnDate(withdrawalDay);
        investment.setWithdrawnValue(expectedBalance);

        return investment;
    }
}
