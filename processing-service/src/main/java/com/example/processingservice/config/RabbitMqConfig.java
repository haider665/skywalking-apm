package com.example.processingservice.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author mojib.haider
 * @since 7/8/24
 */
@Configuration
public class RabbitMqConfig {

    @Bean
    public Queue queue() {
        return new Queue("test-queue");
    }

    @Bean
    public DirectExchange exchange() {
        return ExchangeBuilder.directExchange("test-exchange").build();
    }

    @Bean
    public Binding binding(DirectExchange exchange, Queue queue) {
        return BindingBuilder.bind(queue).to(exchange).with("test-routing-key");
    }
}
