package com.example.chwblog.service;

import com.example.chwblog.dto.PostRequestDto;
import com.example.chwblog.model.Comment;
import com.example.chwblog.model.Post;
import com.example.chwblog.repository.PostRepository;
import com.example.chwblog.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;

    @Transactional
    public Long update(Long id, PostRequestDto requestDto,
                       String username) {

        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
        post.update(requestDto, username);
        return post.getId();
    }
}
