package com.movie.test.report.like.repository;

import com.movie.test.report.like.entity.LikeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeRepository extends JpaRepository<LikeEntity, Long>, LikeReporisotyCustrom {

    LikeEntity findByReportIdAndUserId(String reportId, String userId);

    Long countByReportId(String reportId);
}
