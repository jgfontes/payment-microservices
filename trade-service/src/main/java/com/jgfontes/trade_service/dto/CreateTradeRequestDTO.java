package com.jgfontes.trade_service.dto;

import com.jgfontes.trade_service.model.Status;
import com.jgfontes.trade_service.model.Type;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@AllArgsConstructor
@Builder
public class CreateTradeRequestDTO implements Serializable {
    private BigDecimal amount;
    private String currency;
    private Status status;
    private Type type;
    private String clientId;
}
