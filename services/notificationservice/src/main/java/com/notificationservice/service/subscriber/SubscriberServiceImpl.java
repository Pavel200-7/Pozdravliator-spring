package com.notificationservice.service.subscriber;

import com.notificationservice.domain.entities.Subscriber;
import com.notificationservice.infrastructure.repository.SubscriberRepository;
import com.notificationservice.service.exception.InvalidRequestDataException;
import com.notificationservice.service.subscriber.data.request.CreateSubscriberCRequest;
import com.notificationservice.service.subscriber.data.request.DeleteSubscriberCRequest;
import com.notificationservice.service.subscriber.data.request.GetSubscribersQRequest;
import com.notificationservice.service.subscriber.data.response.CreateSubscriberCResponse;
import com.notificationservice.service.subscriber.data.response.DeleteSubscriberCResponse;
import com.notificationservice.service.subscriber.data.response.GetSubscribersQResponse;
import com.notificationservice.service.subscriber.helpers.mapper.SubscriberMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.hibernate.query.SortDirection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SubscriberServiceImpl implements SubscriberService {

    private final SubscriberMapper mapper;

    private final SubscriberRepository repository;
    @Override
    public CreateSubscriberCResponse createSubscriber(CreateSubscriberCRequest request) {
        if (repository.existsByEmail(request.getEmail())) {
            throw new InvalidRequestDataException("Подписчик с таким email уже есть.");
        }

        Subscriber subscriber = Subscriber.builder()
                .email(request.getEmail())
                .build();
        repository.save(subscriber);
        return new CreateSubscriberCResponse(true);
    }

    @Override
    public DeleteSubscriberCResponse deleteSubscriber(DeleteSubscriberCRequest request) {
        Optional<Subscriber> subscriber = repository.findByEmail(request.getEmail());
        if (subscriber.isEmpty()) {
            throw new EntityNotFoundException("Подписчика с таким email нет.");
        }
        repository.delete(subscriber.get());
        return new DeleteSubscriberCResponse(true);
    }

    @Override
    public GetSubscribersQResponse getSubscribers(GetSubscribersQRequest request) {
        Pageable pageable = createPageable(request);

        Page<Subscriber> friends = switch (request.getSearch()) {
            case null -> repository.findAll(pageable);
            case String search when search.trim().isEmpty() -> repository.findAll(pageable);
            default -> repository.findByEmailContains(request.getSearch(), pageable);
        };

        return mapper.toResponse(friends, request);
    }

    private Pageable createPageable(GetSubscribersQRequest request) {
        Sort.Direction direction = request.getSortDirection() == SortDirection.ASCENDING
                ? Sort.Direction.ASC
                : Sort.Direction.DESC;

        String sortBy = request.getSortBy().getFieldName();
        Sort sort = Sort.by(direction, sortBy);

        return PageRequest.of(
                request.getPage(),
                request.getPageSize(),
                sort
        );
    }
}
