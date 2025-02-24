package com.spalon.transaction_api.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.spalon.transaction_api.business.mappers.TransactionMapper;
import com.spalon.transaction_api.business.model.Transaction;
import com.spalon.transaction_api.business.services.TransactionService;
import com.spalon.transaction_api.controller.dto.TransactionRequestDTO;

@WebFluxTest(TransactionController.class)
public class TransactionControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockitoBean
    private TransactionService transactionService;

    @MockitoBean
    private TransactionMapper transactionMapper;

    @Test
    void testAddTransaction() throws Exception {
        OffsetDateTime dateTime = OffsetDateTime.of(2025, 1, 1, 12, 0, 0, 0, ZoneOffset.UTC);
        Transaction transaction = new Transaction(10.0, dateTime);
        TransactionRequestDTO transactionRequestDTO = new TransactionRequestDTO(10.0, dateTime);
        when(transactionMapper.toTransaction(transactionRequestDTO)).thenReturn(transaction);

        webTestClient.post()
                .uri("/transaction")
                .bodyValue(transactionRequestDTO)
                .exchange()
                .expectStatus().isCreated();

        verify(transactionService, times(1)).addTransaction(transaction);
    }

    @Test
    void testRemoveAllTransactions() {

        webTestClient.delete()
                .uri("/transaction")
                .exchange()
                .expectStatus()
                .isOk().expectBody().isEmpty();

        verify(transactionService, times(1)).removeAllTransactions();
    }
}
