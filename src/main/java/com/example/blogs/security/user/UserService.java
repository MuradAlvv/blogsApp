package com.example.blogs.security.user;

import com.example.blogs.security.user.dto.UsernameRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public boolean checkIfUserExistByUsername(UsernameRequestDto nameRequestDto) {
        return userRepository.findByUsername(nameRequestDto.getUsername()).isPresent();
    }
}
