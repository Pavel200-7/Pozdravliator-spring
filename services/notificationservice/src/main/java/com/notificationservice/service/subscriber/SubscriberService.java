package com.notificationservice.service.subscriber;

import com.notificationservice.service.subscriber.data.request.*;
import com.notificationservice.service.subscriber.data.response.*;

public interface SubscriberService {
    public CreateSubscriberCResponse createSubscriber(CreateSubscriberCRequest request);
    public DeleteSubscriberCResponse deleteSubscriber(DeleteSubscriberCRequest request);
    public GetSubscribersQResponse getSubscribers(GetSubscribersQRequest request);

}
