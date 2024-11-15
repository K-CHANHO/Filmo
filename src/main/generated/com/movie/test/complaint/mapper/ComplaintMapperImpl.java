package com.movie.test.complaint.mapper;

import com.movie.test.complaint.dto.ComplaintDto;
import com.movie.test.complaint.entity.ComplaintEntity;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-15T17:27:09+0900",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.3.jar, environment: Java 17.0.7 (Oracle Corporation)"
)
public class ComplaintMapperImpl implements ComplaintMapper {

    @Override
    public ComplaintEntity toEntity(ComplaintDto dto) {
        if ( dto == null ) {
            return null;
        }

        ComplaintEntity.ComplaintEntityBuilder complaintEntity = ComplaintEntity.builder();

        complaintEntity.complaintId( dto.getComplaintId() );
        complaintEntity.userId( dto.getUserId() );
        complaintEntity.type( dto.getType() );
        complaintEntity.targetId( dto.getTargetId() );
        complaintEntity.content( dto.getContent() );

        return complaintEntity.build();
    }

    @Override
    public ComplaintDto toDto(ComplaintEntity entity) {
        if ( entity == null ) {
            return null;
        }

        ComplaintDto.ComplaintDtoBuilder<?, ?> complaintDto = ComplaintDto.builder();

        complaintDto.createDate( entity.getCreateDate() );
        complaintDto.lastModifiedDate( entity.getLastModifiedDate() );
        complaintDto.complaintId( entity.getComplaintId() );
        complaintDto.userId( entity.getUserId() );
        complaintDto.targetId( entity.getTargetId() );
        complaintDto.type( entity.getType() );
        complaintDto.content( entity.getContent() );

        return complaintDto.build();
    }
}
