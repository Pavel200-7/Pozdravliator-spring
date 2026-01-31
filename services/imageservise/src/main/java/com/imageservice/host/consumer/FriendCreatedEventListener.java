package com.imageservice.host.consumer;

import com.imageservice.infrastructure.event.FriendCreatedEvent;
import com.imageservice.service.image.ImageService;
import com.imageservice.service.image.data.request.CreateImageCRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class FriendCreatedEventListener {

    private final ImageService service;

    @RabbitListener(queues = "friends.queue")
    public void handleFriendCreatedEvent(FriendCreatedEvent event) {
        log.info("Получено событие FriendCreatedEvent: {}", event);

        CreateImageCRequest cRequest = CreateImageCRequest.builder()
                .id(event.getId())
                .build();
        service.createImage(cRequest);
        log.info("Изображение создано для друга с ID: {}", event.getId());
    }
}