package com.movie.test.user.follow.dto;

import com.movie.test.common.dto.BaseTimeDTO;
import com.movie.test.user.follow.entity.FollowEntity;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Hidden
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class FollowDTO extends BaseTimeDTO {

    private String followId;

    private String userId; // 팔로우 당사자

    private String followTarget; // 팔로우 대상

    public static FollowDTO toDTO(FollowEntity entity) {
        FollowDTO dto = FollowDTO.builder()
                .followId(entity.getFollowId())
                .userId(entity.getUserId())
                .followTarget(entity.getFollowTarget())
                .createDate(entity.getCreateDate())
                .lastModifiedDate(entity.getLastModifiedDate())
                .build();

        return dto;
    }

    public static FollowEntity toEntity(FollowDTO dto) {
        FollowEntity entity = FollowEntity.builder()
                .followId(dto.getFollowId())
                .userId(dto.getUserId())
                .followTarget(dto.getFollowTarget())
                .build();

        return entity;
    }

    private String followTargetNickname;
}
