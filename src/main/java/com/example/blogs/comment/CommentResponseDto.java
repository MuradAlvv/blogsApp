package com.example.blogs.comment;

import com.example.blogs.post.Post;
import com.example.blogs.profile.ProfileDetailsMapper;
import com.example.blogs.profile.ProfileDetailsResponseDto;
import com.example.blogs.security.user.User;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class CommentResponseDto {

    private Integer id;
    private String content;
    private ProfileDetailsResponseDto author;
    private Integer postId;
    private LocalDateTime createdAt;

    private boolean hasReply;


    public CommentResponseDto(Integer id, String content, User author,
                              Integer postId, LocalDateTime createdAt, boolean hasReply) {
        this.id = id;
        this.content = content;
        this.postId = postId;
        this.createdAt = createdAt;
        this.author = new ProfileDetailsResponseDto(author.getUsername(), author.getProfileImage().getUrl());
        this.hasReply = hasReply;
    }

//    public CommentResponseDto(Integer id, String content, User author,
//                              Integer postId, Set<Comment> replies,
//                              LocalDateTime createdAt) {
//        this(id,content,author,postId,createdAt);
//        this.replies = CommentMapper.toResponseDtoSet(replies);
//    }
}
