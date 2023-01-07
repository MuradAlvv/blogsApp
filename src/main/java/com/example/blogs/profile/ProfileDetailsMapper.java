package com.example.blogs.profile;

import org.springframework.stereotype.Component;

@Component
public class ProfileDetailsMapper {

    public static ProfileDetailsResponseDto toResponseDto(ProfileDetails profileImage) {
        ProfileDetailsResponseDto profileImageResponseDto = new ProfileDetailsResponseDto();
        profileImageResponseDto.setUrl(profileImage.getUrl());
        return profileImageResponseDto;
    }
}
