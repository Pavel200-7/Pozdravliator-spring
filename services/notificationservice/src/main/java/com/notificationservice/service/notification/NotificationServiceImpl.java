package com.notificationservice.service.notification;

import com.notificationservice.domain.entities.Subscription;
import com.notificationservice.domain.enums.SubscriptionType;
import com.notificationservice.service.notification.data.request.SendBirthdayReminderMessageCRequest;
import com.notificationservice.service.notification.data.request.SendWeeklyBirthdayDigestCRequest;
import com.notificationservice.service.notification.data.response.SendBirthdayReminderMessageCResponse;
import com.notificationservice.service.notification.data.response.SendWeeklyBirthdayDigestCResponse;
import com.notificationservice.service.notification.helpers.iterator.builder.SubscriptionPageIteratorBuilder;
import com.notificationservice.service.notification.helpers.messagebuilder.MessageBuilder;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final SubscriptionPageIteratorBuilder builder;
    private final MessageBuilder messageBuilder;
    private final JavaMailSender emailSender;


    @Override
    public SendBirthdayReminderMessageCResponse sendReminderMessage(SendBirthdayReminderMessageCRequest request) {
        Iterable<Subscription> subscriptionIterable = builder.buildSubscriptionPageIterator(SubscriptionType.BIRTHDAY_REMINDER);
        for (Subscription subscription : subscriptionIterable) {
            String email = subscription.getSubscriber().getEmail();
            SimpleMailMessage message = messageBuilder.createReminderMessage(request.getFriends(), email);
            emailSender.send(message);
        }
        return new SendBirthdayReminderMessageCResponse(true);
    }

    @Override
    public SendWeeklyBirthdayDigestCResponse sendWeeklyBirthdayDigestMessage(SendWeeklyBirthdayDigestCRequest request)
            throws MessagingException, FileNotFoundException {
        Iterable<Subscription> subscriptionIterable = builder.buildSubscriptionPageIterator(SubscriptionType.WEEKLY_BIRTHDAY_DIGEST);
        for (Subscription subscription : subscriptionIterable) {
            String email = subscription.getSubscriber().getEmail();
            MimeMessage message = messageBuilder.createWeeklyBirthdayDigestMessage(request.getFriends(), email);
            emailSender.send(message);
        }
        return new SendWeeklyBirthdayDigestCResponse(true);
    }
}
