package com.example.blogs.notification;

import com.example.blogs.security.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Integer> {

    @Query("select new com.example.blogs.notification.NotificationResponseDto(n.id,n.title,n.content)" +
            " from Notification n where n.user.id = :userId")
    List<NotificationResponseDto> getAllNotifications(Integer userId);
}
