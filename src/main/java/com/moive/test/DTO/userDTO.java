package com.moive.test.DTO;

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
    private String userid;
    private String type;
    private String nickname;

}
