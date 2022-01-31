package com.example.chwblog.service;

import com.example.chwblog.dto.CommentRequestDto;
import com.example.chwblog.model.Comment;
import com.example.chwblog.repository.CommentRepository;
import com.example.chwblog.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentRepository CommentRepository;

    @Transactional
    public Long update(Long id, CommentRequestDto requestDto,
                       String username) {

        Comment comment = CommentRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
        comment.update(requestDto, username);
        return comment.getId();
    }
}
