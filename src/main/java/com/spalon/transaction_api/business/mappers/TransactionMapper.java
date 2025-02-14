package com.spalon.transaction_api.business.mappers;

import org.springframework.stereotype.Component;

import com.spalon.transaction_api.business.model.Transaction;
import com.spalon.transaction_api.controller.dto.TransactionRequestDTO;

@Component
public class TransactionMapper {

    public Transaction toTransaction(TransactionRequestDTO transactionRequestDTO) {
        return new Transaction(
                transactionRequestDTO.value(),
                transactionRequestDTO.dateTime());
    }

}
