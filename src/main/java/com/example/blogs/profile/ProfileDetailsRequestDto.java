package com.example.blogs.profile;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;


@Data
public class ProfileDetailsRequestDto {

    private MultipartFile image;

}
