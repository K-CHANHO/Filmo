package com.movie.test.user.block.mapper;

import com.movie.test.user.block.dto.BlockSaveDto;
import com.movie.test.user.block.entity.BlockEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BlockSaveMapper {

    BlockSaveMapper INSTANCE = Mappers.getMapper(BlockSaveMapper.class);

    BlockEntity toEntity(BlockSaveDto blockSaveDto);

}
