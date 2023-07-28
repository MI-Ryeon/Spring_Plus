package com.sparta.spring_plus.post.controller;

import com.sparta.spring_plus.common.dto.ApiResponseDto;
import com.sparta.spring_plus.common.security.UserDetailsImpl;
import com.sparta.spring_plus.post.dto.PostListResponseDto;
import com.sparta.spring_plus.post.dto.PostRequestDto;
import com.sparta.spring_plus.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/posts")
public class PostController {
    private final PostService postService;

    @GetMapping
    public ResponseEntity<PostListResponseDto> getPosts() {
        PostListResponseDto result = postService.getPosts();
        return ResponseEntity.ok().body(result);
    }

    @PostMapping
    public ResponseEntity<ApiResponseDto> createPost(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                                     @RequestBody PostRequestDto postRequestDto) {
        postService.createPost(userDetails.getUser(), postRequestDto);
        return ResponseEntity.ok().body(new ApiResponseDto("게시글 생성 성공", HttpStatus.OK.value()));
    }
}
