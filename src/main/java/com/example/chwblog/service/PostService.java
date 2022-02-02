package com.example.chwblog.service;

import com.example.chwblog.dto.PostRequestDto;
import com.example.chwblog.model.Post;
import com.example.chwblog.repository.PostRepository;
import com.example.chwblog.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;

    public ModelAndView getPost(){
        ModelAndView modelAndView = new ModelAndView("index.html");
        List<Post> postList = postRepository.findAllByOrderByCreatedAtDesc();
        modelAndView.addObject("postList",postList);
        return modelAndView;
    }

    //post.html===============================================

    public ModelAndView getPostingPage(){
        ModelAndView modelAndView = new ModelAndView("post.html");
        return modelAndView;
    }


    @PostMapping("/posting")
    public Post createPost(@RequestBody PostRequestDto requestDto,
                           @AuthenticationPrincipal UserDetailsImpl userDetails) {

        String username = userDetails.getUser().getUsername();
        Post post = new Post(requestDto, username);
        postRepository.save(post);
        return post;
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

    @ResponseBody
    @DeleteMapping("/detail/{id}")
    public int deletePost(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        Optional<Post> post = postRepository.findById(id);
        String postUsername = post.get().getUsername();
        String username = userDetails.getUser().getUsername();

        int deleteMsg;
        if(postUsername == username){
            postRepository.deleteById(id);
            deleteMsg = 0;
        }else{
            deleteMsg = 1;
        }

        return deleteMsg;
    }


    @GetMapping("/detail/edit/{id}")
    public int editPostCheck(@PathVariable("id") Long Id, @AuthenticationPrincipal UserDetailsImpl userDetails) {

        Optional<Post> post = postRepository.findById(Id);
        String postUsername = post.get().getUsername();
        String username = userDetails.getUser().getUsername();

        int editMsg;
        if(postUsername == username) {
            editMsg = 0;
        }
        else{
            editMsg = 1;
        }
        return editMsg;

    }

    // edit ========================================================================

    @GetMapping("/edit/{id}")
    public ModelAndView editingPage(@PathVariable("id") Long Id) {

        Optional<Post> post = postRepository.findById(Id);
        ModelAndView modelAndView = new ModelAndView("edit.html");
        modelAndView.addObject("postId_edit",post.get().getId());

        return modelAndView;

    }


    @PatchMapping("/edit/{id}")
    public int editPost(@PathVariable Long id, @RequestBody PostRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){


        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 글입니다.")
        );

        String postUsername = post.getUsername();
        String username = userDetails.getUser().getUsername();
        int editMsg;

        if(postUsername == username) {
            if (requestDto.getTitle() != null) {
                post.setTitle(requestDto.getContents());
            }
            if (requestDto.getContents() != null) {
                post.setContents(requestDto.getContents());
            }
            editMsg = 0;
            postRepository.save(post);

        } else{
            editMsg = 1;

        }

        return editMsg;
    }

}
