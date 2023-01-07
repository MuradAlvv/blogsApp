package com.example.blogs.post;


import com.example.blogs.author.Author;
import com.example.blogs.author.AuthorResponseDto;
import com.example.blogs.like.LikeRepository;
import com.example.blogs.profile.ProfileDetailsMapper;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PostResponseDto {

    private LikeRepository likeRepository;

    private Integer id;

    private String content;
    private AuthorResponseDto authorResponseDto;
    private Integer likes;

    private List<AuthorResponseDto> likedUsers;

    public PostResponseDto(String content, Author author, Integer id, Integer likes) {
        this.content = content;
        this.authorResponseDto = new AuthorResponseDto(author.getFullName(),
                ProfileDetailsMapper.toResponseDto(author.getUser().getProfileImage()));
        this.id = id;
        this.likes = likes;

//        List<Like> likeList = likeRepository.findAllByPostId(id);
//        List<User> liked = likeList.stream().map(Like::getUser).toList();
//
//        this.likedUsers = liked;
    }

    public void setLikeRepository(LikeRepository likeRepository) {
        this.likeRepository = likeRepository;
    }
}
