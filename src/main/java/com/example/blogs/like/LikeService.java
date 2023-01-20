package com.example.blogs.like;

import com.example.blogs.notification.NotificationRequestDto;
import com.example.blogs.notification.NotificationService;
import com.example.blogs.post.Post;
import com.example.blogs.post.PostRepository;
import com.example.blogs.security.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;


@Service
@RequiredArgsConstructor
public class LikeService {

    private final LikeRepository likeRepository;
    private final PostRepository postRepository;
    private final NotificationService notificationService;
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


            String content = securityUtil.getUsername() + "liked your post";
            String title = "Like notify";
            Integer userId = post.getAuthor().getUser().getId();

            NotificationRequestDto notificationRequestDto = new NotificationRequestDto();
            notificationRequestDto.setTitle(title);
            notificationRequestDto.setContent(content);
            notificationRequestDto.setUserId(userId);

            notificationService.send(notificationRequestDto);

        } else {
            Post post = postRepository.findById(postId).get();
            likeRepository.deleteByPostAndUser(post, securityUtil.getUser());
            post.setLikes(likeRepository.findAllByPostId(postId).size());
//            postRepository.save(post);
        }

    }
}
