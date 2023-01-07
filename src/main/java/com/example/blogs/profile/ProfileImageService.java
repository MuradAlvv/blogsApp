package com.example.blogs.profile;

import com.example.blogs.security.SecurityUtil;
import com.example.blogs.security.user.User;
import com.example.blogs.security.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProfileImageService {

    private final ProfileImageRepository profileImageRepository;
    private final UserRepository userRepository;
    private final SecurityUtil securityUtil;


    public void upload(MultipartFile file, String username) throws IOException {

        File dir = new File(ProfileImageController.path + username);
        if (!dir.exists()) {
            dir.mkdir();
        }
        String filename = file.getOriginalFilename();
        String type = filename.substring(filename.lastIndexOf("."));
        String resName = UUID.randomUUID().toString() + type;
        File file1 = new File(dir, resName);
        file.transferTo(file1);

        ProfileImage profileImage = new ProfileImage(file1);
        User user = userRepository.findByName(username).orElseThrow();
        profileImage.setUrl("Files/" + username + "/" + resName);
        user.setProfileImage(profileImage);
        profileImageRepository.save(profileImage);
    }


    public ProfileImageResponseDto getUrl(String username) {
        User user = securityUtil.getUser();
        ProfileImage profileImage = user.getProfileImage();
        ProfileImageResponseDto profileResponseDto = new ProfileImageResponseDto();
        profileResponseDto.setUrl(profileImage.getUrl());
        profileResponseDto.setUsername(securityUtil.getUsername());
        return profileResponseDto;

    }

    public ProfileImageResponseDto getProfileName() {

        if (securityUtil.isAuthenticated()) {
            return new ProfileImageResponseDto(securityUtil.getUsername(), getUrl(securityUtil.getUsername()).getUrl());
        }
        return null;
    }
}
