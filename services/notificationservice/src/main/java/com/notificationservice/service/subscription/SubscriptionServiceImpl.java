package com.notificationservice.service.subscription;

import com.notificationservice.domain.entities.Subscriber;
import com.notificationservice.domain.entities.Subscription;
import com.notificationservice.domain.enums.SubscriptionType;
import com.notificationservice.infrastructure.repository.SubscriberRepository;
import com.notificationservice.infrastructure.repository.SubscriptionRepository;
import com.notificationservice.service.exception.InvalidRequestDataException;
import com.notificationservice.service.subscription.data.request.CreateSubscriptionCRequest;
import com.notificationservice.service.subscription.data.request.DeleteSubscriptionCRequest;
import com.notificationservice.service.subscription.data.request.GetSubscriptionsBySubscriberIdQRequest;
import com.notificationservice.service.subscription.data.request.GetSubscriptionsQRequest;
import com.notificationservice.service.subscription.data.response.CreateSubscriptionCResponse;
import com.notificationservice.service.subscription.data.response.DeleteSubscriptionCResponse;
import com.notificationservice.service.subscription.data.response.GetSubscriptionsBySubscriberIdQResponse;
import com.notificationservice.service.subscription.data.response.GetSubscriptionsQResponse;
import com.notificationservice.service.subscription.data.response.data.SubscriberSubscriptionData;
import com.notificationservice.service.subscription.data.response.data.SubscriptionData;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SubscriptionServiceImpl implements SubscriptionService {

    private final SubscriberRepository subscriberRepository;
    private final SubscriptionRepository subscriptionRepository;

    @Override
    public GetSubscriptionsQResponse getSubscriptions(GetSubscriptionsQRequest request) {
        List<SubscriptionData> subscriptions = Arrays.stream(SubscriptionType.values())
                .map(t -> SubscriptionData.builder()
                        .code(t.getCode())
                        .title(t.getTitle())
                        .description(t.getDescription())
                        .name(t.name())
                        .build()
                ).collect(Collectors.toUnmodifiableList());

        return new GetSubscriptionsQResponse(subscriptions);
    }

    @Override
    public GetSubscriptionsBySubscriberIdQResponse getSubscriptionsBySubscriberId(GetSubscriptionsBySubscriberIdQRequest request) {
        List<Subscription> activeSubscriptions = subscriptionRepository.findBySubscriberId(request.getId());

        List<SubscriberSubscriptionData> subscriptionsData = Arrays.stream(SubscriptionType.values())
                .map(s -> SubscriberSubscriptionData.builder()
                        .code(s.getCode())
                        .title(s.getTitle())
                        .description(s.getDescription())
                        .name(s.name())
                        .isActive(activeSubscriptions.stream().anyMatch(sa -> sa.getSubscriptionType() == s))
                        .build()
                ).collect(Collectors.toUnmodifiableList());

        return new GetSubscriptionsBySubscriberIdQResponse(subscriptionsData);
    }


    @Override
    public CreateSubscriptionCResponse createSubscription(CreateSubscriptionCRequest request) {
        Optional<Subscriber> subscriber = subscriberRepository.findByEmail(request.getEmail());
        if (subscriber.isEmpty()) {
            throw new EntityNotFoundException("Подписчика с таким email нет.");
        }
        boolean isTheSameSubscriptionExist = subscriptionRepository.existsBySubscriberIdAndSubscriptionType(
                subscriber.get().getId(),
                request.getSubscriptionType());
        if (isTheSameSubscriptionExist) {
            throw new InvalidRequestDataException("У данного подписчика уже есть эта подписка");
        }

        Subscription subscription = Subscription.builder()
                .subscriber(subscriber.get())
                .subscriptionType(request.getSubscriptionType())
                .build();
        subscriptionRepository.save(subscription);
        return new CreateSubscriptionCResponse(true);
    }

    @Override
    public DeleteSubscriptionCResponse deleteSubscription(DeleteSubscriptionCRequest request) {
        Optional<Subscriber> subscriber = subscriberRepository.findByEmail(request.getEmail());
        if (subscriber.isEmpty()) {
            throw new EntityNotFoundException("Подписчика с таким email нет.");
        }
        Optional<Subscription> subscription = subscriptionRepository.findBySubscriberIdAndSubscriptionType(
                subscriber.get().getId(),
                request.getSubscriptionType());
        if (subscription.isEmpty()) {
            throw new EntityNotFoundException("У данного подписчика нет такой подписки");
        }

        subscriptionRepository.delete(subscription.get());
        return new DeleteSubscriptionCResponse(true);
    }
}
