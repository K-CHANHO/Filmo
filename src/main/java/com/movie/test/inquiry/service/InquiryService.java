package com.movie.test.inquiry.service;

import com.movie.test.inquiry.dto.InquiryDto;
import com.movie.test.inquiry.dto.InquirySaveDto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface InquiryService {

    InquiryDto getInquiry(String inquiryId);

    void registInquiry(InquirySaveDto inquiryDTO);

    Slice<InquiryDto> getInquiryList(String userId, String lastInquiryId, Pageable pageable);
}
