package com.example.chwblog.controller;
import com.example.chwblog.dto.SignupRequestDto;
import com.example.chwblog.model.User;
import com.example.chwblog.service.KakaoUserService;
import com.example.chwblog.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Controller
public class UserController {

    private final UserService userService;
    private final KakaoUserService kakaoUserService;

    private boolean isAuthenticated() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || AnonymousAuthenticationToken.class.
                isAssignableFrom(authentication.getClass())) {
            return false;
        }
        return authentication.isAuthenticated();
    }
    @Autowired
    public UserController(UserService userService, KakaoUserService kakaoUserService) {
        this.userService = userService;
        this.kakaoUserService = kakaoUserService;
    }

    @GetMapping("/user/login/page")
    public String loginPage (HttpServletResponse response) throws IOException {
        if (!isAuthenticated()) {
            response.setContentType("text/html; charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>alert('로그인이 필요한 기능입니다');</script>");
            out.flush();
            return "login";
        }else{
            return "redirect:/";
        }

    }

    // 회원 로그인 페이지
    @GetMapping("/user/login")
    public String login(HttpServletResponse response) throws IOException  {
        if (isAuthenticated()) {
            response.setContentType("text/html; charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>alert('이미 로그인 상태입니다.'); history.go(-1);</script>");
            out.flush();
            return "redirect:/";
        }else{
            return "login";
        }

    }

    @PostMapping("/user/logout")
    public String logoutPage (HttpServletResponse response) throws IOException {
        if (!isAuthenticated()) {
            response.setContentType("text/html; charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>alert('이미 로그아웃 상태입니다.'); history.go(-1);</script>");
            out.flush();
            return "login";
        }else{
            return "redirect:/user/logout";
        }
    }

    // 회원 가입 페이지
    @GetMapping("/user/signup")
    public String signup() {
        return "signup";
    }

    // 회원 가입 요청 처리
    @PostMapping("/user/signup")
    public ModelAndView registerUser(Model model, @RequestBody SignupRequestDto requestDto) {
        int check = userService.registerUser(requestDto);

        ModelAndView modelAndView = new ModelAndView();
        MappingJackson2JsonView jsonView = new MappingJackson2JsonView();
        modelAndView.setView(jsonView);

        model.addAttribute("idCheck", check);

        return modelAndView;

    }



    @GetMapping("/user/kakao/callback")
    public String kakaoLogin(@RequestParam String code) throws JsonProcessingException {
        kakaoUserService.kakaoLogin(code);
        return "redirect:/";
    }

    @GetMapping("/denied")
    public String accessDenied(@RequestParam(value = "exception",required = false) String exception,
                               Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User)authentication.getPrincipal();
        model.addAttribute("username",user.getUsername());
        model.addAttribute("exception",exception);
        return "denied";
    }
}

