package com.example.blogs.me;

import com.example.blogs.security.user.UserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/me")
public class UsernameController {

//    private final SecurityUtil securityUtil;

    @GetMapping
    public Username getLoggedName() {
        if(SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) {
            UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            return new Username(principal.getUsername());
        }else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
    }

}
