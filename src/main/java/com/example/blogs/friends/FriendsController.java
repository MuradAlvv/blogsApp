package com.example.blogs.friends;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/friends")
public class FriendsController {

    private final FriendsService friendsService;

    @PostMapping("/{friendId}")
    public void makeFriend(@PathVariable Integer friendId) {
        friendsService.makeFriend(friendId);
    }
}
