package com.notificationservice.service.notification.helpers.iterator.builder;

import com.notificationservice.domain.entities.Subscription;
import com.notificationservice.domain.enums.SubscriptionType;

public interface SubscriptionPageIteratorBuilder {
    public Iterable<Subscription> buildSubscriptionPageIterator(SubscriptionType subscriptionType);
}
