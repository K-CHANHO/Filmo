package com.movie.test.report.reply.mapper;

import com.movie.test.report.reply.dto.ReplySaveDto;
import com.movie.test.report.reply.entity.ReplyEntity;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-12T11:43:42+0900",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.3.jar, environment: Java 17.0.7 (Oracle Corporation)"
)
public class ReplySaveMapperImpl implements ReplySaveMapper {

    @Override
    public ReplyEntity toEntity(ReplySaveDto dto) {
        if ( dto == null ) {
            return null;
        }

        ReplyEntity.ReplyEntityBuilder replyEntity = ReplyEntity.builder();

        replyEntity.replyId( dto.getReplyId() );
        replyEntity.upReplyId( dto.getUpReplyId() );
        replyEntity.reportId( dto.getReportId() );
        replyEntity.userId( dto.getUserId() );
        replyEntity.content( dto.getContent() );

        return replyEntity.build();
    }
}
