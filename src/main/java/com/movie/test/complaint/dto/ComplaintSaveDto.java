package com.movie.test.complaint.dto;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComplaintSaveDto {

    @Hidden
    private String userId;

    @Schema(description = "신고할 대상의 id")
    private String targetId;

    @Schema(description = "report: 감상문, reply: 댓글")
    private String type;
}
