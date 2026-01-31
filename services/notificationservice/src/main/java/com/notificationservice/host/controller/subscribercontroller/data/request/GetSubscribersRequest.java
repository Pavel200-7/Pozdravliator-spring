package com.notificationservice.host.controller.subscribercontroller.data.request;

import com.notificationservice.service.subscriber.helpers.enums.SubscriberSortField;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.query.SortDirection;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetSubscribersRequest {
    private String search;
    private SubscriberSortField sortBy;
    private SortDirection sortDirection;
    private int pageSize;
    private int page;
}
