package com.jgfontes.recepy.config;

import jakarta.annotation.PostConstruct;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import org.springframework.amqp.core.Queue;

@Component
public class RabbitMQConnection {

    private static final String EXCHANGE_NAME = "amq.direct";
    public static final String TRADE_QUEUE = "TRADE_QUEUE";
    private AmqpAdmin amqpAdmin;

    public RabbitMQConnection(AmqpAdmin amqpAdmin) {
        this.amqpAdmin = amqpAdmin;
    }

    private Queue queue() {
        return new Queue(TRADE_QUEUE, true, false, false);
    }

    private DirectExchange directExchange() {
        return new DirectExchange(EXCHANGE_NAME);
    }

    private Binding binding(Queue queue, DirectExchange exchange) {
        return new Binding(queue.getName(), Binding.DestinationType.QUEUE, exchange.getName(), queue.getName(), null);
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @PostConstruct
    private void addQueue() {
        int maxAttempts = 10;
        int attempt = 0;
        while (attempt < maxAttempts) {
            try {
                Queue tradeQueue = queue();
                DirectExchange exchange = directExchange();

                amqpAdmin.declareQueue(tradeQueue);
                amqpAdmin.declareExchange(exchange);
                amqpAdmin.declareBinding(binding(tradeQueue, exchange));
                return; // Success
            } catch (Exception e) {
                attempt++;
                System.err.println("RabbitMQ initialization error (attempt " + attempt + "): " + e.getMessage());
                if (attempt >= maxAttempts) {
                    throw e;
                }
                try {
                    Thread.sleep(2000); // Wait before retry
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                    throw new RuntimeException(ie);
                }
            }
        }
    }
}
