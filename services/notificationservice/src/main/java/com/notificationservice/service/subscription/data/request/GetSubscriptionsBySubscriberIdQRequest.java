package com.notificationservice.service.subscription.data.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetSubscriptionsBySubscriberIdQRequest {
    private UUID id;
}
