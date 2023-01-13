package com.example.blogs.comment;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comments")
public class CommentController {

    private final CommentService commentService;


    @PostMapping
    public void comment(@RequestBody CommentRequestDto commentRequestDto) {
        commentService.comment(commentRequestDto);
    }

    @GetMapping("/post/{postId}")
    public Set<CommentResponseDto> getAllComments(@PathVariable Integer postId) {
        return commentService.getAllByPostId(postId);
    }

    @GetMapping("/reply/{commentId}")
    public Set<CommentResponseDto> getAllReplies(@PathVariable Integer commentId) {
        return commentService.getAllByCommentId(commentId);
    }

    @DeleteMapping("/{commentId}")
    public void delete(@PathVariable Integer commentId) {
        commentService.deleteComment(commentId);
    }


}
