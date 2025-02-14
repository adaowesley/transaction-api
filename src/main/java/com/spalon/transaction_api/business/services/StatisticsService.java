package com.spalon.transaction_api.business.services;

import java.util.DoubleSummaryStatistics;
import java.util.List;

import org.springframework.stereotype.Service;

import com.spalon.transaction_api.business.model.Transaction;
import com.spalon.transaction_api.controller.dto.StatisticsResponseDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class StatisticsService {

    
    private final TransactionService transactionService;

    public StatisticsResponseDTO getStatistics(Integer interval) {

        log.info("Getting statistics by interval: {}", interval);

        List<Transaction> transactions = transactionService.getTransaction(interval);

        if(transactions.isEmpty()){
            return new StatisticsResponseDTO(0L, 0.0, 0.0, 0.0, 0.0);
        }

        DoubleSummaryStatistics summaryStatistics = transactions.stream().mapToDouble(transaction -> transaction.getValue()).summaryStatistics();
        return new StatisticsResponseDTO(
                summaryStatistics.getCount(),
                summaryStatistics.getSum(),
                summaryStatistics.getAverage(),
                summaryStatistics.getMax(),
                summaryStatistics.getMin()
        );
    }
}
