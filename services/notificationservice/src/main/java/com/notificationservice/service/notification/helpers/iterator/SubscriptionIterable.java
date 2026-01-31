package com.notificationservice.service.notification.helpers.iterator;

import com.notificationservice.domain.entities.Subscription;
import com.notificationservice.domain.enums.SubscriptionType;
import com.notificationservice.infrastructure.repository.SubscriptionRepository;
import lombok.RequiredArgsConstructor;

import java.util.Iterator;

@RequiredArgsConstructor
public class SubscriptionIterable implements Iterable<Subscription> {

    private final SubscriptionRepository repository;
    private final SubscriptionType subscriptionType;
    private final int pageSize;

    @Override
    public Iterator<Subscription> iterator() {
        return new SubscriptionIterator(repository, subscriptionType, pageSize);
    }
}
