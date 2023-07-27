package com.sparta.spring_plus.user.service;

import com.sparta.spring_plus.common.dto.ApiResponseDto;
import com.sparta.spring_plus.user.dto.SignupRequestDto;

public interface UserService {
    ApiResponseDto signup(SignupRequestDto requestDto);
}
