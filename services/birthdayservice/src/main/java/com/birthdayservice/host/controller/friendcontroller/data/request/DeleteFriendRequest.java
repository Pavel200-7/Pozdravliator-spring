package com.birthdayservice.host.controller.friendcontroller.data.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeleteFriendRequest {

    @NotNull(message = "Id не может быть пустым")
    private UUID id;
}
