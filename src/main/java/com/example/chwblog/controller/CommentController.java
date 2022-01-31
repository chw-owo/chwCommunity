package com.example.chwblog.controller;

import com.example.chwblog.dto.CommentRequestDto;
import com.example.chwblog.model.Comment;
import com.example.chwblog.repository.CommentRepository;
import com.example.chwblog.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class CommentController {

    private final CommentRepository commentRepository;
    private final com.example.chwblog.service.CommentService commentService;

    //detail.html==================================================

    @GetMapping("/comment")
    public List<Comment> getComments() {
        return commentRepository.findAllByOrderByModifiedAtDesc();
    }

    @PostMapping("/comment")
    public Comment createComment(@RequestBody CommentRequestDto requestDto,
                                 @AuthenticationPrincipal UserDetailsImpl userDetails) {

        String username = userDetails.getUser().getUsername();
        Comment Comment = new Comment(requestDto, username);
        return commentRepository.save(Comment);
    }

    @DeleteMapping("/comment/{id}")
    public Long deleteComment(@PathVariable Long id) {
        commentRepository.deleteById(id);
        return id;
    }

    @PatchMapping("/comment/{id}")
    public Comment updateComment(@PathVariable Long id, @RequestBody CommentRequestDto requestDto) {
        Comment comment = commentRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 글입니다.")
        );

        if(requestDto.getContents()!=null){
            comment.setContents(requestDto.getContents());
        }
        return commentRepository.save(comment);
    }

}