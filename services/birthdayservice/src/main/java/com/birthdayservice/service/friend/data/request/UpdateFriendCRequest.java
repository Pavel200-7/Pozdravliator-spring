package com.birthdayservice.service.friend.data.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateFriendCRequest {
    private UUID id;
    private String fullName;
    private LocalDate birthdayDate;
    private Boolean gender;
}
