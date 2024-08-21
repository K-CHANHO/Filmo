package com.movie.test.report.bookmark.service;

import com.movie.test.report.bookmark.dto.BookmarkDto;
import com.movie.test.report.bookmark.dto.BookmarkListDto;
import com.movie.test.report.bookmark.dto.BookmarkSaveDto;
import com.movie.test.report.bookmark.entity.BookmarkEntity;
import com.movie.test.report.bookmark.mapper.BookmarkSaveMapper;
import com.movie.test.report.bookmark.repository.BookmarkRepository;
import com.movie.test.user.userinfo.dto.CustomUser;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookmarkServiceImpl implements BookmarkService{

    private final BookmarkRepository bookmarkRepository;

    @Override
    public BookmarkDto saveBookmark(BookmarkSaveDto bookmarkSaveDto) {

        CustomUser principal = (CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userId = principal.getUserId();
        bookmarkSaveDto.setUserId(userId);

        boolean isExistBookmark = bookmarkRepository.existsByUserIdAndReportId(userId, bookmarkSaveDto.getReportId());
        if(isExistBookmark){
            throw new RuntimeException("이미 북마크된 감상문입니다");
        }

        BookmarkDto bookmarkDto = BookmarkSaveMapper.INSTANCE.toBookmark(bookmarkSaveDto);
        BookmarkEntity savedBookmark = bookmarkRepository.save(BookmarkDto.toEntity(bookmarkDto));

        return BookmarkDto.toDTO(savedBookmark);
    }

    @Override
    public void deleteBookmark(Long bookmarkId) {
        bookmarkRepository.deleteById(bookmarkId);
    }

    @Override
    public Slice<BookmarkDto> getBookmarkList(BookmarkListDto bookmarkListDto, Pageable pageable) {

        Slice<BookmarkEntity> bookmarkEntityList = bookmarkRepository.getBookmarkList(bookmarkListDto, pageable);
        Slice<BookmarkDto> bookmarkList = bookmarkEntityList.map(BookmarkDto::toDTO);

        return bookmarkList;
    }

    @Override
    public boolean validationBookmarkId(Long bookmarkId) {
        if(bookmarkId == null || bookmarkRepository.findById(bookmarkId).isEmpty()) return false;
        return true;
    }

    @Override
    public Long getBookmarkCount(String reportId) {
        Long count = bookmarkRepository.countByReportId(reportId);
        return count;
    }
}
