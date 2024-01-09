package com.movie.test.follow.service;

import com.movie.test.follow.dto.FollowDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface FollowService {
    FollowDTO registFollowing(FollowDTO followDTO);

    void cancleFollow(String followId);

    Slice<FollowDTO> getFollowingList(String userId, Pageable pageable);
}
