package com.sparta.spring_plus.post.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class PostListResponseDto {
    private final List<PostResponseDto> postsList;

    public PostListResponseDto(List<PostResponseDto> postsList) {
        this.postsList = postsList;
    }
}
