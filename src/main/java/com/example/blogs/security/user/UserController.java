package com.example.blogs.security.user;

import com.example.blogs.security.user.dto.UsernameRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @PostMapping("/exist")
    public boolean validateUsername(@RequestBody UsernameRequestDto usernameRequestDto) {
        return userService.checkIfUserExistByUsername(usernameRequestDto);
    }


}
