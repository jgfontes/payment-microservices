package com.jgfontes.receipt_service.consumer;

import com.jgfontes.receipt_service.dto.TradeDTO;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.converter.SimpleMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TradeConsumer {

    private static final String TRADE_QUEUE = "TRADE_QUEUE";

    @RabbitListener(queues = TRADE_QUEUE)
    private void consumer(TradeDTO tradeDTO) {
        System.out.println("Received message: " + tradeDTO);
    }
}
