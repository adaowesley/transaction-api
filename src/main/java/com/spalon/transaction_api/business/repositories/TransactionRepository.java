package com.spalon.transaction_api.business.repositories;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.spalon.transaction_api.business.model.Transaction;

@Repository
public interface TransactionRepository {

    void addTransaction(Transaction transaction);

    void removeTransaction(Transaction transaction);

    void removeAllTransactions();

    List<Transaction> getTransaction(Integer interval);

}
