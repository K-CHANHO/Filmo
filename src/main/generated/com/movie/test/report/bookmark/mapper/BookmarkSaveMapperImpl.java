package com.movie.test.report.bookmark.mapper;

import com.movie.test.report.bookmark.dto.BookmarkDto;
import com.movie.test.report.bookmark.dto.BookmarkSaveDto;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-09-16T12:19:44+0900",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.9 (Oracle Corporation)"
)
public class BookmarkSaveMapperImpl implements BookmarkSaveMapper {

    @Override
    public BookmarkDto toBookmark(BookmarkSaveDto saveDto) {
        if ( saveDto == null ) {
            return null;
        }

        BookmarkDto.BookmarkDtoBuilder bookmarkDto = BookmarkDto.builder();

        bookmarkDto.userId( saveDto.getUserId() );
        bookmarkDto.reportId( saveDto.getReportId() );

        return bookmarkDto.build();
    }
}
