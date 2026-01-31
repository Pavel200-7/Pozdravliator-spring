package com.birthdayservice.service.friend.helpers.enums;

import java.util.Arrays;
import java.util.stream.Collectors;

public enum FriendSortField {
    ID("id"),
    FULL_NAME("fullName"),
    BIRTHDAY_DATE("birthdayDate"),
    GENDER("gender");

    private final String fieldName;

    FriendSortField(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldName() {
        return fieldName;
    }

    public static FriendSortField fromString(String value) {
        for (FriendSortField field : values()) {
            if (field.fieldName.equalsIgnoreCase(value)) {
                return field;
            }
        }
        throw new IllegalArgumentException(
                "Недопустимое поле для сортировки: " + value +
                        ". Допустимые значения: " + Arrays.stream(values())
                        .map(FriendSortField::getFieldName)
                        .collect(Collectors.joining(", "))
        );
    }
}
