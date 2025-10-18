package com.jgfontes.recepy.service;

import com.jgfontes.recepy.config.RabbitMQConnection;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;

@Service
public class RabbitMQServiceImpl implements MessageBrokerService{

    private static final String QUEUE_NAME = "TRADE_QUEUE";

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public void publishMessage(Serializable message) {
        rabbitTemplate.convertAndSend(RabbitMQConnection.TRADE_QUEUE, message);
    }
}
