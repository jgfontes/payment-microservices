package com.jgfontes.recepy.dto;

import com.jgfontes.recepy.model.Status;
import com.jgfontes.recepy.model.Type;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class TradeDTO {
    private UUID id;
    private BigDecimal amount;
    private String currency;
    private Status status;
    private Type type;
    private String clientId;
    private Instant createdAt;
    private Instant updatedAt;
}
