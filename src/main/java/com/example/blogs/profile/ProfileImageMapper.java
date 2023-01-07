package com.example.blogs.profile;

import org.springframework.stereotype.Component;

@Component
public class ProfileImageMapper {

    public static ProfileImageResponseDto toResponseDto(ProfileImage profileImage) {
        ProfileImageResponseDto profileImageResponseDto = new ProfileImageResponseDto();
        profileImageResponseDto.setUrl(profileImage.getUrl());
        return profileImageResponseDto;
    }
}
