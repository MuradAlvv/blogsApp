package com.example.blogs.profile;

import com.example.blogs.post.PostService;
import com.example.blogs.security.SecurityUtil;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/profile")
public class ProfileDetailsController {

    private final ProfileDetailsService profileImageService;
    private final PostService postService;

    public static String path = "D:/ideaPROJECTS/commentssssssss/src/main/resources/Files/";


    @GetMapping("/name")
    public ProfileDetailsResponseDto getName() {
        return profileImageService.getProfileName();
    }

    @GetMapping("/liked/{postId}")
    public List<ProfileDetailsResponseDto> getAllLiked(@PathVariable Integer postId){
        return postService.getAllLikedUsersByPostId(postId);
    }


    @PostMapping("/upload")
    @SneakyThrows
    public void addProfilePhoto(@RequestParam("image") MultipartFile profileImageRequestDto,
                                @RequestParam("username") String name) {


        profileImageService.upload(profileImageRequestDto, name);
    }

}
