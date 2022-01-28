package com.example.chwblog.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentRequestDto {

        private String username;
        private String contents;
        private Long likeNum;
        private Long parentId;
}
