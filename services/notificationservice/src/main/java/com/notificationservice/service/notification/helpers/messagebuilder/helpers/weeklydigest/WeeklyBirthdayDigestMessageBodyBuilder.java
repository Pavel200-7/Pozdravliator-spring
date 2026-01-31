package com.notificationservice.service.notification.helpers.messagebuilder.helpers.weeklydigest;

import com.notificationservice.service.notification.data.request.data.FriendDTO;
import com.notificationservice.service.notification.helpers.messagebuilder.helpers.BodyBuilder;

import java.util.List;

public interface WeeklyBirthdayDigestMessageBodyBuilder extends BodyBuilder {
    public void setFriends(List<FriendDTO> friends);
}
