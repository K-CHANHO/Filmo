package com.movie.test.follow.service;

import com.movie.test.follow.dto.FollowDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.List;

public interface FollowService {
    FollowDTO registFollowing(FollowDTO followDTO);

    void cancleFollow(String followId);

//    Slice<FollowDTO> getFollowingList(String userId, Pageable pageable);
    Slice<FollowDTO> getFollowingList(String userId);
    List<String> getFollowingIdList(String userId);

    Slice<FollowDTO> getFollowerList(String followTarget);
}
