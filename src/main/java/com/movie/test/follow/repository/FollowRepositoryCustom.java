package com.movie.test.follow.repository;


import com.movie.test.user.entity.UserEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface FollowRepositoryCustom {

    Slice<UserEntity> getFollowingUserInfo(String userId, String lastUserId, Pageable pageable);

    Slice<UserEntity> getFollowerUserInfo(String followTarget, String lastUserId, Pageable pageable);
}
