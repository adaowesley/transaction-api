package com.spalon.itau_challenge.business.mappers;

import org.springframework.stereotype.Component;

import com.spalon.itau_challenge.business.model.Transaction;
import com.spalon.itau_challenge.controller.dto.TransactionRequestDTO;

@Component
public class TransactionMapper {

    public Transaction toTransaction(TransactionRequestDTO transactionRequestDTO) {
        return new Transaction(
                transactionRequestDTO.value(),
                transactionRequestDTO.dateTime()
        );
    }

}
