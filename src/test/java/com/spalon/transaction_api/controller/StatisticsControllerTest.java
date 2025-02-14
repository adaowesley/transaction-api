package com.spalon.transaction_api.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.spalon.transaction_api.business.services.StatisticsService;
import com.spalon.transaction_api.controller.dto.StatisticsResponseDTO;

@WebFluxTest(StatisticsController.class)
public class StatisticsControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockitoBean
    private StatisticsService statisticsService;

    @BeforeEach
    public void setUp() {
        // No need for MockitoAnnotations.openMocks(this) as @MockBean handles it
    }

    @Test
    public void testGetStatisticsWithInterval() {
        int interval = 60;
        StatisticsResponseDTO mockResponse = new StatisticsResponseDTO(10L, 50.0, 3.0, 10.0, 2.0);
        Mockito.when(statisticsService.getStatistics(interval)).thenReturn(mockResponse);

        webTestClient.get()
                .uri(uriBuilder -> uriBuilder.path("/statistic").queryParam("interval", interval).build())
                .exchange()
                .expectStatus().isOk()
                .expectBody(StatisticsResponseDTO.class)
                .isEqualTo(mockResponse);
    }

    @Test
    public void testGetStatisticsWithoutInterval() {
        StatisticsResponseDTO mockResponse = new StatisticsResponseDTO(10L, 50.0, 3.0, 10.0, 2.0);
        Mockito.when(statisticsService.getStatistics(60)).thenReturn(mockResponse);

        webTestClient.get()
                .uri("/statistic")
                .exchange()
                .expectStatus().isOk()
                .expectBody(StatisticsResponseDTO.class)
                .isEqualTo(mockResponse);
    }

    @Test
    public void testGetStatisticsWithDifferentInterval() {
        int interval = 30;
        StatisticsResponseDTO mockResponse = new StatisticsResponseDTO(10L, 50.0, 3.0, 10.0, 2.0);
        Mockito.when(statisticsService.getStatistics(interval)).thenReturn(mockResponse);

        webTestClient.get()
                .uri(uriBuilder -> uriBuilder.path("/statistic").queryParam("interval", interval).build())
                .exchange()
                .expectStatus().isOk()
                .expectBody(StatisticsResponseDTO.class)
                .isEqualTo(mockResponse);
    }
}
