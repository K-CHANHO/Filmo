package com.movie.test.user.follow.repository;


import com.movie.test.user.follow.dto.FollowListSearchDTO;
import com.movie.test.user.userinfo.entity.UserEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface FollowRepositoryCustom {

    Slice<UserEntity> getFollowingUserInfo(FollowListSearchDTO followListSearchDTO, Pageable pageable);

    Slice<UserEntity> getFollowerUserInfo(FollowListSearchDTO followListSearchDTO, Pageable pageable);
}
