package com.example.demo.dao;

import com.example.demo.model.TransactionDetails;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface TransactionDetailsRepo extends CrudRepository<TransactionDetails, String> {

    @Query("SELECT t FROM TransactionDetails t WHERE t.transactionDate  >= ?1 and t.transactionDate <= ?2")
    List<TransactionDetails> getTransactionDetailsByDate(LocalDate startDate, LocalDate endDate);

    @Query("SELECT t FROM TransactionDetails t WHERE t.transactionAmount  >= ?1 and t.transactionAmount <= ?2")
    List<TransactionDetails> getTransactionDetailsByAmount(BigDecimal minAmount, BigDecimal maxAmount);

}
