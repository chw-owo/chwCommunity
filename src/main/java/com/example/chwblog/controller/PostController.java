package com.example.chwblog.controller;

import com.example.chwblog.dto.PostRequestDto;
import com.example.chwblog.model.Post;
import com.example.chwblog.security.UserDetailsImpl;
import com.example.chwblog.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
public class PostController {

    private final com.example.chwblog.repository.PostRepository postRepository;
    private final com.example.chwblog.service.PostService postService;

    //index.html==================================================

    @GetMapping("/")
    public ModelAndView getPosts() {
        ModelAndView post = postService.getPost();
        return post;
    }


    //post.html===============================================
    @GetMapping("/posting")
    public ModelAndView getPostPage() {
        ModelAndView page = postService.getPostingPage();
        return page;
    }


    @PostMapping("/posting")
    public Post createPost(@RequestBody PostRequestDto requestDto,
                           @AuthenticationPrincipal UserDetailsImpl userDetails) {
        Post post = postService.createPost(requestDto, userDetails);
        return post;
    }

    //detail.html==============================================
    @GetMapping("/detail/{id}")
    public ModelAndView detailPage(@PathVariable("id") Long id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        ModelAndView post = postService.detailPage(id, userDetails);
        return post;
    }

    @ResponseBody
    @DeleteMapping("/detail/{id}")
    public int deletePost(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        int deleteMsg = postService.deletePost(id, userDetails);
        return deleteMsg;
    }


    @GetMapping("/detail/edit/{id}")
    public int editPostCheck(@PathVariable("id") Long id, @AuthenticationPrincipal UserDetailsImpl userDetails) {

        int editMsg = postService.editPostCheck(id, userDetails);
        return editMsg;

    }

    // edit ========================================================================

    @GetMapping("/edit/{id}")
    public ModelAndView editingPage(@PathVariable("id") Long id) {

        ModelAndView editPage = postService.editingPage(id);
        return editPage;

    }


    @PatchMapping("/edit/{id}")
    public int editPost(@PathVariable Long id, @RequestBody PostRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){

        int editMsg = postService.editPost(id, requestDto, userDetails);
        return editMsg;
    }
}