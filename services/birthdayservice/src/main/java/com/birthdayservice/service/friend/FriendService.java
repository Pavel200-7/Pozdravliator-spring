package com.birthdayservice.service.friend;

import com.birthdayservice.service.friend.data.request.*;
import com.birthdayservice.service.friend.data.response.*;

public interface FriendService {
    public GetFriendQResponse getFriend(GetFriendQRequest request);
    public GetFriendsQResponse getFriends(GetFriendsQRequest request);
    public GetFriendsWithNearestBirthdayQResponse getFriendsWithNearestBirthday(GetFriendsWithNearestBirthdayQRequest request);
    public CreateFriendCResponse createFriend(CreateFriendCRequest request);
    public UpdateFriendCResponse updateFriend(UpdateFriendCRequest request);
    public DeleteFriendCResponse deleteFriend(DeleteFriendCRequest request);
}
