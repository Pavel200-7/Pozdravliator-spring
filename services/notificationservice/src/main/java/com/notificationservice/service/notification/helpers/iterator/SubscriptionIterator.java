package com.notificationservice.service.notification.helpers.iterator;

import com.notificationservice.domain.entities.Subscription;
import com.notificationservice.domain.enums.SubscriptionType;
import com.notificationservice.infrastructure.repository.SubscriptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Collections;
import java.util.Iterator;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
public class SubscriptionIterator implements Iterator<Subscription> {

    private final SubscriptionRepository repository;
    private final SubscriptionType subscriptionType;
    private final int pageSize;

    private int pageNumber = 0;
    private Iterator<Subscription> currentPage = Collections.emptyIterator();
    private boolean hasNextPages = true;

    @Override
    public boolean hasNext() {
        if (currentPage.hasNext()) {
            return true;
        }
        if (hasNextPages) {
            loadNextPage();
            return currentPage.hasNext();
        }
        return false;
    }

    @Override
    public Subscription next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return currentPage.next();
    }

    private void loadNextPage() {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Subscription> page = repository
                .findBySubscriptionTypeWithSubscriber(subscriptionType, pageable);

        currentPage = page.getContent().iterator();
        hasNextPages = page.hasNext();
        pageNumber++;
    }
}
