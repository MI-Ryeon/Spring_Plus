package com.sparta.spring_plus.post.service;

import com.sparta.spring_plus.post.dto.PostListResponseDto;
import com.sparta.spring_plus.post.dto.PostRequestDto;
import com.sparta.spring_plus.post.dto.PostResponseDto;
import com.sparta.spring_plus.post.entity.Post;
import com.sparta.spring_plus.post.repository.PostRepository;
import com.sparta.spring_plus.user.entity.User;
import com.sparta.spring_plus.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public PostListResponseDto getPosts() {
        List<PostResponseDto> postsList = postRepository.findAllByOrderByCreatedAtDesc().stream()
                .map(PostResponseDto::new)
                .collect(Collectors.toList());

        return new PostListResponseDto(postsList);
    }

    public void createPost(User user, PostRequestDto postRequestDto) {
        // 로그인이 안되어 있을 경우, 로그인이 필요하다는 Response 를 주고 싶음.

        Post post = new Post(postRequestDto);
        post.setUser(user);

        postRepository.save(post);
    }

    public PostResponseDto getPost(Long id) {
        Post post = findPost(id);
        return new PostResponseDto(post);
    }

    public Post findPost(Long id) {
        return postRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("선택한 게시글은 존재하지 않습니다."));
    }
}
