package com.birthdayservice.host.controller.friendcontroller.helpers.mapper;

import com.birthdayservice.host.controller.friendcontroller.data.request.*;
import com.birthdayservice.host.controller.friendcontroller.data.response.*;
import com.birthdayservice.service.friend.data.request.*;
import com.birthdayservice.service.friend.data.response.*;

public interface FriendHostMapper {
    public GetFriendResponse toResponse(GetFriendQResponse response);
    public GetFriendsQRequest toQRequest(GetFriendsRequest request);
    public GetFriendsResponse toResponse(GetFriendsQResponse response);
    public GetFriendsWithNearestBirthdayQRequest toQRequest(GetFriendsWithNearestBirthdayRequest request);
    public GetFriendsWithNearestBirthdayResponse toResponse(GetFriendsWithNearestBirthdayQResponse response);
    public CreateFriendCRequest toCRequest(CreateFriendRequest request);
    public CreateFriendResponse toResponse(CreateFriendCResponse cResponse);
    public UpdateFriendCRequest toCRequest(UpdateFriendRequest request);
    public UpdateFriendResponse toResponse(UpdateFriendCResponse cResponse);
    public DeleteFriendCRequest toCRequest(DeleteFriendRequest request);
    public DeleteFriendResponse toResponse(DeleteFriendCResponse cResponse);
}
