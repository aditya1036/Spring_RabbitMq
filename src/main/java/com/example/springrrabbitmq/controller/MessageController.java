package com.example.springrrabbitmq.controller;

import com.example.springrrabbitmq.producer.RabbitMqProducer;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class MessageController {

    private RabbitMqProducer rabbitMqProducer;
    public MessageController(RabbitMqProducer rabbitMqProducer) {
        this.rabbitMqProducer = rabbitMqProducer;
    }

    @GetMapping("/publish")
    public ResponseEntity<String> sendMessage(@RequestParam("message") String message)
    {
        rabbitMqProducer.sendMessage(message);
        return ResponseEntity.ok("Message Sent to RabbitMQ");
    }



}
