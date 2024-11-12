package com.movie.test.report.reply.repository;

import com.movie.test.report.reply.entity.QReplyEntity;
import com.movie.test.report.reply.entity.ReplyEntity;
import com.movie.test.user.block.entity.QBlockEntity;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPQLQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.function.Supplier;

@RequiredArgsConstructor
@Slf4j
public class ReplyRepositoryCustomImpl implements ReplyRepositoryCustom{

    private final JPQLQueryFactory jpqlQueryFactory;
    private QReplyEntity reply = QReplyEntity.replyEntity;
    private QBlockEntity block = QBlockEntity.blockEntity;

    @Override
    public List<ReplyEntity> findFirstDepthReply(String reportId, String userId) {

        // 차단한 유저ID 구하기
        List<String> blockUser = jpqlQueryFactory.select(block.targetId)
                .from(block)
                .where(
                        checkUserId(userId)
                )
                .fetch();

        List<ReplyEntity> fetch = jpqlQueryFactory.selectFrom(reply)
                .where(
                        reply.reportId.eq(reportId),
                        reply.upReplyId.isNull(),
                        reply.userId.notIn(blockUser)
                )
                .orderBy(reply.createDate.desc())
                .fetch();

        return fetch;
    }

    private BooleanBuilder checkUserId(String userId) {
        return nullSafeBooleanBuilder(() -> block.userId.eq(userId));
    }

    private BooleanBuilder nullSafeBooleanBuilder(Supplier<BooleanExpression> supplier) {
        try {
            return new BooleanBuilder(supplier.get());
        } catch (IllegalArgumentException e) {
            return new BooleanBuilder();
        } catch (NullPointerException e) {
            return new BooleanBuilder();
        }
    }
}
