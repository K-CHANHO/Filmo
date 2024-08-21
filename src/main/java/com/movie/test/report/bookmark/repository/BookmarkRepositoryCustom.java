package com.movie.test.report.bookmark.repository;

import com.movie.test.report.bookmark.dto.BookmarkListDto;
import com.movie.test.report.bookmark.entity.BookmarkEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface BookmarkRepositoryCustom {

    Slice<BookmarkEntity> getBookmarkList(BookmarkListDto bookmarkDTO, Pageable pageable);
}
