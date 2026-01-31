package com.notificationservice.host.controller.subscriptioncontroller.data.request;

import com.notificationservice.domain.enums.SubscriptionType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeleteSubscriptionRequest {

    @NotBlank
    @Email
    private String email;

    @NotNull
    private SubscriptionType subscriptionType;
}
