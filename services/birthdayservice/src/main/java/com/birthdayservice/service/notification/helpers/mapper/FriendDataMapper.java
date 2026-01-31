package com.birthdayservice.service.notification.helpers.mapper;

import com.birthdayservice.domain.entities.Friend;
import com.birthdayservice.infrastructure.event.data.FriendData;

import java.util.List;

public interface FriendDataMapper {
    public FriendData toData(Friend friend);
    public List<FriendData> toDataList(List<Friend> friendData);
}
