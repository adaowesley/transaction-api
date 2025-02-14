package com.spalon.transaction_api.business.services.impl;

import java.time.OffsetDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spalon.transaction_api.business.model.Transaction;
import com.spalon.transaction_api.business.repositories.TransactionRepository;
import com.spalon.transaction_api.business.services.TransactionService;
import com.spalon.transaction_api.infrastructure.exceptions.TransactionException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransactionServiceImpl implements TransactionService{

    @Autowired
    private TransactionRepository transactionRepository;

    public void addTransaction(Transaction transaction) {
        log.info("Adding transaction: {}", transaction);
        if(transaction.getDateTime().isAfter(OffsetDateTime.now())) {
            log.error("Transaction date cannot be in the future: {}", transaction);
            throw new TransactionException("Transaction date cannot be in the future");
        }

        if(transaction.getValue() < 0) {
            log.error("Transaction value cannot be negative: {}", transaction);
            throw new TransactionException("Transaction value cannot be negative");
        }

        transactionRepository.addTransaction(transaction);

        log.info("Transaction added: {}", transaction);
    }

    public void removeTransaction(Transaction transaction) {
        log.info("Removing transaction: {}", transaction);
        transactionRepository.removeTransaction(transaction);
    }

    public void removeAllTransactions() {
        log.info("Removing all transactions");
        transactionRepository.removeAllTransactions();
    }

    public List<Transaction> getTransaction(Integer interval){
        log.info("Getting transaction by interval: {}", interval);
        return transactionRepository.getTransaction(interval);
    }

}
