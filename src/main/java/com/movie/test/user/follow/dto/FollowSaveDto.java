package com.movie.test.user.follow.dto;

import com.movie.test.user.follow.entity.FollowEntity;
import com.movie.test.user.follow.mapper.FollowSaveMapper;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class FollowSaveDto {

    @Schema(description = "팔로우 id")
    @Hidden
    private String followId;

    @Schema(description = "팔로우 대상")
    private String targetId;

    @Hidden
    private String userId;

    public static FollowEntity toEntity(FollowSaveDto dto){
        return FollowSaveMapper.INSTANCE.toFollowEntity(dto);
    }

    public static FollowSaveDto toDto(FollowEntity entity){
        return FollowSaveMapper.INSTANCE.toFollowSaveDto(entity);
    }

}
