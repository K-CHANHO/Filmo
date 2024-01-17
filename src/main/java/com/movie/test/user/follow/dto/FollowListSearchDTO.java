package com.movie.test.user.follow.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.RequestParam;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FollowListSearchDTO {
    private String userId;
    private String lastUserId="";
    private String keyword;
    private String type;
}
