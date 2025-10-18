package com.jgfontes.recepy.service;

import com.jgfontes.recepy.dto.CreateTradeRequestDTO;
import com.jgfontes.recepy.dto.TradeDTO;
import com.jgfontes.recepy.dto.UpdateTradeRequestDTO;
import com.jgfontes.recepy.model.Trade;

import java.util.List;
import java.util.UUID;

public interface TradeService {
    TradeDTO createTrade(CreateTradeRequestDTO createTradeRequest);
    List<TradeDTO> getAllTrades();
    TradeDTO getTradeById(UUID id);
    TradeDTO updateTrade(UUID id, UpdateTradeRequestDTO updateTradeRequestDTO);
    boolean deleteTrade(UUID id);
}