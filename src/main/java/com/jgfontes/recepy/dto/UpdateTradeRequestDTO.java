package com.jgfontes.recepy.dto;

import com.jgfontes.recepy.model.Status;
import com.jgfontes.recepy.model.Type;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
@Builder
public class UpdateTradeRequestDTO {
    private BigDecimal amount;
    private String currency;
    private Status status;
    private Type type;
    private String clientId;
}
