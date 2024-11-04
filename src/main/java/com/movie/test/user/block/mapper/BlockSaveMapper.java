package com.movie.test.user.block.mapper;

import com.movie.test.user.block.dto.BlockDto;
import com.movie.test.user.block.dto.BlockSaveDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BlockSaveMapper {

    BlockSaveMapper INSTANCE = Mappers.getMapper(BlockSaveMapper.class);

    BlockDto toMetaDto(BlockSaveDto blockSaveDto);

}
