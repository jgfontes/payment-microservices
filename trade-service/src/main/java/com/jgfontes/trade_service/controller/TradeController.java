package com.jgfontes.trade_service.controller;

import com.jgfontes.trade_service.dto.CreateTradeRequestDTO;
import com.jgfontes.trade_service.dto.TradeDTO;
import com.jgfontes.trade_service.service.TradeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/trades")
@Tag(name = "Trade Controller", description = "Endpoints for managing trades")
public class TradeController {

    @Autowired
    private TradeService tradeService;

    @Operation(summary = "Create a new trade")
    @PostMapping("/create")
    public ResponseEntity<TradeDTO> createTrade(@RequestBody CreateTradeRequestDTO trade) {
        TradeDTO created = tradeService.createTrade(trade);
        return ResponseEntity.ok(created);
    }

    @Operation(summary = "Get all trades")
    @GetMapping("/findAll")
    public ResponseEntity<List<TradeDTO>> getAllTrades() {
        return ResponseEntity.ok(tradeService.getAllTrades());
    }

    @Operation(summary = "Get a trade by ID")
    @GetMapping("/find/{id}")
    public ResponseEntity<TradeDTO> getTradeById(@PathVariable UUID id) {
        TradeDTO trade = tradeService.getTradeById(id);
        return trade != null ? ResponseEntity.ok(trade) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Get a trade by ClientID")
    @GetMapping("/find-by-client-id/{clientId}")
    public ResponseEntity<List<TradeDTO>> getTradeByClientId(@PathVariable String clientId) {
        List<TradeDTO> trades = tradeService.getTradesByClientId(clientId);
        return !trades.isEmpty() ? ResponseEntity.ok(trades) : ResponseEntity.notFound().build();
    }
}