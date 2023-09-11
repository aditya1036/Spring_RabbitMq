package com.example.springrrabbitmq.controller;


import com.example.springrrabbitmq.dto.User;
import com.example.springrrabbitmq.producer.RabbitMqJSONProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1")
public class MessageJSONController {

    @Autowired
    private RabbitMqJSONProducer rabbitMqJSONProducer;

    @PostMapping("/publish/json")
    public ResponseEntity<String> sendJson(@RequestBody User user)
    {
        rabbitMqJSONProducer.sendMessage(user);
        return ResponseEntity.ok("JSON Message sent to Rabbit MQ......");
    }
}
