package com.movie.test.user.follow.repository;

import com.movie.test.user.follow.entity.FollowEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FollowRepository extends JpaRepository<FollowEntity, String>, FollowRepositoryCustom {
    FollowEntity findByUserIdAndTargetId(String userId, String targetId);

    Long countByUserId(String userId);

    Long countByTargetId(String TargetId);

    Boolean existsByUserIdAndTargetId(String userId, String targetId);

    void deleteByUserId(String userId);
}
