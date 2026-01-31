package com.birthdayservice.host.controller.friendcontroller;

import com.birthdayservice.host.controller.friendcontroller.data.request.*;
import com.birthdayservice.host.controller.friendcontroller.data.response.*;
import com.birthdayservice.host.controller.friendcontroller.helpers.mapper.FriendHostMapper;
import com.birthdayservice.service.friend.FriendService;
import com.birthdayservice.service.friend.data.request.GetFriendQRequest;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/friend")
public class FriendController {
    private final FriendService service;
    private final FriendHostMapper mapper;

    @GetMapping("/{id}")
    public ResponseEntity<GetFriendResponse> getFriend(@PathVariable @NotNull UUID id) {
        var qRequest = new GetFriendQRequest(id);
        var qResponse = service.getFriend(qRequest);
        var response = mapper.toResponse(qResponse);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/all")
    public ResponseEntity<GetFriendsResponse> getFriend(@RequestBody @Validated GetFriendsRequest request) {
        var qRequest = mapper.toQRequest(request);
        var qResponse = service.getFriends(qRequest);
        var response = mapper.toResponse(qResponse);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/nearest")
    public ResponseEntity<GetFriendsWithNearestBirthdayResponse> getFriend(@RequestBody @Validated GetFriendsWithNearestBirthdayRequest request) {
        var cRequest = mapper.toQRequest(request);
        var qResponse = service.getFriendsWithNearestBirthday(cRequest);
        var response = mapper.toResponse(qResponse);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CreateFriendResponse> createFriend(@RequestBody @Validated CreateFriendRequest request) {
        var cRequest = mapper.toCRequest(request);
        var cResponse = service.createFriend(cRequest);
        var response = mapper.toResponse(cResponse);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<UpdateFriendResponse> updateFriend(@RequestBody @Validated UpdateFriendRequest request) {
        var cRequest = mapper.toCRequest(request);
        var cResponse = service.updateFriend(cRequest);
        var response = mapper.toResponse(cResponse);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<DeleteFriendResponse> deleteFriend(@RequestBody @Validated DeleteFriendRequest request) {
        var cRequest = mapper.toCRequest(request);
        var cResponse = service.deleteFriend(cRequest);
        var response = mapper.toResponse(cResponse);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }



}
