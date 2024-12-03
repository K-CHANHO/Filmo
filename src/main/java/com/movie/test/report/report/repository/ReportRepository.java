package com.movie.test.report.report.repository;

import com.movie.test.report.report.entity.ReportEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportRepository extends JpaRepository<ReportEntity, String>, ReportRepositoryCustom {

    void deleteByUserId(String userId);
}
