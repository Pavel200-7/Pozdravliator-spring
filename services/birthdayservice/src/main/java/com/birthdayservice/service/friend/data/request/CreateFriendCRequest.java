package com.birthdayservice.service.friend.data.request;

import lombok.*;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateFriendCRequest {
    private String fullName;
    private LocalDate birthdayDate;
    private Boolean gender;
}

