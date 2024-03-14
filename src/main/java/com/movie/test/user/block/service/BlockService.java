package com.movie.test.user.block.service;

import com.movie.test.user.block.dto.BlockDTO;
import com.movie.test.user.follow.dto.FollowListSearchDTO;
import com.movie.test.user.userinfo.dto.UserDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface BlockService {
    BlockDTO registBlock(BlockDTO blockDTO);

    void cancleBlock(String blockId);

    Slice<UserDTO> getBlockList(FollowListSearchDTO blockListSearchDTO, Pageable pageable);

    boolean isBlocking(String userId, String targetId);

    Long countBlock(String userId);
}
