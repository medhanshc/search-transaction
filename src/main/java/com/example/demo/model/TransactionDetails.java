package com.example.demo.model;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
public class TransactionDetails {

    @OneToOne
    private PayerDetails payerDetails;
    @OneToOne
    private PayeeDetails payeeDetails;
    private LocalDateTime transactionDate;
    private BigDecimal transactionAmount;
    private String transactionDescription;
    private String transactionType;
    @Id
    private String referenceNumber;
}
