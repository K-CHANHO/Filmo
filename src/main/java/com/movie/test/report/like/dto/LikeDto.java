package com.movie.test.report.like.dto;

import com.movie.test.report.like.entity.LikeEntity;
import com.movie.test.report.like.mapper.LikeMapper;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Hidden
public class LikeDto {

    @Schema(description = "좋아요 아이디")
    private String likeId;

    @Schema(description = "유저아이디")
    private String userId;

    @Schema(description = "좋아요할 대상의 아이디")
    private String targetId;

    @Schema(description = "좋아요 타입 / report 또는 reply")
    private String type;

    public static LikeEntity toEntity(LikeDto dto){
        return LikeMapper.INSTANCE.toEntity(dto);
    }

    public static LikeDto toDTO(LikeEntity entity) {
        return LikeMapper.INSTANCE.toDto(entity);
    }
}
