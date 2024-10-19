package com.movie.test.user.block.mapper;

import com.movie.test.user.block.dto.BlockSaveDto;
import com.movie.test.user.block.entity.BlockEntity;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
)
public class BlockSaveMapperImpl implements BlockSaveMapper {

    @Override
    public BlockEntity toEntity(BlockSaveDto blockSaveDto) {
        if ( blockSaveDto == null ) {
            return null;
        }

        BlockEntity.BlockEntityBuilder blockEntity = BlockEntity.builder();

        blockEntity.blockId( blockSaveDto.getBlockId() );
        blockEntity.userId( blockSaveDto.getUserId() );
        blockEntity.targetId( blockSaveDto.getTargetId() );

        return blockEntity.build();
    }
}
