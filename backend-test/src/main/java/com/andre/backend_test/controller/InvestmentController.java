package com.andre.backend_test.controller;

import com.andre.backend_test.dto.InvestmentDTO;
import com.andre.backend_test.dto.InvestmentViewDTO;
import com.andre.backend_test.dto.WithdrawalDTO;
import com.andre.backend_test.entity.Investment;
import com.andre.backend_test.service.InvestmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;



import java.util.stream.Stream;


@RestController
@RequestMapping("/investment")
public class InvestmentController {

    @Autowired
    private InvestmentService investmentService;

    @PostMapping
    public ResponseEntity<Investment> createInvestment(@RequestBody InvestmentDTO investmentDTO){
        return new ResponseEntity<>(investmentService.createInvestment(investmentDTO), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InvestmentViewDTO> getInvestmentView(@PathVariable Long id){
        return ResponseEntity.ok(investmentService.getInvestmentView(id));
    }

    @PostMapping("/withdrawal/{id}")
    public ResponseEntity<Double> withdrawalInvestment(@PathVariable Long id, @RequestBody WithdrawalDTO withdrawalDTO) {
        return new ResponseEntity<>(investmentService.withdrawalInvestment(id, withdrawalDTO.getWithdrawalDay()), HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<Stream<InvestmentDTO>> getListInvestments(@RequestParam String owner){
        return ResponseEntity.ok(investmentService.getListInvestments(owner).stream().map(InvestmentDTO::new));
    }
}
