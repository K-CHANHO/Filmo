package com.movie.test.report.repository;

import com.movie.test.report.entity.reportEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface reportRepository extends JpaRepository<reportEntity, String> {
}
