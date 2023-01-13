package com.example.blogs.friends;

import com.example.blogs.security.SecurityUtil;
import com.example.blogs.security.user.User;
import com.example.blogs.security.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FriendsService {

    private final UserRepository userRepository;
    private final SecurityUtil securityUtil;


    public void makeFriend(Integer friendId) {
        User friend = userRepository.findById(friendId).orElseThrow();
        User user = securityUtil.getUser();
        user.getFriends().add(friend);
        friend.getFriends().add(user);
        userRepository.save(user);
        userRepository.save(friend);
    }
}
