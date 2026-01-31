package com.notificationservice.host.controller.subscribercontroller.helpers.mapper;

import com.notificationservice.host.controller.subscribercontroller.data.request.GetSubscribersRequest;
import com.notificationservice.host.controller.subscribercontroller.data.response.GetSubscribersResponse;
import com.notificationservice.service.subscriber.data.request.GetSubscribersQRequest;
import com.notificationservice.service.subscriber.data.response.GetSubscribersQResponse;
import org.springframework.stereotype.Component;

@Component
public class SubscriberHostMapperImpl implements SubscriberHostMapper{
    @Override
    public GetSubscribersQRequest toRequest(GetSubscribersRequest request) {
        return GetSubscribersQRequest.builder()
                .search(request.getSearch())
                .sortBy(request.getSortBy())
                .sortDirection(request.getSortDirection())
                .pageSize(request.getPageSize())
                .page(request.getPage())
                .build();
    }

    @Override
    public GetSubscribersResponse toResponse(GetSubscribersQResponse response) {
        return GetSubscribersResponse.builder()
                .items(response.getItems())
                .page(response.getPage())
                .pageSize(response.getPageSize())
                .hasNext(response.getHasNext())
                .hasPrev(response.getHasPrev())
                .total(response.getTotal())
                .totalPages(response.getTotalPages())
                .build();
    }
}
