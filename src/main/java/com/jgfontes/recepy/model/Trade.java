package com.jgfontes.recepy.model;

import lombok.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Trade {
    private UUID id;
    private BigDecimal amount;
    private String currency;
    private Status status;
    private Type type;
    private String clientId;
    private Instant createdAt;
    private Instant updatedAt;
}
