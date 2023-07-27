package com.sparta.spring_plus.common.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.spring_plus.common.dto.ApiResponseDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import java.io.IOException;

public class AuthenticationFailureHandlerImpl implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        ApiResponseDto responseDto = new ApiResponseDto("닉네임 또는 패스워드를 확인해주세요.", HttpStatus.UNAUTHORIZED.value());
        response.setContentType("application/json");

        ObjectMapper mapper = new ObjectMapper();
        String jsonStr = mapper.writeValueAsString(responseDto);

        jsonStr = new String(jsonStr.getBytes("UTF-8"), "ISO-8859-1"); // utf-8 -> iso-8859-1 변환
        response.getOutputStream().print(jsonStr);
    }
}
