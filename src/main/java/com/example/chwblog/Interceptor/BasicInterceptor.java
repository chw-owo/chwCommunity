package com.example.chwblog.Interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;

public class BasicInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)throws Exception{
        HttpSession session = request.getSession(false);
        if(session == null) {
            //session is null != 로그인 상태가 아님
            response.setContentType("text/html; charset=utf-8");
            PrintWriter out = response.getWriter();
            out.print("<script>alert('BasicInterceptor 로그인이 필요한 기능입니다.'); location.href='/user/login';</script>");
            out.flush();
            out.close();
            return false;
        }
        return true;

    }
}
