package com.notificationservice.service.subscriber.helpers.mapper;

import com.notificationservice.domain.entities.Subscriber;
import com.notificationservice.service.subscriber.data.request.GetSubscribersQRequest;
import com.notificationservice.service.subscriber.data.response.GetSubscribersQResponse;
import com.notificationservice.service.subscriber.data.response.data.SubscriberData;
import org.springframework.data.domain.Page;

public interface SubscriberMapper {
    public GetSubscribersQResponse toResponse(Page<Subscriber> page, GetSubscribersQRequest request);
    public SubscriberData toSubscriberData(Subscriber entity);
}
