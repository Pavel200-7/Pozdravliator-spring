package com.birthdayservice.service.friend.data.response;

import com.birthdayservice.service.friend.data.response.data.FriendData;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetFriendsWithNearestBirthdayQResponse {
    private List<FriendData> items;
}
