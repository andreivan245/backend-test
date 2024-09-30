package com.andre.backend_test.service;

import com.andre.backend_test.dto.InvestmentDTO;
import com.andre.backend_test.dto.InvestmentViewDTO;
import com.andre.backend_test.entity.Investment;
import com.andre.backend_test.exception.*;
import com.andre.backend_test.repository.InvestmentRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class InvestmentServiceTest {

    private static final Long ID = 1L;
    private static final String OWNER = "Nametest";
    private static final LocalDate CREATIONDATE = LocalDate.now();
    private static final double VALUE = 1000;
    private static final boolean ALREADYWITHDRAWN = false;
    private static final LocalDate WITHDRAWNDATE = null;
    private static final double WITHDRAWNVALUE = 0;
    private static final LocalDate WITHDRAWALDAY = LocalDate.now();

    private static final Investment investment = new Investment(OWNER, CREATIONDATE, VALUE, ALREADYWITHDRAWN, WITHDRAWNDATE, WITHDRAWNVALUE);
    private static final InvestmentDTO investmentDTO = new InvestmentDTO(investment);

    @Autowired
    @InjectMocks
    private InvestmentService investmentService;

    @Mock
    private InvestmentRepository investmentRepository;


    @Nested
    class CreateInvestment{

        @Test
        @DisplayName("Should save the investment in the database")
        void createInvestmentWithSuccess() {
            Investment investmentTest = new Investment(investmentDTO);

            when(investmentRepository.save(any())).thenReturn(investmentTest);

            Investment savedInvestment = investmentService.createInvestment(investmentDTO);

            assertEquals(OWNER,savedInvestment.getOwner());
            assertEquals(CREATIONDATE,savedInvestment.getCreationDate());
            assertEquals(VALUE,savedInvestment.getValue());
            assertEquals(ALREADYWITHDRAWN, savedInvestment.getAlreadyWithdrawn());
            assertEquals(WITHDRAWNDATE, savedInvestment.getWithdrawnDate());
            assertEquals(WITHDRAWNDATE, savedInvestment.getWithdrawnDate());
        }

        @Test
        @DisplayName("Should throws exception because of value")
        void createInvestmentThrowExceptionBecauseOfValue() {
            double invalidValue = -1;
            InvestmentDTO investmentTestDTO = new InvestmentDTO(OWNER,CREATIONDATE,invalidValue,ALREADYWITHDRAWN,WITHDRAWNDATE,WITHDRAWNVALUE);

            assertThrows(NewInvestmentIsInvalidException.class, () -> investmentService.createInvestment(investmentTestDTO));
        }

        @Test
        @DisplayName("Should throws exception because of creation date")
        void createInvestmentThrowExceptionBecauseOfCreationDate() {
            LocalDate invalidCreationDate = LocalDate.of(2077, 1, 1);
            InvestmentDTO investmentTestDTO = new InvestmentDTO(OWNER,invalidCreationDate,VALUE,ALREADYWITHDRAWN,WITHDRAWNDATE,WITHDRAWNVALUE);

            assertThrows(NewInvestmentIsInvalidException.class, () -> investmentService.createInvestment(investmentTestDTO));
        }
    }

    @Nested
    class GetInvestmentView{
        @Test
        @DisplayName("Should return investment view with success")
        void getInvestmentViewWithSuccess() {
            investment.setId(ID);
            InvestmentViewDTO expectedInvestmentViewDTO = new InvestmentViewDTO(VALUE,VALUE);

            when(investmentRepository.findById(ID)).thenReturn(Optional.of(investment));

            InvestmentViewDTO investmentViewDTO = investmentService.getInvestmentView(ID);

            assertEquals(expectedInvestmentViewDTO.getInitialValue(),investmentViewDTO.getInitialValue());
            assertEquals(expectedInvestmentViewDTO.getExpectedBalance(),investmentViewDTO.getExpectedBalance());
        }

        @Test
        @DisplayName("Should throws exception because of id")
        void getInvestmentViewThrowExceptionBecauseOfId() {

            assertThrows(InvestmentNotFoundException.class, () -> investmentService.getInvestmentView(anyLong()));
        }

    }


    @Nested
    class WithdrawalInvestment {
        @Test
        @DisplayName("Should withdrawal investment with success")
        void withdrawalInvestmentWithSuccess() {
            Investment investmentTest = new Investment(investmentDTO);
            investmentTest.setId(ID);

            when(investmentRepository.findById(ID)).thenReturn(Optional.of(investmentTest));

            Double valueWithdrawal = investmentService.withdrawalInvestment(ID, WITHDRAWALDAY);

            assertEquals(VALUE,valueWithdrawal);
        }

        @Test
        @DisplayName("Should withdrawal investment with success and returns the value with the rate applied")
        void withdrawalInvestmentWithSuccessAndReturnsValueWithRateApplied() {
            Investment investmentTest = new Investment(investmentDTO);
            investmentTest.setId(ID);

            when(investmentRepository.findById(ID)).thenReturn(Optional.of(investmentTest));

            Double valueWithdrawal = investmentService.withdrawalInvestment(ID, WITHDRAWALDAY);

            assertEquals(VALUE,valueWithdrawal);
            verify(investmentRepository,times(2)).findById(ID);
            verify(investmentRepository,times(1)).save(investmentTest);
        }


        @Test
        @DisplayName("Should withdraw the investment and throw an exception because of the withdrawal date is before the investment was created")
        void withdrawalInvestmentThrowExceptionBecauseOfWithdrawalDateIsBeforeTheCreateDate() {
            Investment investmentTest = new Investment(investmentDTO);
            investmentTest.setId(ID);
            LocalDate invalidWithdrawalDay = LocalDate.of(2024,9,26);

            when(investmentRepository.findById(ID)).thenReturn(Optional.of(investmentTest));

            assertThrows(WithdrawalInvestmentIsInvalidException.class, () -> investmentService.withdrawalInvestment(ID,invalidWithdrawalDay));
        }

        @Test
        @DisplayName("Should withdraw the investment and throw an exception because of the withdrawal date is in the future")
        void withdrawalInvestmentThrowExceptionBecauseOfWithdrawalDateIsInTheFuture() {
            Investment investmentTest = new Investment(investmentDTO);
            investmentTest.setId(ID);
            LocalDate invalidWithdrawalDay = LocalDate.now().plusDays(1);

            when(investmentRepository.findById(ID)).thenReturn(Optional.of(investmentTest));

            assertThrows(WithdrawalInvestmentIsInvalidException.class, () -> investmentService.withdrawalInvestment(ID,invalidWithdrawalDay));
        }


    }

    @Nested
    class GetListInvestments{
        @Test
        @DisplayName("Should return list of investments with an investment")
        void getListInvestmentsWithSuccess() {
            Investment investmentTest = new Investment(investmentDTO);

            when(investmentRepository.findByOwner(OWNER)).thenReturn(Collections.singletonList(investmentTest));

            List<Investment> investmentList = investmentService.getListInvestments(OWNER);

            assertFalse(investmentList.isEmpty());
            assertEquals(OWNER,investmentList.get(0).getOwner());
            assertEquals(CREATIONDATE,investmentList.get(0).getCreationDate());
            assertEquals(VALUE,investmentList.get(0).getValue());
            assertEquals(ALREADYWITHDRAWN,investmentList.get(0).getAlreadyWithdrawn());
            assertEquals(WITHDRAWNDATE,investmentList.get(0).getWithdrawnDate());
            assertEquals(WITHDRAWNVALUE,investmentList.get(0).getWithdrawnValue());
        }


        @Test
        @DisplayName("Should throw exception InvestmentNotFound")
        void getListInvestmentsThrowExceptionNotFound() {
            assertThrows(InvestmentListNotFoundException.class, () -> investmentService.getListInvestments(OWNER));
        }
    }


    @Nested
    class  withdrawalBalanceCalculation{
        @Test
        @DisplayName("Should return whether the amount has already been withdrawn, the day of withdrawal and the amount of withdrawal")
        void withdrawalBalanceCalculationWithSuccess() {
            Investment investmentResult = investmentService.withdrawalBalanceCalculation(investment, WITHDRAWALDAY);

            assertTrue(investmentResult.getAlreadyWithdrawn());
            assertEquals(WITHDRAWALDAY,investmentResult.getWithdrawnDate());
            assertEquals(VALUE,investmentResult.getWithdrawnValue());
        }

        @Test
        @DisplayName("Should throw exception because investment already withdrawn")
        void withdrawalBalanceCalculationThrowExceptionBecauseInvestmentAlreadyWithdrawn() {
            Investment investmentTest = new Investment(investmentDTO);
            investmentTest.setAlreadyWithdrawn(true);

            assertThrows(InvestmentAlreadyWithdrawnException.class, () -> investmentService.withdrawalBalanceCalculation(investmentTest,WITHDRAWALDAY));
        }

    }

}