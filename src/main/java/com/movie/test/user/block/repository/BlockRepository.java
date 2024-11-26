package com.movie.test.user.block.repository;

import com.movie.test.user.block.entity.BlockEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlockRepository extends JpaRepository<BlockEntity, String>, BlockRepositoryCustom {
    BlockEntity findByUserIdAndTargetId(String userId, String targetId);

    boolean existsByUserIdAndTargetId(String userId, String targetId);

    Long countByUserId(String userId);

    void deleteByBlockIdAndUserId(String blockId, String userId);

    void deleteByUserId(String userId);
}
