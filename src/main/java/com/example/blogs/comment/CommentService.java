package com.example.blogs.comment;

import com.example.blogs.post.Post;
import com.example.blogs.post.PostRepository;
import com.example.blogs.profile.ProfileDetailsResponseDto;
import com.example.blogs.security.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final SecurityUtil securityUtil;

    public void comment(CommentRequestDto commentRequestDto) {
        Comment comment = new Comment();
        comment.setAuthor(securityUtil.getUser());
        comment.setContent(commentRequestDto.getContent());

        Post post = postRepository.findById(commentRequestDto.getPostId()).orElseThrow();
        comment.setPost(post);
        post.setHasComment(true);
        if (Objects.nonNull(commentRequestDto.getCommentId())) {
            Comment mainComment = commentRepository.findById(commentRequestDto.getCommentId()).orElseThrow();
            mainComment.setHasReply(true);
            if (Objects.nonNull(mainComment.getReplies())) {
                mainComment.getReplies().add(comment);
            } else {
                mainComment.setReplies(new HashSet<>());
                mainComment.getReplies().add(comment);
            }
            commentRepository.save(comment);
            commentRepository.save(mainComment);
        } else {
            commentRepository.save(comment);
        }
    }

    public Set<CommentResponseDto> getAllByPostId(Integer postId) {
        Set<CommentResponseDto> commentResponseDtos = commentRepository.getCommentsByPostId(postId);

        return commentResponseDtos.stream().filter(c -> {
            Comment comment = commentRepository.findById(c.getId()).get();
            Comment mainComment = commentRepository.getAllComments().stream().filter(cm -> cm.getReplies().contains(comment))
                    .findFirst().orElse(null);
            return Objects.isNull(mainComment);
        }).collect(Collectors.toSet());

    }

    public Set<CommentResponseDto> getAllByCommentId(Integer commentId) {
        CommentResponseDto commentResponseDto = commentRepository.getCommentById(commentId).orElseThrow();
        Comment comment = commentRepository.findById(commentResponseDto.getId()).get();
        Set<Comment> replies = comment.getReplies();
        Set<CommentResponseDto> commentResponseDtoSet = new HashSet<>();
        replies.forEach(obj -> {
            CommentResponseDto commentResponseDtoReply = new CommentResponseDto();
            commentResponseDtoReply.setContent(obj.getContent());
            commentResponseDtoReply.setPostId(obj.getPost().getId());
            commentResponseDtoReply.setAuthor(new ProfileDetailsResponseDto(obj.getAuthor().getUsername(),
                    obj.getAuthor().getProfileImage().getUrl()));
            commentResponseDtoReply.setId(obj.getId());
            commentResponseDtoReply.setCreatedAt(obj.getCreatedAt());
            commentResponseDtoSet.add(commentResponseDtoReply);
        });

        return commentResponseDtoSet;

    }

    public void deleteComment(Integer commentId) {
        commentRepository.deleteById(commentId);
    }
}
