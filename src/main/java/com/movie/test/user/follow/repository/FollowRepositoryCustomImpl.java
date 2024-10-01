package com.movie.test.user.follow.repository;


import com.movie.test.user.follow.dto.FollowListSearchDTO;
import com.movie.test.user.follow.entity.QFollowEntity;
import com.movie.test.user.userinfo.entity.QUserEntity;
import com.movie.test.user.userinfo.entity.UserEntity;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
public class FollowRepositoryCustomImpl implements FollowRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    private QFollowEntity follow = QFollowEntity.followEntity;
    private QUserEntity user = QUserEntity.userEntity;

    @Override
    public Slice<UserEntity> getFollowingUserInfo(FollowListSearchDTO followListSearchDTO, Pageable pageable) {

        List<UserEntity> followingUserInfo = jpaQueryFactory.select(user)
                .from(follow).leftJoin(user).on(follow.targetId.eq(user.userId))
                .where(
                        follow.userId.eq(followListSearchDTO.getUserId()),
                        follow.targetId.gt(followListSearchDTO.getLastUserId()),
                        nicknameCheck(followListSearchDTO.getKeyword())
                )
                .orderBy(follow.createDate.desc())
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
    public Slice<UserEntity> getFollowerUserInfo(FollowListSearchDTO followListSearchDTO, Pageable pageable) {

        // 팔로워 id 리스트 추출
        // TODO : JOIN이니 여기서 바로 user 정보 리턴해주기 !
        List<UserEntity> followerUserInfo = jpaQueryFactory.select(user)
                .from(follow).leftJoin(user).on(follow.userId.eq(user.userId))
                .where(
                        follow.userId.eq(followListSearchDTO.getUserId()),
                        follow.targetId.gt(followListSearchDTO.getLastUserId()),
                        nicknameCheck(followListSearchDTO.getKeyword())
                )
                .orderBy(follow.createDate.desc())
                .fetch();

//        // id 리스트를 통해 유저정보 추출
//        List<UserEntity> followerUserInfo = jpaQueryFactory.selectFrom(user)
//                .where(user.userId.in(followerUserId))
//                .limit(pageable.getPageSize() + 1)
//                .fetch();

        boolean hasNext = false;
        if(followerUserInfo.size() > pageable.getPageSize()){
            followerUserInfo.remove(pageable.getPageSize());
            hasNext = true;
        }

        Slice<UserEntity> result = new SliceImpl<>(followerUserInfo, pageable, hasNext);

        return result;
    }

    private BooleanExpression nicknameCheck(String keyword) {
        return keyword != null ? user.nickname.contains(keyword) : null;
    }
}
