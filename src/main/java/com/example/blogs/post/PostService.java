package com.example.blogs.post;


import com.example.blogs.author.Author;
import com.example.blogs.author.AuthorRepository;
import com.example.blogs.profile.ProfileImage;
import com.example.blogs.profile.ProfileImageResponseDto;
import com.example.blogs.profile.ProfileImageService;
import com.example.blogs.security.SecurityUtil;
import com.example.blogs.security.user.User;
import com.example.blogs.security.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final AuthorRepository authorRepository;
    private final UserRepository userRepository;
    private final ProfileImageService profileImageService;
    private final SecurityUtil securityUtil;

    public Page<PostResponseDto> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return postRepository.findAllPosts(pageable);
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

    public void deletePostById(Integer postId) {
        List<Integer> postsIds = getLoggedUserPostsIds();
        if (postsIds.contains(postId)) {
            postRepository.deleteById(postId);
        } else {
            System.out.println("No such post in author posts");
            throw new RuntimeException();
        }
    }

    public List<Integer> getLoggedUserPostsIds() {
        List<Post> posts = postRepository.findAllPostsByUserId(securityUtil.getUser().getId());
        List<Integer> postsIds = posts.stream().map(Post::getId).toList();
        return postsIds;
    }

    public List<PostResponseDto> getUserPosts(String username) {
        User user = userRepository.findByName(username).orElseThrow();
        List<PostResponseDto> posts = postRepository.findAllPostsByUser(user);
        return posts;
    }
}
