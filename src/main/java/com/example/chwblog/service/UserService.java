package com.example.chwblog.service;
import com.example.chwblog.dto.SignupRequestDto;
import com.example.chwblog.model.User;
import com.example.chwblog.repository.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

@Service
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public int registerUser(SignupRequestDto requestDto) {
// 회원 ID 중복 확인
        String username = requestDto.getUsername();
        Optional<User> found = userRepository.findByUsername(username);

        if (found.isPresent()) {
            return 0; //throw new IllegalArgumentException("중복된 사용자 ID 가 존재합니다.");
        }

// 패스워드 암호화
        String password_tmp = requestDto.getPassword();
        String password = passwordEncoder.encode(password_tmp);

        User user = new User(username, password);
        userRepository.save(user);
        return 1;
    }

    public static boolean isAuthenticated() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || AnonymousAuthenticationToken.class.
                isAssignableFrom(authentication.getClass())) {
            return false;
        }
        return authentication.isAuthenticated();
    }


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


    public ModelAndView registerUser(Model model, @RequestBody SignupRequestDto requestDto) {
        int check = registerUser(requestDto);

        ModelAndView modelAndView = new ModelAndView();
        MappingJackson2JsonView jsonView = new MappingJackson2JsonView();
        modelAndView.setView(jsonView);

        model.addAttribute("idCheck", check);

        return modelAndView;

    }

    public String accessDenied(@RequestParam(value = "exception",required = false) String exception,
                               Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User)authentication.getPrincipal();
        model.addAttribute("username",user.getUsername());
        model.addAttribute("exception",exception);
        return "denied";
    }


}