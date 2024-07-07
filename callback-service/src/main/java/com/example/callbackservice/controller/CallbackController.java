package com.example.callbackservice.controller;

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
@RestController
@RequestMapping("/callback")
public class CallbackController {

    private final WebClient webClient;

    public CallbackController(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://processing-service").build();
    }

    @GetMapping("/notify")
    public ResponseEntity<String> sendNotification(){
        return new ResponseEntity<>("sent notification", HttpStatus.OK);
    }

    @GetMapping("/test")
    public Mono<String> test(){
        return webClient.get()
                .uri("/processing/save")
                .retrieve()
                .bodyToMono(String.class);
    }
}
