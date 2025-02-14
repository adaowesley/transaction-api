package com.spalon.transaction_api.business.model;

import java.time.OffsetDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Transaction {

    private Double value;
    private OffsetDateTime dateTime;
}
