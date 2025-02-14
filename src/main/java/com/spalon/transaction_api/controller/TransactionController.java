package com.spalon.transaction_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spalon.transaction_api.business.mappers.TransactionMapper;
import com.spalon.transaction_api.business.services.TransactionService;
import com.spalon.transaction_api.controller.dto.TransactionRequestDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    private final TransactionService transactionService;
    @Autowired
    private final TransactionMapper transactionMapper;

    @PostMapping
    @Operation(description = "Endpoint responsible for adding a transaction")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Transaction added successfully"),
            @ApiResponse(responseCode = "422", description = "Invalid transaction data"),
            @ApiResponse(responseCode = "400", description = "Invalid request data"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<Void> addTransaction(@RequestBody TransactionRequestDTO entity) {
        transactionService.addTransaction(transactionMapper.toTransaction(entity));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping
    @Operation(description = "Endpoint responsible for removing all transactions")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All transactions removed successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request data"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<Void> removeAllTransactions() {
        transactionService.removeAllTransactions();
        return ResponseEntity.ok().build();
    }
}
