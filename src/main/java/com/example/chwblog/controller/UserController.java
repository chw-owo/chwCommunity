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

    @Autowired
    public UserController(UserService userService, KakaoUserService kakaoUserService) {
        this.userService = userService;
        this.kakaoUserService = kakaoUserService;
    }

    @GetMapping("/user/login/page")
    public String loginPage (HttpServletResponse response) throws IOException {
        String result = userService.loginPage(response);
        return result;

    }

    // 회원 로그인 페이지
    @GetMapping("/user/login")
    public String login(HttpServletResponse response) throws IOException  {
        String result = userService.login(response);
        return result;

    }

    @PostMapping("/user/logout")
    public String logoutPage (HttpServletResponse response) throws IOException {
        String result = userService.logoutPage(response);
        return result;
    }

    // 회원 가입 페이지
    @GetMapping("/user/signup")
    public String signup() {
        return "signup";
    }

    // 회원 가입 요청 처리
    @PostMapping("/user/signup")
    public ModelAndView registerUser(Model model, @RequestBody SignupRequestDto requestDto) {
        ModelAndView modelAndView = userService.registerUser(model, requestDto);
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
        String result = userService.accessDenied(exception, model);
        return result;
    }
}

