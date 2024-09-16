package com.movie.test.report.reply.mapper;

import com.movie.test.report.reply.dto.ReplySaveDto;
import com.movie.test.report.reply.entity.ReplyEntity;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-09-16T12:19:44+0900",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.9 (Oracle Corporation)"
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
