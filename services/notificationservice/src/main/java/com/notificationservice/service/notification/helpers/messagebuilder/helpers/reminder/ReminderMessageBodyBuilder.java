package com.notificationservice.service.notification.helpers.messagebuilder.helpers.reminder;

import com.notificationservice.service.notification.data.request.data.FriendDTO;
import com.notificationservice.service.notification.helpers.messagebuilder.helpers.BodyBuilder;

import java.util.List;

public interface ReminderMessageBodyBuilder extends BodyBuilder {
    public void setFriends(List<FriendDTO> friends);
}
