package com.movie.test.user.block.dto;

import com.movie.test.common.dto.BaseTimeDTO;
import com.movie.test.user.block.entity.BlockEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

//@Hidden
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class BlockDTO extends BaseTimeDTO {

    private String blockId;

    private String userId; // 유저아이디

    private String targetId; // 차단 대상
    private String blockTargetNickname; // 차단 대상 닉네임

    public static BlockDTO toDTO(BlockEntity entity) {
        BlockDTO dto = BlockDTO.builder()
                .blockId(entity.getBlockId())
                .userId(entity.getUserId())
                .targetId(entity.getTargetId())
                .createDate(entity.getCreateDate())
                .lastModifiedDate(entity.getLastModifiedDate())
                .build();

        return dto;
    }

    public static BlockEntity toEntity(BlockDTO dto) {
        BlockEntity entity = BlockEntity.builder()
                .blockId(dto.getBlockId())
                .userId(dto.getUserId())
                .targetId(dto.getTargetId())
                .build();

        return entity;
    }
}
