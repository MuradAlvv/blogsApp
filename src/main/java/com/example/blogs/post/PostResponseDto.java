package com.example.blogs.post;


import com.example.blogs.author.Author;
import com.example.blogs.author.AuthorResponseDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostResponseDto {

    private String content;
    private AuthorResponseDto authorResponseDto;

    public PostResponseDto(String content, Author author) {
        this.content = content;
        this.authorResponseDto = new AuthorResponseDto(author.getFullName());
    }
}
