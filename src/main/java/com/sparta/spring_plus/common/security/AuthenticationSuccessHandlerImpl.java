package com.sparta.spring_plus.common.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.spring_plus.common.dto.ApiResponseDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;

public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_OK);
        ApiResponseDto responseDto = new ApiResponseDto("로그인 성공", HttpStatus.OK.value());
        response.setContentType("application/json");

        ObjectMapper mapper = new ObjectMapper();
        String jsonStr = mapper.writeValueAsString(responseDto);

        jsonStr = new String(jsonStr.getBytes("UTF-8"), "ISO-8859-1"); // utf-8 -> iso-8859-1 변환
        response.getOutputStream().print(jsonStr);
    }
}
