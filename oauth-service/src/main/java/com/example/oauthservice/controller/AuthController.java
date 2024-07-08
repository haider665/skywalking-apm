package com.example.oauthservice.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author mojib.haider
 * @since 7/8/24
 */
@Slf4j
@RestController
@RequestMapping("/auth")
public class AuthController {

    @GetMapping("/verify")
    public ResponseEntity<String> verify(){
        log.info("Auth service verify");
        return new ResponseEntity<>("Verified", HttpStatus.OK);
    }
}
