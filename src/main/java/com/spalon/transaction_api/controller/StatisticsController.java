package com.spalon.transaction_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spalon.transaction_api.business.services.StatisticsService;
import com.spalon.transaction_api.controller.dto.StatisticsResponseDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/statistic")
public class StatisticsController {

    @Autowired
    private final StatisticsService statisticsService;

    @GetMapping
    @Operation(description = "Endpoint responsible for getting transaction statistics")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Transaction added successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request data"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<StatisticsResponseDTO> getStatistics(@RequestParam(required = false, defaultValue = "60") Integer interval) {
        return ResponseEntity.ok(statisticsService.getStatistics(interval));
    }

}
