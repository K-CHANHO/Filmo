package com.movie.test.user.block.dto;

import com.movie.test.common.dto.BaseTimeDTO;
import com.movie.test.user.block.entity.BlockEntity;
import com.movie.test.user.block.mapper.BlockMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class BlockDto extends BaseTimeDTO {

    private String blockId;

    private String userId; // 유저아이디

    private String targetId; // 차단 대상
    private String blockTargetNickname; // 차단 대상 닉네임

    public static BlockDto toDTO(BlockEntity entity) {
        return BlockMapper.INSTANCE.toDto(entity);
    }

    public static BlockEntity toEntity(BlockDto dto) {
        return BlockMapper.INSTANCE.toEntity(dto);
    }
}
