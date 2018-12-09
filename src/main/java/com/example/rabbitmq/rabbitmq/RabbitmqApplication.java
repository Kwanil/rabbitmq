package com.example.rabbitmq.rabbitmq;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableRabbit
public class RabbitmqApplication {
    private static final String ROUTING_KEY = "foo.bar.#";

    @Value("${queue.name}")
    String queueName;

    @Value("${topic.exchange.name}")
    private String topicExchangeName;

    @Bean
    Queue queue() {
        return QueueBuilder.nonDurable(queueName).build();
    }

    @Bean
    Exchange exchange() {
        return ExchangeBuilder.topicExchange(topicExchangeName).build();
    }

    @Bean
    Binding binding(Queue queue, Exchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY).noargs();
    }

    @Bean
    SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
                                             MessageListenerAdapter listenerAdapter) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(queueName);
        container.setMessageListener(listenerAdapter);
        return container;
    }

    @Bean
    MessageListenerAdapter listenerAdapter(Consumer consumer) {
        return new MessageListenerAdapter(consumer, "receiveMessage");
    }

    @Bean
    CommandLineRunner send(Producer producer) {
        return args -> producer.sendTo(topicExchangeName, "hello world");
    }

    public static void main(String[] args) {
        SpringApplication.run(RabbitmqApplication.class, args).close();
    }
}
