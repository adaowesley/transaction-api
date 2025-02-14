package com.spalon.transaction_api.controller.dto;

import java.time.OffsetDateTime;

public record TransactionRequestDTO(Double value, OffsetDateTime dateTime) {
}
