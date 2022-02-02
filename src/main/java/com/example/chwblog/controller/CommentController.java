package com.example.chwblog.controller;

import com.example.chwblog.dto.CommentRequestDto;
import com.example.chwblog.model.Comment;
import com.example.chwblog.repository.CommentRepository;
import com.example.chwblog.security.UserDetailsImpl;
import com.example.chwblog.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class CommentController {

    private final CommentRepository commentRepository;
    private final CommentService commentService;

    //detail.html==================================================

    @GetMapping("/comment")
    public List<Comment> getComments() {
        return commentRepository.findAllByOrderByCreatedAtDesc();
    }

    @PostMapping("/comment")
    public Comment createComment(@RequestBody CommentRequestDto requestDto,
                                 @AuthenticationPrincipal UserDetailsImpl userDetails) {

        Comment comment = commentService.createComment(requestDto, userDetails);
        return comment;
    }

    @DeleteMapping("/comment/{id}")
    public Long deleteComment(@PathVariable Long id) {
        commentRepository.deleteById(id);
        return id;
    }

    @DeleteMapping("/comment/one/{id}")
    public int deleteComment(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        int deleteMsg = commentService.deleteComment(id, userDetails);
        return deleteMsg;
    }



    @PatchMapping("/comment/{id}")
    public int editupdateComment(@PathVariable Long id, @RequestBody CommentRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        int editMsg = commentService.editComment(id, requestDto, userDetails);
        return editMsg;
    }

}