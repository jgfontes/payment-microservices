package com.jgfontes.recepy.service;

import java.io.Serializable;

public interface MessageBrokerService {
    void publishMessage(Serializable message);
}
