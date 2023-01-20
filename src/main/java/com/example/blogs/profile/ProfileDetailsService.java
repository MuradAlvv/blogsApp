package com.example.blogs.profile;

import com.example.blogs.security.SecurityUtil;
import com.example.blogs.security.user.User;
import com.example.blogs.security.user.UserRepository;
import com.example.blogs.security.user.dto.FullnameRequestDto;
import com.example.blogs.security.user.dto.UpdateProfileRequestDto;
import com.example.blogs.security.user.dto.UsernameRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProfileDetailsService {

    private final ProfileDetailsRepository profileImageRepository;
    private final UserRepository userRepository;
    private final SecurityUtil securityUtil;


    public void upload(ProfileDetailsRequestDto profileDetailsRequestDto) throws IOException {

        User user = securityUtil.getUser();
        String username = user.getUsername();
        MultipartFile file = profileDetailsRequestDto.getImage();
        File dir = new File(ProfileDetailsController.path + username);
        if (!dir.exists()) {
            dir.mkdir();
        }
        createAndSaveFile(user, username, file, dir);
    }


    public String getUrl(String username) {
        User user = userRepository.findByUsername(username).orElseThrow();
        ProfileDetails profileImage = user.getProfileImage();
        return profileImage.getUrl();

    }

    public ProfileDetailsResponseDto getProfileDetails() {

        if (securityUtil.isAuthenticated()) {
            return new ProfileDetailsResponseDto(securityUtil.getUsername(), getUrl(securityUtil.getUsername()));
        }
        return null;
    }

    public void updateUsername(UsernameRequestDto updateNameRequestDto) {
        User user = securityUtil.getUser();
        user.setUsername(updateNameRequestDto.getUsername());
        userRepository.save(user);
    }

    public void updateProfilePhoto(ProfileDetailsRequestDto profileDetailsRequestDto) throws IOException {
        User user = securityUtil.getUser();
        String username = securityUtil.getUsername();
        profileImageRepository.deleteById(user.getProfileImage().getId());

        MultipartFile file = profileDetailsRequestDto.getImage();
        File dir = Paths.get(ProfileDetailsController.path + username).toFile();
        createAndSaveFile(user, username, file, dir);
    }

    public void updateFullname(FullnameRequestDto fullnameRequestDto) {
        User user = securityUtil.getUser();
        user.setFullname(fullnameRequestDto.getFullname());
        userRepository.save(user);
    }

    private void createAndSaveFile(User user, String username, MultipartFile file, File dir) throws IOException {
        String filename = file.getOriginalFilename();
        String type = filename.substring(filename.lastIndexOf("."));
        String resName = UUID.randomUUID() + type;
        File file1 = new File(dir, resName);
        file.transferTo(file1);
        ProfileDetails profileDetails = new ProfileDetails(file1);
        profileDetails.setUrl("Files/" + username + "/" + resName);
        user.setProfileImage(profileDetails);
        profileImageRepository.save(profileDetails);
    }

    public void updateProfile(UpdateProfileRequestDto updateProfileRequestDto) throws IOException {
        ProfileDetailsRequestDto profileDetailsRequestDto = new ProfileDetailsRequestDto();
        profileDetailsRequestDto.setImage(updateProfileRequestDto.getImage());
        updateProfilePhoto(profileDetailsRequestDto);

        UsernameRequestDto usernameRequestDto = new UsernameRequestDto();
        usernameRequestDto.setUsername(updateProfileRequestDto.getUsername());
        updateUsername(usernameRequestDto);

        FullnameRequestDto fullnameRequestDto = new FullnameRequestDto();
        fullnameRequestDto.setFullname(updateProfileRequestDto.getFullname());
        updateFullname(fullnameRequestDto);
    }

}
