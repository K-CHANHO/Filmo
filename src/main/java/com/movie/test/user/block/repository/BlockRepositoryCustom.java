package com.movie.test.user.block.repository;

import com.movie.test.user.follow.dto.FollowListSearchDTO;
import com.querydsl.core.Tuple;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface BlockRepositoryCustom {

    Slice<Tuple> getBlockList(FollowListSearchDTO searchDTO, Pageable pageable);
}
