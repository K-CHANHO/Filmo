package com.movie.test.user.follow.service;

import com.movie.test.user.follow.dto.FollowDTO;
import com.movie.test.user.follow.dto.FollowListSearchDTO;
import com.movie.test.user.userinfo.dto.UserDto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface FollowService {
    FollowDTO registFollowing(FollowDTO followDTO);

    void cancleFollow(String followId);

    Slice<UserDto> getFollowingUserInfo(FollowListSearchDTO followListSearchDTO, Pageable pageable);

    Slice<UserDto> getFollowerUserInfo(FollowListSearchDTO followListSearchDTO, Pageable pageable);

    boolean isFollowing(String userId, String followTarget);

    Long countFollowing(String userId);

    Long countFollower(String userId);

    Long countBlock(String userId);
}
