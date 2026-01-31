package com.notificationservice.service.subscription.data.response;

import com.notificationservice.service.subscription.data.response.data.SubscriberSubscriptionData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetSubscriptionsBySubscriberIdQResponse {
    private List<SubscriberSubscriptionData> items;
}
