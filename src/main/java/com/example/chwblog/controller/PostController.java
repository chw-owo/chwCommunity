package com.example.chwblog.controller;

import com.example.chwblog.dto.PostRequestDto;
import com.example.chwblog.model.Post;
import com.example.chwblog.security.UserDetailsImpl;
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
    private final com.example.chwblog.service.PostService PostService;

    //index.html==================================================

    @GetMapping("/")
    public ModelAndView getPosts() {
        ModelAndView modelAndView = new ModelAndView("index.html");
        int tmp = 11;
        List<Post> postList = postRepository.findAllByOrderByModifiedAtDesc();
        modelAndView.addObject("tmp",tmp);
        modelAndView.addObject("postList",postList);
        return modelAndView;
        //return postRepository.findAllByOrderByModifiedAtDesc();
    }

    @DeleteMapping("/api/posts/{id}")
    public Long deletePost(@PathVariable Long id) {
        postRepository.deleteById(id);
        return id;
    }


    //post.html===============================================
    @GetMapping("/posting")
    public ModelAndView getPostPage() {
        ModelAndView modelAndView = new ModelAndView("post.html");

        return modelAndView;

    }

    @PostMapping("/posting")
    public Post createPost(@RequestBody PostRequestDto requestDto,
                           @AuthenticationPrincipal UserDetailsImpl userDetails) {

        String username = userDetails.getUser().getUsername();

        Post Post = new Post(requestDto, username);
        return postRepository.save(Post);
    }

    //detail.html==============================================
    @GetMapping("/detail/{id}")
    public ModelAndView detailPage(@PathVariable("id") Long Id, @AuthenticationPrincipal UserDetailsImpl userDetails) {

        Optional<Post> post = postRepository.findById(Id);

        ModelAndView modelAndView = new ModelAndView("detail.html");
        modelAndView.addObject("postId",post.get().getId());
        modelAndView.addObject("postUsername",post.get().getUsername());
        modelAndView.addObject("postTitle", post.get().getTitle());
        modelAndView.addObject("postContents", post.get().getContents());
        modelAndView.addObject("postLikeNum", post.get().getLikeNum());
        modelAndView.addObject("postCommentNum", post.get().getCommentNum());

        return modelAndView;
    }

    // edit ========================================================================

    @GetMapping("/edit/{id}")
    public ModelAndView editPage(@PathVariable("id") Long Id) {

        Optional<Post> post = postRepository.findById(Id);
        ModelAndView modelAndView = new ModelAndView("edit.html");
        modelAndView.addObject("postId_edit",post.get().getId());

        return modelAndView;

    }

    @PatchMapping("/edit/{id}")
    public Post editUpdate(@PathVariable Long id, @RequestBody PostRequestDto requestDto){


        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 글입니다.")
        );
        if(requestDto.getTitle()!=null){
            post.setTitle(requestDto.getContents());
        }
        if(requestDto.getContents()!=null){
            post.setContents(requestDto.getContents());
        }

        return postRepository.save(post);
    }
}