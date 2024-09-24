package com.andre.backend_test.controller;

import com.andre.backend_test.dto.InvestmentDTO;
import com.andre.backend_test.service.InvestmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/investment")
public class InvestmentController {

    @Autowired
    private InvestmentService investmentService;

    @PostMapping
    public ResponseEntity createInvestment(@RequestBody InvestmentDTO investmentDTO){
        return new ResponseEntity(investmentService.createInvestment(investmentDTO), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity getInvestmentView(@PathVariable Long id){
        return ResponseEntity.ok(investmentService.getInvestmentView(id));
    }

    @PostMapping("/withdrawal/{id}")
    public ResponseEntity withdrawalInvestment(@RequestBody Long id, LocalDate withdrawalDay) {
        return new ResponseEntity(investmentService.withdrawalInvestment(id, withdrawalDay), HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity getListInvestments(@RequestParam String owner){
        if(investmentService.getListInvestments(owner).isEmpty()){
            throw new RuntimeException("Owner not found: " + HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(investmentService.getListInvestments(owner).stream().map(InvestmentDTO::new));
    }
}
