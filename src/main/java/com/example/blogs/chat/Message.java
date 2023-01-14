package com.example.blogs.chat;

import com.example.blogs.profile.ProfileDetailsResponseDto;
import com.example.blogs.security.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message {

    private String content;

}
