package com.movie.test.user.block.repository;

import com.movie.test.user.block.entity.QBlockEntity;
import com.movie.test.user.follow.dto.FollowListSearchDTO;
import com.movie.test.user.userinfo.entity.QUserEntity;
import com.movie.test.user.userinfo.entity.UserEntity;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.jdt.internal.compiler.impl.ITypeRequestor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
public class BlockRepositoryCustomImpl implements BlockRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    private QBlockEntity block = QBlockEntity.blockEntity;
    private QUserEntity user = QUserEntity.userEntity;

    @Override
    public Slice<UserEntity> getBlockList(FollowListSearchDTO searchDTO, Pageable pageable) {

        List<String> fetch = jpaQueryFactory.select(block.targetId)
                .from(block)
                .where(
                        block.userId.eq(searchDTO.getUserId())
                )
                .orderBy(block.createDate.desc())
                .fetch();

        List<UserEntity> userEntities = jpaQueryFactory.selectFrom(user)
                .where(
                        user.userId.in(fetch)
                )
                .limit(pageable.getPageSize() + 1)
                .fetch();

        boolean hasNext = false;
        if(userEntities.size() > pageable.getPageSize()){
            hasNext = true;
            userEntities.remove(pageable.getPageSize());
        }

        return new SliceImpl<>(userEntities, pageable, hasNext);
    }
}
