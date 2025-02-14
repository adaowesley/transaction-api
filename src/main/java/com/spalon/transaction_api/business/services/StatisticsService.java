package com.spalon.transaction_api.business.services;

import com.spalon.transaction_api.controller.dto.StatisticsResponseDTO;

public interface StatisticsService {

    StatisticsResponseDTO getStatistics(Integer interval);

}
