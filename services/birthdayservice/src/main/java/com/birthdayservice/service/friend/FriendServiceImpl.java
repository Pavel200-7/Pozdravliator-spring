package com.birthdayservice.service.friend;

import com.birthdayservice.domain.entities.Friend;
import com.birthdayservice.infrastructure.event.FriendCreatedEvent;
import com.birthdayservice.infrastructure.event.FriendDeletedEvent;
import com.birthdayservice.infrastructure.publisher.RabbitMQEventPublisher;
import com.birthdayservice.infrastructure.repository.FriendRepository;
import com.birthdayservice.service.friend.data.request.*;
import com.birthdayservice.service.friend.data.response.*;
import com.birthdayservice.service.friend.helpers.mapper.FriendMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.query.SortDirection;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class FriendServiceImpl implements FriendService {
    private final FriendRepository repository;
    private final FriendMapper mapper;
    private final RabbitMQEventPublisher publisher;

    @Override
    public GetFriendQResponse getFriend(GetFriendQRequest request) {
        Friend friend = repository.findById(request.getId())
                .orElseThrow(EntityNotFoundException::new);
        log.info("Друг с именем {} получен", friend.getFullName());
        return mapper.toResponse(friend);
    }

    @Override
    public GetFriendsQResponse getFriends(GetFriendsQRequest request) {
        Pageable pageable = createPageable(request);

        Page<Friend> friends = switch (request.getSearch()) {
            case null -> repository.findAll(pageable);
            case String search when search.trim().isEmpty() -> repository.findAll(pageable);
            default -> repository.findByFullNameContains(request.getSearch(), pageable);
        };
        log.info("Получена выборка друзей");
        return mapper.toResponse(friends, request);
    }

    private Pageable createPageable(GetFriendsQRequest request) {
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

    @Override
    public GetFriendsWithNearestBirthdayQResponse getFriendsWithNearestBirthday(GetFriendsWithNearestBirthdayQRequest request) {
        Pageable pageable = PageRequest.of(0, request.getLimit());
        Page<Friend> friendsPage = repository.findFriendsWithNearestBirthday(pageable);
        List<Friend> friends = friendsPage.getContent();

        log.info("Получена выборка друзей с ближайшими ДР");
        return new GetFriendsWithNearestBirthdayQResponse(friends.stream()
                .map(f -> mapper.toFriendData(f))
                .toList());
    }

    @Override
    public CreateFriendCResponse createFriend(@Validated CreateFriendCRequest request) {
        Friend friend = mapper.toEntity(request);
        var r = repository.save(friend);

        FriendCreatedEvent event = FriendCreatedEvent.builder()
                .id(friend.getId())
                .fullName(friend.getFullName())
                .birthdayDate(friend.getBirthdayDate())
                .gender(friend.getGender())
                .build();
        publisher.publishFriendCreated(event);

        log.info("Друг с именем {} создан", friend.getFullName());
        return new CreateFriendCResponse(true);
    }

    @Override
    public UpdateFriendCResponse updateFriend(UpdateFriendCRequest request) {
        Friend friend = repository.findById(request.getId())
                .orElseThrow(EntityNotFoundException::new);
        friend = mapper.toEntity(friend, request);
        repository.save(friend);

        log.info("Данные друга с именем {} изменены", friend.getFullName());
        return new UpdateFriendCResponse(true);
    }

    @Override
    public DeleteFriendCResponse deleteFriend(DeleteFriendCRequest request) {
        Friend friend = repository.findById(request.getId())
                .orElseThrow(EntityNotFoundException::new);
        repository.delete(friend);

        FriendDeletedEvent event = FriendDeletedEvent.builder()
                .id(friend.getId())
                .build();
        publisher.publishFriendDeleted(event);

        log.info("Друг с именем {} удален", friend.getFullName());
        return new DeleteFriendCResponse(true);
    }
}
