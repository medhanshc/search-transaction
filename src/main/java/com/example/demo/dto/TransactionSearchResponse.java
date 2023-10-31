package com.example.demo.dto;

import com.example.demo.model.PayeeDetails;
import com.example.demo.model.PayerDetails;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransactionSearchResponse extends ResponseData {

    private PayeeDetails payeeDetails;
    private PayerDetails payerDetails;
    private LocalDateTime transactionDate;
    private String transactionDescription;
    private BigDecimal transactionAmount;
    private String transactionType;
    private String referenceNumber;

}
