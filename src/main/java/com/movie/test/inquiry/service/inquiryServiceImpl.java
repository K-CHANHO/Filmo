package com.movie.test.inquiry.service;

import com.movie.test.inquiry.dto.inquiryDTO;
import com.movie.test.inquiry.entity.inquiryEntity;
import com.movie.test.inquiry.repository.inquiryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class inquiryServiceImpl implements inquiryService {

    @Autowired
    private inquiryRepository inquiryRepository;

    @Autowired
    private JavaMailSender sender;

    @Override
    public inquiryDTO getInquiry(String inquiryId) {
        inquiryEntity inquiryEntity = inquiryRepository.findById(inquiryId).get();
        inquiryDTO dto = inquiryDTO.toDTO(inquiryEntity);

        return dto;
    }

    @Override
    public void registInquiry(inquiryDTO inquiry) {
        inquiry.setInquiryId(UUID.randomUUID().toString());
        inquiryRepository.save(inquiryDTO.toEntity(inquiry));

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("gamsangmoon1@naver.com");
        message.setTo(inquiry.getUserEmail());
        message.setSubject(inquiry.getTitle());
        message.setText(inquiry.getContent());
        message.setSentDate(new Date());
        sender.send(message);
    }
}
