package com.example.chwblog.validator;

import org.springframework.stereotype.Component;

@Component
public class UserValidator {

    public static void validateUserInput(String username, String password) {
        if (username == null || username.isEmpty()|| username =="") {
            throw new IllegalArgumentException("회원 Id 가 유효하지 않습니다.");
        }

        if (password == null || password.isEmpty()|| username =="") {
            throw new IllegalArgumentException("비밀번호가 유효하지 않습니다.");
        }
    }
}
