package com.movie.test.report.like.mapper;

import com.movie.test.report.like.dto.LikeDto;
import com.movie.test.report.like.dto.LikeSaveDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface LikeSaveMapper {

    LikeSaveMapper INSTANCE = Mappers.getMapper(LikeSaveMapper.class);

    LikeDto toLikeDto(LikeSaveDto likeSaveDto);
}
