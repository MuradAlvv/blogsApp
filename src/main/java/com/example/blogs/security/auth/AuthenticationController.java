package com.example.blogs.security.auth;

import com.example.blogs.security.user.dto.LoginRequestDto;
import com.example.blogs.security.user.dto.RegisterRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthenticationController {


    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public void register(@RequestBody RegisterRequestDto registerRequestDto, HttpServletRequest request) {
        authenticationService.register(registerRequestDto, request);
    }

    @PostMapping("/confirm")
    public void sendConfirmEmail(@RequestBody RegisterRequestDto registerRequestDto) {
        authenticationService.sendConfirmEmail(registerRequestDto);
    }


    @PostMapping("/login")
    public void login(@RequestBody LoginRequestDto loginUserRequestDto, HttpServletRequest request) {
        authenticationService.login(loginUserRequestDto, request);
    }

    @GetMapping("/logout")
    public void logout(HttpServletRequest request) {
        SecurityContextHolder.getContext().getAuthentication().setAuthenticated(false);
        SecurityContextHolder.clearContext();
    }

}
