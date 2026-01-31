package com.notificationservice.host.controller.subscribercontroller.helpers.mapper;

import com.notificationservice.host.controller.subscribercontroller.data.request.GetSubscribersRequest;
import com.notificationservice.host.controller.subscribercontroller.data.response.GetSubscribersResponse;
import com.notificationservice.service.subscriber.data.request.GetSubscribersQRequest;
import com.notificationservice.service.subscriber.data.response.GetSubscribersQResponse;

public interface SubscriberHostMapper {
    public GetSubscribersQRequest toRequest(GetSubscribersRequest request);
    public GetSubscribersResponse toResponse(GetSubscribersQResponse response);
}
