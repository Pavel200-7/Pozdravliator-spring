package com.birthdayservice;

import com.birthdayservice.service.notification.NotificationService;
import com.birthdayservice.service.notification.data.request.SendBirthdayReminderMessageCRequest;
import com.birthdayservice.service.notification.data.request.SendWeeklyBirthdayDigestCRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ScheduledTasks {

    private final NotificationService service;

    @Scheduled(cron = "0 0 8 * * *")
    public void SendBirthdayReminderMessage() {
        service.sendReminderMessage(new SendBirthdayReminderMessageCRequest());
        log.info("Началась отправка сообщения о завтрашних др");
    }

    @Scheduled(cron = "0 0 8 * * SUN")
    public void SendWeeklyBirthdayDigest() {
        service.sendWeeklyBirthdayDigestMessage(new SendWeeklyBirthdayDigestCRequest());
        log.info("Началась отправка сообщения о др на следующей неделе");
    }
}
