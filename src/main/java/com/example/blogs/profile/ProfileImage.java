package com.example.blogs.profile;

import com.example.blogs.security.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class ProfileImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String url;

    public ProfileImage(java.io.File file) {
        this(file.getName(), file.getAbsolutePath());
    }

    public ProfileImage(String name, String url) {
        setName(name);
        setUrl(url);
    }

}
