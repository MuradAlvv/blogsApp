package com.example.blogs.like;

import com.example.blogs.post.Post;
import com.example.blogs.post.PostRepository;
import com.example.blogs.security.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;


@Service
@RequiredArgsConstructor
public class LikeService {

    private final LikeRepository likeRepository;
    private final PostRepository postRepository;
    private final SecurityUtil securityUtil;


    @Transactional
    public void like(Integer postId) {

        List<Like> likes = likeRepository.findAllByUser(securityUtil.getUser());
        List<Integer> likedPosts = likes.stream().map(l -> l.getPost().getId()).toList();

        if (!likedPosts.contains(postId)) {
            Like like = new Like();
            Post post = postRepository.findById(postId).orElseThrow();
            like.setPost(post);
            like.setUser(securityUtil.getUser());
            likeRepository.save(like);
            if (Objects.nonNull(post.getLikes())) {
                post.setLikes(likeRepository.findAllByPostId(postId).size());
            } else {
                post.setLikes(1);
            }
//            postRepository.save(post);
        } else {
            Post post = postRepository.findById(postId).get();
            likeRepository.deleteByPostAndUser(post, securityUtil.getUser());
            post.setLikes(likeRepository.findAllByPostId(postId).size());
//            postRepository.save(post);
        }
    }
}
