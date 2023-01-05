package com.example.blogs.security.auth;

import com.example.blogs.security.user.dto.LoginRequestDto;
import com.example.blogs.security.user.dto.RegisterRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthenticationController {


    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public void register(@RequestBody RegisterRequestDto registerRequestDto) {
        authenticationService.register(registerRequestDto);
    }


    @PostMapping("/login")
    public void login(@RequestBody LoginRequestDto loginUserRequestDto, HttpServletRequest request) {
        authenticationService.login(loginUserRequestDto,request);
        Enumeration<String> a = request.getSession().getAttributeNames();


    }

    @GetMapping("/logout")
    public void logout(HttpServletRequest request){
//        System.out.println(request.getSession().getAttribute("SPRING_SECURITY_CONTEXT"));
        SecurityContextHolder.getContext().getAuthentication().setAuthenticated(false);
        SecurityContextHolder.clearContext();
//        try {
//            System.out.println(request.getSession().getAttribute("SESSIONID"));
//        }catch (Exception e) {
//            e.printStackTrace();
//        }
    }

}
