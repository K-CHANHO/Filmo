package com.movie.test.report.like.repository;

import com.movie.test.report.like.dto.LikeDto;
import com.movie.test.report.like.entity.LikeEntity;
import com.movie.test.report.like.entity.QLikeEntity;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
public class LikeRepositoryCustomImpl implements LikeRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    private QLikeEntity like = QLikeEntity.likeEntity;

    @Override
    public Optional<LikeEntity> findByLikeDto(LikeDto likeDto) {
        LikeEntity likeEntity = jpaQueryFactory.selectFrom(like)
                .where(
                        like.targetId.eq(likeDto.getTargetId()),
                        like.type.eq(likeDto.getType()),
                        like.userId.eq(likeDto.getUserId()),
                        like.isDeleted.eq(false)
                )
                .fetchFirst();

        return Optional.ofNullable(likeEntity);
    }

    @Override
    public Boolean existByLikeDto(LikeDto likeDto) {
        return findByLikeDto(likeDto).isPresent();
    }
}
