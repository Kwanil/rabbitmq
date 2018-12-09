package com.example.rabbitmq.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Producer {
    private static final String ROUTING_KEY = "foo.bar.baz";
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public Producer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendTo(String exchange, Object message) {
        logger.info("[Producer] send message : {}, {} ", exchange, message);
        rabbitTemplate.convertAndSend(exchange, ROUTING_KEY, message);
    }
}
