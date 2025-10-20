package com.jgfontes.trade_service.service;

import com.jgfontes.trade_service.dto.CreateTradeRequestDTO;
import com.jgfontes.trade_service.dto.TradeDTO;

import java.util.List;
import java.util.UUID;

public interface TradeService {
    TradeDTO createTrade(CreateTradeRequestDTO createTradeRequest);
    List<TradeDTO> getAllTrades();
    TradeDTO getTradeById(UUID id);
    List<TradeDTO> getTradesByClientId(String clientId);
    boolean deleteTrade(UUID id);
}