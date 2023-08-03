package com.sparta.spring_plus.post.controller;

import com.sparta.spring_plus.common.dto.ApiResponseDto;
import com.sparta.spring_plus.common.security.UserDetailsImpl;
import com.sparta.spring_plus.post.dto.PostListResponseDto;
import com.sparta.spring_plus.post.dto.PostRequestDto;
import com.sparta.spring_plus.post.dto.PostResponseDto;
import com.sparta.spring_plus.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class PostController {
    private final PostService postService;

    @PostMapping("/posts")
    public ResponseEntity<ApiResponseDto> createPost(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                                     @RequestBody PostRequestDto postRequestDto) {
        postService.createPost(userDetails.getUser(), postRequestDto);
        return ResponseEntity.ok().body(new ApiResponseDto("게시글 생성 성공", HttpStatus.CREATED.value()));
    }

    @GetMapping("/posts")
    public ResponseEntity<PostListResponseDto> getPosts() {
        PostListResponseDto result = postService.getPosts();
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/posts/{id}")
    public ResponseEntity<PostResponseDto> getPost(@PathVariable Long id) {
        PostResponseDto result = postService.getPost(id);
        return ResponseEntity.ok().body(result);
    }

    @PutMapping("/posts/{id}")
    public ResponseEntity<ApiResponseDto> updatePost(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                                     @RequestBody PostRequestDto postRequestDto,
                                                     @PathVariable Long id) {
        postService.updatePost(userDetails.getUser(), postRequestDto, id);
        return ResponseEntity.ok().body(new ApiResponseDto("게시글 수정 성공", HttpStatus.OK.value()));
    }

    @DeleteMapping("/posts/{id}")
    public ResponseEntity<ApiResponseDto> deletePost(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                                     @PathVariable Long id) {
        postService.deletePost(userDetails.getUser(), id);
        return ResponseEntity.ok().body(new ApiResponseDto("게시글 삭제 성공", HttpStatus.OK.value()));
    }
}