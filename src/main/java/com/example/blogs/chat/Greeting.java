package com.example.blogs.chat;

import com.example.blogs.profile.ProfileDetailsResponseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Greeting {

    private String content;
    private ProfileDetailsResponseDto profileDetailsResponseDto;

}
