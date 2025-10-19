package com.jgfontes.receipt_service.consumer;

import com.jgfontes.receipt_service.dto.TradeDTO;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class TradeConsumer {

    private static final String TRADE_QUEUE = "TRADE_QUEUE";

    @RabbitListener(queues = TRADE_QUEUE)
    private void consumer(TradeDTO tradeDTO) {
        printReceipt(tradeDTO);
    }

    public void printReceipt(TradeDTO tradeDTO) {
        System.out.println("===================================");
        System.out.println("           TRADE RECEIPT           ");
        System.out.println("===================================");
        System.out.println("Trade ID   : " + tradeDTO.getId());
        System.out.println("Client ID  : " + tradeDTO.getClientId());
        System.out.println("Amount     : " + tradeDTO.getAmount() + " " + tradeDTO.getCurrency());
        System.out.println("Status     : " + tradeDTO.getStatus());
        System.out.println("Type       : " + tradeDTO.getType());
        System.out.println("Created At : " + tradeDTO.getCreatedAt());
        System.out.println("Updated At : " + tradeDTO.getUpdatedAt());
        System.out.println("===================================");
    }
}
