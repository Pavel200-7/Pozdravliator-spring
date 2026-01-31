package com.notificationservice.host.consumer.helpers.mapper;

import com.notificationservice.infrastructure.event.data.FriendData;
import com.notificationservice.service.notification.data.request.data.FriendDTO;

import java.util.List;

public interface FriendDataMapper {
    public FriendDTO toDto(FriendData friendData);
    public List<FriendDTO> toDtoList(List<FriendData> friendData);
}
