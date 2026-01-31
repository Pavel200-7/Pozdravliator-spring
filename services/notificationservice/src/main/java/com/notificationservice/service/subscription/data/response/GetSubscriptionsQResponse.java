package com.notificationservice.service.subscription.data.response;

import com.notificationservice.service.subscription.data.response.data.SubscriptionData;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetSubscriptionsQResponse {
    private List<SubscriptionData> items;
}
