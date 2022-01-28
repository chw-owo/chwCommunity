package com.example.chwblog.repository;

import com.example.chwblog.Timestamped;
import com.example.chwblog.dto.CommentRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor // 기본생성자를 만듭니다.
@Getter
@Setter
@Entity // 테이블과 연계됨을 스프링에게 알려줍니다.
public class Comment extends Timestamped { // 생성,수정 시간을 자동으로 만들어줍니다.
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column
    private String username;

    @Column(nullable = false)
    private String contents;

    @Column
    private Long likeNum;

    @Column(nullable=false)
    private Long parentId;

    @PrePersist
    public void prePersist(){
        this.username = this.username == null ? "anonymous" : this.username;
        this.likeNum = this.likeNum == null ? Long.valueOf(0) : this.likeNum;
    }


    public Comment(String username, String contents, Long likeNum, Long parentId) {

        this.username = username;
        this.contents = contents;
        this.likeNum = likeNum;
        this.parentId = parentId ;
    }

    public void update(CommentRequestDto requestDto) {

        this.username = requestDto.getUsername();
        this.contents = requestDto.getContents();
        this.likeNum = requestDto.getLikeNum();
        this.parentId  = requestDto.getParentId ();
    }

    public Comment(CommentRequestDto requestDto) {

        this.username = requestDto.getUsername();
        this.contents = requestDto.getContents();
        this.likeNum = requestDto.getLikeNum();
        this.parentId = requestDto.getParentId();
    }
}