package com.movie.test.report.report.repository;

import com.movie.test.report.report.dto.ReportListSearchDTO;
import com.movie.test.report.report.entity.QReportEntity;
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

    @Override
    public Slice<String> getReportListId(ReportListSearchDTO reportListSearchDTO, Pageable pageable) {

        List<String> reportIdList = jpaQueryFactory.select(report.reportId)
                .from(report)
                .where(
                        report.reportId.gt(reportListSearchDTO.getLastReportId()),
                        searchCondition(reportListSearchDTO)
                )
                .limit(pageable.getPageSize() + 1)
                .orderBy(report.createDate.desc())
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
    public Long getReportSearchCount(ReportListSearchDTO reportListSearchDTO) {

        Long reportCount = jpaQueryFactory.select(report.count())
                .from(report)
                .where(
                        searchCondition(reportListSearchDTO)
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

    public BooleanBuilder searchCondition(ReportListSearchDTO searchDTO) {
        return keywordTitle(searchDTO.getKeyword()).or(keywordContent(searchDTO.getKeyword())).or(otherUserId(searchDTO.getOtherUserId()));
    }

    private BooleanBuilder keywordTitle(String keyword) {
        return nullSafeBooleanBuilder(() -> report.title.contains(keyword));
    }

    private BooleanBuilder keywordContent(String keyword) {
        return nullSafeBooleanBuilder(() -> report.content.contains(keyword));
    }

    private BooleanBuilder otherUserId(String otherUserId){
        return nullSafeBooleanBuilder(() -> report.userId.eq(otherUserId));
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
