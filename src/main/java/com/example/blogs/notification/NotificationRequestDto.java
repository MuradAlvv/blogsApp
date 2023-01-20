package com.example.blogs.notification;

import com.example.blogs.security.user.User;
import lombok.Data;

@Data
public class NotificationRequestDto {

    private String title;
    private String content;
    private Integer userId;
}
