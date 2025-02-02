package com.spalon.itau_challenge.controller.dto;

import java.time.OffsetDateTime;

public record TransactionRequestDTO(Double value, OffsetDateTime dateTime) {
}
