package com.example.chwblog.dto;

import lombok.Getter;

@Getter
public class PostRequestDto {

        private String title;
        private String username;
        private String contents;

        private Long likeNum;
        private Long commentNum;
        private Long parentId;

}
