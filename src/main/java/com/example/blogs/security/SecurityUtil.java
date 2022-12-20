package com.example.blogs.security;

import com.example.blogs.me.Username;
import com.example.blogs.security.user.User;
import com.example.blogs.security.user.UserDetails;
import com.example.blogs.security.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class SecurityUtil {

    private final UserRepository userRepository;


    public User getUser() {
        String username = getUsername();
        User user = userRepository.findByName(username).orElseThrow();
        return user;
    }

    public String getUsername() {
        if (SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) {
            UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            return principal.getUsername();
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
    }
}
