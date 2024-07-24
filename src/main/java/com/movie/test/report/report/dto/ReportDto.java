package com.movie.test.report.report.dto;

import com.movie.test.common.dto.BaseTimeDTO;
import com.movie.test.report.report.entity.ReportEntity;
import com.movie.test.report.report.mapper.ReportMapper;
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
public class ReportDto extends BaseTimeDTO {

    private String reportId;
    private String title;
    private String content;
    private String userId;
    private String movieId;
    private String imageUrl; // 이미지 url

    private String tagString; // 태그 내용
    private long complaintCount; // 신고 횟수
    private Long replyCount; // 댓글 수
    private Long likeCount; // 좋아요 수
//    private boolean isLike; // 좋아요 유무 상태
    private Long viewCount; // 조회수


    public static ReportDto toDto(ReportEntity entity) {
        return ReportMapper.INSTANCE.toDto(entity);
    }

    public static ReportEntity toEntity(ReportDto dto) {
        return ReportMapper.INSTANCE.toEntity(dto);
    }
}
