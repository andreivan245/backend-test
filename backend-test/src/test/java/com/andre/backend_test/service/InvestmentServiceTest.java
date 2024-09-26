package com.andre.backend_test.service;

import com.andre.backend_test.dto.InvestmentDTO;
import com.andre.backend_test.entity.Investment;
import com.andre.backend_test.exception.NewInvestmentIsInvalidException;
import com.andre.backend_test.repository.InvestmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class InvestmentServiceTest {

    private static final String OWNER = "Nametest";
    private static final LocalDate CREATIONDATE = LocalDate.now();
    private static final double VALUE = 1000;
    private static final boolean ALREADYWITHDRAWN = false;
    private static final LocalDate WITHDRAWNDATE = null;
    private static final double WITHDRAWNVALUE = 0;

    @Autowired
    @InjectMocks
    private InvestmentService investmentService;

    @Mock
    private InvestmentRepository investmentRepository;

    private Investment investment;
    private InvestmentDTO investmentDTO;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        investment = new Investment(OWNER, CREATIONDATE, VALUE, ALREADYWITHDRAWN, WITHDRAWNDATE, WITHDRAWNVALUE);
        investmentDTO = new InvestmentDTO(investment);
    }


    @Test
    @DisplayName("Should save the investment in the database")
    void createInvestmentSuccess() {
        when(investmentRepository.save(any())).thenReturn(investment);

        Investment savedInvestment = investmentService.createInvestment(investmentDTO);

        assertEquals(OWNER,savedInvestment.getOwner());
        assertEquals(CREATIONDATE,savedInvestment.getCreationDate());
        assertEquals(VALUE,savedInvestment.getValue());
        assertEquals(ALREADYWITHDRAWN, savedInvestment.getAlreadyWithdrawn());
        assertEquals(WITHDRAWNDATE, savedInvestment.getWithdrawnDate());
        assertEquals(WITHDRAWNDATE, savedInvestment.getWithdrawnDate());
    }

    @Test
    @DisplayName("Should not save the investment in the database")
    void createInvestmentNotSaved() {
        when(investmentRepository.save(any())).thenThrow(new NewInvestmentIsInvalidException("Investment invalid"));

        Exception exception = assertThrows(NewInvestmentIsInvalidException.class, () -> investmentService.createInvestment(investmentDTO));

        assertEquals("Investment invalid", exception.getMessage());
    }



    @Test
    void getInvestmentView() {
    }

    @Test
    void withdrawalInvestment() {
    }

    @Test
    void getListInvestments() {
    }

    @Test
    void newInvestmentIsValid() {
    }

    @Test
    void expectedBalanceCalculationView() {
    }

    @Test
    void withdrawalIsValid() {
    }

    @Test
    void withdrawalBalanceCalculation() {
    }

    @Test
    void applyTax() {
    }

    @Test
    void findEntityById() {
    }
}