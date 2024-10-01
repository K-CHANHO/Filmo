package com.movie.test.user.follow.dto;

import io.swagger.v3.oas.annotations.Hidden;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Hidden
// follow랑 block이랑 로직 비슷해서 두 곳에서 사용.
public class FollowListSearchDTO {
    private String userId;

    @Builder.Default
    private String lastUserId="";

    private String keyword;
}
