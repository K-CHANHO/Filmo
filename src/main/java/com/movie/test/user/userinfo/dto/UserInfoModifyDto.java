package com.movie.test.user.userinfo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoModifyDto {

    private String nickname;
    private String profileUrl;
    private String introduction;
}
