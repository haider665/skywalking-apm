package com.example.callbackservice.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 * @author mojib.haider
 * @since 7/7/24
 */
@Slf4j
@RestController
@RequestMapping("/callback")
public class CallbackController {

    private final WebClient webClient;
    private final RabbitTemplate rabbitTemplate;


    public CallbackController(WebClient.Builder webClientBuilder, RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
        this.webClient = webClientBuilder.baseUrl("http://processing-service").build();
    }

    @GetMapping("/notify")
    public ResponseEntity<String> sendNotification(){
        log.info("Callback service notify");

        rabbitTemplate.convertAndSend("test-exchange", "test-routing-key", "message");

        return new ResponseEntity<>("sent notification", HttpStatus.OK);
    }

    @GetMapping("/delay")
    public ResponseEntity<String> delay() throws InterruptedException {
        log.info("Callback service delay");

        Thread.sleep(3000);

        return new ResponseEntity<>("sent notification", HttpStatus.OK);
    }

    @GetMapping("/error")
    public ResponseEntity<String> error() throws Exception {
        log.info("Callback service error");

        throw new Exception();
    }

    @GetMapping("/save")
    public Mono<String> test(){
        log.info("Callback service test");

        return webClient.get()
                .uri("/processing/save")
                .retrieve()
                .bodyToMono(String.class);
    }
}
