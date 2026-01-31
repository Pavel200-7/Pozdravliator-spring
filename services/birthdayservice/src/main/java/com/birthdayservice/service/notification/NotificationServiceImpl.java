package com.birthdayservice.service.notification;


import com.birthdayservice.domain.entities.Friend;
import com.birthdayservice.infrastructure.event.SendingBirthdayReminderMessageStartedEvent;
import com.birthdayservice.infrastructure.event.SendingWeeklyBirthdayDigestStartedEvent;
import com.birthdayservice.infrastructure.event.data.FriendData;
import com.birthdayservice.infrastructure.publisher.RabbitMQEventPublisher;
import com.birthdayservice.infrastructure.repository.FriendRepository;
import com.birthdayservice.service.notification.data.request.SendBirthdayReminderMessageCRequest;
import com.birthdayservice.service.notification.data.request.SendWeeklyBirthdayDigestCRequest;
import com.birthdayservice.service.notification.data.response.SendBirthdayReminderMessageCResponse;
import com.birthdayservice.service.notification.data.response.SendWeeklyBirthdayDigestCResponse;
import com.birthdayservice.service.notification.helpers.mapper.FriendDataMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final int MAX_ITEMS_A_PAGE = 30;

    private final FriendRepository repository;
    private final FriendDataMapper mapper;
    private final RabbitMQEventPublisher publisher;

    @Override
    public SendBirthdayReminderMessageCResponse sendReminderMessage(SendBirthdayReminderMessageCRequest request) {
        LocalDate tomorrow = LocalDate.now().plusDays(1);
        List<Friend> friends = repository.findFriendsByBirthdayMonthAndDay(
                tomorrow.getMonthValue(),
                tomorrow.getDayOfMonth(),
                PageRequest.of(0, MAX_ITEMS_A_PAGE)
        );

        if (friends.isEmpty()) {
            log.info("Завтра никто не празднует день рожденья");
            return new SendBirthdayReminderMessageCResponse(true);
        }

        List<FriendData> friendData = mapper.toDataList(friends);
        SendingBirthdayReminderMessageStartedEvent event = new SendingBirthdayReminderMessageStartedEvent(friendData);
        publisher.publishSendingBirthdayReminderMessageStarted(event);
        return new SendBirthdayReminderMessageCResponse(true);
    }

    @Override
    public SendWeeklyBirthdayDigestCResponse sendWeeklyBirthdayDigestMessage(SendWeeklyBirthdayDigestCRequest request) {
        List<Friend> friends = repository.findNextWeekBirthdaysFriends(PageRequest.of(0, MAX_ITEMS_A_PAGE));
        List<FriendData> friendData = mapper.toDataList(friends);
        SendingWeeklyBirthdayDigestStartedEvent event = new SendingWeeklyBirthdayDigestStartedEvent(friendData);

        log.info("Началась отправка еженедельной рассылки о ближайших ДР");
        publisher.publishSendingWeeklyBirthdayDigestStartedEvent(event);
        return new SendWeeklyBirthdayDigestCResponse(true);
    }
}
