package com.movie.test.report.like.repository;

import com.movie.test.report.like.entity.LikeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeRepository extends JpaRepository<LikeEntity, Long>, LikeReporisotyCustrom {

    boolean existsByTargetIdAndUserIdAndType(String targetId, String userId, String type);

    Long countByTargetId(String targetId);

    void deleteByLikeIdAndUserId(long likeId, String userId);

    void deleteByTargetId(String targetId);
}
