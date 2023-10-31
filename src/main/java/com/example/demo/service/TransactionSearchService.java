package com.example.demo.service;

import com.example.demo.dao.TransactionDetailsRepo;
import com.example.demo.dto.AccountType;
import com.example.demo.dto.TransactionSearchRequest;
import com.example.demo.dto.TransactionSearchResponse;
import com.example.demo.dto.TransactionType;
import com.example.demo.model.PayeeDetails;
import com.example.demo.model.PayerDetails;
import com.example.demo.model.TransactionDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Validated
public class TransactionSearchService {

    @Autowired
    private TransactionDetailsRepo transactionDetailsRepo;

    public List<TransactionSearchResponse> getTransactionDetails(TransactionSearchRequest transactionSearchRequest) {
        return getTransactionSearchResponse(transactionSearchRequest.getMinAmount(), transactionSearchRequest.getStartDate(),
                                            transactionSearchRequest.getMaxAmount(), transactionSearchRequest.getEndDate());
    }

    private List<TransactionSearchResponse> getTransactionSearchResponse(BigDecimal minAmount, LocalDate startDate,
                                                                         BigDecimal maxAmount, LocalDate endDate) {
        List<TransactionDetails> transactionDetails = getTransactionDetails();
        List<TransactionDetails> transactionDetailsList = transactionDetails.stream()
                //.forEach(t -> t)
                .filter(transaction -> filterTransaction(minAmount, startDate, maxAmount, endDate, transaction))
                .collect(Collectors.toList());
        List<TransactionSearchResponse> searchResponse = new ArrayList<>();
        for(TransactionDetails td : transactionDetailsList) {
            TransactionSearchResponse transactionSearchResponse = new TransactionSearchResponse();
            transactionSearchResponse.setTransactionAmount(td.getTransactionAmount());
            transactionSearchResponse.setTransactionType(td.getTransactionType());
            transactionSearchResponse.setTransactionDescription(td.getTransactionDescription());
            transactionSearchResponse.setPayeeDetails(td.getPayeeDetails());
            transactionSearchResponse.setPayerDetails(td.getPayerDetails());
            transactionSearchResponse.setTransactionDate(td.getTransactionDate());
            transactionSearchResponse.setReferenceNumber(td.getReferenceNumber());
            searchResponse.add(transactionSearchResponse);
        }
        return searchResponse;
    }

    private boolean filterTransaction(BigDecimal minAmount, LocalDate startDate,
                                                                    BigDecimal maxAmount, LocalDate endDate,
                                                                    TransactionDetails transaction) {

        if(minAmount.compareTo(BigDecimal.ZERO) > 0 || maxAmount.compareTo(BigDecimal.ZERO) > 0) {
            return transaction.getTransactionAmount().compareTo(minAmount) >= 0
                    && transaction.getTransactionAmount().compareTo(maxAmount) <= 0;
        }
            LocalDate transactionDate = transaction.getTransactionDate().toLocalDate();
            return transactionDate.equals(startDate) || transactionDate.isAfter(startDate)
                    && transactionDate.equals(endDate) || transactionDate.isBefore(endDate);

    }

    private List<TransactionDetails> getTransactionDetails() {
        TransactionDetails td1 = TransactionDetails.builder()
                .payerDetails(getPayerDetails(BigDecimal.valueOf(123), "SBI", "SBIFSC", LocalDateTime.of(2023, 2, 11, 10, 11, 44),
                        AccountType.SAVINGS.name(), BigDecimal.valueOf(25000.00)))
                .payeeDetails(getPayeeDetails(BigDecimal.valueOf(321), "ICICI", "ICICIFSC", LocalDateTime.of(2023,2, 11, 20, 11, 44),
                        AccountType.SAVINGS.name(), BigDecimal.valueOf(25000.00)))
                .referenceNumber("REF-124")
                .transactionAmount(BigDecimal.valueOf(25000.00))
                .transactionDescription("loan repayment")
                .transactionType(TransactionType.DEBIT.name())
                .transactionDate(LocalDateTime.of(2023, 2, 11, 10, 11, 44))
                .build();

        TransactionDetails td2 = TransactionDetails.builder()
                .payerDetails(getPayerDetails(BigDecimal.valueOf(432), "AXIS", "AXISIFSC", LocalDateTime.of(2023, 1, 12, 13, 11, 54),
                        AccountType.SAVINGS.name(), BigDecimal.valueOf(15000.00)))
                .payeeDetails(getPayeeDetails(BigDecimal.valueOf(324), "SBI", "SBIFSC", LocalDateTime.of(2023, 1, 12, 15, 11, 54),
                        AccountType.CURRENT.name(), BigDecimal.valueOf(15000.00)))
                .transactionAmount(BigDecimal.valueOf(15000.00))
                .referenceNumber("REF-122")
                .transactionDescription("credit card bill payment")
                .transactionType(TransactionType.DEBIT.name())
                .transactionDate(LocalDateTime.of(2023, 1, 12, 13, 11, 54))
                .build();

        TransactionDetails td3 = TransactionDetails.builder()
                .payerDetails(getPayerDetails(BigDecimal.valueOf(121), "AXIS", "AXISIFSC", LocalDateTime.of(2023, 9, 4, 23, 11, 5),
                        AccountType.SAVINGS.name(), BigDecimal.valueOf(2000.00)))
                .payeeDetails(getPayeeDetails(BigDecimal.valueOf(221), "SBI", "SBIFSC", LocalDateTime.of(2023, 9, 5, 23, 11, 5),
                        AccountType.SAVINGS.name(), BigDecimal.valueOf(2000.00)))
                .transactionAmount(BigDecimal.valueOf(2000.00))
                .referenceNumber("REF-443")
                .transactionDescription("electricity bill payment")
                .transactionType(TransactionType.DEBIT.name())
                .transactionDate(LocalDateTime.of(2023, 9, 4, 23, 11, 5))
                .build();

        TransactionDetails td4 = TransactionDetails.builder()
                .payerDetails(getPayerDetails(BigDecimal.valueOf(554), "AXIS", "AXISIFSC", LocalDateTime.of(2023, 2, 12, 10, 11, 44),
                        AccountType.SAVINGS.name(), BigDecimal.valueOf(22000.00)))
                .payeeDetails(getPayeeDetails(BigDecimal.valueOf(221), "SBI", "SBIFSC", LocalDateTime.of(2023, 2, 12, 13, 11, 44),
                        AccountType.SAVINGS.name(), BigDecimal.valueOf(22000.00)))
                .transactionDescription("transfer from another account")
                .transactionAmount(BigDecimal.valueOf(22000.00))
                .referenceNumber("REF-775")
                .transactionType(TransactionType.CREDIT.name())
                .transactionDate(LocalDateTime.of(2023, 2, 12, 10, 11, 44))
                .build();

        TransactionDetails td5 = TransactionDetails.builder()
                .payerDetails(getPayerDetails(BigDecimal.valueOf(667), "AXIS", "AXISIFSC", LocalDateTime.of(2023, 8, 14, 15, 15, 54),
                        AccountType.SAVINGS.name(), BigDecimal.valueOf(45000.00)))
                .payeeDetails(getPayeeDetails(BigDecimal.valueOf(232), "SBI", "SBIFSC", LocalDateTime.of(2023, 8, 14, 15, 15, 15),
                        AccountType.SAVINGS.name(), BigDecimal.valueOf(45000.00)))
                .transactionAmount(BigDecimal.valueOf(45000.00))
                .referenceNumber("REF-453")
                .transactionDescription("received from friend")
                .transactionType(TransactionType.CREDIT.name())
                .transactionDate(LocalDateTime.of(2023, 8, 14, 15, 15, 15))
                .build();

        TransactionDetails td6 = TransactionDetails.builder()
                .payerDetails(getPayerDetails(BigDecimal.valueOf(443), "AXIS", "AXISIFSC", LocalDateTime.of(2023, 5, 5, 3, 11, 34),
                        AccountType.SAVINGS.name(), BigDecimal.valueOf(4000.00)))
                .payeeDetails(getPayeeDetails(BigDecimal.valueOf(232), "SBI", "SBIFSC", LocalDateTime.of(2023, 5, 5, 6, 11, 34),
                        AccountType.SAVINGS.name(), BigDecimal.valueOf(4000.00)))
                .transactionAmount(BigDecimal.valueOf(4000.00))
                .referenceNumber("REF-342")
                .transactionDescription("received from friend")
                .transactionType(TransactionType.CREDIT.name())
                .transactionDate(LocalDateTime.of(2023, 5, 5, 3, 11, 34))
                .build();

        return List.of(td1, td2, td3, td4, td5, td6);
    }

    private PayerDetails getPayerDetails(BigDecimal accountNumber, String bankName, String ifscCode, LocalDateTime localDateTime, String accountType, BigDecimal amount) {
        return PayerDetails.builder()
                .accountNumber(accountNumber)
                .bankName(bankName)
                .ifscCode(ifscCode)
                .transactionDate(localDateTime)
                .accountType(accountType)
                .transactionAmount(amount)
                .build();
    }

    private PayeeDetails getPayeeDetails(BigDecimal accountNumber, String bankName, String ifscCode, LocalDateTime localDateTime, String accountType, BigDecimal amount) {
        return PayeeDetails.builder()
                .accountNumber(accountNumber)
                .bankName(bankName)
                .ifscCode(ifscCode)
                .transactionDate(localDateTime)
                .accountType(accountType)
                .transactionAmount(amount)
                .build();
    }
}
