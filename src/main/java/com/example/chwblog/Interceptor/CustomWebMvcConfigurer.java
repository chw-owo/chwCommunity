//package com.example.chwblog.Interceptor;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@Configuration
//@RequiredArgsConstructor
//public class CustomWebMvcConfigurer implements WebMvcConfigurer {
//
//    private static final String[] EXCLUDE_PATHS = {
//            "/",
//            "/detail/**",
//            "/comment",
//            "/user/logout",
//            "/css/**", "/js/**", "/images/**", "/lib/**"
//
//    };
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new BasicInterceptor())
//                .addPathPatterns("/posting")
//                .addPathPatterns("/edit/**");
//
//        registry.addInterceptor(new LoginInterceptor())
//                .addPathPatterns("/user/login")
//                .addPathPatterns("/user/signup");
//
//        registry.addInterceptor(new LogoutInterceptor())
//                .addPathPatterns("/user/logout");
//    }
//
//
//}
