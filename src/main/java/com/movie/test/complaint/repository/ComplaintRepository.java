package com.movie.test.complaint.repository;

import com.movie.test.complaint.entity.ComplaintEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComplaintRepository extends JpaRepository<ComplaintEntity, String> {
}
