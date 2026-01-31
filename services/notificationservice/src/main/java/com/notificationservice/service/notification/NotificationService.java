package com.notificationservice.service.notification;

import com.notificationservice.service.notification.data.request.*;
import com.notificationservice.service.notification.data.response.*;
import jakarta.mail.MessagingException;

import java.io.FileNotFoundException;

public interface NotificationService {
    public SendBirthdayReminderMessageCResponse sendReminderMessage(SendBirthdayReminderMessageCRequest request);
    public SendWeeklyBirthdayDigestCResponse sendWeeklyBirthdayDigestMessage(SendWeeklyBirthdayDigestCRequest request) throws MessagingException, FileNotFoundException;
}
