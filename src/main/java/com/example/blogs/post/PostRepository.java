package com.example.blogs.post;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {

    @Query("select new com.example.blogs.post.PostResponseDto(p.content,p.author) from Post p")
    Page<PostResponseDto> findAllPosts(Pageable pageable);

    @Query("select new com.example.blogs.post.PostResponseDto(p.content,p.author) from Post p " +
            "where p.author.id=:authorId")
    Page<PostResponseDto> findAllPostsByAuthorId(Integer authorId, Pageable pageable);

    @Query("select new com.example.blogs.post.PostResponseDto(p.content,p.author) from Post p "
            + "where p.id = :id")
    Optional<PostResponseDto> findPostById(Integer id);
}
