package com.birthdayservice.service.friend.helpers.mapper;

import com.birthdayservice.domain.entities.Friend;
import com.birthdayservice.service.friend.data.request.CreateFriendCRequest;
import com.birthdayservice.service.friend.data.request.GetFriendsQRequest;
import com.birthdayservice.service.friend.data.request.UpdateFriendCRequest;
import com.birthdayservice.service.friend.data.response.GetFriendQResponse;
import com.birthdayservice.service.friend.data.response.GetFriendsQResponse;
import com.birthdayservice.service.friend.data.response.data.FriendData;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FriendMapperImpl implements FriendMapper {
    public Friend toEntity(CreateFriendCRequest request) {
        return Friend.builder()
                .fullName(request.getFullName())
                .birthdayDate(request.getBirthdayDate())
                .gender(request.getGender())
                .build();
    }

    @Override
    public Friend toEntity(Friend oldFriendData, UpdateFriendCRequest request) {
        oldFriendData.setFullName(request.getFullName());
        oldFriendData.setBirthdayDate(request.getBirthdayDate());
        oldFriendData.setGender(request.getGender());
        return oldFriendData;
    }

    @Override
    public GetFriendQResponse toResponse(Friend entity) {
        return GetFriendQResponse.builder()
                .id(entity.getId())
                .fullName(entity.getFullName())
                .birthdayDate(entity.getBirthdayDate())
                .gender(entity.getGender())
                .build();
    }

    @Override
    public GetFriendsQResponse toResponse(Page<Friend> page, GetFriendsQRequest request) {
        List<FriendData> items = page.getContent().stream()
                .map(i -> this.toFriendData(i))
                .toList();
        int offset = request.getPage() * request.getPageSize();
        return GetFriendsQResponse.builder()
                .items(items)
                .page(request.getPage())
                .pageSize(request.getPageSize())
                .hasNext(page.hasNext())
                .hasPrev(page.hasPrevious())
                .total(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .build();
    }

    @Override
    public FriendData toFriendData(Friend entity) {
        return FriendData.builder()
                .id(entity.getId())
                .fullName(entity.getFullName())
                .birthdayDate(entity.getBirthdayDate())
                .gender(entity.getGender())
                .build();
    }
}
