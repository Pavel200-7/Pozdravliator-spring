package com.imageservice.infrastructure.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FriendCreatedEvent implements Serializable {
    private UUID id;
    private String fullName;
    private LocalDate birthdayDate;
    private Boolean gender;
}
