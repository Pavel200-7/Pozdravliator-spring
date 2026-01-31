package com.birthdayservice.infrastructure.event;

import com.birthdayservice.infrastructure.event.data.FriendData;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SendingWeeklyBirthdayDigestStartedEvent implements Serializable {
    private List<FriendData> friends;
}
