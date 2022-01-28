package com.example.chwblog.dto;

import lombok.Getter;

@Getter
public class CommentRequestDto {

        private String username;
        private String contents;
        private Long likeNum;
        private Long parentId;
}
