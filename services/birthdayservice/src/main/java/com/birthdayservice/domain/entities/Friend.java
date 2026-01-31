package com.birthdayservice.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "friends")
public class Friend {

    @Id
    @Column(name = "id")
    @GeneratedValue
    private UUID id;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "birthday_date", nullable = false)
    private LocalDate birthdayDate;

    @Column(name = "gender", nullable = false)
    private Boolean gender;
}
