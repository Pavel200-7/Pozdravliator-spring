package com.birthdayservice.host.controller.friendcontroller.data.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateFriendRequest {

    @NotBlank(message = "ФИО не может быть пустым")
    @Length(min = 3, max = 40, message = "ФИО должно содержать от 3 до 40 символов")
    private String fullName;

    @NotNull(message = "Дата рождения обязательна")
    @Past(message = "Дата рождения должна быть в прошлом")
    private LocalDate birthdayDate;

    @NotNull(message = "Пол обязателен")
    private Boolean gender;
}
