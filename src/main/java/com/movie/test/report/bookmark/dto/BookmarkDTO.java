package com.movie.test.report.bookmark.dto;

import com.movie.test.report.bookmark.entity.BookmarkEntity;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookmarkDTO {

    @Hidden
    private Long bookmarkId;
    @Hidden
    private String userId;
    private String reportId;

    public static BookmarkEntity toEntity(BookmarkDTO dto) {

        BookmarkEntity entity = BookmarkEntity.builder()
                .userId(dto.getUserId())
                .reportId(dto.getReportId())
                .build();

        return entity;
    }

    public static BookmarkDTO toDTO(BookmarkEntity entity){

        BookmarkDTO dto = BookmarkDTO.builder()
                .bookmarkId(entity.getBookmarkId())
                .userId(entity.getUserId())
                .reportId(entity.getReportId())
                .build();

        return dto;
    }
}
