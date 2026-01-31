package com.birthdayservice.service.friend.data.response.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.UUID;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class FriendData {
    protected UUID id;
    protected String fullName;
    protected LocalDate birthdayDate;
    protected Boolean gender;
}
