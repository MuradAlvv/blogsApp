package com.example.blogs.post;

import com.example.blogs.security.user.User;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.OnDelete;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {

    @Query("select new com.example.blogs.post.PostResponseDto(p.content,p.author,p.id,p.likes) from Post p")
    Page<PostResponseDto> findAllPosts(Pageable pageable);


    @Query("select new com.example.blogs.post.PostResponseDto(p.content,p.author,p.id,p.likes) from Post p "
            + "where p.id = :id")
    Optional<PostResponseDto> findPostById(Integer id);

    @Query("select new com.example.blogs.post.PostResponseDto(p.content,p.author,p.id,p.likes) from Post p where p.author.user = :user")
    List<PostResponseDto> findAllPostsByUser(User user);

    @Query("select p from Post p where p.author.user.id = :id")
    List<Post> findAllPostsByUserId(Integer id);
}
