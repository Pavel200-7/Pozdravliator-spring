package com.birthdayservice.infrastructure.publisher;

import com.birthdayservice.infrastructure.event.FriendCreatedEvent;
import com.birthdayservice.infrastructure.event.FriendDeletedEvent;
import com.birthdayservice.infrastructure.event.SendingBirthdayReminderMessageStartedEvent;
import com.birthdayservice.infrastructure.event.SendingWeeklyBirthdayDigestStartedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RabbitMQEventPublisherImpl implements RabbitMQEventPublisher {
    private final RabbitTemplate rabbitTemplate;

    public void publishFriendCreated(FriendCreatedEvent event) {
        rabbitTemplate.convertAndSend(
                "friends.exchange",
                "friend.created",
                event
        );
    }

    public void publishFriendDeleted(FriendDeletedEvent event) {
        rabbitTemplate.convertAndSend(
                "friends.exchange",
                "friend.deleted",
                event
        );
    }

    @Override
    public void publishSendingBirthdayReminderMessageStarted(SendingBirthdayReminderMessageStartedEvent event) {
        rabbitTemplate.convertAndSend(
                "notifications.exchange",
                "notification.birthday.reminder",
                event
        );
    }

    @Override
    public void publishSendingWeeklyBirthdayDigestStartedEvent(SendingWeeklyBirthdayDigestStartedEvent event) {
        rabbitTemplate.convertAndSend(
                "notifications.exchange",
                "notification.weekly.digest",
                event
        );
    }
}
