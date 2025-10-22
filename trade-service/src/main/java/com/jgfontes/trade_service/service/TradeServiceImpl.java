package com.jgfontes.trade_service.service;

import com.jgfontes.trade_service.dto.CreateTradeRequestDTO;
import com.jgfontes.trade_service.dto.TradeDTO;
import com.jgfontes.trade_service.model.Trade;
import com.jgfontes.trade_service.repository.TradeRepository;
import com.jgfontes.trade_service.util.TradeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TradeServiceImpl implements TradeService {

    @Autowired
    private TradeRepository tradeRepository;

    @Autowired
    private MessageBrokerService messageBrokerService;

    @Override
    public TradeDTO createTrade(CreateTradeRequestDTO createTradeRequestDTO) {
        Trade trade = TradeMapper.mapCreateRequestToEntity(createTradeRequestDTO); // Is the ID generated here?
        System.out.println("Saving trade to database");
        Trade savedTrade =  tradeRepository.save(trade);

        TradeDTO tradeDTO = TradeMapper.mapEntityToDTO(savedTrade);
        System.out.println("Publishing trade to message broker: " + tradeDTO);
        messageBrokerService.publishMessage(tradeDTO);

        return tradeDTO;
    }

    @Override
    public Page<TradeDTO> getAllTrades(Pageable pageable) {
        return tradeRepository.findAll(pageable).map(TradeMapper::mapEntityToDTO);
    }

    @Override
    public TradeDTO getTradeById(UUID id) {
        return tradeRepository.findById(id)
                .map(TradeMapper::mapEntityToDTO)
                .orElse(null);
    }

    @Override
    public List<TradeDTO> getTradesByClientId(String clientId) {
        return tradeRepository.findByClientId(clientId).stream()
                .map(TradeMapper::mapEntityToDTO)
                .toList();
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