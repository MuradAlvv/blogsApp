package com.example.blogs.like;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/likes")
public class LikeController {

    private final LikeService likeService;

    @PostMapping("/{postId}")
    public void like(@PathVariable Integer postId) {
        likeService.like(postId);
    }
}
