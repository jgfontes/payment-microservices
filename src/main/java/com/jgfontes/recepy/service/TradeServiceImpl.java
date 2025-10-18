package com.jgfontes.recepy.service;

import com.jgfontes.recepy.dto.CreateTradeRequestDTO;
import com.jgfontes.recepy.dto.TradeDTO;
import com.jgfontes.recepy.dto.UpdateTradeRequestDTO;
import com.jgfontes.recepy.model.Trade;
import com.jgfontes.recepy.repository.TradeRepository;
import com.jgfontes.recepy.util.TradeMapper;
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
    public Trade createTrade(CreateTradeRequestDTO createTradeRequestDTO) {
        Trade trade = TradeMapper.mapCreateRequestToEntity(createTradeRequestDTO);
        return tradeRepository.save(trade);
    }

    @Override
    public List<TradeDTO> getAllTrades() {
        return tradeRepository.findAll()
                .stream()
                .map(TradeMapper::mapEntityToDTO)
                .toList();
    }

    @Override
    public TradeDTO getTradeById(UUID id) {
        return tradeRepository.findById(id)
                .map(TradeMapper::mapEntityToDTO)
                .orElse(null);
    }

    @Override
    public TradeDTO updateTrade(UUID id, UpdateTradeRequestDTO updateTradeRequestDTO) {
        Optional<Trade> existing = tradeRepository.findById(id);
        if (existing.isPresent() && existing.get().getId().equals(id)) {
            Trade mappedTrade = TradeMapper.mapUpdateRequestToEntity(updateTradeRequestDTO);
            Trade persistedTrade = tradeRepository.save(mappedTrade);
            return TradeMapper.mapEntityToDTO(persistedTrade);
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