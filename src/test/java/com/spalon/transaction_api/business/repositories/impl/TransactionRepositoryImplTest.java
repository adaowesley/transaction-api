package com.spalon.transaction_api.business.repositories.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.OffsetDateTime;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.spalon.transaction_api.business.model.Transaction;
import com.spalon.transaction_api.business.repositories.TransactionRepository;

public class TransactionRepositoryImplTest {

    private TransactionRepository transactionRepository;

    @BeforeEach
    public void setUp() {
        transactionRepository = new TransactionRepositoryImpl();
    }

    @Test
    public void testAddTransaction() {
        Transaction transaction = new Transaction(25.30, OffsetDateTime.now());
        transactionRepository.addTransaction(transaction);
        List<Transaction> transactions = transactionRepository.getTransaction(60);
        assertTrue(transactions.contains(transaction));
    }

    @Test
    public void testRemoveTransaction() {
        Transaction transaction = new Transaction(30.0, OffsetDateTime.now());
        transactionRepository.addTransaction(transaction);
        transactionRepository.removeTransaction(transaction);
        List<Transaction> transactions = transactionRepository.getTransaction(60);
        assertTrue(!transactions.contains(transaction));
    }

    @Test
    public void testRemoveAllTransactions() {
        Transaction transaction1 = new Transaction(40.0, OffsetDateTime.now());
        Transaction transaction2 = new Transaction(50.0, OffsetDateTime.now());
        transactionRepository.addTransaction(transaction1);
        transactionRepository.addTransaction(transaction2);
        transactionRepository.removeAllTransactions();
        List<Transaction> transactions = transactionRepository.getTransaction(60);
        assertTrue(transactions.isEmpty());
    }

    @Test
    public void testGetTransaction() {
        Transaction transaction1 = new Transaction(60.0, OffsetDateTime.now().minusSeconds(30));
        Transaction transaction2 = new Transaction(70.0, OffsetDateTime.now().minusSeconds(90));

        transactionRepository.addTransaction(transaction1);
        transactionRepository.addTransaction(transaction2);

        List<Transaction> transactions = transactionRepository.getTransaction(60);

        assertEquals(1, transactions.size());
        assertTrue(transactions.contains(transaction1));
        assertTrue(!transactions.contains(transaction2));
    }
}