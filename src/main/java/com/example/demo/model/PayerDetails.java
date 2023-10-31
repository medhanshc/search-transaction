package com.example.demo.model;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
public class PayerDetails {

    @Id
    private BigDecimal accountNumber;
    private String bankName;
    private String ifscCode;
    private String accountType;
    private BigDecimal transactionAmount;
    private LocalDateTime transactionDate;
}
