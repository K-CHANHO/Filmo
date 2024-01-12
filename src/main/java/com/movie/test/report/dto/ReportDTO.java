package com.movie.test.report.dto;

import com.movie.test.common.dto.BaseTimeDTO;
import com.movie.test.report.entity.ReportEntity;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.nio.charset.StandardCharsets;

@Hidden
@SuperBuilder
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class ReportDTO extends BaseTimeDTO {

    private String reportId;
    private String title;
    private String content;
    private String userId;

    private long complaintCount; // 신고 횟수

    private String tagString; // 태그 내용

    public static ReportDTO toDTO(ReportEntity entity) {
        ReportDTO dto = ReportDTO.builder()
                .reportId(entity.getReportId())
                .title(entity.getTitle())
                .content(new String(entity.getContent(), StandardCharsets.UTF_8))
                .userId(entity.getUserId())
                .createDate(entity.getCreateDate())
                .lastModifiedDate(entity.getLastModifiedDate())
                .build();

        return dto;
    }

    public static ReportEntity toEntity(ReportDTO dto) {
        ReportEntity entity = ReportEntity.builder()
                .reportId(dto.getReportId())
                .title(dto.getTitle())
                .content(dto.getContent().getBytes(StandardCharsets.UTF_8))
                .userId(dto.getUserId())
                .build();

        return entity;
    }
}
