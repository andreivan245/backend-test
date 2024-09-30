package com.andre.backend_test.controller;

import com.andre.backend_test.dto.InvestmentDTO;
import com.andre.backend_test.dto.InvestmentViewDTO;
import com.andre.backend_test.dto.WithdrawalDTO;
import com.andre.backend_test.entity.Investment;
import com.andre.backend_test.exception.handler.ErrorMessage;
import com.andre.backend_test.service.InvestmentService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

@OpenAPIDefinition(info = @Info(
        title = "Back End Test Project Documentation",
     description = "API for an application that stores and manages investments.",
     version = "1.0.0"))

@RestController
@RequestMapping("/investment")
public class InvestmentController {

    @Autowired
    private InvestmentService investmentService;

    @Operation(description = "Creates a new investment")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Return investment"),
            @ApiResponse(responseCode = "406", description = "Investment creation date or invalid value", content =  @Content(
                    schema = @Schema(implementation = ErrorMessage.class)
            ) )
    })
    @PostMapping
    public ResponseEntity<Investment> createInvestment(@RequestBody InvestmentDTO investmentDTO){
        return new ResponseEntity<>(investmentService.createInvestment(investmentDTO), HttpStatus.CREATED);
    }

    @Operation(description = "Return an investment view")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Return an investment view"),
            @ApiResponse(responseCode = "404", description = "Investment not found", content =  @Content(
                    schema = @Schema(implementation = ErrorMessage.class)
            ) )
    })
    @GetMapping("/{id}")
    public ResponseEntity<InvestmentViewDTO> getInvestmentView(@PathVariable Long id){
        return ResponseEntity.ok(investmentService.getInvestmentView(id));
    }

    @Operation(description = "Withdraw the investment")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Returns the amount withdrawn"),
            @ApiResponse(responseCode = "406", description = "Invalid withdrawal date or investment already withdrawn", content =  @Content(
                    schema = @Schema(implementation = ErrorMessage.class)
            ) )
    })
    @PostMapping("/withdrawal/{id}")
    public ResponseEntity<Double> withdrawalInvestment(@PathVariable Long id, @RequestBody WithdrawalDTO withdrawalDTO) {
        return new ResponseEntity<>(investmentService.withdrawalInvestment(id, withdrawalDTO.getWithdrawalDay()), HttpStatus.OK);
    }

    @Operation(description = "Returns an owner's list of investments")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Returns an owner's list of investments"),
            @ApiResponse(responseCode = "404", description = "Investment list not found", content =  @Content(
                    schema = @Schema(implementation = ErrorMessage.class)
            ) )
    })
    @GetMapping("/")
    public ResponseEntity<Stream<InvestmentDTO>> getListInvestments(@RequestParam String owner){
        return ResponseEntity.ok(investmentService.getListInvestments(owner).stream().map(InvestmentDTO::new));
    }
}
