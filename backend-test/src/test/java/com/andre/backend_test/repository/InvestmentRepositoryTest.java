package com.andre.backend_test.repository;

import com.andre.backend_test.dto.InvestmentDTO;
import com.andre.backend_test.entity.Investment;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class InvestmentRepositoryTest {

    private static final String OWNER = "Nametest";
    private static final LocalDate CREATIONDATE = LocalDate.now();
    private static final double VALUE = 1000;
    private static final boolean ALREADYWITHDRAWN = false;
    private static final LocalDate WITHDRAWNDATE = null;
    private static final double WITHDRAWNVALUE = 0;


    @Autowired
    private InvestmentRepository investmentRepository;

    @Autowired
    private EntityManager entityManager;

    @Test
    @DisplayName("Should get list of owner's investments successfully from database")
    void findByOwnerSuccess() {
        InvestmentDTO dataDTO = new InvestmentDTO(OWNER,CREATIONDATE,VALUE,ALREADYWITHDRAWN,WITHDRAWNDATE,WITHDRAWNVALUE);
        this.createInvestment(dataDTO);

        List<Investment> foundedInvestment = this.investmentRepository.findByOwner(OWNER);

        assertThat(!foundedInvestment.isEmpty());
        assertEquals(foundedInvestment.size(),1);
    }

    @Test
    @DisplayName("Should get from the database an empty list of owner's investments")
    void findByOwnerEmpty() {

        List<Investment> foundedInvestment = this.investmentRepository.findByOwner(OWNER);

        assertThat(foundedInvestment.isEmpty());
    }

    private void createInvestment(InvestmentDTO dataDTO){
        Investment newInvestment = new Investment(dataDTO);
        this.entityManager.persist(newInvestment);
    }
}