package com.movie.test.inquiry.service;

import com.movie.test.inquiry.dto.inquiryDTO;

public interface inquiryService {

    inquiryDTO getInquiry(String inquiryId);

    void registInquiry(inquiryDTO inquiryDTO);
}
