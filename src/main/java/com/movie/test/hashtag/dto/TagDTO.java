package com.movie.test.hashtag.dto;

import com.movie.test.common.dto.BaseTimeDTO;
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
}
