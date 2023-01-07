package com.example.blogs.profile;

import com.example.blogs.security.SecurityUtil;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/profile")
public class ProfileDetailsController {

    private final SecurityUtil securityUtil;
    private final ProfileDetailsService profileImageService;

    public static String path = "D:/ideaPROJECTS/commentssssssss/src/main/resources/Files/";


    @GetMapping("/name")
    public ProfileDetailsResponseDto getName() {
        return profileImageService.getProfileName();
    }


//    @GetMapping("/details")
//    public ProfileImageResponseDto getLoggedName() {
//
//        return profileImageService.getUrl(securityUtil.getUsername());
//    }


    @PostMapping("/upload")
    @SneakyThrows
    public void addProfilePhoto(@RequestParam("image") MultipartFile profileImageRequestDto,
                                @RequestParam("username") String name) {


        profileImageService.upload(profileImageRequestDto, name);
    }

}
