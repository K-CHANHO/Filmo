package com.movie.test.inquiry.mapper;

import com.movie.test.inquiry.dto.InquiryDto;
import com.movie.test.inquiry.entity.InquiryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.nio.charset.StandardCharsets;

@Mapper
public interface InquiryMapper {

    InquiryMapper INSTANCE = Mappers.getMapper(InquiryMapper.class);

    @Mapping(source = "dto.content", target = "content", qualifiedByName = "toByte")
    InquiryEntity toEntity(InquiryDto dto);

    @Mapping(source = "entity.content", target = "content", qualifiedByName = "toString")
    InquiryDto toDto(InquiryEntity entity);

    @Named("toByte")
    static byte[] toByte(String content) {
        return content.getBytes(StandardCharsets.UTF_8);
    }

    @Named("toString")
    static String toString(byte[] content) {
        return new String(content, StandardCharsets.UTF_8);
    }
}
