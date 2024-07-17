package com.movie.test.user.signup.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserSignUpDto {

    private String uid; // 소셜로그인으로 부터 받은 UID
    private String type; // 소셜로그인 종류
    private String profileURL; // 프로필사진 URL

}
