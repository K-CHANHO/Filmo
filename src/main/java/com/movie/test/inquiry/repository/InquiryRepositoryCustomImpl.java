package com.movie.test.inquiry.repository;

import com.movie.test.inquiry.entity.InquiryEntity;
import com.movie.test.inquiry.entity.QInquiryEntity;
import com.movie.test.user.userinfo.entity.UserEntity;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
public class InquiryRepositoryCustomImpl implements InquiryRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    private QInquiryEntity inquiry = QInquiryEntity.inquiryEntity;

    @Override
    public Slice<InquiryEntity> getInquiryList(String userId, String lastInquiryId, Pageable pageable) {

        List<InquiryEntity> fetch = jpaQueryFactory.selectFrom(inquiry)
                .where(
                        inquiry.inquiryId.gt(lastInquiryId),
                        inquiry.userId.eq(userId)
                )
                .limit(pageable.getPageSize() + 1)
                .fetch();

        boolean hasNext = false;
        if(fetch.size() > pageable.getPageSize()){
            fetch.remove(pageable.getPageSize());
            hasNext = true;
        }

        Slice<InquiryEntity> result = new SliceImpl<>(fetch, pageable, hasNext);

        return result;
    }
}
