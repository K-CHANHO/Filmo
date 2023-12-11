package com.movie.test.report.repository;

import com.movie.test.report.entity.ReportEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReportRepository extends JpaRepository<ReportEntity, String> {

    List<ReportEntity> findByTitleContainingAndContentContainingOrderByCreateDateDesc(String keyword, String keyword2);
}
