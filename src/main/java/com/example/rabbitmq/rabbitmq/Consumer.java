package com.example.rabbitmq.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @RabbitListener(queues = "${queue.name}", id = "container")
    public void handler(String msg) {
        logger.info("[Consumer] consumer receive message : {} ", msg);
    }
}
