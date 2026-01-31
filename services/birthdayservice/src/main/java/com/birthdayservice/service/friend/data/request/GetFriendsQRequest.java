package com.birthdayservice.service.friend.data.request;

import com.birthdayservice.service.friend.helpers.enums.FriendSortField;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.query.SortDirection;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetFriendsQRequest {
    private String search;
    private FriendSortField sortBy;
    private SortDirection sortDirection;
    private int pageSize;
    private int page;
}
