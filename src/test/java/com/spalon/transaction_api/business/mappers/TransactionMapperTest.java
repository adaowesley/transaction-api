package com.spalon.transaction_api.business.mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.OffsetDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.spalon.transaction_api.business.model.Transaction;
import com.spalon.transaction_api.controller.dto.TransactionRequestDTO;

public class TransactionMapperTest {

    private TransactionMapper transactionMapper;

    @BeforeEach
    public void setUp() {
        transactionMapper = new TransactionMapper();
    }

    @Test
    public void testToTransaction() {
        // Given
        TransactionRequestDTO transactionRequestDTO = new TransactionRequestDTO(100.0,
                OffsetDateTime.parse("2023-10-01T10:00:00+00:00"));

        // When
        Transaction transaction = transactionMapper.toTransaction(transactionRequestDTO);

        // Then
        assertEquals(100.0, transaction.getValue(), 0.0);
        assertEquals("2023-10-01T10:00Z", transaction.getDateTime().toString());
    }
}