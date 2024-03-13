package com.movie.test.inquiry.repository;

import com.movie.test.inquiry.entity.InquiryEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface InquiryRepositoryCustom {

    Slice<InquiryEntity> getInquiryList(String userId, String lastInquiryId, Pageable pageable);
}
