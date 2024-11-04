package com.movie.test.complaint.dto;

import com.movie.test.common.dto.BaseTimeDTO;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComplaintSaveDto extends BaseTimeDTO {

    @Hidden
    private String userId;

    @Schema(description = "신고할 대상의 id", requiredMode = Schema.RequiredMode.REQUIRED)
    private String targetId;

    @Schema(description = "report: 감상문, reply: 댓글", requiredMode = Schema.RequiredMode.REQUIRED)
    private String type;

    @Schema(description = "신고 내용")
    private String content;

}
