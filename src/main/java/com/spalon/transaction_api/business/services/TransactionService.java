package com.spalon.transaction_api.business.services;

import java.util.List;

import com.spalon.transaction_api.business.model.Transaction;

public interface TransactionService {
    void addTransaction(Transaction transaction);

    void removeTransaction(Transaction transaction);

    void removeAllTransactions();

    List<Transaction> getTransaction(Integer interval);
}
