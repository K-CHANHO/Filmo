package com.movie.test.report.bookmark.repository;

import com.movie.test.report.bookmark.dto.BookmarkListDto;
import com.movie.test.report.bookmark.entity.BookmarkEntity;
import com.movie.test.report.bookmark.entity.QBookmarkEntity;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;

import java.util.List;

@RequiredArgsConstructor
public class BookmarkRepositoryCustomImpl implements BookmarkRepositoryCustom{

    private final JPAQueryFactory jpaQueryFactory;

    private QBookmarkEntity bookmark = QBookmarkEntity.bookmarkEntity;

    @Override
    public Slice<BookmarkEntity> getBookmarkList(BookmarkListDto bookmarkListDto, Pageable pageable) {

        List<BookmarkEntity> bookmarkEntityList = jpaQueryFactory.selectFrom(bookmark)
                .where(
                        bookmark.userId.eq(bookmarkListDto.getUserId()),
                        checkBookmarkId(bookmarkListDto.getBookmarkId())
                )
                .limit(pageable.getPageSize() + 1)
                .orderBy(bookmark.bookmarkId.desc())
                .fetch();

        boolean hasNext = false;
        if(bookmarkEntityList.size() > pageable.getPageSize()){
            bookmarkEntityList.remove(pageable.getPageSize());
            hasNext = true;
        }

        SliceImpl<BookmarkEntity> bookmarkEntities = new SliceImpl<>(bookmarkEntityList, pageable, hasNext);

        return bookmarkEntities;
    }

    private BooleanExpression checkBookmarkId(String bookmarkId){
        return bookmarkId != null ? bookmark.bookmarkId.lt(bookmarkId) : null;
    }
}
