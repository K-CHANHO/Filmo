package com.movie.test.report.complaint.dto;

import com.movie.test.common.dto.BaseTimeDTO;
import com.movie.test.report.complaint.entity.ComplaintEntity;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
@EqualsAndHashCode(callSuper=false)
@Hidden
@NoArgsConstructor
@AllArgsConstructor
public class ComplaintDTO extends BaseTimeDTO {

    private String complaintId; // 신고 id
    private String userId; // 신고한 유저
    private String reportId; // 신고당한 감상문
    private String content; // 신고내용

    public static ComplaintDTO toDTO(ComplaintEntity entity) {

        ComplaintDTO dto = ComplaintDTO.builder()
                .complaintId(entity.getComplaintId())
                .userId(entity.getUserId())
                .reportId(entity.getReportId())
                .content(entity.getContent())
                .createDate(entity.getCreateDate())
                .lastModifiedDate(entity.getLastModifiedDate())
                .build();

        return dto;
    }

    public static ComplaintEntity toEntity(ComplaintDTO dto) {

        ComplaintEntity entity = ComplaintEntity.builder()
                .complaintId(dto.getComplaintId())
                .userId(dto.getUserId())
                .reportId(dto.getReportId())
                .content(dto.getContent())
                .build();

        return entity;
    }
}
