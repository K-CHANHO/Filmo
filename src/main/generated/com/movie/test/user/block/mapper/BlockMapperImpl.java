package com.movie.test.user.block.mapper;

import com.movie.test.user.block.dto.BlockDto;
import com.movie.test.user.block.entity.BlockEntity;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
)
public class BlockMapperImpl implements BlockMapper {

    @Override
    public BlockEntity toEntity(BlockDto blockDTO) {
        if ( blockDTO == null ) {
            return null;
        }

        BlockEntity.BlockEntityBuilder blockEntity = BlockEntity.builder();

        blockEntity.blockId( blockDTO.getBlockId() );
        blockEntity.userId( blockDTO.getUserId() );
        blockEntity.targetId( blockDTO.getTargetId() );

        return blockEntity.build();
    }

    @Override
    public BlockDto toDto(BlockEntity blockEntity) {
        if ( blockEntity == null ) {
            return null;
        }

        BlockDto.BlockDtoBuilder<?, ?> blockDto = BlockDto.builder();

        blockDto.createDate( blockEntity.getCreateDate() );
        blockDto.lastModifiedDate( blockEntity.getLastModifiedDate() );
        blockDto.blockId( blockEntity.getBlockId() );
        blockDto.userId( blockEntity.getUserId() );
        blockDto.targetId( blockEntity.getTargetId() );

        return blockDto.build();
    }
}
