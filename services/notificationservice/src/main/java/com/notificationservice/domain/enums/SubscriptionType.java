package com.notificationservice.domain.enums;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum SubscriptionType {
    BIRTHDAY_REMINDER(1, "Напоминание о дне рождения", "Рассылка за день до дня рождения"),
    WEEKLY_BIRTHDAY_DIGEST(2, "Еженедельный дайджест", "Еженедельная рассылка о ближайших днях рождениях");

    private final int code;
    private final String title;
    private final String description;

    SubscriptionType(int code, String title, String description) {
        this.code = code;
        this.title = title;
        this.description = description;
    }

    public static SubscriptionType fromCode(int code) {
        return Arrays.stream(SubscriptionType.values())
                .filter(st -> st.code == code)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Unknown code: " + code));
    }


}
