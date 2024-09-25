package com.andre.backend_test.dto;

import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class WithdrawalDTO {

    LocalDate withdrawalDay;

    public WithdrawalDTO() {
    }

    public WithdrawalDTO(LocalDate withdrawalDay) {
        this.withdrawalDay = withdrawalDay;
    }

    public LocalDate getWithdrawalDay() {
        return withdrawalDay;
    }

    public void setWithdrawalDay(LocalDate withdrawalDay) {
        this.withdrawalDay = withdrawalDay;
    }
}
