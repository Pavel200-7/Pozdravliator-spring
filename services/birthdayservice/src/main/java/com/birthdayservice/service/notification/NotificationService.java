package com.birthdayservice.service.notification;

import com.birthdayservice.service.notification.data.request.*;
import com.birthdayservice.service.notification.data.response.*;

public interface NotificationService {
    public SendBirthdayReminderMessageCResponse sendReminderMessage(SendBirthdayReminderMessageCRequest request);
    public SendWeeklyBirthdayDigestCResponse sendWeeklyBirthdayDigestMessage(SendWeeklyBirthdayDigestCRequest request);
}
