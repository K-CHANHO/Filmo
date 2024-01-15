package com.movie.test.hashtag.repository;

import com.movie.test.hashtag.entity.TagInReportEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface TagInReportRepository extends JpaRepository<TagInReportEntity, Long> {

    @Query(value = "select t.tagId" +
            " from mv_tagInReport t" +
            " where t.reportId = :reportId"
            , nativeQuery = true)
    List<Long> findTagIdByReportId(@Param("reportId") String reportId);


    void deleteByReportId(String reportId);
}
