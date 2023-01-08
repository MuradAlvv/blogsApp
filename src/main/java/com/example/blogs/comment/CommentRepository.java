package com.example.blogs.comment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {


    @Query("select c from Comment c")
    Set<Comment> getAllComments();

    @Query("select new com.example.blogs.comment.CommentResponseDto(c.id,c.content,c.author," +
            "c.post.id,c.createdAt,c.hasReply) from Comment c where c.post.id=:postId")
    Set<CommentResponseDto> getCommentsByPostId(Integer postId);

    @Query("select new com.example.blogs.comment.CommentResponseDto(c.id,c.content,c.author," +
            "c.post.id,c.createdAt,c.hasReply) from Comment c where c.id=:commentId")
    Optional<CommentResponseDto> getCommentById(Integer commentId);
}
