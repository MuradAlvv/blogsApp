package com.example.blogs.author;

import com.example.blogs.security.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Integer> {

    List<Author> findAuthorByUser(User user);
}
