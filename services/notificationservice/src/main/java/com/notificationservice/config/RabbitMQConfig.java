package com.notificationservice.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Bean
    public TopicExchange notificationsExchange() {
        return new TopicExchange("notifications.exchange");
    }

    @Bean
    public Queue notificationsQueue() {
        return QueueBuilder.durable("notifications.queue")
                .withArgument("x-dead-letter-exchange", "notifications.dlx")
                .build();
    }

    @Bean
    public Binding birthdayReminderBinding() {
        return BindingBuilder.bind(notificationsQueue())
                .to(notificationsExchange())
                .with("notification.birthday.reminder");
    }

    @Bean
    public Binding weeklyDigestBinding() {
        return BindingBuilder.bind(notificationsQueue())
                .to(notificationsExchange())
                .with("notification.weekly.digest");
    }

    @Bean
    public Jackson2JsonMessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
