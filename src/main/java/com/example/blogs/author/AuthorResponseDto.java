package com.example.blogs.author;

import com.example.blogs.profile.ProfileImageResponseDto;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthorResponseDto {

    private String fullName;

    private ProfileImageResponseDto profileUrl;

}
