package com.movie.test.complaint.dto;

import com.movie.test.common.dto.BaseTimeDTO;
import com.movie.test.complaint.entity.ComplaintEntity;
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
    private String complaintUser; // 신고한 유저
    private String getComplaintReportId; // 신고당한 감상문
    private String content; // 신고내용

    public static ComplaintDTO toDTO(ComplaintEntity entity) {

        ComplaintDTO dto = ComplaintDTO.builder()
                .complaintId(entity.getComplaintId())
                .complaintUser(entity.getComplaintUser())
                .getComplaintReportId(entity.getGetComplaintReportId())
                .content(entity.getContent())
                .createDate(entity.getCreateDate())
                .lastModifiedDate(entity.getLastModifiedDate())
                .build();

        return dto;
    }

    public static ComplaintEntity toEntity(ComplaintDTO dto) {

        ComplaintEntity entity = ComplaintEntity.builder()
                .complaintId(dto.getComplaintId())
                .complaintUser(dto.getComplaintUser())
                .getComplaintReportId(dto.getGetComplaintReportId())
                .content(dto.getContent())
                .build();

        return entity;
    }
}
