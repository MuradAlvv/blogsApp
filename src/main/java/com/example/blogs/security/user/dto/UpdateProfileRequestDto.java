package com.example.blogs.security.user.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class UpdateProfileRequestDto {

    private String username;
    private String fullname;
    private MultipartFile image;

}
