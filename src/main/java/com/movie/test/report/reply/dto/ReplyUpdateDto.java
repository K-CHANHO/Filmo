package com.movie.test.report.reply.dto;

import com.movie.test.common.dto.BaseTimeDTO;
import com.movie.test.report.reply.entity.ReplyEntity;
import com.movie.test.report.reply.mapper.ReplyUpdateMapper;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ReplyUpdateDto extends BaseTimeDTO {

    @Schema(description = "댓글 id")
    private String replyId; // 댓글 고유 id

    @NotBlank
    @Schema(description = "수정할 댓글내용")
    private String content; // 댓글내용

    public static ReplyEntity toEntity(ReplyUpdateDto dto){
        return ReplyUpdateMapper.INSTANCE.toEntity(dto);
    }
}
