package com.movie.test.complaint.dto;

import com.movie.test.common.dto.BaseTimeDTO;
import com.movie.test.complaint.entity.ComplaintEntity;
import com.movie.test.complaint.mapper.ComplaintMapper;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Hidden
@SuperBuilder
@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@AllArgsConstructor
public class ComplaintDto extends BaseTimeDTO {

    private String complaintId; // 신고 id
    private String userId; // 신고한 유저
    private String targetId; // 대상 id
    private String type; // 감상문 또는 댓글
    private String content; // 신고내용

    public static ComplaintDto toDTO(ComplaintEntity entity) {
        return ComplaintMapper.INSTANCE.toDto(entity);
    }

    public static ComplaintEntity toEntity(ComplaintDto dto) {
        return ComplaintMapper.INSTANCE.toEntity(dto);
    }
}
