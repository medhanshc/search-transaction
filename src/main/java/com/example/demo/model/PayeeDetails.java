package com.example.demo.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
public class PayeeDetails {

    @Id
    private BigDecimal accountNumber;
    private String bankName;
    private String ifscCode;
    private String accountType;
    private BigDecimal transactionAmount;
    private LocalDateTime transactionDate;

}
