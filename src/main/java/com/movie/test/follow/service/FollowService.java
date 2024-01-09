package com.movie.test.follow.service;

import com.movie.test.follow.dto.FollowDTO;

public interface FollowService {
    FollowDTO registFollowing(FollowDTO followDTO);

    void cancleFollow(String followId);
}
