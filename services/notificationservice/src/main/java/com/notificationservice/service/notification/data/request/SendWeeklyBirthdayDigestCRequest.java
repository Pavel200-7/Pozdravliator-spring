package com.notificationservice.service.notification.data.request;

import com.notificationservice.service.notification.data.request.data.FriendDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SendWeeklyBirthdayDigestCRequest {
    private List<FriendDTO> friends;
}
