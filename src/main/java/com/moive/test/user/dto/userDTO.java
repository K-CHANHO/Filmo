package com.moive.test.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class userDTO {

    private String uid;
    private String userid; // 자체 uid
    private String type;
    private String nickname;

}
