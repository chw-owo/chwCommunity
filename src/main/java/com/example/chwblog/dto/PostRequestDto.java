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

        public PostRequestDto(String title, String contents, Long likeNum, Long commentNum) {
                this.title = title;
                this.contents = contents;
                this.likeNum = likeNum;
                this.commentNum = commentNum;
        }
}
