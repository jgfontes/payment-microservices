package com.jgfontes.trade_service.util;

import com.jgfontes.trade_service.dto.CreateTradeRequestDTO;
import com.jgfontes.trade_service.dto.TradeDTO;
import com.jgfontes.trade_service.model.Currency;
import com.jgfontes.trade_service.model.Status;
import com.jgfontes.trade_service.model.Trade;
import com.jgfontes.trade_service.model.Type;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class TradeMapperTest {

    @Test
    void mapCreateRequestToEntity_shouldMapAllFields() {
        // Arrange
        CreateTradeRequestDTO request = CreateTradeRequestDTO.builder()
                .amount(new BigDecimal("100.50"))
                .currency(Currency.USD)
                .status(Status.COMPLETED)
                .type(Type.DEBIT)
                .clientId("client123")
                .build();

        // Act
        Trade result = TradeMapper.mapCreateRequestToEntity(request);

        // Assert
        assertThat(result).isNotNull();
        assertThat(result.getAmount()).isEqualByComparingTo("100.50");
        assertThat(result.getCurrency()).isEqualTo(Currency.USD);
        assertThat(result.getStatus()).isEqualTo(Status.COMPLETED);
        assertThat(result.getType()).isEqualTo(Type.DEBIT);
        assertThat(result.getClientId()).isEqualTo("client123");
        assertThat(result.getCreatedAt()).isNotNull();
        assertThat(result.getUpdatedAt()).isNotNull();
    }

    @Test
    void mapDTOToEntity_shouldMapAllFields() {
        // Arrange
        UUID id = UUID.randomUUID();
        Instant createdAt = Instant.now().minusSeconds(3600);
        Instant updatedAt = Instant.now();

        TradeDTO dto = TradeDTO.builder()
                .id(id)
                .amount(new BigDecimal("200.75"))
                .currency(Currency.EUR)
                .status(Status.PENDING)
                .type(Type.CREDIT)
                .clientId("client456")
                .createdAt(createdAt)
                .updatedAt(updatedAt)
                .build();

        // Act
        Trade result = TradeMapper.mapDTOToEntity(dto);

        // Assert
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(id);
        assertThat(result.getAmount()).isEqualByComparingTo("200.75");
        assertThat(result.getCurrency()).isEqualTo(Currency.EUR);
        assertThat(result.getStatus()).isEqualTo(Status.PENDING);
        assertThat(result.getType()).isEqualTo(Type.CREDIT);
        assertThat(result.getClientId()).isEqualTo("client456");
        assertThat(result.getCreatedAt()).isEqualTo(createdAt);
        assertThat(result.getUpdatedAt()).isEqualTo(updatedAt);
    }

    @Test
    void mapEntityToDTO_shouldMapAllFields() {
        // Arrange
        UUID id = UUID.randomUUID();
        Instant createdAt = Instant.now().minusSeconds(7200);
        Instant updatedAt = Instant.now().minusSeconds(3600);

        Trade entity = Trade.builder()
                .id(id)
                .amount(new BigDecimal("300.25"))
                .currency(Currency.EUR)
                .status(Status.FAILED)
                .type(Type.DEBIT)
                .clientId("client789")
                .createdAt(createdAt)
                .updatedAt(updatedAt)
                .build();

        // Act
        TradeDTO result = TradeMapper.mapEntityToDTO(entity);

        // Assert
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(id);
        assertThat(result.getAmount()).isEqualByComparingTo("300.25");
        assertThat(result.getCurrency()).isEqualTo(Currency.EUR);
        assertThat(result.getStatus()).isEqualTo(Status.FAILED);
        assertThat(result.getType()).isEqualTo(Type.DEBIT);
        assertThat(result.getClientId()).isEqualTo("client789");
        assertThat(result.getCreatedAt()).isEqualTo(createdAt);
        assertThat(result.getUpdatedAt()).isEqualTo(updatedAt);
    }

    @Test
    void mapCreateRequestToEntity_shouldHandleNullValues() {
        // Arrange
        CreateTradeRequestDTO request = CreateTradeRequestDTO.builder()
                .amount(null)
                .currency(null)
                .status(null)
                .type(null)
                .clientId(null)
                .build();

        // Act
        Trade result = TradeMapper.mapCreateRequestToEntity(request);

        // Assert
        assertThat(result).isNotNull();
        assertThat(result.getAmount()).isNull();
        assertThat(result.getCurrency()).isNull();
        assertThat(result.getStatus()).isNull();
        assertThat(result.getType()).isNull();
        assertThat(result.getClientId()).isNull();
        assertThat(result.getCreatedAt()).isNotNull();
        assertThat(result.getUpdatedAt()).isNotNull();
    }
}