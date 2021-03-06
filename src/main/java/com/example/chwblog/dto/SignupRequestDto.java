package com.example.chwblog.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SignupRequestDto {
    private String username;
    private String password;

    public SignupRequestDto(String username, String password) {
        this.username = username;
        this.password = password;
    }
}