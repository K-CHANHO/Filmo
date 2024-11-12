package com.movie.test.user.block.mapper;

import com.movie.test.user.block.dto.BlockDto;
import com.movie.test.user.block.dto.BlockSaveDto;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-12T11:33:42+0900",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.3.jar, environment: Java 17.0.7 (Oracle Corporation)"
)
public class BlockSaveMapperImpl implements BlockSaveMapper {

    @Override
    public BlockDto toMetaDto(BlockSaveDto blockSaveDto) {
        if ( blockSaveDto == null ) {
            return null;
        }

        BlockDto.BlockDtoBuilder<?, ?> blockDto = BlockDto.builder();

        blockDto.userId( blockSaveDto.getUserId() );
        blockDto.targetId( blockSaveDto.getTargetId() );

        return blockDto.build();
    }
}
