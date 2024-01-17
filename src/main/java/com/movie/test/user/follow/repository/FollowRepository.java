package com.movie.test.user.follow.repository;

import com.movie.test.user.follow.entity.FollowEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FollowRepository extends JpaRepository<FollowEntity, String>, FollowRepositoryCustom {
    FollowEntity findByUserIdAndFollowTarget(String userId, String followTarget);


    Long countByUserIdAndType(String userId, String type);

    Long countByFollowTarget(String userId);
}
