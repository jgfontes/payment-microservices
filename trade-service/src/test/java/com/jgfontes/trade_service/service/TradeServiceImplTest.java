package com.jgfontes.trade_service.service;

import com.jgfontes.trade_service.dto.CreateTradeRequestDTO;
import com.jgfontes.trade_service.dto.TradeDTO;
import com.jgfontes.trade_service.model.Status;
import com.jgfontes.trade_service.model.Trade;
import com.jgfontes.trade_service.model.Type;
import com.jgfontes.trade_service.repository.TradeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TradeServiceImplTest {

    @Mock
    private TradeRepository tradeRepository;

    @Mock
    private MessageBrokerService messageBrokerService;

    @InjectMocks
    private TradeServiceImpl tradeService;

    private UUID tradeId;
    private Trade trade;
    private CreateTradeRequestDTO createRequest;
    private final String clientId = "client123";

    @BeforeEach
    void setUp() {
        tradeId = UUID.randomUUID();
        Instant now = Instant.now();

        trade = Trade.builder()
                .id(tradeId)
                .amount(new BigDecimal("100.50"))
                .currency("USD")
                .type(Type.DEBIT)
                .status(Status.COMPLETED)
                .clientId(clientId)
                .createdAt(now)
                .updatedAt(now)
                .build();

        createRequest = CreateTradeRequestDTO.builder()
                .amount(new BigDecimal("100.50"))
                .currency("USD")
                .type(Type.DEBIT)
                .status(Status.COMPLETED)
                .clientId(clientId)
                .build();
    }

    @Test
    void createTrade_shouldSaveTradeAndPublishMessage() {
        // Arrange
        when(tradeRepository.save(any(Trade.class))).thenReturn(trade);

        // Act
        TradeDTO result = tradeService.createTrade(createRequest);

        // Assert
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(tradeId);
        verify(tradeRepository).save(any(Trade.class));
        verify(messageBrokerService).publishMessage(any(TradeDTO.class));
    }

    @Test
    void getAllTrades_shouldReturnAllTrades() {
        // Arrange
        Trade trade2 = Trade.builder()
                .id(UUID.randomUUID())
                .clientId("client456")
                .status(Status.PENDING)
                .type(Type.CREDIT)
                .build();

        when(tradeRepository.findAll()).thenReturn(Arrays.asList(trade, trade2));

        // Act
        List<TradeDTO> result = tradeService.getAllTrades();

        // Assert
        assertThat(result).hasSize(2);
        assertThat(result.get(0).getId()).isEqualTo(tradeId);
        assertThat(result.get(1).getClientId()).isEqualTo("client456");
    }

    @Test
    void getTradeById_whenTradeExists_shouldReturnTrade() {
        // Arrange
        when(tradeRepository.findById(tradeId)).thenReturn(Optional.of(trade));

        // Act
        TradeDTO result = tradeService.getTradeById(tradeId);

        // Assert
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(tradeId);
        assertThat(result.getStatus()).isEqualTo(Status.COMPLETED);
        assertThat(result.getType()).isEqualTo(Type.DEBIT);
    }

    @Test
    void getTradeById_whenTradeDoesNotExist_shouldReturnNull() {
        // Arrange
        UUID nonExistentId = UUID.randomUUID();
        when(tradeRepository.findById(nonExistentId)).thenReturn(Optional.empty());

        // Act
        TradeDTO result = tradeService.getTradeById(nonExistentId);

        // Assert
        assertThat(result).isNull();
    }

    @Test
    void getTradesByClientId_shouldReturnTradesForClient() {
        // Arrange
        when(tradeRepository.findByClientId(clientId)).thenReturn(Arrays.asList(trade));

        // Act
        List<TradeDTO> result = tradeService.getTradesByClientId(clientId);

        // Assert
        assertThat(result).hasSize(1);
        assertThat(result.getFirst().getClientId()).isEqualTo(clientId);
        assertThat(result.getFirst().getType()).isEqualTo(Type.DEBIT);
    }

    @Test
    void deleteTrade_whenTradeExists_shouldDeleteAndReturnTrue() {
        // Arrange
        when(tradeRepository.existsById(tradeId)).thenReturn(true);

        // Act
        boolean result = tradeService.deleteTrade(tradeId);

        // Assert
        assertThat(result).isTrue();
        verify(tradeRepository).deleteById(tradeId);
    }

    @Test
    void deleteTrade_whenTradeDoesNotExist_shouldReturnFalse() {
        // Arrange
        UUID nonExistentId = UUID.randomUUID();
        when(tradeRepository.existsById(nonExistentId)).thenReturn(false);

        // Act
        boolean result = tradeService.deleteTrade(nonExistentId);

        // Assert
        assertThat(result).isFalse();
        verify(tradeRepository, never()).deleteById(any());
    }
}