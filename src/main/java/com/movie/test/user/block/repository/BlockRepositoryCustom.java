package com.movie.test.user.block.repository;

import com.movie.test.user.follow.dto.FollowListSearchDTO;
import com.movie.test.user.userinfo.entity.UserEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface BlockRepositoryCustom {

    Slice<UserEntity> getBlockList(FollowListSearchDTO searchDTO, Pageable pageable);
}
