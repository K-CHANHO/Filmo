package com.movie.test.report.bookmark.dto;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Hidden
public class BookmarkListDto {

    @Schema(description = "북마크 아이디")
    Long bookmarkId;

    String userId;

}
