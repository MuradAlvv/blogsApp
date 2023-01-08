package com.example.blogs.comment;

import lombok.Data;

@Data
public class CommentRequestDto {

    private String content;
    private Integer postId;
    private Integer commentId;

}
