package com.movie.test.report.report.mapper;

import com.movie.test.report.report.dto.ReportDto;
import com.movie.test.report.report.entity.ReportEntity;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-08-28T15:58:36+0900",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.3.jar, environment: Java 17.0.7 (Oracle Corporation)"
)
public class ReportMapperImpl implements ReportMapper {

    @Override
    public ReportEntity toEntity(ReportDto reportDTO) {
        if ( reportDTO == null ) {
            return null;
        }

        ReportEntity.ReportEntityBuilder reportEntity = ReportEntity.builder();

        reportEntity.reportId( reportDTO.getReportId() );
        reportEntity.title( reportDTO.getTitle() );
        reportEntity.content( reportDTO.getContent() );
        reportEntity.userId( reportDTO.getUserId() );
        reportEntity.movieId( reportDTO.getMovieId() );
        reportEntity.imageUrl( reportDTO.getImageUrl() );

        return reportEntity.build();
    }

    @Override
    public ReportDto toDto(ReportEntity reportEntity) {
        if ( reportEntity == null ) {
            return null;
        }

        ReportDto.ReportDtoBuilder<?, ?> reportDto = ReportDto.builder();

        reportDto.createDate( reportEntity.getCreateDate() );
        reportDto.lastModifiedDate( reportEntity.getLastModifiedDate() );
        reportDto.reportId( reportEntity.getReportId() );
        reportDto.title( reportEntity.getTitle() );
        reportDto.content( reportEntity.getContent() );
        reportDto.userId( reportEntity.getUserId() );
        reportDto.movieId( reportEntity.getMovieId() );
        reportDto.imageUrl( reportEntity.getImageUrl() );

        return reportDto.build();
    }
}
