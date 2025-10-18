package com.jgfontes.recepy.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
    private UUID id;
    private BigDecimal amount;
    private String currency;
    private Status status;
    private Type type;
    private String clientId;
    private Instant createdAt;
    private Instant updatedAt;
}
