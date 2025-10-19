package com.jgfontes.receipt_service.dto;

import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TradeDTO implements Serializable {
    private UUID id;
    private BigDecimal amount;
    private String currency;
    private Status status;
    private Type type;
    private String clientId;
    private Instant createdAt;
    private Instant updatedAt;

    @Override
    public String toString() {
        return "TradeDTO{" +
                "id=" + id +
                ", amount=" + amount +
                ", currency='" + currency + '\'' +
                ", status=" + status +
                ", type=" + type +
                ", clientId='" + clientId + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
