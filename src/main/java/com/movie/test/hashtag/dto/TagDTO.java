package com.movie.test.hashtag.dto;

import com.movie.test.common.dto.BaseTimeDTO;
import com.movie.test.hashtag.entity.TagEntity;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Hidden
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
