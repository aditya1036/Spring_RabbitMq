package com.example.springrrabbitmq.producer;

import com.example.springrrabbitmq.dto.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMqJSONProducer {
    @Value("${rabbitmq.exchange.json.name}")
    private String json_exchange;
    @Value("${rabbitmq.routing.json.key}")
    public String json_routing_key;

    private RabbitTemplate rabbitTemplate;

    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMqJSONProducer.class);

    public RabbitMqJSONProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }


    public void sendMessage(User user)
    {
        LOGGER.info(String.format("Message sent -> %s" , user.toString()));
        rabbitTemplate.convertAndSend(json_exchange , json_routing_key , user);
    }


}
