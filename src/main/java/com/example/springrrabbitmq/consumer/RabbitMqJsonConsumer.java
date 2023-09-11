package com.example.springrrabbitmq.consumer;


import com.example.springrrabbitmq.dto.User;
import com.example.springrrabbitmq.producer.RabbitMqJSONProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitMqJsonConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMqJsonConsumer.class);

    @RabbitListener(queues = {"${rabbitmq.queue.json.name}"})
    public void consume(User message)
    {
        LOGGER.info(String.format("Message Consumed -> %s" , message.toString()));
    }
}
