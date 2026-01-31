package com.notificationservice.service.notification.helpers.messagebuilder.helpers.weeklydigest;

import com.notificationservice.service.notification.data.request.data.FriendDTO;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.List;

@Setter
@RequiredArgsConstructor
@Component
public class WeeklyBirthdayDigestMessageBodyBuilderImpl implements WeeklyBirthdayDigestMessageBodyBuilder {

    private List<FriendDTO> friends;
    private final TemplateEngine templateEngine;


    @Override
    public String buildBody() {
        Context context = new Context();

        if (friends == null || friends.isEmpty()) {
            String htmlContent = templateEngine.process("email/weekly_digest_empty", context);
            return htmlContent;
        }

        context.setVariable("friends", friends);
        String htmlContent = templateEngine.process("email/weekly_digest", context);
        return htmlContent;
    }
}
