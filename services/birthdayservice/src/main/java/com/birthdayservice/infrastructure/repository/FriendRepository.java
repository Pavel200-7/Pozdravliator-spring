package com.birthdayservice.infrastructure.repository;

import com.birthdayservice.domain.entities.Friend;
import org.springframework.data.domain.Limit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface FriendRepository extends JpaRepository<Friend, UUID> {

    Page<Friend> findByFullNameContains(String fullName, Pageable pageable);

    @Query("""
            SELECT f FROM Friend f
            ORDER BY
                CASE
                    WHEN EXTRACT(MONTH FROM f.birthdayDate) > EXTRACT(MONTH FROM CURRENT_DATE)
                       OR (EXTRACT(MONTH FROM f.birthdayDate) = EXTRACT(MONTH FROM CURRENT_DATE)
                           AND EXTRACT(DAY FROM f.birthdayDate) >= EXTRACT(DAY FROM CURRENT_DATE))
                    THEN 0
                    ELSE 1
                END,
                EXTRACT(MONTH FROM f.birthdayDate),
                EXTRACT(DAY FROM f.birthdayDate),
                f.fullName
        """)
    Page<Friend> findFriendsWithNearestBirthday(Pageable pageable);

    @Query("""
        SELECT f FROM Friend f
        WHERE MONTH(f.birthdayDate) = :month 
          AND DAY(f.birthdayDate) = :day
        ORDER BY f.fullName
        """)
    List<Friend> findFriendsByBirthdayMonthAndDay(
            @Param("month") int month,
            @Param("day") int day,
            Pageable pageable);

    @Query(value = """
    SELECT f.* FROM friends f
    WHERE (EXTRACT(MONTH FROM f.birthday_date), EXTRACT(DAY FROM f.birthday_date)) IN (
        SELECT EXTRACT(MONTH FROM CURRENT_DATE + INTERVAL '1 day'),
               EXTRACT(DAY FROM CURRENT_DATE + INTERVAL '1 day') UNION ALL
        SELECT EXTRACT(MONTH FROM CURRENT_DATE + INTERVAL '2 days'),
               EXTRACT(DAY FROM CURRENT_DATE + INTERVAL '2 days') UNION ALL
        SELECT EXTRACT(MONTH FROM CURRENT_DATE + INTERVAL '3 days'),
               EXTRACT(DAY FROM CURRENT_DATE + INTERVAL '3 days') UNION ALL
        SELECT EXTRACT(MONTH FROM CURRENT_DATE + INTERVAL '4 days'),
               EXTRACT(DAY FROM CURRENT_DATE + INTERVAL '4 days') UNION ALL
        SELECT EXTRACT(MONTH FROM CURRENT_DATE + INTERVAL '5 days'),
               EXTRACT(DAY FROM CURRENT_DATE + INTERVAL '5 days') UNION ALL
        SELECT EXTRACT(MONTH FROM CURRENT_DATE + INTERVAL '6 days'),
               EXTRACT(DAY FROM CURRENT_DATE + INTERVAL '6 days') UNION ALL
        SELECT EXTRACT(MONTH FROM CURRENT_DATE + INTERVAL '7 days'),
               EXTRACT(DAY FROM CURRENT_DATE + INTERVAL '7 days')
    )
    ORDER BY EXTRACT(MONTH FROM f.birthday_date),
             EXTRACT(DAY FROM f.birthday_date),
             f.full_name
    """, nativeQuery = true)
    List<Friend> findNextWeekBirthdaysFriends(Pageable pageable);
}
