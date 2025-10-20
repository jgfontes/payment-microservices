package com.jgfontes.trade_service.util;

import com.jgfontes.trade_service.dto.CreateTradeRequestDTO;
import com.jgfontes.trade_service.dto.TradeDTO;
import com.jgfontes.trade_service.model.Trade;
import lombok.NoArgsConstructor;

import java.time.Instant;

@NoArgsConstructor
public class TradeMapper {

    public static Trade mapCreateRequestToEntity(CreateTradeRequestDTO createTradeRequestDTO) {
        return Trade.builder().amount(createTradeRequestDTO.getAmount())
            .currency(createTradeRequestDTO.getCurrency())
            .status(createTradeRequestDTO.getStatus())
            .type(createTradeRequestDTO.getType())
            .clientId(createTradeRequestDTO.getClientId())
            .createdAt(Instant.now())
            .updatedAt(Instant.now())
            .build();
    }

    public static Trade mapDTOToEntity(TradeDTO tradeDTO) {
        return Trade.builder().id(tradeDTO.getId())
            .amount(tradeDTO.getAmount())
            .currency(tradeDTO.getCurrency())
            .status(tradeDTO.getStatus())
            .type(tradeDTO.getType())
            .clientId(tradeDTO.getClientId())
            .createdAt(tradeDTO.getCreatedAt())
            .updatedAt(tradeDTO.getUpdatedAt())
            .build();
    }

    public static TradeDTO mapEntityToDTO(Trade trade) {
        return TradeDTO.builder().id(trade.getId())
            .amount(trade.getAmount())
            .currency(trade.getCurrency())
            .status(trade.getStatus())
            .type(trade.getType())
            .clientId(trade.getClientId())
            .createdAt(trade.getCreatedAt())
            .updatedAt(trade.getUpdatedAt())
            .build();
    }
}
