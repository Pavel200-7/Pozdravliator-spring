package com.notificationservice.service.notification.helpers.messagebuilder;

import com.notificationservice.service.notification.data.request.data.FriendDTO;
import com.notificationservice.service.notification.helpers.messagebuilder.helpers.reminder.ReminderMessageBodyBuilder;
import com.notificationservice.service.notification.helpers.messagebuilder.helpers.weeklydigest.WeeklyBirthdayDigestMessageBodyBuilder;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class MessageBuilderImpl implements MessageBuilder{

    @Autowired
    private final JavaMailSender emailSender;
    private final ReminderMessageBodyBuilder reminderMessageBodyBuilder;
    private final WeeklyBirthdayDigestMessageBodyBuilder weeklyBirthdayDigestMessageBodyBuilder;


    @Override
    public SimpleMailMessage createReminderMessage(List<FriendDTO> friends, String email) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("Pavel200-7@yandex.ru");
        simpleMailMessage.setTo(email);
        simpleMailMessage.setSubject("Напоминение о грядущем празднике.");

        reminderMessageBodyBuilder.setFriends(friends);
        simpleMailMessage.setText(reminderMessageBodyBuilder.buildBody());
        return simpleMailMessage;
    }

    @Override
    public MimeMessage createWeeklyBirthdayDigestMessage(List<FriendDTO> friends, String email)
            throws MessagingException, FileNotFoundException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
        helper.setFrom("Pavel200-7@yandex.ru");
        helper.setTo(email);
        helper.setSubject("Еженедельный дайджест дней рождения ваших друзей");

        weeklyBirthdayDigestMessageBodyBuilder.setFriends(friends);
        helper.setText(weeklyBirthdayDigestMessageBodyBuilder.buildBody(), true);
        return message;
    }
}
