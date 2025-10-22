package com.jgfontes.trade_service.dto;

import com.jgfontes.trade_service.model.Currency;
import com.jgfontes.trade_service.model.Status;
import com.jgfontes.trade_service.model.Type;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateTradeRequestDTO implements Serializable {
    @NotNull
    @DecimalMin(value = "0.01", message = "Amount must be positive")
    private BigDecimal amount;

    @NotNull

    private Currency currency;

    @NotNull
    private Status status;

    @NotNull
    private Type type;

    @NotBlank
    @Size(max = 50)
    private String clientId;
}
