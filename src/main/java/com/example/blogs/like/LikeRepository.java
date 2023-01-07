package com.example.blogs.like;

import com.example.blogs.post.Post;
import com.example.blogs.security.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LikeRepository extends JpaRepository<Like, Integer> {

    List<Like> findAllByUser(User user);

    List<Like> findAllByPostId(Integer postId);

    void deleteByPost(Post post);

    void deleteByPostAndUser(Post post, User user);
}
