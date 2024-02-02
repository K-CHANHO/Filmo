package com.movie.test.report.bookmark.repository;

import com.movie.test.report.bookmark.dto.BookmarkDTO;
import com.movie.test.report.bookmark.entity.BookmarkEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface BookmarkRepositoryCustom {

    Slice<BookmarkEntity> getBookmarkList(BookmarkDTO bookmarkDTO, Pageable pageable);
}
