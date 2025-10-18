package com.jgfontes.recepy.repository;

import com.jgfontes.recepy.model.Trade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TradeRepository extends JpaRepository<Trade, UUID> {
}
