package com.notificationservice.infrastructure.repository;

import com.notificationservice.domain.entities.Subscriber;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface SubscriberRepository extends JpaRepository<Subscriber, UUID> {
    Optional<Subscriber> findByEmail(String email);
    Page<Subscriber> findByEmailContains(String email, Pageable pageable);

    boolean existsByEmail(String email);
    List<Subscriber> findAllByEmail(String email);
}
