package com.movie.test.inquiry.service;

import com.movie.test.inquiry.dto.inquiryDTO;
import com.movie.test.inquiry.entity.inquiryEntity;
import com.movie.test.inquiry.repository.inquiryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class inquiryServiceImpl implements inquiryService {

    @Autowired
    private inquiryRepository inquiryRepository;

    @Override
    public inquiryDTO getInquiry(String inquiryId) {
        inquiryEntity inquiryEntity = inquiryRepository.findById(inquiryId).get();
        inquiryDTO dto = inquiryDTO.toDTO(inquiryEntity);

        return dto;
    }

    @Override
    public void registInquiry(inquiryDTO inquiry) {
        inquiryRepository.save(inquiryDTO.toEntity(inquiry));

    }
}
