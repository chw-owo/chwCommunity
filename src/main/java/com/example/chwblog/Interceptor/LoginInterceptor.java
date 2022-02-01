package com.example.chwblog.Interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;

public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)throws Exception{
        HttpSession session = request.getSession(false);
        if(session != null) {
            //로그인 상태
            response.setContentType("text/html; charset=utf-8");
            PrintWriter out = response.getWriter();
            out.print("<script>alert('LoginInterceptor 이미 로그인 된 상태입니다.'); location.href='/';</script>");
            out.flush();
            out.close();
            return false;
        }
        return true;

    }
}
