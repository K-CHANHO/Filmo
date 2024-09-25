package com.movie.test.report.reply.mapper;

import com.movie.test.report.reply.dto.ReplyUpdateDto;
import com.movie.test.report.reply.entity.ReplyEntity;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-09-25T22:33:01+0900",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.9 (Oracle Corporation)"
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
