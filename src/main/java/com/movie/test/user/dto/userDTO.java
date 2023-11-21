package com.movie.test.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class userDTO {

    private String uid;
    private String userId; // 자체 uid
    private String type; // kakao OR google OR naver
    private String nickname;
    private String profileURL; // 프로필사진 url
    private Timestamp last_login_date;
    private Timestamp create_date;

}
