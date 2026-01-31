package com.notificationservice.domain.entities;

import com.notificationservice.domain.enums.SubscriptionType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "subscriptions")
public class Subscription {

    @Id
    @Column(name = "id")
    @GeneratedValue
    private UUID id;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "subscription_type", nullable = false)
    private SubscriptionType subscriptionType;

    @JoinColumn(name = "subscriber_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Subscriber subscriber;
}
