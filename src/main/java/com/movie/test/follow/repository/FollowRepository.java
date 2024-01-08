package com.movie.test.follow.repository;

import com.movie.test.follow.entity.FollowEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FollowRepository extends JpaRepository<FollowEntity, String> {
    FollowEntity findByUserIdAndFollowTarget(String userId, String followTarget);
}
