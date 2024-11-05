package com.movie.test.user.block.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class BlockListDto {

    private String blockId;
    private String userId;
    private String nickname;

}
