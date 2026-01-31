package com.birthdayservice.service.friend.data.response;

import com.birthdayservice.service.friend.data.response.data.FriendData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@EqualsAndHashCode(callSuper=true)
@SuperBuilder
@NoArgsConstructor
public class GetFriendQResponse extends FriendData {
}
