package com.jgfontes.trade_service.dto;

import com.jgfontes.trade_service.model.Currency;
import com.jgfontes.trade_service.model.Status;
import com.jgfontes.trade_service.model.Type;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class TradeDTO implements Serializable {
    private UUID id;
    private BigDecimal amount;
    private Currency currency;
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
