package com.sparta.spring_plus.post.service;

import com.sparta.spring_plus.post.dto.PostListResponseDto;
import com.sparta.spring_plus.post.dto.PostRequestDto;
import com.sparta.spring_plus.post.dto.PostResponseDto;
import com.sparta.spring_plus.post.entity.Post;
import com.sparta.spring_plus.post.repository.PostRepository;
import com.sparta.spring_plus.user.entity.User;
import com.sparta.spring_plus.user.entity.UserRoleEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.RejectedExecutionException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    @Transactional
    public void createPost(User user, PostRequestDto postRequestDto) {
        // 로그인이 안되어 있을 경우, 로그인이 필요하다는 Response 를 주고 싶음.

        Post post = new Post(postRequestDto);
        post.setUser(user);

        postRepository.save(post);
    }

    @Transactional(readOnly = true)
    public PostListResponseDto getPosts() {
        List<PostResponseDto> postsList = postRepository.findAllByOrderByModifiedAtDesc().stream()
                .map(PostResponseDto::new)
                .collect(Collectors.toList());

        return new PostListResponseDto(postsList);
    }

    @Transactional(readOnly = true)
    public PostResponseDto getPost(Long id) {
        Post post = findPost(id);
        return new PostResponseDto(post);
    }

    @Transactional
    public void updatePost(User user, PostRequestDto postRequestDto, Long id) {
        Post post = findPost(id);

        if (!(user.getRole().equals(UserRoleEnum.ADMIN) || post.getUser().equals(user))) {
            throw new RejectedExecutionException("작성자, 관리자만 수정할 수 있습니다.");
        }

        post.update(postRequestDto);
    }

    @Transactional
    public void deletePost(User user, Long id) {
        Post post = findPost(id);

        if (!(user.getRole().equals(UserRoleEnum.ADMIN) || post.getUser().equals(user))) {
            throw new RejectedExecutionException("작성자, 관리자만 삭제할 수 있습니다.");
        }

        postRepository.deleteById(id);
    }
    public Post findPost(Long id) {
        return postRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("선택한 게시글은 존재하지 않습니다."));
    }
}
