package com.notificationservice.service.notification.helpers.messagebuilder.helpers.reminder;

import com.notificationservice.service.notification.data.request.data.FriendDTO;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.List;

@Setter
@RequiredArgsConstructor
@Component
public class ReminderMessageBodyBuilderImpl implements ReminderMessageBodyBuilder {

    private List<FriendDTO> friends;

    @Override
    public String buildBody() {
        if (friends == null) {
            return "";
        }

        return createStringReminderMessage(friends);
    }

    private String createStringReminderMessage(List<FriendDTO> friends) {
        return switch (friends.size()) {
            case 1 -> createSingleFriendMessage(friends.getFirst());
            default -> createMultipleFriendsMessage(friends);
        };
    }

    private String createSingleFriendMessage(FriendDTO friend) {
        if (friend.isMale()) {
            return String.format(
                    "Завтра день рождения у %s! Не забудьте поздравить его с %d-летием!",
                    friend.getFullName(),
                    friend.getAge()
            );
        } else {
            return String.format(
                    "Завтра день рождения у %s! Не забудьте поздравить её с %d-летием!",
                    friend.getFullName(),
                    friend.getAge()
            );
        }
    }

    private String createMultipleFriendsMessage(List<FriendDTO> friends) {
        List<String> friendsData = friends.stream().map(f -> String.format("%s (%d лет)",
                f.getFullName(),
                f.getAge())).toList();

        return String.format("Завтра дни рожденья у: %s. Не забудте их поздравить",
                String.join(", ", friendsData));
    }
}
