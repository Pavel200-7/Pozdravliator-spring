package com.notificationservice.service.subscriber.data.response;

import com.notificationservice.service.subscriber.data.response.data.SubscriberData;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetSubscribersQResponse {
    private List<SubscriberData> items;
    private int page;
    private int pageSize;
    private Boolean hasNext;
    private Boolean hasPrev;
    private long total;
    private int totalPages;
}
