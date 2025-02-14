package com.spalon.transaction_api.business.repositories.impl;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.spalon.transaction_api.business.model.Transaction;
import com.spalon.transaction_api.business.repositories.TransactionRepository;

@Repository
public class TransactionRepositoryImpl implements TransactionRepository {

    private List<Transaction> transactions = new ArrayList<>();

    @Override
    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    @Override
    public void removeTransaction(Transaction transaction) {
        transactions.remove(transaction);
    }

    @Override
    public void removeAllTransactions() {
        transactions.clear();
    }

    @Override
    public List<Transaction> getTransaction(Integer interval) {
        return transactions.stream()
                .filter(transaction -> transaction.getDateTime().isAfter(OffsetDateTime.now().minusSeconds(interval)))
                .collect(Collectors.toList());
    }

}
