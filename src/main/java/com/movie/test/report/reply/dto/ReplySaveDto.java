package com.movie.test.report.reply.dto;

import com.movie.test.common.dto.BaseTimeDTO;
import com.movie.test.report.reply.entity.ReplyEntity;
import com.movie.test.report.reply.mapper.ReplySaveMapper;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class ReplySaveDto extends BaseTimeDTO {

    @Hidden
    private String replyId; // 댓글 고유 id

    @Schema(description = "대댓글인 경우 원댓글의 id, 아닌 경우 빈 값")
    private String upReplyId; // 대댓글인 경우 원댓글 id

    @NotBlank
    @Schema(description = "감상문 id")
    private String reportId; // 감상문 id

    @NotBlank
    @Schema(description = "댓글내용")
    private String content; // 댓글내용

    @Hidden
    private String userId; // 댓글 작성자 id

    public static ReplyEntity toEntity(ReplySaveDto dto){
        return ReplySaveMapper.INSTANCE.toEntity(dto);
    }

}
