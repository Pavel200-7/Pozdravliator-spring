package com.notificationservice.infrastructure.event.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FriendData implements Serializable {
    private String fullName;
    private LocalDate birthday;
    private Boolean gender;
}
