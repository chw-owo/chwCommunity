package com.example.chwblog.repository;

import com.example.chwblog.Timestamped;
import com.example.chwblog.dto.PostRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor // 기본생성자를 만듭니다.
@Getter
@Entity // 테이블과 연계됨을 스프링에게 알려줍니다.
public class Post extends Timestamped { // 생성,수정 시간을 자동으로 만들어줍니다.
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String contents;

    @Column
    private Long likeNum= Long.valueOf(0);

    @Column
    private Long commentNum= Long.valueOf(0);

    @Column
    private Long parentId = Long.valueOf(0);


    public Post(String title, String username, String contents, Long likeNum, Long commentNum) {
        this.title = title;
        this.username = username;
        this.contents = contents;
        this.likeNum = likeNum;
        this.commentNum = commentNum;
        this.parentId = Long.valueOf(0);//
    }

    public void update(PostRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.username = requestDto.getUsername();
        this.contents = requestDto.getContents();
        this.likeNum = requestDto.getLikeNum();
        this.commentNum = requestDto.getCommentNum();
        this.parentId = Long.valueOf(0);
    }

    public Post(PostRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.username = requestDto.getUsername();
        this.contents = requestDto.getContents();
        this.likeNum = requestDto.getLikeNum();
        this.commentNum = requestDto.getCommentNum();
        this.parentId = Long.valueOf(0);
    }
}