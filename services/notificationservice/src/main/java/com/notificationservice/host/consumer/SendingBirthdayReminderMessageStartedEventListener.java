package com.notificationservice.host.consumer;

import com.notificationservice.host.consumer.helpers.mapper.FriendDataMapper;
import com.notificationservice.infrastructure.event.SendingBirthdayReminderMessageStartedEvent;
import com.notificationservice.service.notification.NotificationService;
import com.notificationservice.service.notification.data.request.SendBirthdayReminderMessageCRequest;
import com.notificationservice.service.notification.data.request.data.FriendDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class SendingBirthdayReminderMessageStartedEventListener {

    private final NotificationService service;
    private final FriendDataMapper mapper;

    @RabbitListener(queues = "notifications.queue")
    public void handleSendingBirthdayReminderMessageStartedEvent(SendingBirthdayReminderMessageStartedEvent event) {
        List<FriendDTO> friendDTO = mapper.toDtoList(event.getFriends());
        SendBirthdayReminderMessageCRequest cRequest = new SendBirthdayReminderMessageCRequest(friendDTO);
        service.sendReminderMessage(cRequest);
        log.info("Сообщения, нопоминающие о завтрашнем (них) др отправлены");
    }
}
