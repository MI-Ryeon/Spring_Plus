package com.sparta.spring_plus.user.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public class SignupRequestDto {
    @NotEmpty(message = "필수 정보입니다.")
    @Pattern(regexp = "^[a-z0-9]{3,}&", message = "3자 이상의 영문 소문자, 숫자만 사용 가능합니다.")
    private String username;

    @NotEmpty(message = "필수 정보입니다.")
    @Pattern(regexp = "^[a-zA-Z0-9!@#$%^&*()_+=-]{4,}", message = "4자 이상의 영문 대/소문자, 숫자, 특수문자를 사용해 주세요.")
    private String password;

    @NotEmpty(message = "필수 정보입니다.")
    private String passwordConfirm;
}
