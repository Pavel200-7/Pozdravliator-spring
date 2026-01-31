package com.birthdayservice.infrastructure.publisher;

import com.birthdayservice.infrastructure.event.*;

public interface RabbitMQEventPublisher {
        public void publishFriendCreated(FriendCreatedEvent event);
        public void publishFriendDeleted(FriendDeletedEvent event);
        public void publishSendingBirthdayReminderMessageStarted(SendingBirthdayReminderMessageStartedEvent event);
        public void publishSendingWeeklyBirthdayDigestStartedEvent(SendingWeeklyBirthdayDigestStartedEvent event);
}
