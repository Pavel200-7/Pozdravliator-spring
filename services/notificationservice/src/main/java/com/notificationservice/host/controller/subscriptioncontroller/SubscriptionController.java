package com.notificationservice.host.controller.subscriptioncontroller;

import com.notificationservice.host.controller.subscribercontroller.data.request.DeleteSubscriberRequest;
import com.notificationservice.host.controller.subscribercontroller.data.response.CreateSubscriberResponse;
import com.notificationservice.host.controller.subscribercontroller.data.response.DeleteSubscriberResponse;
import com.notificationservice.host.controller.subscriptioncontroller.data.request.CreateSubscriptionRequest;
import com.notificationservice.host.controller.subscriptioncontroller.data.request.DeleteSubscriptionRequest;
import com.notificationservice.host.controller.subscriptioncontroller.data.response.CreateSubscriptionResponse;
import com.notificationservice.host.controller.subscriptioncontroller.data.response.DeleteSubscriptionResponse;
import com.notificationservice.host.controller.subscriptioncontroller.data.response.GetSubscriptionsBySubscriberIdResponse;
import com.notificationservice.host.controller.subscriptioncontroller.data.response.GetSubscriptionsResponse;
import com.notificationservice.service.subscriber.data.request.DeleteSubscriberCRequest;
import com.notificationservice.service.subscriber.data.response.DeleteSubscriberCResponse;
import com.notificationservice.service.subscription.SubscriptionService;
import com.notificationservice.service.subscription.data.request.CreateSubscriptionCRequest;
import com.notificationservice.service.subscription.data.request.DeleteSubscriptionCRequest;
import com.notificationservice.service.subscription.data.request.GetSubscriptionsBySubscriberIdQRequest;
import com.notificationservice.service.subscription.data.request.GetSubscriptionsQRequest;
import com.notificationservice.service.subscription.data.response.CreateSubscriptionCResponse;
import com.notificationservice.service.subscription.data.response.DeleteSubscriptionCResponse;
import com.notificationservice.service.subscription.data.response.GetSubscriptionsBySubscriberIdQResponse;
import com.notificationservice.service.subscription.data.response.GetSubscriptionsQResponse;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/subscription")
public class SubscriptionController {
    private final SubscriptionService service;

    @GetMapping
    public
    ResponseEntity<GetSubscriptionsResponse> getSubscriptions() {
        GetSubscriptionsQRequest qRequest = new GetSubscriptionsQRequest();
        GetSubscriptionsQResponse qResponse = service.getSubscriptions(qRequest);
        GetSubscriptionsResponse response = new GetSubscriptionsResponse(qResponse.getItems());
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @GetMapping("/by_subscriber/{subscriberId}")
    public ResponseEntity<GetSubscriptionsBySubscriberIdResponse> getSubscriptionsBySubscriberId(@PathVariable @NotNull UUID subscriberId) {
        GetSubscriptionsBySubscriberIdQRequest qRequest = new GetSubscriptionsBySubscriberIdQRequest(subscriberId);
        GetSubscriptionsBySubscriberIdQResponse qResponse = service.getSubscriptionsBySubscriberId(qRequest);
        GetSubscriptionsBySubscriberIdResponse response = new GetSubscriptionsBySubscriberIdResponse(qResponse.getItems());
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CreateSubscriptionResponse> createSubscriber(@RequestBody @Validated CreateSubscriptionRequest request) {
        CreateSubscriptionCRequest cRequest = new CreateSubscriptionCRequest(request.getEmail(), request.getSubscriptionType());
        CreateSubscriptionCResponse cResponse = service.createSubscription(cRequest);
        CreateSubscriptionResponse response = new CreateSubscriptionResponse(cResponse.getSucceed());
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<DeleteSubscriberResponse> createSubscriber(@RequestBody @Validated DeleteSubscriptionRequest request) {
        DeleteSubscriptionCRequest cRequest = new DeleteSubscriptionCRequest(request.getEmail(), request.getSubscriptionType());
        DeleteSubscriptionCResponse cResponse = service.deleteSubscription(cRequest);
        DeleteSubscriptionResponse response = new DeleteSubscriptionResponse(cResponse.getSucceed());
        return new ResponseEntity(response, HttpStatus.OK);
    }
}
