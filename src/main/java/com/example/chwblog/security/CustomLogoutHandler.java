package com.example.chwblog.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component

public class CustomLogoutHandler implements LogoutHandler {

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = null;
        if(authentication != null){
            try {
                out = response.getWriter();
            } catch (IOException e) {
                e.printStackTrace();
            }
            out.println("<script>alert('로그아웃 되었습니다. 로그인 페이지로 이동합니다.');window.location.href='/user/login'; </script>");
            out.flush();

        }else{
            try {
                out = response.getWriter();
            } catch (IOException e) {
                e.printStackTrace();
            }
            out.println("<script>alert('이미 로그아웃 상태입니다.');window.location.href='/';</script>");
            out.flush();

        }
    }
}
