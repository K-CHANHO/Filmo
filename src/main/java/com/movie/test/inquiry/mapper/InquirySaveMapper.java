package com.movie.test.inquiry.mapper;

import com.movie.test.inquiry.dto.InquirySaveDto;
import com.movie.test.inquiry.entity.InquiryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.nio.charset.StandardCharsets;

@Mapper
public interface InquirySaveMapper {

    InquirySaveMapper INSTANCE = Mappers.getMapper(InquirySaveMapper.class);

    @Mapping(source = "dto.content", target = "content", qualifiedByName = "toByte")
    InquiryEntity toEntity(InquirySaveDto dto);

    @Named("toByte")
    static byte[] toByte(String content) {
        return content.getBytes(StandardCharsets.UTF_8);
    }
}