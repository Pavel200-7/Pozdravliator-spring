package com.notificationservice.infrastructure.repository;

import com.notificationservice.domain.entities.Subscription;
import com.notificationservice.domain.enums.SubscriptionType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, UUID> {
    boolean existsBySubscriberIdAndSubscriptionType(UUID subscriberId, SubscriptionType subscriptionType);
    Optional<Subscription> findBySubscriberIdAndSubscriptionType(UUID subscriberId, SubscriptionType subscriptionType);

    @Query(value = "SELECT s FROM Subscription s " +
            "JOIN FETCH s.subscriber sub " +  // FETCH для немедленной загрузки
            "WHERE s.subscriptionType = :type ",
            countQuery = "SELECT COUNT(s) FROM Subscription s " +  // Обязательно для Page!
                    "WHERE s.subscriptionType = :type")
    Page<Subscription> findBySubscriptionTypeWithSubscriber(
            @Param("type") SubscriptionType type,
            Pageable pageable);

    @Query("SELECT s FROM Subscription s WHERE s.subscriber.id = :subscriberId")
    List<Subscription> findBySubscriberId(@Param("subscriberId") UUID subscriberId);
}
