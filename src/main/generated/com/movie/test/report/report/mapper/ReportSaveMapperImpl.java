package com.movie.test.report.report.mapper;

import com.movie.test.report.report.dto.ReportDto;
import com.movie.test.report.report.dto.ReportSaveDto;
import com.movie.test.user.userinfo.dto.UserDto;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-08-06T15:12:57+0900",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.3.jar, environment: Java 17.0.7 (Oracle Corporation)"
)
public class ReportSaveMapperImpl implements ReportSaveMapper {

    @Override
    public ReportDto toReportDto(ReportSaveDto reportSaveDto) {
        if ( reportSaveDto == null ) {
            return null;
        }

        ReportDto.ReportDtoBuilder<?, ?> reportDto = ReportDto.builder();

        reportDto.title( reportSaveDto.getTitle() );
        reportDto.content( reportSaveDto.getContent() );
        reportDto.movieId( reportSaveDto.getMovieId() );
        reportDto.imageUrl( reportSaveDto.getImageUrl() );
        reportDto.tagString( reportSaveDto.getTagString() );

        return reportDto.build();
    }

    @Override
    public ReportSaveDto toReportSaveDto(UserDto userDto) {
        if ( userDto == null ) {
            return null;
        }

        ReportSaveDto.ReportSaveDtoBuilder reportSaveDto = ReportSaveDto.builder();

        return reportSaveDto.build();
    }
}
