package com.example.demo.dto;

import lombok.Data;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class TransactionSearchRequest {

    @NotNull(message = "Transaction start date is mandatory.")
    private LocalDate startDate;

    @NotNull(message = "Transaction end date is mandatory.")
    private LocalDate endDate;

//    @NotNull(message = "Transaction minimum amount is mandatory.")
//    @DecimalMin(value = "0.0", inclusive = false, message = "Invalid transaction minimum amount.")
//    @Digits(integer=10, fraction=4, message = "Invalid transaction minimum amount.")
    private BigDecimal minAmount;

//    @NotNull(message = "Transaction maximum amount is mandatory.")
//    @DecimalMin(value = "0.0", inclusive = false, message = "Invalid transaction maximum amount.")
//    @Digits(integer=10, fraction=4, message = "Invalid transaction maximum amount.")
    private BigDecimal maxAmount;
}
