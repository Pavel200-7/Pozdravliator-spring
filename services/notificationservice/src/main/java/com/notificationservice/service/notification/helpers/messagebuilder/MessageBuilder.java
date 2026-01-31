package com.notificationservice.service.notification.helpers.messagebuilder;

import com.notificationservice.service.notification.data.request.data.FriendDTO;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.SimpleMailMessage;

import java.io.FileNotFoundException;
import java.util.List;

public interface MessageBuilder {
    public SimpleMailMessage createReminderMessage(List<FriendDTO> friends, String email);
    public MimeMessage createWeeklyBirthdayDigestMessage(List<FriendDTO> friends, String email) throws MessagingException, FileNotFoundException;
}
