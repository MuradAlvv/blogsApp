package com.example.blogs.security.auth;

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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public void register(RegisterRequestDto userRegisterRequestDto) {
        User user = new User();
        user.setName(userRegisterRequestDto.getUsername());
        user.setPassword(passwordEncoder.encode(userRegisterRequestDto.getPassword()));
        userRepository.save(user);
    }

    public void login(LoginRequestDto loginUserRequestDto, HttpServletRequest request) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(loginUserRequestDto.getUsername(),
                loginUserRequestDto.getPassword());

        Authentication authentication = authenticationManager.authenticate(token);

        SecurityContext context = SecurityContextHolder.getContext();
        context.setAuthentication(authentication);

        HttpSession session = request.getSession(true);
        session.setAttribute("SPRING_SECURITY_CONTEXT", context);
    }
}
