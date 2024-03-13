package com.movie.test.inquiry.service;

import com.movie.test.inquiry.dto.InquiryDTO;
import com.movie.test.inquiry.entity.InquiryEntity;
import com.movie.test.inquiry.repository.InquiryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

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

        StringBuilder stringBuilder = new StringBuilder(String.valueOf(System.currentTimeMillis()));
        stringBuilder.append(UUID.randomUUID().toString());

        inquiry.setInquiryId(stringBuilder.toString());
        inquiryRepository.save(InquiryDTO.toEntity(inquiry));

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("gamsangmoon1@naver.com");
        message.setTo("gamsangmoon1@naver.com");
        message.setSubject("[문의]" + inquiry.getTitle());
        message.setText(inquiry.getUserEmail() + "님으로부터의 문의 : " + inquiry.getContent());
        message.setSentDate(new Date());
        sender.send(message);
    }

    @Override
    public Slice<InquiryDTO> getInquiryList(String userId, String lastInquiryId, Pageable pageable) {

        if (lastInquiryId == null) lastInquiryId = "";
        Slice<InquiryEntity> inquiryList = inquiryRepository.getInquiryList(userId, lastInquiryId, pageable);
        return inquiryList.map(InquiryDTO::toDTO);
    }
}
