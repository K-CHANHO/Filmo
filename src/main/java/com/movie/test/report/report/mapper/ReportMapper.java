package com.movie.test.report.report.mapper;

import com.movie.test.report.report.dto.ReportDto;
import com.movie.test.report.report.entity.ReportEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ReportMapper {

    ReportMapper INSTANCE = Mappers.getMapper(ReportMapper.class);

    ReportEntity toEntity(ReportDto reportDTO);
    ReportDto toDto(ReportEntity reportEntity);

}
