package com.example.blogs.security;

import com.example.blogs.security.user.User;
import com.example.blogs.security.user.UserDetails;
import com.example.blogs.security.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class SecurityUtil {

    private final UserRepository userRepository;

    public User getUser() {
        String username = getUsername();
        try {
            User user = userRepository.findByName(username).orElseThrow();
            return user;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public String getUsername() {
        try {
            SecurityContext context = SecurityContextHolder.getContext();
            if (Objects.nonNull(context)) {
                Authentication authentication = context.getAuthentication();
                if (Objects.nonNull(authentication)) {
                    if (authentication.isAuthenticated()) {
                        UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                        return principal.getUsername();
                    } else {
                        System.out.println("not authorized");
                    }
                }
            }
            return null;
        } catch (Exception e) {
            System.out.println("not authorized");
        }
        return null;
    }

    public boolean isAuthenticated() {
        SecurityContext context = SecurityContextHolder.getContext();
        if (Objects.nonNull(context)) {
            Authentication authentication = context.getAuthentication();
            if (Objects.nonNull(authentication)) {
                return authentication.isAuthenticated();
            }
        }
        return false;
    }

}
