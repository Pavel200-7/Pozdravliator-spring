package com.notificationservice.service.subscription;

import com.notificationservice.service.subscription.data.request.*;
import com.notificationservice.service.subscription.data.response.*;

public interface SubscriptionService {
    public GetSubscriptionsQResponse getSubscriptions(GetSubscriptionsQRequest request);
    public GetSubscriptionsBySubscriberIdQResponse  getSubscriptionsBySubscriberId(GetSubscriptionsBySubscriberIdQRequest request);
    public CreateSubscriptionCResponse createSubscription(CreateSubscriptionCRequest request);
    public DeleteSubscriptionCResponse deleteSubscription(DeleteSubscriptionCRequest request);
}
