package com.example.springrrabbitmq.config;


import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {

    // Spring Bean for Rabbit MQ Queue

    @Value("${rabbitmq.queue.name}")
    private String queue;

    @Value("${rabbitmq.queue.json.name}")
    private String queue_json;


    @Value("${rabbitmq.exchange.name}")
    private String exchange_name;

    @Value("${rabbitmq.exchange.json.name}")
    private String json_exchange_name;


    @Value("${rabbitmq.routing.key}")
    private String routing_key;

    @Value("${rabbitmq.routing.json.key}")
    private String json_routing_key;

    @Bean
    public Queue queue() {
        return new Queue(queue);
    }

    @Bean
    public Queue jsonQueue() {
        return new Queue(queue_json);
    }

    @Bean
    public TopicExchange exchange()
    {
        return new TopicExchange(exchange_name);
    }

    @Bean
    public TopicExchange jsonExchange()
    {
        return new TopicExchange(json_exchange_name);
    }

    @Bean
    public Binding binding()
    {
        return BindingBuilder
                .bind(queue())
                .to(exchange())
                .with(routing_key);
    }

    @Bean
    public Binding bindingJson()
    {
        return BindingBuilder
                .bind(jsonQueue())
                .to(jsonExchange())
                .with(json_routing_key);
    }


    @Bean
    public MessageConverter converter(){
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory)
    {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter());
        return rabbitTemplate;
    }



}
