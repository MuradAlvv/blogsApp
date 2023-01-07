package com.example.blogs.post;


import com.example.blogs.author.Author;
import com.example.blogs.author.AuthorResponseDto;
import com.example.blogs.profile.ProfileImageMapper;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class PostResponseDto {
    private Integer id;

    private String content;
    private AuthorResponseDto authorResponseDto;
    private Integer likes;

    public PostResponseDto(String content, Author author, Integer id, Integer likes) {
        this.content = content;
        this.authorResponseDto = new AuthorResponseDto(author.getFullName(),
                ProfileImageMapper.toResponseDto(author.getUser().getProfileImage()));
        this.id = id;
        this.likes = likes;
    }
}
