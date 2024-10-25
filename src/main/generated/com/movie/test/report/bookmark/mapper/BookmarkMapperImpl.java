package com.movie.test.report.bookmark.mapper;

import com.movie.test.report.bookmark.dto.BookmarkDto;
import com.movie.test.report.bookmark.entity.BookmarkEntity;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-10-25T11:15:54+0900",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.3.jar, environment: Java 17.0.7 (Oracle Corporation)"
)
public class BookmarkMapperImpl implements BookmarkMapper {

    @Override
    public BookmarkEntity toEntity(BookmarkDto dto) {
        if ( dto == null ) {
            return null;
        }

        BookmarkEntity.BookmarkEntityBuilder bookmarkEntity = BookmarkEntity.builder();

        bookmarkEntity.bookmarkId( dto.getBookmarkId() );
        bookmarkEntity.userId( dto.getUserId() );
        bookmarkEntity.reportId( dto.getReportId() );

        return bookmarkEntity.build();
    }

    @Override
    public BookmarkDto toDto(BookmarkEntity entity) {
        if ( entity == null ) {
            return null;
        }

        BookmarkDto.BookmarkDtoBuilder bookmarkDto = BookmarkDto.builder();

        bookmarkDto.bookmarkId( entity.getBookmarkId() );
        bookmarkDto.userId( entity.getUserId() );
        bookmarkDto.reportId( entity.getReportId() );

        return bookmarkDto.build();
    }
}
