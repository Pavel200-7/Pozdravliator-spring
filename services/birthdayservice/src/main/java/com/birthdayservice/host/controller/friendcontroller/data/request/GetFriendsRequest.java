package com.birthdayservice.host.controller.friendcontroller.data.request;

import com.birthdayservice.service.friend.helpers.enums.FriendSortField;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Any;
import org.hibernate.query.SortDirection;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetFriendsRequest {
    private String search;
    private FriendSortField sortBy;
    private SortDirection sortDirection;

    @Min(5)
    @Max(100)
    private int pageSize;

    @Min(0)
    private int page;

}
