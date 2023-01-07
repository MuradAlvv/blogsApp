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
public class ProfileDetailsService {

    private final ProfileDetailsRepository profileImageRepository;
    private final UserRepository userRepository;
    private final SecurityUtil securityUtil;


    public void upload(MultipartFile file, String username) throws IOException {

        File dir = new File(ProfileDetailsController.path + username);
        if (!dir.exists()) {
            dir.mkdir();
        }
        String filename = file.getOriginalFilename();
        String type = filename.substring(filename.lastIndexOf("."));
        String resName = UUID.randomUUID().toString() + type;
        File file1 = new File(dir, resName);
        file.transferTo(file1);

        ProfileDetails profileImage = new ProfileDetails(file1);
        User user = userRepository.findByName(username).orElseThrow();
        profileImage.setUrl("Files/" + username + "/" + resName);
        user.setProfileImage(profileImage);
        profileImageRepository.save(profileImage);
    }


    public String getUrl(String username) {
        User user = userRepository.findByName(username).orElseThrow();
        ProfileDetails profileImage = user.getProfileImage();
        return profileImage.getUrl();

    }

    public ProfileDetailsResponseDto getProfileName() {

        if (securityUtil.isAuthenticated()) {
            return new ProfileDetailsResponseDto(securityUtil.getUsername(),getUrl(securityUtil.getUsername()));
        }
        return null;
    }
}
