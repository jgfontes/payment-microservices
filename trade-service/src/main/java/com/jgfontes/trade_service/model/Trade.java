package com.jgfontes.trade_service.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Trade {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private BigDecimal amount;
    @Enumerated(EnumType.STRING)
    private Currency currency;
    private Status status;
    private Type type;
    private String clientId;
    private Instant createdAt;
    private Instant updatedAt;
}
