package com.movie.test.report.like.dto;

import com.movie.test.report.like.entity.LikeEntity;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
//@Hidden
public class LikeDTO {

    private Long likeId;
    private String reportId;
    private String userId;

    public static LikeEntity toEntity(LikeDTO dto){

        LikeEntity entity = LikeEntity.builder()
                .reportId(dto.getReportId())
                .userId(dto.getUserId())
                .build();

        return entity;
    }

    public static LikeDTO toDTO(LikeEntity entity) {

        LikeDTO dto = LikeDTO.builder()
                .likeId(entity.getLikeId())
                .reportId(entity.getReportId())
                .userId(entity.getUserId())
                .build();

        return dto;
    }
}
