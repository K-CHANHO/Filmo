package com.movie.test.hashtag.dto;

import com.movie.test.hashtag.entity.TagInReportEntity;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Hidden
public class TagInReportDTO {

    private String reportId;
    private Long tagId;

    public static TagInReportEntity toEntity(TagInReportDTO dto){
        TagInReportEntity entity = TagInReportEntity.builder()
                .reportId(dto.getReportId())
                .tagId(dto.getTagId())
                .build();

        return entity;
    }

    public static TagInReportDTO toDTO(TagInReportEntity entity){
        TagInReportDTO dto = TagInReportDTO.builder()
                .tagId(entity.getTagId())
                .reportId(entity.getReportId())
                .build();

        return dto;
    }
}
