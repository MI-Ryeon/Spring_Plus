package com.sparta.spring_plus.user.controller;

import com.sparta.spring_plus.common.dto.ApiResponseDto;
import com.sparta.spring_plus.user.dto.SignupRequestDto;
import com.sparta.spring_plus.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<ApiResponseDto> signup(@RequestBody SignupRequestDto requestDto) {
        ApiResponseDto result = userService.signup(requestDto);
        return ResponseEntity.ok().body(result);
    }
}
