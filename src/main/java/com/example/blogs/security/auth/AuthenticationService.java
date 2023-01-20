package com.example.blogs.security.auth;


import com.example.blogs.email.EmailService;
import com.example.blogs.security.user.dto.LoginRequestDto;
import com.example.blogs.security.user.User;
import com.example.blogs.security.user.dto.RegisterRequestDto;
import com.example.blogs.security.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.GeneralSecurityException;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final EmailService emailService;

    private String confirmCode;

    public void register(RegisterRequestDto userRegisterRequestDto, HttpServletRequest httpServletRequest) {

        User user = new User();
        user.setEmail(userRegisterRequestDto.getEmail());
        user.setFullname(userRegisterRequestDto.getFullname());
        user.setUsername(userRegisterRequestDto.getUsername());
        user.setPassword(passwordEncoder.encode(userRegisterRequestDto.getPassword()));
        if (confirmEmail(userRegisterRequestDto)) {
            userRepository.save(user);
            LoginRequestDto loginRequestDto = new LoginRequestDto();
            loginRequestDto.setPassword(userRegisterRequestDto.getPassword());
            loginRequestDto.setUsername(userRegisterRequestDto.getUsername());
            login(loginRequestDto, httpServletRequest);
        } else {
            throw new RuntimeException("invalid confirm code");
        }
    }

    private boolean confirmEmail(RegisterRequestDto registerRequestDto) {
        return confirmCode.equals(registerRequestDto.getEmailConfirmRequestDto().getCode());
    }

    public void sendConfirmEmail(RegisterRequestDto registerRequestDto) {
        try {
            confirmCode = emailService.sendTo(registerRequestDto.getEmail());
        } catch (MessagingException | IOException | GeneralSecurityException e) {
            throw new RuntimeException(e);
        }
    }

    public void login(LoginRequestDto loginUserRequestDto, HttpServletRequest request) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(loginUserRequestDto.getUsername(),
                loginUserRequestDto.getPassword());

        try {
            Authentication authentication = authenticationManager.authenticate(token);
            SecurityContext context = SecurityContextHolder.getContext();
            context.setAuthentication(authentication);

        } catch (Exception e) {
            try {
                User user = userRepository.findByEmail(loginUserRequestDto.getUsername()).orElseThrow();
                UsernamePasswordAuthenticationToken tokenEmail =
                        new UsernamePasswordAuthenticationToken(user.getUsername(), loginUserRequestDto.getPassword());
                Authentication authentication = authenticationManager.authenticate(tokenEmail);
                SecurityContext context = SecurityContextHolder.getContext();
                context.setAuthentication(authentication);

            } catch (Exception exception) {
                throw new RuntimeException("bad credentials");
            }
        }

    }

}
