package com.movie.test.report.like.mapper;

import com.movie.test.report.like.dto.LikeDto;
import com.movie.test.report.like.entity.LikeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface LikeMapper {

    LikeMapper INSTANCE = Mappers.getMapper(LikeMapper.class);

    LikeEntity toEntity(LikeDto dto);
    LikeDto toDto(LikeEntity entity);
}
