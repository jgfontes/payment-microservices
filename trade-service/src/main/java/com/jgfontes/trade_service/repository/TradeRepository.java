package com.jgfontes.trade_service.repository;

import com.jgfontes.trade_service.model.Trade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TradeRepository extends JpaRepository<Trade, UUID> {
    List<Trade> findByClientId(String clientId);
}
