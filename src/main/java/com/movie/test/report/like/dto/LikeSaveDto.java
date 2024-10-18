package com.movie.test.report.like.dto;

import com.movie.test.report.like.mapper.LikeSaveMapper;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LikeSaveDto {

    @Schema(description = "좋아요할 대상 아이디", requiredMode = Schema.RequiredMode.REQUIRED)
    private String targetId;

    @Schema(description = "좋아요 타입 / report 또는 reply", requiredMode = Schema.RequiredMode.REQUIRED)
    private String type;

    public static LikeDto toLikeDto(LikeSaveDto dto){
        return LikeSaveMapper.INSTANCE.toLikeDto(dto);
    }

}
