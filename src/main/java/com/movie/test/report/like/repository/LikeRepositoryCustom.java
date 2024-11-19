package com.movie.test.report.like.repository;

import com.movie.test.report.like.dto.LikeDto;
import com.movie.test.report.like.entity.LikeEntity;

import java.util.Optional;

public interface LikeRepositoryCustom {
    Optional<LikeEntity> findByLikeDto(LikeDto likeDto);
    Boolean existByLikeDto(LikeDto likeDto);
}
