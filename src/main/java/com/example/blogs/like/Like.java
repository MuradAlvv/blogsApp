package com.example.blogs.like;

import com.example.blogs.post.Post;
import com.example.blogs.security.user.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "likes")
@Getter
@Setter
public class Like {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @OneToOne
    private User user;
    @OneToOne
    private Post post;
}
