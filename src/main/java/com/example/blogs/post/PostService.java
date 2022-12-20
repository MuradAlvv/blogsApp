package com.example.blogs.post;


import com.example.blogs.author.Author;
import com.example.blogs.author.AuthorRepository;
import com.example.blogs.security.SecurityUtil;
import com.example.blogs.security.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final AuthorRepository authorRepository;
    private final SecurityUtil securityUtil;

    public Page<PostResponseDto> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return postRepository.findAllPosts(pageable);
    }

    public Page<PostResponseDto> getAllByAuthorId(int authorId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return postRepository.findAllPostsByAuthorId(authorId, pageable);
    }

    public PostResponseDto getPostById(int id) {
        return postRepository.findPostById(id).orElseThrow();
    }

    public void create(PostRequestDto postRequestDto) {
        Post post = new Post();
        Author author = new Author();
        author.setFullName(securityUtil.getUsername());
        author.setUser(securityUtil.getUser());
        authorRepository.save(author);
        post.setAuthor(author);
        post.setContent(postRequestDto.getContent());
        postRepository.save(post);
    }
}
