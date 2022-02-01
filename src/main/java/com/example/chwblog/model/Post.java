package com.example.chwblog.model;

import com.example.chwblog.Timestamped;
import com.example.chwblog.dto.PostRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor // 기본생성자를 만듭니다.
@Getter
@Setter
@Entity // 테이블과 연계됨을 스프링에게 알려줍니다.
public class Post extends Timestamped { // 생성,수정 시간을 자동으로 만들어줍니다.
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable=false)
    private String username;


    @Column(nullable = false)
    private String contents;

    @Column
    private Long likeNum;

    @Column
    private Long commentNum;

    @PrePersist
    public void prePersist(){
        //this.username = this.username == null ? "anonymous" : this.username;
        this.likeNum = this.likeNum == null ? Long.valueOf(0) : this.likeNum;
        this.commentNum = this.commentNum == null ? Long.valueOf(0) : this.commentNum;
    }




    public void update(PostRequestDto requestDto, String username) {
        this.title = requestDto.getTitle();
        this.username = username;
        this.contents = requestDto.getContents();
        this.likeNum = requestDto.getLikeNum();
        this.commentNum = requestDto.getCommentNum();
    }

    public Post(PostRequestDto requestDto, String username) {
        this.title = requestDto.getTitle();
        this.username = username;
        this.contents = requestDto.getContents();
        this.likeNum = requestDto.getLikeNum();
        this.commentNum = requestDto.getCommentNum();
    }
}