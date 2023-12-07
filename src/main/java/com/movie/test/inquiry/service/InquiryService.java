package com.movie.test.inquiry.service;

import com.movie.test.inquiry.dto.InquiryDTO;

public interface InquiryService {

    InquiryDTO getInquiry(String inquiryId);

    void registInquiry(InquiryDTO inquiryDTO);
}
