package com.notificationservice.service.subscriber.helpers.enums;

import java.util.Arrays;
import java.util.stream.Collectors;

public enum SubscriberSortField {
    ID("id"),
    EMAIL("email");

    private final String fieldName;

    SubscriberSortField(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldName() {
        return fieldName;
    }

    public static SubscriberSortField fromString(String value) {
        for (SubscriberSortField field : values()) {
            if (field.fieldName.equalsIgnoreCase(value)) {
                return field;
            }
        }
        throw new IllegalArgumentException(
                "Недопустимое поле для сортировки: " + value +
                        ". Допустимые значения: " + Arrays.stream(values())
                        .map(SubscriberSortField::getFieldName)
                        .collect(Collectors.joining(", "))
        );
    }
}
