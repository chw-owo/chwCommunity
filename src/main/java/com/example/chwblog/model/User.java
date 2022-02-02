package com.example.chwblog.model;
import com.example.chwblog.dto.SignupRequestDto;
import com.example.chwblog.validator.UserValidator;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;


@Setter
@Getter // get 함수를 일괄적으로 만들어줍니다.
@NoArgsConstructor // 기본 생성자를 만들어줍니다.
@Entity // DB 테이블 역할을 합니다.
public class User {

    // ID가 자동으로 생성 및 증가합니다.
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    // nullable: null 허용 여부
// unique: 중복 허용 여부 (false 일때 중복 허용)


    //@Size(min=3, message="3자 이상으로 작성해야 합니다.")
    @Column(nullable = false, unique = true)
    private String username;


    //@Size(min=4, message="4자 이상으로 작성해야 합니다.")
    @Column(nullable = false)
    private String password;


    @Column(unique = true)
    private Long kakaoId;

    public User(String username, String password) {

        UserValidator.validateUserInput(username, password);

        this.username = username;
        this.password = password;
        this.kakaoId = null;
    }



    public User(String username, String password, Long kakaoId) {

        this.username = username;
        this.password = password;
        this.kakaoId = kakaoId;


    }

    public User(SignupRequestDto requestDto) {

        if (requestDto.getUsername() == null || requestDto.getUsername().isEmpty()||requestDto.getUsername()=="") {
            throw new IllegalArgumentException("회원 Id 가 유효하지 않습니다.");
        }

        if (requestDto.getPassword() == null || requestDto.getPassword().isEmpty()||requestDto.getPassword()=="") {
            throw new IllegalArgumentException("비밀번호가 유효하지 않습니다.");
        }

        this.username = requestDto.getUsername();
        this.password = requestDto.getPassword();
        this.kakaoId = null;

    }
}

