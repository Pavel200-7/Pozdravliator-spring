package com.notificationservice.service.subscriber.data.request;

import com.notificationservice.service.subscriber.helpers.enums.SubscriberSortField;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.query.SortDirection;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetSubscribersQRequest {
    private String search;
    private SubscriberSortField sortBy;
    private SortDirection sortDirection;
    private int pageSize;
    private int page;
}
