package com.imageservice.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableRabbit
public class RabbitMQConsumerConfig {

    @Bean
    public TopicExchange friendsExchange() {
        return new TopicExchange("friends.exchange");
    }

    @Bean
    public Queue friendQueue() {
        return QueueBuilder.durable("friends.queue")
                .withArgument("x-dead-letter-exchange", "friend.dlx")
                .build();
    }

    @Bean
    public Binding friendCreatedBinding() {
        return BindingBuilder.bind(friendQueue())
                .to(friendsExchange())
                .with("friend.created");
    }

    @Bean
    public Binding friendDeletedBinding() {
        return BindingBuilder.bind(friendQueue())
                .to(friendsExchange())
                .with("friend.deleted");
    }

    @Bean
    public Jackson2JsonMessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
