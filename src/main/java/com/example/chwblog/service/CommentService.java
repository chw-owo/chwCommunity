package com.example.chwblog.service;

import com.example.chwblog.dto.CommentRequestDto;
import com.example.chwblog.repository.Comment;
import com.example.chwblog.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentRepository CommentRepository;

    @Transactional
    public Long update(Long id, CommentRequestDto requestDto) {
        Comment comment = CommentRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
        comment.update(requestDto);
        return comment.getId();
    }
}
