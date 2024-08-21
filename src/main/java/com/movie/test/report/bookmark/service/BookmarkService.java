package com.movie.test.report.bookmark.service;

import com.movie.test.report.bookmark.dto.BookmarkDto;
import com.movie.test.report.bookmark.dto.BookmarkListDto;
import com.movie.test.report.bookmark.dto.BookmarkSaveDto;
import com.movie.test.user.userinfo.dto.CustomUser;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface BookmarkService {

    BookmarkDto saveBookmark(BookmarkSaveDto bookmarkSaveDto);

    void deleteBookmark(Long bookmarkId);

    Slice<BookmarkDto> getBookmarkList(BookmarkListDto bookmarkListDto, Pageable pageable);

    boolean validationBookmarkId(Long bookmarkId, CustomUser loginUser);

    Long getBookmarkCount(String reportId);

}
