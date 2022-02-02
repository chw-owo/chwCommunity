package com.example.chwblog.model;

import static org.junit.jupiter.api.Assertions.*;

import com.example.chwblog.dto.PostRequestDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PostTest {
    @Test
    @DisplayName("정상 케이스")
    void createPost_Normal() {
// given
        String title="test";
        String username="test name";
        String contents="test contents";

        Long likeNum = 1L;
        Long commentNum = 1L;

        PostRequestDto requestDto = new PostRequestDto(
                title,
                contents,
                likeNum,
                commentNum
        );

// when
        Post post = new Post(requestDto, username);

// then
        assertNull(post.getId());
        assertEquals(title, post.getTitle());
        assertEquals(username, post.getUsername());
        assertEquals(contents, post.getContents());
        assertEquals(likeNum, post.getLikeNum());
        assertEquals(commentNum, post.getCommentNum());
    }
}