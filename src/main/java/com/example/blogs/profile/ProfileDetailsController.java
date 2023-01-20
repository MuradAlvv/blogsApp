package com.example.blogs.profile;

import com.example.blogs.post.PostService;
import com.example.blogs.security.SecurityUtil;
import com.example.blogs.security.user.dto.UpdateProfileRequestDto;
import com.example.blogs.security.user.dto.UsernameRequestDto;
import com.example.blogs.security.user.dto.UsernameResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/profile")
public class ProfileDetailsController {

    private final ProfileDetailsService profileDetailsService;
    private final PostService postService;
    private final SecurityUtil securityUtil;

    public static String path = "D:/ideaPROJECTS/commentssssssss/src/main/resources/Files/";


    @GetMapping("/username")
    public UsernameResponseDto getName() {
        return new UsernameResponseDto(securityUtil.getUsername());
    }

    @GetMapping("/photo")
    public ProfileDetailsResponseDto getProfilePhoto() {
        return profileDetailsService.getProfileDetails();
    }

    @GetMapping("/liked/{postId}")
    public List<ProfileDetailsResponseDto> getAllLiked(@PathVariable Integer postId) {
        return postService.getAllLikedUsersByPostId(postId);
    }


    @SneakyThrows
    @PostMapping("/photo")
    public void uploadProfilePhoto(@RequestParam("image") MultipartFile file) {
        ProfileDetailsRequestDto profileDetailsRequestDto = new ProfileDetailsRequestDto();
        profileDetailsRequestDto.setImage(file);
        profileDetailsService.upload(profileDetailsRequestDto);

    }

//    @PutMapping("/username")
//    public void updateName(@RequestBody UsernameRequestDto updateNameRequestDto) {
//        profileDetailsService.updateUsername(updateNameRequestDto);
//    }

    @SneakyThrows
    @PutMapping("/photo")
    public void updateProfilePhoto(@RequestParam MultipartFile file) {
        ProfileDetailsRequestDto profileDetailsRequestDto = new ProfileDetailsRequestDto();
        profileDetailsRequestDto.setImage(file);
        profileDetailsService.updateProfilePhoto(profileDetailsRequestDto);

    }


    @SneakyThrows
    @PutMapping
    public void updateProfile(@RequestBody UpdateProfileRequestDto updateProfileRequestDto) {
        profileDetailsService.updateProfile(updateProfileRequestDto);
    }

}
