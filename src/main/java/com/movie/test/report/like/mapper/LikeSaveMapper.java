package com.movie.test.report.like.mapper;

import com.movie.test.report.like.dto.LikeDto;
import com.movie.test.report.like.dto.LikeSaveDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface LikeSaveMapper {

    LikeSaveMapper INSTANCE = Mappers.getMapper(LikeSaveMapper.class);

    LikeDto toLikeDto(LikeSaveDto likeSaveDto);
}
