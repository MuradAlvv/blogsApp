package com.example.blogs.post;


import com.example.blogs.author.Author;
import com.example.blogs.author.AuthorResponseDto;
import com.example.blogs.like.Like;
import com.example.blogs.like.LikeRepository;
import com.example.blogs.profile.ProfileDetailsMapper;
import com.example.blogs.profile.ProfileDetailsResponseDto;
import com.example.blogs.profile.ProfileDetailsService;
import com.example.blogs.security.user.User;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class PostResponseDto {
    private Integer id;

    private String content;
    private AuthorResponseDto authorResponseDto;
    private Integer likes;

    private LocalDateTime createdAt;
    private boolean hasComment;

    public PostResponseDto(String content, Author author,
                           Integer id, Integer likes, LocalDateTime createdAt, boolean hasComment) {
        this.content = content;
        this.authorResponseDto = new AuthorResponseDto(author.getFullName(),
                ProfileDetailsMapper.toResponseDto(author.getUser().getProfileImage()));
        this.id = id;
        this.likes = likes;
        this.createdAt = createdAt;
        this.hasComment = hasComment;
    }


}
