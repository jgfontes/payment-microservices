package com.jgfontes.trade_service.service;

import com.jgfontes.trade_service.dto.CreateTradeRequestDTO;
import com.jgfontes.trade_service.dto.TradeDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface TradeService {
    TradeDTO createTrade(CreateTradeRequestDTO createTradeRequest);
    Page<TradeDTO> getAllTrades(Pageable pageable);
    TradeDTO getTradeById(UUID id);
    List<TradeDTO> getTradesByClientId(String clientId);
    boolean deleteTrade(UUID id);
}