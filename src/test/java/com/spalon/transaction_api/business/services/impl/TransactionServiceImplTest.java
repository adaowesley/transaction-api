package com.spalon.transaction_api.business.services.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.OffsetDateTime;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.spalon.transaction_api.business.model.Transaction;
import com.spalon.transaction_api.business.repositories.TransactionRepository;
import com.spalon.transaction_api.infrastructure.exceptions.TransactionException;

public class TransactionServiceImplTest {

    @InjectMocks
    private TransactionServiceImpl transactionService;

    @Mock
    private TransactionRepository transactionRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddTransaction_Success() {
        Transaction transaction = new Transaction(100.0, OffsetDateTime.now().minusDays(1));

        transactionService.addTransaction(transaction);

        verify(transactionRepository, times(1)).addTransaction(transaction);
    }

    @Test
    public void testAddTransaction_FutureDate() {
        Transaction transaction = new Transaction(100.0, OffsetDateTime.now().plusDays(1));

        TransactionException exception = assertThrows(TransactionException.class,
                () -> transactionService.addTransaction(transaction));
        assertEquals("Transaction date cannot be in the future", exception.getMessage());
    }

    @Test
    public void testAddTransaction_NegativeValue() {
        Transaction transaction = new Transaction(-100.0, OffsetDateTime.now().minusDays(1));

        TransactionException exception = assertThrows(TransactionException.class,
                () -> transactionService.addTransaction(transaction));
        assertEquals("Transaction value cannot be negative", exception.getMessage());
    }

    @Test
    public void testAddTransaction_ZeroValue() {
        Transaction transaction = new Transaction(0.0, OffsetDateTime.now().minusDays(1));

        transactionService.addTransaction(transaction);

        verify(transactionRepository, times(1)).addTransaction(transaction);
    }

    @Test
    public void testRemoveTransaction() {
        Transaction transaction = new Transaction(100.0, OffsetDateTime.now());

        transactionService.removeTransaction(transaction);

        verify(transactionRepository, times(1)).removeTransaction(transaction);
    }

    @Test
    public void testRemoveAllTransactions() {
        transactionService.removeAllTransactions();

        verify(transactionRepository, times(1)).removeAllTransactions();
    }

    @Test
    public void testGetTransaction() {
        Integer interval = 30;
        OffsetDateTime dateTime = OffsetDateTime.now();
        List<Transaction> transactions = List.of(
                new Transaction(100.0, dateTime),
                new Transaction(200.0, dateTime),
                new Transaction(300.0, dateTime));

        when(transactionRepository.getTransaction(interval)).thenReturn(transactions);

        List<Transaction> result = transactionService.getTransaction(interval);

        verify(transactionRepository, times(1)).getTransaction(interval);
        assertEquals(transactions, result);
    }
}