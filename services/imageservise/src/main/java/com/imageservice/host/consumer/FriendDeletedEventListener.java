package com.imageservice.host.consumer;

import com.imageservice.infrastructure.event.FriendDeletedEvent;
import com.imageservice.service.image.ImageService;
import com.imageservice.service.image.data.request.DeleteImageCRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class FriendDeletedEventListener {

    private final ImageService service;

    @RabbitListener(queues = "friends.queue")
    public void handleDeleteImageCRequest(FriendDeletedEvent event) {
        DeleteImageCRequest cRequest = DeleteImageCRequest.builder()
                .id(event.getId())
                .build();
        service.deleteImage(cRequest);
        log.info("изображение удалено.");
    }
}