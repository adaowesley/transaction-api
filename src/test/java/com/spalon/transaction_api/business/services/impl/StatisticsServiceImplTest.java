package com.spalon.transaction_api.business.services.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.spalon.transaction_api.business.model.Transaction;
import com.spalon.transaction_api.business.services.TransactionService;
import com.spalon.transaction_api.controller.dto.StatisticsResponseDTO;

public class StatisticsServiceImplTest {

    @Mock
    private TransactionService transactionService;

    @InjectMocks
    private StatisticsServiceImpl statisticsService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetStatisticsWithEmptyTransactions() {
        when(transactionService.getTransaction(1)).thenReturn(Collections.emptyList());

        StatisticsResponseDTO response = statisticsService.getStatistics(1);

        assertEquals(0L, response.count());
        assertEquals(0.0, response.sum());
        assertEquals(0.0, response.avg());
        assertEquals(0.0, response.max());
        assertEquals(0.0, response.min());
    }

    @Test
    public void testGetStatisticsWithTransactions() {
        OffsetDateTime dateTime = OffsetDateTime.of(2025, 1, 1, 12, 0, 0, 0, ZoneOffset.UTC);

        List<Transaction> transactions = List.of(
                new Transaction(100.0, dateTime),
                new Transaction(200.0, dateTime),
                new Transaction(300.0, dateTime));

        when(transactionService.getTransaction(1)).thenReturn(transactions);

        StatisticsResponseDTO response = statisticsService.getStatistics(1);

        assertEquals(3L, response.count());
        assertEquals(600.0, response.sum());
        assertEquals(200.0, response.avg());
        assertEquals(300.0, response.max());
        assertEquals(100.0, response.min());
    }
}