package com.sparta.spring_plus.comment.service;

import com.sparta.spring_plus.comment.dto.CommentRequestDto;
import com.sparta.spring_plus.comment.entity.Comment;
import com.sparta.spring_plus.comment.repository.CommentRepository;
import com.sparta.spring_plus.post.entity.Post;
import com.sparta.spring_plus.post.service.PostService;
import com.sparta.spring_plus.user.entity.User;
import com.sparta.spring_plus.user.entity.UserRoleEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.RejectedExecutionException;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostService postService;

    @Transactional
    public void createComment(User user, CommentRequestDto requestDto) {
        Post post = postService.findPost(requestDto.getPostId());
        Comment comment = new Comment(requestDto);
        comment.setUser(user);
        comment.setPost(post);

        commentRepository.save(comment);
    }

    @Transactional
    public void updateComment(User user, CommentRequestDto requestDto, Long id) {
        Comment comment = findComment(id);

        if (!(user.getRole().equals(UserRoleEnum.ADMIN) || comment.getUser().equals(user))) {
            throw new RejectedExecutionException("작성자, 관리자만 수정할 수 있습니다.");
        }

        comment.update(requestDto);
    }

    @Transactional
    public void deleteComment(User user, Long id) {
        Comment comment = findComment(id);

        if (!(user.getRole().equals(UserRoleEnum.ADMIN) || comment.getUser().equals(user))) {
            throw new RejectedExecutionException("작성자, 관리자만 삭제할 수 있습니다.");
        }

        commentRepository.delete(comment);
    }

    public Comment findComment(Long id) {
        return commentRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("선택한 댓글이 존재하지 않습니다."));
    }
}
