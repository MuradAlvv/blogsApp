package com.example.blogs.post;

import com.example.blogs.profile.ProfileDetailsResponseDto;
import com.example.blogs.profile.ProfileDetailsService;
import com.example.blogs.security.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

//    @GetMapping
//    public Page<PostResponseDto> getAllPosts(@RequestParam int page, @RequestParam int size) {
//        return postService.getAll(page, size);
//    }

    @GetMapping()
    public List<PostResponseDto> getAllPostslist() {
        return postService.getAll(0, 1000).stream().toList();

    }


    @GetMapping("/author/{username}")
    public List<PostResponseDto> getAllPostsByUsername(@PathVariable String username) {
        return postService.getUserPosts(username);
    }


    @GetMapping("/{id}")
    public PostResponseDto getById(@PathVariable Integer id) {
        return postService.getPostById(id);
    }


    @PostMapping
    public void create(@RequestBody PostRequestDto postRequestDto) {
        postService.create(postRequestDto);
    }


    @DeleteMapping("/{postId}")
    public void delete(@PathVariable Integer postId) {
        postService.deletePostById(postId);
    }

}
