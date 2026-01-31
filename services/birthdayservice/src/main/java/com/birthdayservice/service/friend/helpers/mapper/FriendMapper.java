package com.birthdayservice.service.friend.helpers.mapper;

import com.birthdayservice.domain.entities.Friend;
import com.birthdayservice.service.friend.data.request.*;
import com.birthdayservice.service.friend.data.response.*;
import com.birthdayservice.service.friend.data.response.data.FriendData;
import org.springframework.data.domain.Page;

public interface FriendMapper {
    public Friend toEntity(CreateFriendCRequest request);
    public Friend toEntity(Friend oldFriendData, UpdateFriendCRequest request);
    public GetFriendQResponse toResponse(Friend entity);
    public FriendData toFriendData(Friend entity);
    public GetFriendsQResponse toResponse(Page<Friend> page, GetFriendsQRequest request);
}
