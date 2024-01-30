package com.movie.test.report.view.repository;

import com.movie.test.report.view.entity.ViewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ViewRepository extends JpaRepository<ViewEntity, String> {
    Long countByReportId(String reportId);
}
