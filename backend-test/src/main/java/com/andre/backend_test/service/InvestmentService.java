package com.andre.backend_test.service;

import com.andre.backend_test.dto.InvestmentDTO;
import com.andre.backend_test.dto.InvestmentViewDTO;
import com.andre.backend_test.entity.Investment;
import com.andre.backend_test.repository.InvestmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
public class InvestmentService {

    private static final Double WINRATE = 0.52;
    private static final Double TAXLESSTHANONEYEAR = 0.225;
    private static final Double TAXBETWEENTWOANDONEYEAR = 0.185;
    private static final Double TAXMORETHANTWOYEARS = 0.150;

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

    public Double withdrawalInvestment(Long id, LocalDate withdrawalDay){
        Investment entityInvestment = findEntityById(id);

        if(!withdrawalIsValid(entityInvestment,withdrawalDay)){
            throw new RuntimeException("Withdrawal day is invalid");
        }

        investmentRepository.save(withdrawalBalanceCalculation(entityInvestment,withdrawalDay));


        return applyTax(id);
    }

    public List<Investment> getListInvestments(String owner){
        return investmentRepository.findByOwner(owner);
    }

    public Boolean newInvestmentIsValid(LocalDate date, Double value){
        LocalDate today = LocalDate.now();

        if(today.isBefore(date) || value < 0) return false;

        return true;
    }

    public InvestmentViewDTO expectedBalanceCalculationView(Investment investment){
        Double initialAmount = investment.getValue();
        Double expectedBalance = investment.getValue();
        LocalDate today = LocalDate.now();

        long monthsBetween = ChronoUnit.MONTHS.between(investment.getCreationDate(),today);

        int monthsBetweenInt = (int) monthsBetween;

        for(int i = 0; i < monthsBetweenInt; i++){
            expectedBalance = expectedBalance + (expectedBalance*WINRATE);
        }

        return new InvestmentViewDTO(initialAmount,Math.floor(expectedBalance));
    }

    public Boolean withdrawalIsValid(Investment investment, LocalDate withdrawalDay){
        LocalDate today = LocalDate.now();
        if(withdrawalDay.isBefore(investment.getCreationDate()) || withdrawalDay.isAfter(today)) return false;

        return true;
    }

    public Investment withdrawalBalanceCalculation(Investment investment, LocalDate withdrawalDay){
        Double expectedBalance = investment.getValue();

        if(investment.getAlreadyWithdrawn()){
            throw new RuntimeException("Value already withdrawn");
        }

        long monthsBetween = ChronoUnit.MONTHS.between(investment.getCreationDate(),withdrawalDay);

        int monthsBetweenInt = (int) monthsBetween;

        for(int i = 0; i < monthsBetweenInt; i++){
            expectedBalance = expectedBalance + (expectedBalance*WINRATE);
        }

        investment.setAlreadyWithdrawn(true);
        investment.setWithdrawnDate(withdrawalDay);
        investment.setWithdrawnValue(Math.floor(expectedBalance));

        return investment;
    }

    public Double applyTax(Long id){
        Investment entityInvestment = findEntityById(id);

        double gains = entityInvestment.getWithdrawnValue()-entityInvestment.getValue();
        Double netGain;

        long monthsBetween = ChronoUnit.MONTHS.between(entityInvestment.getCreationDate(),entityInvestment.getWithdrawnDate());

        int monthsBetweenInt = (int) monthsBetween;

        if(monthsBetweenInt  < 12){
            netGain = gains - (gains * TAXLESSTHANONEYEAR);
        } else if (monthsBetweenInt  > 12 && monthsBetweenInt < 24) {
            netGain = gains - (gains * TAXBETWEENTWOANDONEYEAR);
        }else {
            netGain = gains - (gains * TAXMORETHANTWOYEARS);
        }

        return Math.floor(entityInvestment.getValue() + netGain);
    }

    public Investment findEntityById(Long id){
        Optional<Investment> optionalEntityInvestment = investmentRepository.findById(id);

        return optionalEntityInvestment.orElseThrow(
                () -> new RuntimeException("Id not found: " + id));
    }
}
