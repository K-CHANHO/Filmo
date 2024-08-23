package com.movie.test.inquiry.service;

import com.movie.test.common.cef.CustomUUID;
import com.movie.test.inquiry.dto.InquiryDto;
import com.movie.test.inquiry.dto.InquirySaveDto;
import com.movie.test.inquiry.entity.InquiryEntity;
import com.movie.test.inquiry.mapper.InquirySaveMapper;
import com.movie.test.inquiry.repository.InquiryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class InquiryServiceImpl implements InquiryService {

    private final InquiryRepository inquiryRepository;
    private final JavaMailSender sender;

    @Override
    public InquiryDto getInquiry(String inquiryId) {
        InquiryEntity inquiryEntity = inquiryRepository.findById(inquiryId).orElseThrow(NullPointerException::new);
        InquiryDto dto = InquiryDto.toDTO(inquiryEntity);
        return dto;
    }

    @Override
    public void registInquiry(InquirySaveDto inquiry) {

        inquiry.setInquiryId(CustomUUID.createUUID());
        inquiryRepository.save(InquirySaveMapper.INSTANCE.toEntity(inquiry));

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("gamsangmoon1@naver.com");
        message.setTo("gamsangmoon1@naver.com");
        message.setSubject("[문의]" + inquiry.getTitle());
        message.setText(inquiry.getUserEmail() + "님으로부터의 문의 : " + inquiry.getContent());
        message.setSentDate(new Date());
        sender.send(message);
    }

    @Override
    public Slice<InquiryDto> getInquiryList(String userId, String lastInquiryId, Pageable pageable) {

        if (lastInquiryId == null) lastInquiryId = "";
        Slice<InquiryEntity> inquiryList = inquiryRepository.getInquiryList(userId, lastInquiryId, pageable);
        return inquiryList.map(InquiryDto::toDTO);
    }
}
