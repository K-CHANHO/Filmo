package com.movie.test.user.block.dto;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BlockDeleteDto {

    @Schema(description = "차단 id", requiredMode = Schema.RequiredMode.REQUIRED)
    private String blockId;

    @Hidden
    private String userId;

}
