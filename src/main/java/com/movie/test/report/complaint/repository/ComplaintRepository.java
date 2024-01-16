package com.movie.test.report.complaint.repository;

import com.movie.test.report.complaint.entity.ComplaintEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComplaintRepository extends JpaRepository<ComplaintEntity, String> {

    long countByReportId(String reportId);

    void deleteByReportId(String reportId);

    boolean existsByUserIdAndReportId(String userId, String reportId);

    ComplaintEntity findByUserIdAndReportId(String userId, String reportId);

    void deleteByUserIdAndReportId(String userId, String reportId);
}
