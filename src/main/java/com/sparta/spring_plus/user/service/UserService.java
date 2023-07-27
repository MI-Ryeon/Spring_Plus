package com.sparta.spring_plus.user.service;

import com.sparta.spring_plus.common.dto.ApiResponseDto;
import com.sparta.spring_plus.user.dto.SignupRequestDto;
import com.sparta.spring_plus.user.entity.User;
import com.sparta.spring_plus.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public ApiResponseDto signup(SignupRequestDto requestDto) {
        String username = requestDto.getUsername();
        String password = requestDto.getPassword();
        String passwordConfirm = requestDto.getPasswordConfirm();

        if (userRepository.findByUsername(username).isPresent()) {
            throw new IllegalArgumentException("이미 회원가입된 아이디입니다.");
        }

        if (password.contains(username)) {
            throw new IllegalArgumentException("비밀번호에 아이디를 포함해서는 안됩니다.");
        }

        if (!password.equals(passwordConfirm)) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        User user = new User(username, passwordEncoder.encode(password));
        userRepository.save(user);

        return new ApiResponseDto("회원가입 성공", HttpStatus.CREATED.value());
    }
}
