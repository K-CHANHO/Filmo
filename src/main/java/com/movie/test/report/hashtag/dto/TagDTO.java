package com.movie.test.report.hashtag.dto;

import com.movie.test.common.dto.BaseTimeDTO;
import com.movie.test.report.hashtag.entity.TagEntity;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
//@Hidden
public class TagDTO extends BaseTimeDTO {

    private Long tagId;
    private String content;

    public static TagEntity toEntity(TagDTO dto){
        TagEntity entity = TagEntity.builder()
                .tagId(dto.getTagId())
                .content(dto.getContent())
                .build();

        return entity;
    }

    public static TagDTO toDTO(TagEntity entity) {
        TagDTO dto = TagDTO.builder()
                .tagId(entity.getTagId())
                .content(entity.getContent())
                .createDate(entity.getCreateDate())
                .lastModifiedDate(entity.getLastModifiedDate())
                .build();

        return dto;
    }
}
