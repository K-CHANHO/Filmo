package com.movie.test.inquiry.service;

import com.movie.test.inquiry.dto.InquiryDTO;
import com.movie.test.inquiry.entity.InquiryEntity;
import com.movie.test.inquiry.repository.InquiryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class InquiryServiceImpl implements InquiryService {

    private final InquiryRepository inquiryRepository;
    private final JavaMailSender sender;

    @Override
    public InquiryDTO getInquiry(String inquiryId) {
        InquiryEntity inquiryEntity = inquiryRepository.findById(inquiryId).orElseThrow(NullPointerException::new);
        InquiryDTO dto = InquiryDTO.toDTO(inquiryEntity);

        return dto;
    }

    @Override
    public void registInquiry(InquiryDTO inquiry) {

        StringBuilder stringBuilder = new StringBuilder(UUID.randomUUID().toString());
        stringBuilder.append(System.currentTimeMillis());

        inquiry.setInquiryId(stringBuilder.toString());
        inquiryRepository.save(InquiryDTO.toEntity(inquiry));

        SimpleMailMessage message = new SimpleMailMessage();
//        message.setFrom("gamsangmoon1@naver.com");
//        message.setTo(inquiry.getUserEmail());
        message.setFrom(inquiry.getUserEmail());
        message.setTo("gamsangmoon1@naver.com");
        message.setSubject(inquiry.getTitle());
        message.setText(inquiry.getContent());
        message.setSentDate(new Date());
        sender.send(message);
    }
}
