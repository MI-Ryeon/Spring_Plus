package com.sparta.spring_plus.comment.repository;

import com.sparta.spring_plus.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
