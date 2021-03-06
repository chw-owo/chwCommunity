package com.example.chwblog.model;

import com.example.chwblog.Timestamped;
import com.example.chwblog.dto.CommentRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor // 기본생성자를 만듭니다.
@Getter
@Setter
@Entity // 테이블과 연계됨을 스프링에게 알려줍니다.
public class Comment extends Timestamped { // 생성,수정 시간을 자동으로 만들어줍니다.
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(nullable=false)
    private String username;

    @Column(nullable = false)
    private String contents;

    @Column
    private Long likeNum;

    @Column(nullable=false)
    private Long parentId;

    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist(){
        //this.username = this.username == null ? "anonymous" : this.username;
        this.likeNum = this.likeNum == null ? Long.valueOf(0) : this.likeNum;
        this.createdAt = LocalDateTime.now();
    }


    public Comment(String username, String contents, Long likeNum, Long parentId) {

        this.username = username;
        this.contents = contents;
        this.likeNum = likeNum;
        this.parentId = parentId ;
    }

    public void update(CommentRequestDto requestDto, String username) {

        this.username = username;
        this.contents = requestDto.getContents();
        this.likeNum = requestDto.getLikeNum();
        this.parentId  = requestDto.getParentId ();
    }

    public Comment(CommentRequestDto requestDto, String username) {

        this.username = username;
        this.contents = requestDto.getContents();
        this.likeNum = requestDto.getLikeNum();
        this.parentId = requestDto.getParentId();
    }
}