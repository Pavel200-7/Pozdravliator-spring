package com.birthdayservice.service.notification.helpers.mapper;

import com.birthdayservice.domain.entities.Friend;
import com.birthdayservice.infrastructure.event.data.FriendData;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FriendDataMapperImpl implements FriendDataMapper {

    @Override
    public FriendData toData(Friend friend) {
        return FriendData.builder()
                .fullName(friend.getFullName())
                .birthday(friend.getBirthdayDate())
                .gender(friend.getGender().booleanValue())
                .build();
    }

    @Override
    public List<FriendData> toDataList(List<Friend> friendData) {
        return friendData.stream()
                .map(this::toData)
                .toList();
    }
}
