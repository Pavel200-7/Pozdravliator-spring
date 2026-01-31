package com.notificationservice.host.controller.subscriptioncontroller.data.response;

import com.notificationservice.service.subscription.data.response.data.SubscriberSubscriptionData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetSubscriptionsBySubscriberIdResponse {
    private List<SubscriberSubscriptionData> items;
}
