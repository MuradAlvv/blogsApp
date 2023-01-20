package com.example.blogs.security.user.dto;

import lombok.Data;

@Data
public class RegisterRequestDto {

    private String email;
    private String password;
    private String username;
    private String fullname;
    private EmailConfirmRequestDto emailConfirmRequestDto;

}
