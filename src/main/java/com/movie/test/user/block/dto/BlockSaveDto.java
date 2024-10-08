package com.movie.test.user.block.dto;

import com.movie.test.user.block.entity.BlockEntity;
import com.movie.test.user.block.mapper.BlockSaveMapper;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BlockSaveDto {

    @Hidden
    private String blockId;

    @Hidden
    @Schema(description = "유저아이디")
    private String userId;

    @Schema(description = "대상 유저아이디", requiredMode = Schema.RequiredMode.REQUIRED)
    private String targetId;

    public static BlockEntity toEntity(BlockSaveDto dto){
        return BlockSaveMapper.INSTANCE.toEntity(dto);
    }
}
