package com.movie.test.common.dto;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.sql.Timestamp;

@Data
@SuperBuilder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public abstract class BaseTimeDTO {

    @Hidden
    @Schema(description = "생성시간")
    private Timestamp createDate;

    @Hidden
    @Schema(description = "마지막 수정시간")
    private Timestamp lastModifiedDate;

}
