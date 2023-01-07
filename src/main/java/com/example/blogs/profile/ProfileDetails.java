package com.example.blogs.profile;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class ProfileDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String url;

    public ProfileDetails(java.io.File file) {
        this(file.getName(), file.getAbsolutePath());
    }

    public ProfileDetails(String name, String url) {
        setName(name);
        setUrl(url);
    }

}
