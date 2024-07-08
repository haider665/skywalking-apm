package com.example.processingservice.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author mojib.haider
 * @since 7/7/24
 */
@Slf4j
@RestController
@RequestMapping("/processing")
public class ProcessingController {

    @GetMapping("/save")
    public ResponseEntity<String> save(){
        log.info("Processing Service save");
        return new ResponseEntity<>("Processed", HttpStatus.OK);
    }
}
