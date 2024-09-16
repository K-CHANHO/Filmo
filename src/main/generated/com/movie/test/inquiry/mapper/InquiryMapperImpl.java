package com.movie.test.inquiry.mapper;

import com.movie.test.inquiry.dto.InquiryDto;
import com.movie.test.inquiry.entity.InquiryEntity;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-09-16T12:19:44+0900",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.9 (Oracle Corporation)"
)
public class InquiryMapperImpl implements InquiryMapper {

    @Override
    public InquiryEntity toEntity(InquiryDto dto) {
        if ( dto == null ) {
            return null;
        }

        InquiryEntity.InquiryEntityBuilder inquiryEntity = InquiryEntity.builder();

        inquiryEntity.content( InquiryMapper.toByte( dto.getContent() ) );
        inquiryEntity.inquiryId( dto.getInquiryId() );
        inquiryEntity.userId( dto.getUserId() );
        inquiryEntity.category( dto.getCategory() );
        inquiryEntity.title( dto.getTitle() );
        inquiryEntity.userEmail( dto.getUserEmail() );
        inquiryEntity.answerYN( dto.getAnswerYN() );

        return inquiryEntity.build();
    }

    @Override
    public InquiryDto toDto(InquiryEntity entity) {
        if ( entity == null ) {
            return null;
        }

        InquiryDto.InquiryDtoBuilder<?, ?> inquiryDto = InquiryDto.builder();

        inquiryDto.content( InquiryMapper.toString( entity.getContent() ) );
        inquiryDto.createDate( entity.getCreateDate() );
        inquiryDto.lastModifiedDate( entity.getLastModifiedDate() );
        inquiryDto.inquiryId( entity.getInquiryId() );
        inquiryDto.userId( entity.getUserId() );
        inquiryDto.category( entity.getCategory() );
        inquiryDto.title( entity.getTitle() );
        inquiryDto.answerYN( entity.getAnswerYN() );
        inquiryDto.userEmail( entity.getUserEmail() );

        return inquiryDto.build();
    }
}
