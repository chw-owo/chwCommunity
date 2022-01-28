package com.example.chwblog.controller;

import com.example.chwblog.dto.CommentRequestDto;
import com.example.chwblog.dto.PostRequestDto;
import com.example.chwblog.repository.Comment;
import com.example.chwblog.repository.CommentRepository;
import com.example.chwblog.repository.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

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
    public Comment createComment(@RequestBody CommentRequestDto requestDto) {
        Comment Comment = new Comment(requestDto);
        return commentRepository.save(Comment);
    }

    @DeleteMapping("/comment/{id}")
    public Long deleteComment(@PathVariable Long id) {
        commentRepository.deleteById(id);
        return id;
    }

    @PutMapping("/comment/{id}")
    public Comment updateComment(@PathVariable Long id, @RequestBody CommentRequestDto requestDto) {
        Comment comment = commentRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 글입니다.")
        );
        comment.update(requestDto);

        return commentRepository.save(comment);
    }

}