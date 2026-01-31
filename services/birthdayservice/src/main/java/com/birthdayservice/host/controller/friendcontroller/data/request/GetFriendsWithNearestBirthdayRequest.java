package com.birthdayservice.host.controller.friendcontroller.data.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetFriendsWithNearestBirthdayRequest {
    @Min(0)
    @Max(100)
    private int limit;
}
