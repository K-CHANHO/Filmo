package com.movie.test.hashtag.repository;

import com.movie.test.hashtag.entity.TagInReportEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagInReportRepository extends JpaRepository<TagInReportEntity, Long> {

}
