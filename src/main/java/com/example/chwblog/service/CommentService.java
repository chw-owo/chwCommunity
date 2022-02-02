package com.example.chwblog.service;

import com.example.chwblog.dto.CommentRequestDto;
import com.example.chwblog.model.Comment;
import com.example.chwblog.repository.CommentRepository;
import com.example.chwblog.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentRepository commentRepository;

//detail.html==================================================

    public Comment createComment(@RequestBody CommentRequestDto requestDto,
                                 @AuthenticationPrincipal UserDetailsImpl userDetails) {

        String username = userDetails.getUser().getUsername();
        Comment Comment = new Comment(requestDto, username);
        return commentRepository.save(Comment);
    }

    public int deleteComment(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        Optional<Comment> comment = commentRepository.findById(id);
        String commentUsername = comment.get().getUsername();
        String username = userDetails.getUser().getUsername();

        int deleteMsg;
        if (commentUsername == username) {
            commentRepository.deleteById(id);
            deleteMsg = 0;
        } else {
            deleteMsg = 1;
        }

        return deleteMsg;
    }


    public int editComment(@PathVariable Long id, @RequestBody CommentRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        Comment comment = commentRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 글입니다.")
        );


        String commentUsername = comment.getUsername();
        String username = userDetails.getUser().getUsername();
        int editMsg;

        if(commentUsername == username) {
            if (requestDto.getContents() != null) {
                comment.setContents(requestDto.getContents());
            }
            editMsg = 0;
            commentRepository.save(comment);

        } else{
            editMsg = 1;

        }

        return editMsg;
    }
}
