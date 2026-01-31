package com.notificationservice.host.controller.subscriptioncontroller.data.response;

import com.notificationservice.service.subscription.data.response.data.SubscriptionData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetSubscriptionsResponse {
    private List<SubscriptionData> items;
}
