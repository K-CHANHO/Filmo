package com.movie.test.report.bookmark.repository;

import com.movie.test.report.bookmark.entity.BookmarkEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookmarkRepository extends JpaRepository<BookmarkEntity, String>, BookmarkRepositoryCustom {
    Long countByReportId(String reportId);

    boolean existsByUserIdAndReportId(String userId, String reportId);

    void deleteByUserId(String userId);
}
