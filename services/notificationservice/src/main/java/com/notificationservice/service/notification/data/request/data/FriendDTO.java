package com.notificationservice.service.notification.data.request.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FriendDTO {
    private String fullName;
    private LocalDate birthday;
    private Boolean gender;

    public boolean isMale() {
        return Boolean.TRUE.equals(gender);
    }

    public boolean isFemale() {
        return Boolean.FALSE.equals(gender);
    }

    public int getAge() {
        return LocalDate.now().getYear() -  birthday.getYear();
    }
}
