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
public class FollowListSearchDTO {
    private String userId;

    @Builder.Default
    private String lastUserId="";

    private String keyword;
    private String type;
}
