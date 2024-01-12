package com.movie.test.follow.repository;

import com.movie.test.follow.entity.FollowEntity;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FollowRepository extends JpaRepository<FollowEntity, String>, FollowRepositoryCustom {
    FollowEntity findByUserIdAndFollowTarget(String userId, String followTarget);

    boolean existsByUserIdAndFollowTarget(String userId, String followTarget);
}
