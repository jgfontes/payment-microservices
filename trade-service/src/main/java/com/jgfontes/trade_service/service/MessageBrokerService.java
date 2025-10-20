package com.jgfontes.trade_service.service;

import java.io.Serializable;

public interface MessageBrokerService {
    void publishMessage(Serializable message);
}
