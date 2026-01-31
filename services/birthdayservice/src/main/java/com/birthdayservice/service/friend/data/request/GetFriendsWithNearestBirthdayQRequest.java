package com.birthdayservice.service.friend.data.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetFriendsWithNearestBirthdayQRequest {
    private int limit;
}
