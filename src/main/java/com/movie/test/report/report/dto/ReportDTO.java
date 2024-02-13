package com.movie.test.report.report.dto;

import com.movie.test.common.dto.BaseTimeDTO;
import com.movie.test.report.report.entity.ReportEntity;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

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

    private String movieId;

    private String tagString; // 태그 내용

    private long complaintCount; // 신고 횟수

    private Long replyCount; // 댓글 수

    private Long likeCount; // 좋아요 수
    private boolean isLike; // 좋아요 유무 상택

    private Long viewCount; // 조회수

    private String imageUrl; // 이미지 url

    public static ReportDTO toDTO(ReportEntity entity) {
        ReportDTO dto = ReportDTO.builder()
                .userId(entity.getUserId())
                .reportId(entity.getReportId())
                .title(entity.getTitle())
                .content(entity.getContent())
                .movieId(entity.getMovieId())
                .createDate(entity.getCreateDate())
                .lastModifiedDate(entity.getLastModifiedDate())
                .imageUrl(entity.getImageUrl())
                .build();

        return dto;
    }

    public static ReportEntity toEntity(ReportDTO dto) {
        ReportEntity entity = ReportEntity.builder()
                .userId(dto.getUserId())
                .reportId(dto.getReportId())
                .title(dto.getTitle())
                .content(dto.getContent())
                .movieId(dto.getMovieId())
                .imageUrl(dto.getImageUrl())
                .build();

        return entity;
    }
}
