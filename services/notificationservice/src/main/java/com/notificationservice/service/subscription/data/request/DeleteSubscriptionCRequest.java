package com.notificationservice.service.subscription.data.request;

import com.notificationservice.domain.enums.SubscriptionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeleteSubscriptionCRequest {
    private String email;
    private SubscriptionType subscriptionType;
}
