package com.jgfontes.recepy.controller;

import com.jgfontes.recepy.dto.CreateTradeRequestDTO;
import com.jgfontes.recepy.dto.TradeDTO;
import com.jgfontes.recepy.dto.UpdateTradeRequestDTO;
import com.jgfontes.recepy.model.Trade;
import com.jgfontes.recepy.service.TradeService;
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
    @GetMapping("/{id}")
    public ResponseEntity<TradeDTO> getTradeById(@PathVariable UUID id) {
        TradeDTO trade = tradeService.getTradeById(id);
        return trade != null ? ResponseEntity.ok(trade) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Update a trade")
    @PutMapping("/{id}")
    public ResponseEntity<TradeDTO> updateTrade(@PathVariable UUID id, @RequestBody UpdateTradeRequestDTO updateTradeRequestDTO) {
        TradeDTO updated = tradeService.updateTrade(id, updateTradeRequestDTO);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Delete a trade")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTrade(@PathVariable UUID id) {
        boolean deleted = tradeService.deleteTrade(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}