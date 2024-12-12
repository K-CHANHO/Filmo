package com.movie.test.report.report.repository;

import com.movie.test.complaint.entity.QComplaintEntity;
import com.movie.test.report.report.dto.ReportSearchDTO;
import com.movie.test.report.report.entity.QReportEntity;
import com.movie.test.user.block.entity.QBlockEntity;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;

import java.util.List;
import java.util.function.Supplier;

@RequiredArgsConstructor
@Slf4j
public class ReportRepositoryCustomImpl implements ReportRepositoryCustom{

    private final JPAQueryFactory jpaQueryFactory;

    private QReportEntity report = QReportEntity.reportEntity;
    private QBlockEntity block = QBlockEntity.blockEntity;
    private QComplaintEntity complaint = QComplaintEntity.complaintEntity;

    @Override
    public Slice<String> getReportListId(ReportSearchDTO reportSearchDTO, Pageable pageable) {

        // 차단한 유저의 ID 구하기
        List<String> blockUserId = jpaQueryFactory.select(block.targetId)
                .from(block)
                .where(
                        block.userId.eq(reportSearchDTO.getUserId())
                )
                .fetch();

        // 신고한 게시물 ID 구하기
        List<String> complaintReportId = jpaQueryFactory.select(complaint.targetId)
                .from(complaint)
                .where(
                        complaint.userId.eq(reportSearchDTO.getUserId()),
                        complaint.type.eq("report")
                )
                .fetch();

        List<String> reportIdList = jpaQueryFactory.select(report.reportId)
                .from(report)
                .where(
                        //report.reportId.lt(reportSearchDTO.getLastReportId()),
                        searchCondition(reportSearchDTO),
                        report.userId.notIn(blockUserId),
                        report.reportId.notIn(complaintReportId)
                )
                .orderBy(report.createDate.desc())
                .limit(pageable.getPageSize() + 1)
                .fetch();

        boolean hasNext = false;
        if(reportIdList.size() > pageable.getPageSize()){
            reportIdList.remove(pageable.getPageSize());
            hasNext = true;
        }

        Slice<String> reportId = new SliceImpl<>(reportIdList, pageable, hasNext);

        return reportId;
    }

    @Override
    public Long getReportSearchCount(ReportSearchDTO reportSearchDTO) {

        Long reportCount = jpaQueryFactory.select(report.count())
                .from(report)
                .where(
                        searchCondition(reportSearchDTO)
                )
                .fetchFirst();

        return reportCount;
    }

    @Override
    public List<String> getReportIdByUserId(String userId) {
        List<String> reportIdList = jpaQueryFactory.select(report.reportId)
                .from(report)
                .where(report.userId.eq(userId))
                .limit(5)
                .orderBy(report.createDate.desc())
                .fetch();

        return reportIdList;
    }

    public BooleanBuilder searchCondition(ReportSearchDTO searchDTO) {
        return keywordTitle(searchDTO.getKeyword()).or(keywordContent(searchDTO.getKeyword())).or(targetUserId(searchDTO.getTargetId())).or(lastReportId(searchDTO.getLastReportId()));
    }

    private BooleanBuilder keywordTitle(String keyword) {
        return nullSafeBooleanBuilder(() -> report.title.contains(keyword));
    }

    private BooleanBuilder keywordContent(String keyword) {
        return nullSafeBooleanBuilder(() -> report.content.contains(keyword));
    }

    private BooleanBuilder targetUserId(String targetUserId){
        return nullSafeBooleanBuilder(() -> report.userId.eq(targetUserId));
    }

    private BooleanBuilder lastReportId(String lastReportId){
        return nullSafeBooleanBuilder(() -> report.reportId.lt(lastReportId));
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
