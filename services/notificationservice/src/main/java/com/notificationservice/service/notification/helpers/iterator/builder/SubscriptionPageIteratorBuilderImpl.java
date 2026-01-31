package com.notificationservice.service.notification.helpers.iterator.builder;

import com.notificationservice.domain.entities.Subscription;
import com.notificationservice.domain.enums.SubscriptionType;
import com.notificationservice.infrastructure.repository.SubscriptionRepository;
import com.notificationservice.service.notification.helpers.iterator.SubscriptionIterable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SubscriptionPageIteratorBuilderImpl implements SubscriptionPageIteratorBuilder {

    private final int PAGE_SIZE = 100;
    private final SubscriptionRepository repository;

    public Iterable<Subscription> buildSubscriptionPageIterator(SubscriptionType subscriptionType) {
        return new SubscriptionIterable(
                repository,
                subscriptionType,
                PAGE_SIZE);
    }
}
