package com.movie.test.follow.repository;

import com.movie.test.follow.entity.QFollowEntity;
import com.movie.test.user.entity.QUserEntity;
import com.movie.test.user.entity.UserEntity;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
public class FollowRepositoryCustomImpl implements FollowRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    private QFollowEntity follow = QFollowEntity.followEntity;
    private QUserEntity user = QUserEntity.userEntity;

    @Override
    public Slice<UserEntity> getFollowingUserInfo(String userId, String lastUserId, String keyword, Pageable pageable) {

        // 팔로잉 id 리스트 추출
        List<String> followingUserId = jpaQueryFactory.select(follow.followTarget)
                .from(follow, user).leftJoin(user).on(follow.followTarget.eq(user.userId))
                .where(
                        follow.userId.eq(userId),
                        follow.followTarget.gt(lastUserId),
                        keywordCheck(keyword)
                )
                .orderBy(follow.createDate.desc())
                .fetch();

        // id 리스트를 통해 유저정보 추출
        List<UserEntity> followingUserInfo = jpaQueryFactory.selectFrom(user)
                .where(user.userId.in(followingUserId))
                .limit(pageable.getPageSize() + 1)
                .fetch();

        boolean hasNext = false;
        if(followingUserInfo.size() > pageable.getPageSize()){
            followingUserInfo.remove(pageable.getPageSize());
            hasNext = true;
        }

        Slice<UserEntity> result = new SliceImpl<>(followingUserInfo, pageable, hasNext);

        return result;
    }

    @Override
    public Slice<UserEntity> getFollowerUserInfo(String followTarget, String lastUserId, String keyword, Pageable pageable) {

        // 팔로워 id 리스트 추출
        List<String> followerUserId = jpaQueryFactory.select(follow.followTarget)
                .from(follow).leftJoin(user).on(follow.userId.eq(user.userId))
                .where(
                        follow.followTarget.eq(followTarget),
                        follow.userId.gt(lastUserId),
                        keywordCheck(keyword)
                )
                .orderBy(follow.createDate.desc())
                .fetch();

        // id 리스트를 통해 유저정보 추출
        List<UserEntity> followerUserInfo = jpaQueryFactory.selectFrom(user)
                .where(user.userId.in(followerUserId))
                .limit(pageable.getPageSize() + 1)
                .fetch();

        boolean hasNext = false;
        if(followerUserInfo.size() > pageable.getPageSize()){
            followerUserInfo.remove(pageable.getPageSize());
            hasNext = true;
        }

        Slice<UserEntity> result = new SliceImpl<>(followerUserInfo, pageable, hasNext);

        JPAQuery<String> where = jpaQueryFactory.select(follow.followTarget)
                .from(follow)
                .where(
                        follow.followTarget.eq(followTarget),
                        follow.userId.gt(lastUserId)
                );

        return result;
    }

    private BooleanExpression keywordCheck(String keyword) {
        return keyword != null ? user.nickname.contains(keyword) : null;
    }
}
