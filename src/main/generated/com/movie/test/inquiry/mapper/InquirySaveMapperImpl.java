package com.movie.test.inquiry.mapper;

import com.movie.test.inquiry.dto.InquirySaveDto;
import com.movie.test.inquiry.entity.InquiryEntity;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-10-14T15:06:45+0900",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.3.jar, environment: Java 17.0.7 (Oracle Corporation)"
)
public class InquirySaveMapperImpl implements InquirySaveMapper {

    @Override
    public InquiryEntity toEntity(InquirySaveDto dto) {
        if ( dto == null ) {
            return null;
        }

        InquiryEntity.InquiryEntityBuilder inquiryEntity = InquiryEntity.builder();

        inquiryEntity.content( InquirySaveMapper.toByte( dto.getContent() ) );
        inquiryEntity.inquiryId( dto.getInquiryId() );
        inquiryEntity.userId( dto.getUserId() );
        inquiryEntity.category( dto.getCategory() );
        inquiryEntity.title( dto.getTitle() );
        inquiryEntity.userEmail( dto.getUserEmail() );

        return inquiryEntity.build();
    }
}
