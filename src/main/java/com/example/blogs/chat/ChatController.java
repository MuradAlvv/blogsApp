package com.example.blogs.chat;

import com.example.blogs.profile.ProfileDetailsResponseDto;
import com.example.blogs.security.SecurityUtil;
import com.example.blogs.security.user.User;
import com.example.blogs.security.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.socket.WebSocketSession;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;
import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class ChatController {

    private final SecurityUtil securityUtil;
    private final UserRepository userRepository;

    @MessageMapping("/chat")
    @SendTo("/topic/greetings")
    public Greeting showMessage(Principal principal, Message message) {
        String name = principal.getName();
        User user = userRepository.findByUsername(name).orElseThrow();
        return new Greeting(message.getContent(),
                new ProfileDetailsResponseDto(user.getUsername(), user.getProfileImage().getUrl()));
    }
}
