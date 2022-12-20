package com.example.blogs.post;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    public List<PostResponseDto> getAllPostslist(){
        return postService.getAll(0,1000).stream().toList();

    }

    @GetMapping("/author/{authorId}")
    public Page<PostResponseDto> getAllByAuthorId(@PathVariable Integer authorId,
                                                  @RequestParam int page,
                                                  @RequestParam int size) {
        return postService.getAllByAuthorId(authorId, page, size);
    }

    @GetMapping("/{id}")
    public PostResponseDto getById(@PathVariable Integer id) {
        return postService.getPostById(id);
    }

    @PostMapping
    public void create(@RequestBody PostRequestDto postRequestDto) {
        postService.create(postRequestDto);
    }

}
