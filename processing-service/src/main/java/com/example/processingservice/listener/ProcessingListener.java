package com.example.processingservice.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import static java.util.Objects.isNull;

/**
 * @author mojib.haider
 * @since 7/8/24
 */
@Slf4j
@Component
public class ProcessingListener {

    private final WebClient webClient;


    public ProcessingListener(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://oauth-service").build();
    }

    @RabbitListener(queues = "test-queue")
    public void listener(String message) throws Exception {
        log.info("Processing Service Queue Listener");
        log.info(message);

        String response = webClient.get()
                .uri("/auth/verify")
                .retrieve()
                .bodyToMono(String.class)
                .block();

        if (isNull(response)) {
            throw new Exception();
        }
    }
}
