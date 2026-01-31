package com.birthdayservice.host.controller.friendcontroller.helpers.mapper;

import com.birthdayservice.host.controller.friendcontroller.data.request.*;
import com.birthdayservice.host.controller.friendcontroller.data.response.*;
import com.birthdayservice.service.friend.data.request.*;
import com.birthdayservice.service.friend.data.response.*;
import org.springframework.stereotype.Component;

@Component
public class FriendHostMapperImpl implements FriendHostMapper {

    @Override
    public GetFriendResponse toResponse(GetFriendQResponse response) {
        return GetFriendResponse.builder()
                .id(response.getId())
                .fullName(response.getFullName())
                .birthdayDate(response.getBirthdayDate())
                .gender(response.getGender())
                .build();
    }

    @Override
    public GetFriendsQRequest toQRequest(GetFriendsRequest request) {
        return GetFriendsQRequest.builder()
                .search(request.getSearch())
                .sortBy(request.getSortBy())
                .sortDirection(request.getSortDirection())
                .pageSize(request.getPageSize())
                .page(request.getPage())
                .build();
    }

    @Override
    public GetFriendsResponse toResponse(GetFriendsQResponse response) {
        return GetFriendsResponse.builder()
                .items(response.getItems())
                .page(response.getPage())
                .pageSize(response.getPageSize())
                .hasNext(response.getHasNext())
                .hasPrev(response.getHasPrev())
                .total(response.getTotal())
                .totalPages(response.getTotalPages())
                .build();
    }

    @Override
    public GetFriendsWithNearestBirthdayQRequest toQRequest(GetFriendsWithNearestBirthdayRequest request) {
        return GetFriendsWithNearestBirthdayQRequest.builder()
                .limit(request.getLimit())
                .build();
    }

    @Override
    public GetFriendsWithNearestBirthdayResponse toResponse(GetFriendsWithNearestBirthdayQResponse response) {
        return GetFriendsWithNearestBirthdayResponse.builder()
                .items(response.getItems())
                .build();
    }

    public CreateFriendCRequest toCRequest(CreateFriendRequest request) {
        return CreateFriendCRequest.builder()
                .fullName(request.getFullName())
                .birthdayDate(request.getBirthdayDate())
                .gender(request.getGender())
                .build();
    }

    public CreateFriendResponse toResponse(CreateFriendCResponse cResponse) {
        return new CreateFriendResponse(cResponse.getSucceed());
    }

    @Override
    public UpdateFriendCRequest toCRequest(UpdateFriendRequest request) {
        return UpdateFriendCRequest.builder()
                .id(request.getId())
                .fullName(request.getFullName())
                .birthdayDate(request.getBirthdayDate())
                .gender(request.getGender())
                .build();
    }

    @Override
    public UpdateFriendResponse toResponse(UpdateFriendCResponse cResponse) {
        return new UpdateFriendResponse(cResponse.getSucceed());
    }

    @Override
    public DeleteFriendCRequest toCRequest(DeleteFriendRequest request) {
        return new DeleteFriendCRequest(request.getId());
    }

    @Override
    public DeleteFriendResponse toResponse(DeleteFriendCResponse cResponse) {
        return new DeleteFriendResponse(cResponse.getSucceed());
    }
}
