package com.example.chwblog.model;

import com.example.chwblog.dto.SignupRequestDto;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Nested
    @DisplayName("회원가입")
    class CreateUser {

        private String username;
        private String password;
        private Long kakaoId;

        @BeforeEach
        void setup() {
            username = "testname";
            password = "testpassword";
        }

        @Test
        @DisplayName("정상 케이스")
        void createProduct_Normal() {
// given
            SignupRequestDto requestDto = new SignupRequestDto(
                    username,
                    password
            );

// when
            //User user = new User(requestDto);
            User user = new User(username,password);

// then
            assertNull(user.getId());
            assertEquals(username, user.getUsername());
            assertEquals(password, user.getPassword());

        }

        @Nested
        @DisplayName("실패 케이스")
        class FailCases1 {
            @Nested
            @DisplayName("username")
            class userName {
                @Test
                @DisplayName("null")
                void fail1() {
// given
                    username = null;

                    SignupRequestDto requestDto = new SignupRequestDto(
                            null,
                            password
                    );

// when
                    Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                        new User(username,password);
                    });

// then
                    assertEquals("회원 Id 가 유효하지 않습니다.", exception.getMessage());
                }

                @Test
                @DisplayName("빈 문자열")
                void fail2() {
// given
                    username = "";

                    SignupRequestDto requestDto = new SignupRequestDto(
                            username,
                            password
                    );

// when
                    Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                        new User(username,password);
                    });

// then
                    assertEquals("회원 Id 가 유효하지 않습니다.", exception.getMessage());
                }

            }

            @Nested
            @DisplayName("password")
            class FailCases2 {
                @Test
                @DisplayName("null")
                void fail1() {
// given
                    password = null;

                    SignupRequestDto requestDto = new SignupRequestDto(
                            username,
                            null
                    );

// when
                    Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                        new User(username,password);
                    });

// then
                    assertEquals("비밀번호가 유효하지 않습니다.", exception.getMessage());
                }

                @Test
                @DisplayName("빈 문자열")
                void fail2() {
// given
                    password = "";

                    SignupRequestDto requestDto = new SignupRequestDto(
                            username,
                            password
                    );

// when
                    Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                        new User(username,password);
                    });

// then
                    assertEquals("비밀번호가 유효하지 않습니다.", exception.getMessage());
                }
            }
        }
    }
}