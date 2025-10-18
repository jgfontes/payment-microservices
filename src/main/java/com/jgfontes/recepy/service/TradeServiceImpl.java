package com.jgfontes.recepy.service;

import com.jgfontes.recepy.model.Trade;
import com.jgfontes.recepy.repository.TradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TradeServiceImpl implements TradeService {

    private final TradeRepository tradeRepository;

    @Autowired
    public TradeServiceImpl(TradeRepository tradeRepository) {
        this.tradeRepository = tradeRepository;
    }

    @Override
    public Trade createTrade(Trade trade) {
        return tradeRepository.save(trade);
    }

    @Override
    public List<Trade> getAllTrades() {
        return tradeRepository.findAll();
    }

    @Override
    public Trade getTradeById(UUID id) {
        return tradeRepository.findById(id).orElse(null);
    }

    @Override
    public Trade updateTrade(UUID id, Trade trade) {
        Optional<Trade> existing = tradeRepository.findById(id);
        if (existing.isPresent() && existing.get().getId().equals(id)) {
            return tradeRepository.save(trade);
        }

        return null;
    }

    @Override
    public boolean deleteTrade(UUID id) {
        if (tradeRepository.existsById(id)) {
            tradeRepository.deleteById(id);
            return true;
        }
        return false;
    }
}