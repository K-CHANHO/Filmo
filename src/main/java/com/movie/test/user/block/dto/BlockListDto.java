package com.movie.test.user.block.dto;

import com.movie.test.user.userinfo.dto.UserDto;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class BlockListDto {

    private String blockId;
    private UserDto targetUser;

}
