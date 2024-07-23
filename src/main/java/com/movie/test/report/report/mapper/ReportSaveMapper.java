package com.movie.test.report.report.mapper;

import com.movie.test.report.report.dto.ReportDTO;
import com.movie.test.report.report.dto.ReportSaveDto;
import com.movie.test.user.userinfo.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ReportSaveMapper {

    ReportSaveMapper INSTANCE = Mappers.getMapper(ReportSaveMapper.class);

    ReportDTO toReportDto(ReportSaveDto reportSaveDto);
    ReportSaveDto toReportSaveDto(UserDto userDto);

}
