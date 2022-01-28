package com.example.chwblog.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostRequestDto {

        private String title;
        private String username;
        private String contents;

        private Long likeNum;
        private Long commentNum;

}
