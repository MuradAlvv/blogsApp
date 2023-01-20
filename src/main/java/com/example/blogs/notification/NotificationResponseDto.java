package com.example.blogs.notification;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class NotificationResponseDto {

    private Integer id;
    private String title;
    private String content;
}
