package com.sparta.spring_plus.comment.controller;

import com.sparta.spring_plus.comment.dto.CommentRequestDto;
import com.sparta.spring_plus.comment.service.CommentService;
import com.sparta.spring_plus.common.dto.ApiResponseDto;
import com.sparta.spring_plus.common.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/comments")
    public ResponseEntity<ApiResponseDto> createComment(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                                        @RequestBody CommentRequestDto requestDto) {
        commentService.createComment(userDetails.getUser(), requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponseDto("댓글 작성 완료", HttpStatus.CREATED.value()));
    }

    @PutMapping("/comments/{id}")
    public ResponseEntity<ApiResponseDto> updateComment(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                                        @RequestBody CommentRequestDto requestDto,
                                                        @PathVariable Long id) {
        commentService.updateComment(userDetails.getUser(), requestDto, id);
        return ResponseEntity.ok().body(new ApiResponseDto("댓글 수정 완료", HttpStatus.OK.value()));
    }

    @DeleteMapping("/comments/{id}")
    public ResponseEntity<ApiResponseDto> deleteComment(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                                        @PathVariable Long id) {
        commentService.deleteComment(userDetails.getUser(), id);
        return ResponseEntity.ok().body(new ApiResponseDto("댓글 삭제 완료", HttpStatus.OK.value()));
    }
}
