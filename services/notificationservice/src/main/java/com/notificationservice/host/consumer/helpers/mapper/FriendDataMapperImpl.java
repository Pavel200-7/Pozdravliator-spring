package com.notificationservice.host.consumer.helpers.mapper;

import com.notificationservice.infrastructure.event.data.FriendData;
import com.notificationservice.service.notification.data.request.data.FriendDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FriendDataMapperImpl implements FriendDataMapper {
    @Override
    public FriendDTO toDto(FriendData friendData) {
        return FriendDTO.builder()
                .fullName(friendData.getFullName())
                .birthday(friendData.getBirthday())
                .gender(friendData.getGender().booleanValue())
                .build();
    }

    @Override
    public List<FriendDTO> toDtoList(List<FriendData> friendData) {
        return friendData.stream()
                .map(this::toDto)
                .toList();
    }
}
