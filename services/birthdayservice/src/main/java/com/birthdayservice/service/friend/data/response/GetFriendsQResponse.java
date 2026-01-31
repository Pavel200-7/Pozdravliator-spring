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
public class GetFriendsQResponse {
    private List<FriendData> items;
    private int page;
    private int pageSize;
    private Boolean hasNext;
    private Boolean hasPrev;
    private long total;
    private int totalPages;
}
