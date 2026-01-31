package com.notificationservice.host.consumer;

import com.notificationservice.host.consumer.helpers.mapper.FriendDataMapper;
import com.notificationservice.infrastructure.event.SendingBirthdayReminderMessageStartedEvent;
import com.notificationservice.infrastructure.event.SendingWeeklyBirthdayDigestStartedEvent;
import com.notificationservice.service.notification.NotificationService;
import com.notificationservice.service.notification.data.request.SendWeeklyBirthdayDigestCRequest;
import com.notificationservice.service.notification.data.request.data.FriendDTO;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class SendingWeeklyBirthdayDigestStartedEventListener {

    private final NotificationService service;
    private final FriendDataMapper mapper;

    @RabbitListener(queues = "notifications.queue")
    public void handleSendingWeeklyBirthdayDigestStartedEvent(SendingWeeklyBirthdayDigestStartedEvent event)
            throws MessagingException, FileNotFoundException {
        List<FriendDTO> friendDTO = mapper.toDtoList(event.getFriends());
        SendWeeklyBirthdayDigestCRequest cRequest = new SendWeeklyBirthdayDigestCRequest(friendDTO);
        service.sendWeeklyBirthdayDigestMessage(cRequest);
        log.info("Сообщения, нопоминающие о грядущих др отправлены");
    }
}
