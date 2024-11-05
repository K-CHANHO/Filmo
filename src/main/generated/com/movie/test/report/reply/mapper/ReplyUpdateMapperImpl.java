package com.movie.test.report.reply.mapper;

import com.movie.test.report.reply.dto.ReplyUpdateDto;
import com.movie.test.report.reply.entity.ReplyEntity;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-05T10:39:20+0900",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.3.jar, environment: Java 17.0.7 (Oracle Corporation)"
)
public class ReplyUpdateMapperImpl implements ReplyUpdateMapper {

    @Override
    public ReplyEntity toEntity(ReplyUpdateDto dto) {
        if ( dto == null ) {
            return null;
        }

        ReplyEntity.ReplyEntityBuilder replyEntity = ReplyEntity.builder();

        replyEntity.replyId( dto.getReplyId() );
        replyEntity.content( dto.getContent() );

        return replyEntity.build();
    }
}
