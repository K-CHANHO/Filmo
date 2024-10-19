package com.movie.test.report.reply.mapper;

import com.movie.test.report.reply.dto.ReplySaveDto;
import com.movie.test.report.reply.entity.ReplyEntity;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
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
