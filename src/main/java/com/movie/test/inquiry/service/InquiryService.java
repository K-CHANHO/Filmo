package com.movie.test.inquiry.service;

import com.movie.test.inquiry.dto.InquiryDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface InquiryService {

    InquiryDTO getInquiry(String inquiryId);

    void registInquiry(InquiryDTO inquiryDTO);

    Slice<InquiryDTO> getInquiryList(String userId, String lastInquiryId, Pageable pageable);
}
