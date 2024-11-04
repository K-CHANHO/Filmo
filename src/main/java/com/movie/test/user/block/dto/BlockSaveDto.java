package com.movie.test.user.block.dto;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BlockSaveDto {

    @Hidden
    private String userId;

    @Schema(description = "차단할 대상 아이디", requiredMode = Schema.RequiredMode.REQUIRED)
    private String targetId;

}
