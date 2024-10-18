package com.movie.test.user.block.mapper;

import com.movie.test.user.block.dto.BlockSaveDto;
import com.movie.test.user.block.entity.BlockEntity;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-10-18T12:18:27+0900",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.3.jar, environment: Java 17.0.7 (Oracle Corporation)"
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
