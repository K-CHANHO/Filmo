package com.movie.test.complaint.repository;

import com.movie.test.complaint.entity.ComplaintEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComplaintRepository extends JpaRepository<ComplaintEntity, String> {

    long countByTargetId(String targetId);

    void deleteByTargetId(String targetId);

    boolean existsByUserIdAndTargetId(String userId, String targetId);

    ComplaintEntity findByUserIdAndTargetId(String userId, String targetId);

    void deleteByUserIdAndTargetId(String userId, String targetId);
}
