package com.notificationservice.host.controller.subscribercontroller;

import com.notificationservice.host.controller.subscribercontroller.data.request.*;
import com.notificationservice.host.controller.subscribercontroller.data.response.*;
import com.notificationservice.host.controller.subscribercontroller.helpers.mapper.SubscriberHostMapper;
import com.notificationservice.service.subscriber.SubscriberService;
import com.notificationservice.service.subscriber.data.request.*;
import com.notificationservice.service.subscriber.data.response.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/subscriber")
public class SubscriberController {

    private final SubscriberService service;
    private final SubscriberHostMapper mapper;

    @PostMapping("/all")
    public ResponseEntity<GetSubscribersResponse> getSubscribers(@RequestBody @Validated GetSubscribersRequest request) {
        var qRequest = mapper.toRequest(request);
        var qResponse = service.getSubscribers(qRequest);
        var response = mapper.toResponse(qResponse);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CreateSubscriberResponse> createSubscriber(@RequestBody @Validated CreateSubscriberRequest request) {
        CreateSubscriberCRequest cRequest = new CreateSubscriberCRequest(request.getEmail());
        CreateSubscriberCResponse cResponse = service.createSubscriber(cRequest);
        CreateSubscriberResponse response = new CreateSubscriberResponse(cResponse.getSucceed());
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<DeleteSubscriberResponse> createSubscriber(@RequestBody @Validated DeleteSubscriberRequest request) {
        DeleteSubscriberCRequest cRequest = new DeleteSubscriberCRequest(request.getEmail());
        DeleteSubscriberCResponse cResponse = service.deleteSubscriber(cRequest);
        DeleteSubscriberResponse response = new DeleteSubscriberResponse(cResponse.getSucceed());
        return new ResponseEntity(response, HttpStatus.OK);
    }
}
