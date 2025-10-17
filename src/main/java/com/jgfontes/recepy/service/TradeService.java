package com.jgfontes.recepy.service;

import com.jgfontes.recepy.model.Trade;

import java.util.List;
import java.util.UUID;

public interface TradeService {
    Trade createTrade(Trade trade);
    List<Trade> getAllTrades();
    Trade getTradeById(UUID id);
    Trade updateTrade(UUID id, Trade trade);
    boolean deleteTrade(UUID id);
}