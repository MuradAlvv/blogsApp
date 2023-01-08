package com.example.blogs.comment;

import com.example.blogs.post.Post;
import com.example.blogs.security.user.User;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Getter
@Setter
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String content;
    @ManyToOne
    private Post post;
    @OneToOne
    private User author;
    @OneToMany
    private Set<Comment> replies;
    private boolean hasReply;
    @CreationTimestamp
    private LocalDateTime createdAt;
}
