package com.movie.test.report.like.dto;

import com.movie.test.report.like.entity.LikeEntity;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Hidden
public class LikeDTO {

    private long likeId;
    private String userId;
    private String targetId;
    private String type;

    public static LikeEntity toEntity(LikeDTO dto){

        LikeEntity entity = LikeEntity.builder()
                .userId(dto.getUserId())
                .targetId(dto.getTargetId())
                .type(dto.getType())
                .build();

        return entity;
    }

    public static LikeDTO toDTO(LikeEntity entity) {

        LikeDTO dto = LikeDTO.builder()
                .likeId(entity.getLikeId())
                .userId(entity.getUserId())
                .targetId(entity.getTargetId())
                .type(entity.getType())
                .build();

        return dto;
    }
}
