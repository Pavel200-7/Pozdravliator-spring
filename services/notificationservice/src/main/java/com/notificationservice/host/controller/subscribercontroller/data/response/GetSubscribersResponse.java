package com.notificationservice.host.controller.subscribercontroller.data.response;

import com.notificationservice.service.subscriber.data.response.data.SubscriberData;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetSubscribersResponse {
    private List<SubscriberData> items;
    private int page;
    private int pageSize;
    private Boolean hasNext;
    private Boolean hasPrev;
    private long total;
    private int totalPages;
}
